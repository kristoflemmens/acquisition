package com.github.kristoflemmens.acquisition.core;

import com.github.kristoflemmens.acquisition.core.acquisitiondate.AcquisitionDateDetermined;
import com.github.kristoflemmens.acquisition.core.acquisitiondate.AcquisitionDates;
import com.github.kristoflemmens.acquisition.core.acquisitiondate.DetermineAcquisitionDate;
import com.github.kristoflemmens.acquisition.core.acquisitiondate.DetermineAcquisitionDateHandler;
import com.github.kristoflemmens.acquisition.core.requestDocument.Actor;
import com.github.kristoflemmens.acquisition.core.start.AcquisitionRequestCreated;
import com.github.kristoflemmens.acquisition.core.start.CreateAcquisitionRequest;
import com.github.kristoflemmens.acquisition.core.start.CreateAcquisitionRequestHandler;
import com.github.kristoflemmens.acquisition.core.validation.Validation;
import com.github.kristoflemmens.acquisition.core.validation.actorduplication.ActorDuplicationValidated;
import com.github.kristoflemmens.acquisition.core.validation.actorduplication.ActorDuplicationValidator;
import com.github.kristoflemmens.acquisition.core.validation.actorduplication.ValidateActorDuplication;
import com.github.kristoflemmens.acquisition.core.validation.actorduplication.ValidateActorDuplicationHandler;
import com.github.kristoflemmens.acquisition.core.validation.cumulpayment.CumulPaymentValidated;
import com.github.kristoflemmens.acquisition.core.validation.cumulpayment.CumulPaymentValidator;
import com.github.kristoflemmens.acquisition.core.validation.cumulpayment.ValidateCumulPayment;
import com.github.kristoflemmens.acquisition.core.validation.cumulpayment.ValidateCumulPaymentHandler;
import com.github.kristoflemmens.acquisition.core.validation.data.DataValidated;
import com.github.kristoflemmens.acquisition.core.validation.data.DataValidator;
import com.github.kristoflemmens.acquisition.core.validation.data.ValidateData;
import com.github.kristoflemmens.acquisition.core.validation.data.ValidateDataHandler;
import com.github.kristoflemmens.acquisition.core.validation.itineraexistence.ItineraExistenceValidated;
import com.github.kristoflemmens.acquisition.core.validation.itineraexistence.ItineraExistenceValidator;
import com.github.kristoflemmens.acquisition.core.validation.itineraexistence.ValidateItineraExistence;
import com.github.kristoflemmens.acquisition.core.validation.itineraexistence.ValidateItineraExistenceHandler;
import com.github.kristoflemmens.acquisition.core.validation.legalinformation.minimal.MinimalLegalInformationValidated;
import com.github.kristoflemmens.acquisition.core.validation.legalinformation.minimal.MinimalLegalInformationValidator;
import com.github.kristoflemmens.acquisition.core.validation.legalinformation.minimal.ValidateMinimalLegalInformation;
import com.github.kristoflemmens.acquisition.core.validation.legalinformation.minimal.ValidateMinimalLegalInformationHandler;
import com.github.kristoflemmens.acquisition.eventsourcing.EventStore;
import com.github.kristoflemmens.acquisition.messaging.CommandSender;
import org.fest.assertions.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.Set;

import static com.github.kristoflemmens.acquisition.core.AcquisitionRequest.Builder.newAcquisitionRequest;
import static com.github.kristoflemmens.acquisition.core.InMemoryCommandSender.inMemoryCommandSender;
import static com.github.kristoflemmens.acquisition.core.InMemoryEventStore.inMemoryEventStore;
import static com.google.common.collect.Lists.newArrayList;

public class AcquisitionRequestTest {

