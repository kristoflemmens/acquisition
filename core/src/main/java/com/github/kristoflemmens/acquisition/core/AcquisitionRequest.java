package com.github.kristoflemmens.acquisition.core;

import com.github.kristoflemmens.acquisition.common.Entity;
import com.github.kristoflemmens.acquisition.core.acquisitiondate.AcquisitionDateDetermined;
import com.github.kristoflemmens.acquisition.core.acquisitiondate.AcquisitionDates;
import com.github.kristoflemmens.acquisition.core.requestDocument.RequestDocument;
import com.github.kristoflemmens.acquisition.core.validation.actorduplication.ActorDuplicationValidated;
import com.github.kristoflemmens.acquisition.core.validation.actorduplication.ActorDuplicationValidator;
import com.github.kristoflemmens.acquisition.core.validation.cumulpayment.CumulPaymentValidated;
import com.github.kristoflemmens.acquisition.core.validation.cumulpayment.CumulPaymentValidator;
import com.github.kristoflemmens.acquisition.core.validation.data.DataValidated;
import com.github.kristoflemmens.acquisition.core.validation.data.DataValidator;
import com.github.kristoflemmens.acquisition.core.validation.itineraexistence.ItineraExistenceValidated;
import com.github.kristoflemmens.acquisition.core.validation.itineraexistence.ItineraExistenceValidator;
import com.github.kristoflemmens.acquisition.core.validation.legalinformation.minimal.MinimalLegalInformationValidated;
import com.github.kristoflemmens.acquisition.core.validation.legalinformation.minimal.MinimalLegalInformationValidator;
import com.github.kristoflemmens.acquisition.eventsourcing.Event;
import com.github.kristoflemmens.acquisition.eventsourcing.EventSourced;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.Date;
import java.util.List;

import static com.github.kristoflemmens.acquisition.core.AcquisitionRequest.Builder.newAcquisitionRequest;
import static com.github.kristoflemmens.acquisition.eventsourcing.EventUtils.versionOfLastEvent;
import static com.google.common.collect.Lists.newArrayList;

public class AcquisitionRequest extends Entity<AcquisitionRequestNumber> implements EventSourced {

    private final AcquisitionRequestNumber id;
    private final int version;
    private final List<AcquisitionRequestEvent> unsavedEvents;
    private final RequestDocument requestDocument;
    private final Date acquisitionDate;

    private AcquisitionRequest(
            AcquisitionRequestNumber id,
            int version,
            List<AcquisitionRequestEvent> unsavedEvents,
            RequestDocument requestDocument,
            Date acquisitionDate
    ) {
        this.id = id;
        this.version = version;
        this.unsavedEvents = unsavedEvents;
        this.requestDocument = requestDocument;
        this.acquisitionDate = acquisitionDate;
    }

    @Override
    public AcquisitionRequestNumber id() {
        return id;
    }

    @Override
    public int version() {
        return version;
    }

    @Override
    public List<Event> unsavedEvents() {
        return ImmutableList.<Event>copyOf(unsavedEvents);
    }

    public AcquisitionRequest validateData(DataValidator dataValidator) {
        return apply(new DataValidated(id, version + 1, dataValidator.validate(requestDocument.data())));
    }

    public AcquisitionRequest determineAcquisitionDate(AcquisitionDates acquisitionDates) {
        return apply(new AcquisitionDateDetermined(id, version + 1, acquisitionDates.dateFor(requestDocument.organismCode(), requestDocument.companyId())));
    }

    public AcquisitionRequest validateCumulPaymentForChildren(CumulPaymentValidator cumulPaymentValidator) {
        return apply(cumulPaymentValidations(cumulPaymentValidator));
    }

    private List<Event> cumulPaymentValidations(CumulPaymentValidator cumulPaymentValidator) {
        int version = this.version;
        List<Event> result = newArrayList();
        for (String childInss : requestDocument.inssOfChildren()) {
            result.add(new CumulPaymentValidated(id, ++version, cumulPaymentValidator.validate(acquisitionDate, childInss)));
        }
        return result;
    }

    public AcquisitionRequest validateItineraExistence(ItineraExistenceValidator itineraExistenceValidator) {
        return applyItineraExistenceValidated(new ItineraExistenceValidated(id, version + 1, itineraExistenceValidator.validate(requestDocument.fileOwnerInss())));
    }

    public AcquisitionRequest validateMinimalLegalInformationOfActors(MinimalLegalInformationValidator minimalLegalInformationValidator) {
        return apply(minimalLegalInformationValidations(minimalLegalInformationValidator));
    }

    private List<Event> minimalLegalInformationValidations(MinimalLegalInformationValidator minimalLegalInformationValidator) {
        int version = this.version;
        List<Event> result = newArrayList();
        for (String actorInss : requestDocument.inssOfActors()) {
            result.add(new MinimalLegalInformationValidated(id, ++version, minimalLegalInformationValidator.validate(actorInss)));
        }
        return result;
    }

