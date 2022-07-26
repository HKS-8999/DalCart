package dalcart.app.models;

import dalcart.app.models.Factories.IUserFactory;
import dalcart.app.models.Factories.UserFactory;
import dalcart.app.models.Factories.ValidateFactory;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ValidateUserAttributesTest {
    IUserFactory iUserFactory = new UserFactory();
    ValidateFactory validateFactory = new ValidateFactory();

    @Test
    public void isUserNameValidSuccessTest() {
        IValidate validateUserAttributes = validateFactory.createValidations();
        IUser user = iUserFactory.createUser();
        user.setEmail("harshs893@dal.ca");
        assertEquals(true, validateUserAttributes.isUserNameValid(user));
    }

    @Test
    public void nullUserNameValidFailureTest() {
        IValidate validate = validateFactory.createValidations();
        IUser user = iUserFactory.createUser();
        user.setEmail(null);
        assertEquals(false, validate.isUserNameValid(user));
    }

    @Test
    public void emptyUserNameValidFailureTest() {
        IValidate validate = validateFactory.createValidations();
        IUser user = iUserFactory.createUser();
        user.setEmail("");
        assertEquals(false, validate.isUserNameValid(user));
    }

    @Test
    public void isUserNameValidFailureTest() {
        IValidate validate = validateFactory.createValidations();
        IUser user = iUserFactory.createUser();
        user.setEmail("harsh");
        assertEquals(false, validate.isUserNameValid(user));
    }

    @Test
    public void isPasswordValidSuccessTest() {
        IValidate validate = validateFactory.createValidations();
        IUser user = iUserFactory.createUser();
        user.setPassword("Test12345");
        assertEquals(true, validate.isPasswordValid(user));
    }

    @Test
    public void isNullPasswordValidFailureTest() {
        IValidate validate = validateFactory.createValidations();
        IUser user = iUserFactory.createUser();
        user.setPassword(null);
        assertEquals(false, validate.isPasswordValid(user));
    }

    @Test
    public void isEmptyPasswordValidFailureTest() {
        IValidate validate = validateFactory.createValidations();
        IUser user = iUserFactory.createUser();
        user.setPassword("");
        assertEquals(false, validate.isPasswordValid(user));
    }

    @Test
    public void isPasswordValidFailureTest() {
        IValidate validate = validateFactory.createValidations();
        IUser user = iUserFactory.createUser();
        user.setPassword("pass");
        assertEquals(false, validate.isPasswordValid(user));
    }

    @Test
    public void isMobileNumberValidSuccessTest() {
        IValidate validate = validateFactory.createValidations();
        IUser user = iUserFactory.createUser();
        user.setMobileNo("1234567890");
        assertEquals(true, validate.isMobileNumberValid(user));
    }

    @Test
    public void isMobileNumberValidFailureTest() {
        IValidate validate = validateFactory.createValidations();
        IUser user = iUserFactory.createUser();
        user.setMobileNo("1234567");
        assertEquals(false, validate.isMobileNumberValid(user));
    }

    @Test
    public void isFirstNameValidSuccessTest() {
        IValidate validate = validateFactory.createValidations();
        IUser user = iUserFactory.createUser();
        user.setFirstName("Harsh");

        assertEquals(true, validate.isFirstNameValid(user));

    }

    @Test
    public void isEmptyFirstNameValidFailureTest() {
        IValidate validate = validateFactory.createValidations();
        IUser user = iUserFactory.createUser();
        user.setFirstName("");
        assertEquals(false, validate.isFirstNameValid(user));
    }

    @Test
    public void isNullFirstNameValidFailureTest() {
        IValidate validate = validateFactory.createValidations();
        IUser user = iUserFactory.createUser();
        user.setFirstName(null);
        assertEquals(false, validate.isFirstNameValid(user));
    }

    @Test
    public void isFirstNameValidFailureTest() {
        IValidate validate = validateFactory.createValidations();
        IUser user = iUserFactory.createUser();
        user.setFirstName("   ");
        assertEquals(false, validate.isFirstNameValid(user));
    }

    @Test
    public void isLastNameValidSuccessTest() {
        IValidate validate = validateFactory.createValidations();
        IUser user = iUserFactory.createUser();
        user.setLastName("Shah");

        assertEquals(true, validate.isLastNameValid(user));

    }

    @Test
    public void isEmptyLastNameValidFailureTest() {
        IValidate validate = validateFactory.createValidations();
        IUser user = iUserFactory.createUser();
        user.setLastName("");
        assertEquals(false, validate.isLastNameValid(user));
    }

    @Test
    public void isNullLastNameValidFailureTest() {
        IValidate validate = validateFactory.createValidations();
        IUser user = iUserFactory.createUser();
        user.setLastName(null);
        assertEquals(false, validate.isLastNameValid(user));
    }

    @Test
    public void isLastNameValidFailureTest() {
        IValidate validate = validateFactory.createValidations();
        IUser user = iUserFactory.createUser();
        user.setLastName("   ");
        assertEquals(false, validate.isLastNameValid(user));
    }
}