    private final CommandSender commandSender = inMemoryCommandSender();
    private final EventStore eventStore = inMemoryEventStore();
    private final AcquisitionRequestCreated acquisitionRequestCreated = new AcquisitionRequestCreated(TestConstants.ACQUISITION_REQUEST_NUMBER, 0, TestConstants.DATA);
    private final DataValidated dataValidated = new DataValidated(TestConstants.ACQUISITION_REQUEST_NUMBER, 1, TestConstants.VALIDATIONS);
    private final AcquisitionDateDetermined acquisitionDateDetermined = new AcquisitionDateDetermined(TestConstants.ACQUISITION_REQUEST_NUMBER, 2, TestConstants.ACQUISITION_DATE);
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
        When(new CreateAcquisitionRequest(TestConstants.ACQUISITION_REQUEST_NUMBER, TestConstants.DATA));
        Then(acquisitionRequestCreated);
    }

    @Test
    public void validateAcquisitionRequestData() throws Exception {
        Given(acquisitionRequestCreated);
        When(new ValidateData(TestConstants.ACQUISITION_REQUEST_NUMBER));
        Then(dataValidated);
    }

    @Test
    public void determineAcquisitionDate() throws Exception {
        Given(acquisitionRequestCreated, dataValidated);
        When(new DetermineAcquisitionDate(TestConstants.ACQUISITION_REQUEST_NUMBER));
        Then(acquisitionDateDetermined);
    }

    @Test
    public void validateCumulPaymentForChildren() throws Exception {
        Given(acquisitionRequestCreated, dataValidated, acquisitionDateDetermined);
        When(new ValidateCumulPayment(TestConstants.ACQUISITION_REQUEST_NUMBER));
        Then(new CumulPaymentValidated(TestConstants.ACQUISITION_REQUEST_NUMBER, 3, TestConstants.VALIDATION), new CumulPaymentValidated(TestConstants.ACQUISITION_REQUEST_NUMBER, 4, TestConstants.VALIDATION));
    }

    @Test
    public void validateItineraExistence() throws Exception {
        Given(acquisitionRequestCreated, dataValidated, acquisitionDateDetermined);
        When(new ValidateItineraExistence(TestConstants.ACQUISITION_REQUEST_NUMBER));
        Then(new ItineraExistenceValidated(TestConstants.ACQUISITION_REQUEST_NUMBER, 3, false));
    }

    @Test
    public void validateMinimalLegalInformationForActors() throws Exception {
        Given(acquisitionRequestCreated, dataValidated, acquisitionDateDetermined);
        When(new ValidateMinimalLegalInformation(TestConstants.ACQUISITION_REQUEST_NUMBER));
        Then(new MinimalLegalInformationValidated(TestConstants.ACQUISITION_REQUEST_NUMBER, 3, TestConstants.VALIDATIONS), new MinimalLegalInformationValidated(TestConstants.ACQUISITION_REQUEST_NUMBER, 4, TestConstants.VALIDATIONS));
    }

    @Test
    public void validateActorDuplication() throws Exception {
        Given(acquisitionRequestCreated, dataValidated, acquisitionDateDetermined);
        When(new ValidateActorDuplication(TestConstants.ACQUISITION_REQUEST_NUMBER));
        Then(new ActorDuplicationValidated(TestConstants.ACQUISITION_REQUEST_NUMBER, 3, TestConstants.VALIDATIONS));
    }

    private DataValidator acquisitionRequestDataValidatorStub() {
        return new DataValidator() {

            @Override
            public Set<Validation> validate(String data) {
                return TestConstants.VALIDATIONS;
            }
        };
    }

    private AcquisitionDates acquisitionDatesStub() {
        return new AcquisitionDates() {

            @Override
            public Date dateFor(String organismCode, String companyId) {
                return TestConstants.ACQUISITION_DATE;
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
                return TestConstants.VALIDATIONS;
            }
        };
    }

    private ActorDuplicationValidator actorDuplicationValidatorStub() {
        return new ActorDuplicationValidator() {
            @Override
            public Set<Validation> validate(List<Actor> actors) {
                return TestConstants.VALIDATIONS;
            }
        };
    }

    private void Given(AcquisitionRequestEvent... history) {
        eventStore.save(newAcquisitionRequest()
                .withId(TestConstants.ACQUISITION_REQUEST_NUMBER)
                .withVersion(-1)
                .withUnsavedEvents(newArrayList(history))
                .build());
    }

    private void When(AcquisitionRequestCommand command) {
        commandSender.send(command);
    }

    private void Then(AcquisitionRequestEvent... Events) {
        Assertions.assertThat(eventStore.eventsFor(TestConstants.ACQUISITION_REQUEST_NUMBER)).containsSequence(Events);
    }
}