    public AcquisitionRequest validateActorDuplication(ActorDuplicationValidator actorDuplicationValidator) {
        return apply(new ActorDuplicationValidated(id, version + 1, actorDuplicationValidator.validate(requestDocument.actors())));
    }

    AcquisitionRequest markCommitted() {
        return newAcquisitionRequest()
                .withId(id)
                .withVersion(versionOfLastEvent(unsavedEvents()))
                .withUnsavedEvents(Lists.<AcquisitionRequestEvent>newArrayList())
                .withRequestDocument(requestDocument)
                .withAcquisitionDate(acquisitionDate)
                .build();
    }

    private AcquisitionRequest apply(Event event) {
        if (event instanceof DataValidated) {
            return applyDataValidated((DataValidated) event);
        }
        if (event instanceof AcquisitionDateDetermined) {
            return applyAcquisitionDateDetermined((AcquisitionDateDetermined) event);
        }
        if (event instanceof CumulPaymentValidated) {
            return applyCumulPaymentValidated((CumulPaymentValidated) event);
        }
        if (event instanceof MinimalLegalInformationValidated) {
            return applyMinimalLegalInformationValidated((MinimalLegalInformationValidated) event);
        }
        if (event instanceof ActorDuplicationValidated) {
            return applyActorDuplicationValidated((ActorDuplicationValidated) event);
        }
        throw new UnsupportedOperationException("Cannot handle event " + event);
    }

    AcquisitionRequest apply(Iterable<Event> events) {
        AcquisitionRequest entity = this;
        for (Event Event : events) {
            entity = entity.apply(Event);
        }
        return entity;
    }

    private AcquisitionRequest applyCumulPaymentValidated(CumulPaymentValidated event) {
        return newAcquisitionRequest()
                .withId(id)
                .withVersion(version)
                .withUnsavedEvents(unsavedEventsWith(event))
                .withRequestDocument(requestDocument)
                .withAcquisitionDate(acquisitionDate)
                .build();
    }

    private AcquisitionRequest applyDataValidated(DataValidated event) {
        return newAcquisitionRequest()
                .withId(id)
                .withVersion(version)
                .withUnsavedEvents(unsavedEventsWith(event))
                .withRequestDocument(requestDocument)
                .withAcquisitionDate(acquisitionDate)
                .build();
    }

    private AcquisitionRequest applyAcquisitionDateDetermined(AcquisitionDateDetermined event) {
        return newAcquisitionRequest()
                .withId(id)
                .withVersion(version)
                .withUnsavedEvents(unsavedEventsWith(event))
                .withRequestDocument(requestDocument)
                .withAcquisitionDate(event.acquisitionDate())
                .build();
    }

    private AcquisitionRequest applyItineraExistenceValidated(ItineraExistenceValidated event) {
        return newAcquisitionRequest()
                .withId(id)
                .withVersion(version)
                .withUnsavedEvents(unsavedEventsWith(event))
                .withRequestDocument(requestDocument)
                .withAcquisitionDate(acquisitionDate)
                .build();
    }

    private AcquisitionRequest applyMinimalLegalInformationValidated(MinimalLegalInformationValidated event) {
        return newAcquisitionRequest()
                .withId(id)
                .withVersion(version)
                .withUnsavedEvents(unsavedEventsWith(event))
                .withRequestDocument(requestDocument)
                .withAcquisitionDate(acquisitionDate)
                .build();
    }

    private AcquisitionRequest applyActorDuplicationValidated(ActorDuplicationValidated event) {
        return newAcquisitionRequest()
                .withId(id)
                .withVersion(version)
                .withUnsavedEvents(unsavedEventsWith(event))
                .withRequestDocument(requestDocument)
                .withAcquisitionDate(acquisitionDate)
                .build();
    }

    private List<AcquisitionRequestEvent> unsavedEventsWith(AcquisitionRequestEvent event) {
        unsavedEvents.add(event);
        return unsavedEvents;
    }

    static class Builder {
        private RequestDocument requestDocument;
        private Date acquisitionDate;
        private AcquisitionRequestNumber id;
        private int version;
        private List<AcquisitionRequestEvent> unsavedEvents;

        private Builder() {
        }

        public static Builder newAcquisitionRequest() {
            return new Builder();
        }

        public Builder withRequestDocument(RequestDocument requestDocument) {
            this.requestDocument = requestDocument;
            return this;
        }

        public Builder withAcquisitionDate(Date acquisitionDate) {
            this.acquisitionDate = acquisitionDate;
            return this;
        }

        public Builder withId(AcquisitionRequestNumber id) {
            this.id = id;
            return this;
        }

        public Builder withVersion(int version) {
            this.version = version;
            return this;
        }

        public Builder withUnsavedEvents(List<AcquisitionRequestEvent> unsavedEvents) {
            this.unsavedEvents = unsavedEvents;
            return this;
        }

        public Builder withUnsavedEvents(AcquisitionRequestEvent... events) {
            return withUnsavedEvents(newArrayList(events));
        }

        public AcquisitionRequest build() {
            return new AcquisitionRequest(id, version, unsavedEvents, requestDocument, acquisitionDate);
        }
    }
}
