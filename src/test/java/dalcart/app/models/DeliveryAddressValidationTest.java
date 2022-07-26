package dalcart.app.models;

import dalcart.app.models.Factories.DeliveryInformationModelFactory;
import dalcart.app.models.Factories.IDeliveryInformationModelFactory;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DeliveryAddressValidationTest {
    DeliveryInformationValidator validator = new DeliveryInformationValidator();
    IDeliveryInformationModelFactory factory = new DeliveryInformationModelFactory();

    @Test
    public void isFullNameValidSuccessTest() {
        DeliveryInformationModel informationModel = factory.createDeliveryInformationModel();
        informationModel.setAddress("Priyal Patel");
        Assertions.assertEquals(true, validator.isAddressValid(informationModel));
    }

    @Test
    public void isFullNameValidFailureTest() {
        DeliveryInformationModel informationModel = factory.createDeliveryInformationModel();
        informationModel.setAddress("");
        Assertions.assertEquals(false, validator.isAddressValid(informationModel));
    }

    @Test
    public void isEmailAddressValidSuccessTest() {
        DeliveryInformationModel informationModel = factory.createDeliveryInformationModel();
        informationModel.setEmail("priyal@dal.ca");
        Assertions.assertEquals(true, validator.isEmailAddressValid(informationModel));
    }

    @Test
    public void isEmailAddressValidFailureTest() {
        DeliveryInformationModel informationModel = factory.createDeliveryInformationModel();
        informationModel.setEmail("priyaldal.ca");
        Assertions.assertEquals(false, validator.isEmailAddressValid(informationModel));
    }

    @Test
    public void isMobileNumberValidSuccessTest() {
        DeliveryInformationModel informationModel = factory.createDeliveryInformationModel();
        informationModel.setMobileNumber("1234567890");
        Assertions.assertEquals(true, validator.isMobileNumberValid(informationModel));
    }

    @Test
    public void isMobileNumberValidFailureTest() {
        DeliveryInformationModel informationModel = factory.createDeliveryInformationModel();
        informationModel.setMobileNumber("123456789");
        Assertions.assertEquals(false, validator.isMobileNumberValid(informationModel));
    }

    @Test
    public void isAddressValidSuccessTest() {
        DeliveryInformationModel informationModel = factory.createDeliveryInformationModel();
        informationModel.setAddress("133333, dsfevgv, fgbfb");
        Assertions.assertEquals(true, validator.isAddressValid(informationModel));
    }

    @Test
    public void isAddressValidFailureTest() {
        DeliveryInformationModel informationModel = factory.createDeliveryInformationModel();
        informationModel.setAddress("");
        Assertions.assertEquals(false, validator.isAddressValid(informationModel));
    }

    @Test
    public void isDeliveryInformationValidSuccessTest() {
        DeliveryInformationModel informationModel = factory.createDeliveryInformationModel();
        informationModel.setName("Priyal Patel");
        informationModel.setAddress("1333, South Park Street, Halifax, NS");
        informationModel.setMobileNumber("1234567890");
        informationModel.setEmail("priyal@dal.ca");
        Assertions.assertEquals(true, validator.validateDeliveryDetails(informationModel));
    }

    @Test
    public void isDeliveryInformationValidFailureTest() {
        DeliveryInformationModel informationModel = factory.createDeliveryInformationModel();
        informationModel.setName("Priyal Patel");
        informationModel.setAddress("1333, South Park Street, Halifax, NS");
        informationModel.setMobileNumber("123456789");
        informationModel.setEmail("priyaldal.ca");
        Assertions.assertEquals(false, validator.validateDeliveryDetails(informationModel));
    }
}


