package com.github.kristoflemmens.acquisition.core.acquisitionrequest;

import com.github.kristoflemmens.acquisition.core.acquisitionrequest.acquisitiondate.AcquisitionDateDetermined;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.acquisitiondate.AcquisitionDates;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.acquisitiondate.DetermineAcquisitionDate;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.acquisitiondate.DetermineAcquisitionDateHandler;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.create.AcquisitionRequestCreated;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.create.CreateAcquisitionRequest;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.create.CreateAcquisitionRequestHandler;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.Validation;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.actorduplication.ActorDuplicationValidated;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.actorduplication.ActorDuplicationValidator;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.actorduplication.ValidateActorDuplication;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.actorduplication.ValidateActorDuplicationHandler;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.cumulpayment.CumulPaymentValidated;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.cumulpayment.CumulPaymentValidator;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.cumulpayment.ValidateCumulPayment;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.cumulpayment.ValidateCumulPaymentHandler;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.data.DataValidated;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.data.DataValidator;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.data.ValidateData;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.data.ValidateDataHandler;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.itineraexistence.ItineraExistenceValidated;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.itineraexistence.ItineraExistenceValidator;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.itineraexistence.ValidateItineraExistence;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.itineraexistence.ValidateItineraExistenceHandler;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.legalinformation.minimal.MinimalLegalInformationValidated;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.legalinformation.minimal.MinimalLegalInformationValidator;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.legalinformation.minimal.ValidateMinimalLegalInformation;
import com.github.kristoflemmens.acquisition.core.acquisitionrequest.validate.legalinformation.minimal.ValidateMinimalLegalInformationHandler;
import com.github.kristoflemmens.acquisition.core.file.Actor;
import com.github.kristoflemmens.acquisition.core.testutils.TestConstants;
import com.github.kristoflemmens.acquisition.eventsourcing.EventStore;
import com.github.kristoflemmens.acquisition.messaging.CommandSender;
import org.fest.assertions.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.Set;

import static com.github.kristoflemmens.acquisition.core.acquisitionrequest.AcquisitionRequest.Builder.newAcquisitionRequest;
import static com.github.kristoflemmens.acquisition.core.testutils.InMemoryCommandSender.inMemoryCommandSender;
import static com.github.kristoflemmens.acquisition.core.testutils.InMemoryEventStore.inMemoryEventStore;
import static com.github.kristoflemmens.acquisition.core.testutils.TestConstants.*;
import static com.google.common.collect.Lists.newArrayList;

public class AcquisitionRequestTest {

    private final CommandSender commandSender = inMemoryCommandSender();
    private final EventStore eventStore = inMemoryEventStore();
    private final AcquisitionRequestCreated acquisitionRequestCreated = new AcquisitionRequestCreated(ACQUISITION_REQUEST_NUMBER, 0, DATA);
    private final DataValidated dataValidated = new DataValidated(ACQUISITION_REQUEST_NUMBER, 1, VALIDATIONS);
    private final AcquisitionDateDetermined acquisitionDateDetermined = new AcquisitionDateDetermined(ACQUISITION_REQUEST_NUMBER, 2, ACQUISITION_DATE);
    private final AcquisitionRequestFactory acquisitionRequestFactory = new AcquisitionRequestFactory();

    @Before
    public void setup() throws Exception {
        AcquisitionRequests acquisitionRequests = new AcquisitionRequests(eventStore, acquisitionRequestFactory);
        commandSender.register(new CreateAcquisitionRequestHandler(acquisitionRequests, acquisitionRequestFactory));
        commandSender.register(new ValidateDataHandler(acquisitionRequests, acquisitionRequestDataValidatorStub()));
        commandSender.register(new DetermineAcquisitionDateHandler(acquisitionRequests, acquisitionDatesStub()));
        commandSender.register(new ValidateCumulPaymentHandler(acquisitionRequests, cumulPaymentsValidatorStub()));
        commandSender.register(new ValidateItineraExistenceHandler(acquisitionRequests, itineraExistenceValidatorStub()));
        commandSender.register(new ValidateMinimalLegalInformationHandler(acquisitionRequests, minimalLegalInformationValidatorStub()));
        commandSender.register(new ValidateActorDuplicationHandler(acquisitionRequests, actorDuplicationValidatorStub()));
    }

    @Test
    public void createAcquisitionRequest() throws Exception {
        Given();
        When(new CreateAcquisitionRequest(ACQUISITION_REQUEST_NUMBER, DATA));
        Then(acquisitionRequestCreated);
    }

    @Test
    public void validateAcquisitionRequestData() throws Exception {
        Given(acquisitionRequestCreated);
        When(new ValidateData(ACQUISITION_REQUEST_NUMBER));
        Then(dataValidated);
    }

    @Test
    public void determineAcquisitionDate() throws Exception {
        Given(acquisitionRequestCreated, dataValidated);
        When(new DetermineAcquisitionDate(ACQUISITION_REQUEST_NUMBER));
        Then(acquisitionDateDetermined);
    }

    @Test
    public void validateCumulPaymentForChildren() throws Exception {
        Given(acquisitionRequestCreated, dataValidated, acquisitionDateDetermined);
        When(new ValidateCumulPayment(ACQUISITION_REQUEST_NUMBER));
        Then(new CumulPaymentValidated(ACQUISITION_REQUEST_NUMBER, 3, TestConstants.VALIDATION), new CumulPaymentValidated(ACQUISITION_REQUEST_NUMBER, 4, TestConstants.VALIDATION));
    }

    @Test
    public void validateItineraExistence() throws Exception {
        Given(acquisitionRequestCreated, dataValidated, acquisitionDateDetermined);
        When(new ValidateItineraExistence(ACQUISITION_REQUEST_NUMBER));
        Then(new ItineraExistenceValidated(ACQUISITION_REQUEST_NUMBER, 3, false));
    }

    @Test
    public void validateMinimalLegalInformationForActors() throws Exception {
        Given(acquisitionRequestCreated, dataValidated, acquisitionDateDetermined);
        When(new ValidateMinimalLegalInformation(ACQUISITION_REQUEST_NUMBER));
        Then(new MinimalLegalInformationValidated(ACQUISITION_REQUEST_NUMBER, 3, VALIDATIONS), new MinimalLegalInformationValidated(ACQUISITION_REQUEST_NUMBER, 4, VALIDATIONS));
    }

    @Test
    public void validateActorDuplication() throws Exception {
        Given(acquisitionRequestCreated, dataValidated, acquisitionDateDetermined);
        When(new ValidateActorDuplication(ACQUISITION_REQUEST_NUMBER));
        Then(new ActorDuplicationValidated(ACQUISITION_REQUEST_NUMBER, 3, VALIDATIONS));
    }

    private DataValidator acquisitionRequestDataValidatorStub() {
        return new DataValidator() {

            @Override
            public Set<Validation> validate(String data) {
                return VALIDATIONS;
            }
        };
    }

    private AcquisitionDates acquisitionDatesStub() {
        return new AcquisitionDates() {

            @Override
            public Date dateFor(String organismCode, String companyId) {
                return ACQUISITION_DATE;
            }
        };
    }

    private CumulPaymentValidator cumulPaymentsValidatorStub() {
        return new CumulPaymentValidator() {

            @Override
            public Validation validate(Date acquisitionDate, String childInss) {
                return TestConstants.VALIDATION;
            }
        };
    }

    private ItineraExistenceValidator itineraExistenceValidatorStub() {
        return new ItineraExistenceValidator() {
            @Override
            public boolean validate(String fileOwnerInss) {
                return false;
            }
        };
    }

    private MinimalLegalInformationValidator minimalLegalInformationValidatorStub() {
        return new MinimalLegalInformationValidator() {
            @Override
            public Set<Validation> validate(String actorInss) {
                return VALIDATIONS;
            }
        };
    }

    private ActorDuplicationValidator actorDuplicationValidatorStub() {
        return new ActorDuplicationValidator() {
            @Override
            public Set<Validation> validate(List<Actor> actors) {
                return VALIDATIONS;
            }
        };
    }

    private void Given(AcquisitionRequestEvent... history) {
        eventStore.save(newAcquisitionRequest()
                .withId(ACQUISITION_REQUEST_NUMBER)
                .withVersion(-1)
                .withUnsavedEvents(newArrayList(history))
                .build());
    }

    private void When(AcquisitionRequestCommand command) {
        commandSender.send(command);
    }

    private void Then(AcquisitionRequestEvent... Events) {
        Assertions.assertThat(eventStore.eventsFor(ACQUISITION_REQUEST_NUMBER)).containsSequence(Events);
    }
}
