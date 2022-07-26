package dalcart.app.models;

import dalcart.app.models.Factories.IUserFactory;
import dalcart.app.models.Factories.UserFactory;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class UserTest {
    IUserFactory userFactory = new UserFactory();

    @Test
    public void setUserIdSuccessTest() {
        IUser user = userFactory.createUser();
        user.setUserID(2);
        assertEquals(2, user.getUserID());
    }

    @Test
    public void setUserIdFailureTest() {
        IUser user = userFactory.createUser();
        user.setUserID(2);
        assertNotEquals(3, user.getUserID());
    }

    @Test
    public void getUserIdSuccessTest() {
        IUser user = userFactory.createUser();
        user.setUserID(2);
        user.setUserID(3);
        assertEquals(3, user.getUserID());
    }

    @Test
    public void getUserIdFailureTest() {
        IUser user = userFactory.createUser();
        user.setUserID(3);
        user.setUserID(2);
        assertNotEquals(3, user.getUserID());
    }

    @Test
    public void setEmailSuccessTest() {
        IUser user = userFactory.createUser();
        user.setEmail("test@dal.ca");
        assertEquals("test@dal.ca", user.getEmail());
    }

    @Test
    public void setEmailFailureTest() {
        IUser user = userFactory.createUser();
        user.setEmail("test@dal.ca");
        assertNotEquals("test1@dal.ca", user.getEmail());
    }

    @Test
    public void getEmailValidTest() {
        IUser user = userFactory.createUser();
        user.setEmail("test@dal.ca");
        user.setEmail("test1@dal.ca");
        assertEquals("test1@dal.ca", user.getEmail());
    }

    @Test
    public void getEmailInvalidTest() {
        IUser user = userFactory.createUser();
        user.setEmail("test@dal.ca");
        user.setEmail("test1@dal.ca");
        assertNotEquals("test@dal.ca", user.getUserID());
    }

    @Test
    public void setFirstNameSuccessTest() {
        IUser user = userFactory.createUser();
        user.setFirstName("Harsh");
        assertEquals("Harsh", user.getFirstName());
    }

    @Test
    public void setFirstNameFailureTest() {
        IUser user = userFactory.createUser();
        user.setFirstName("Harsh");
        assertNotEquals("Ram", user.getFirstName());
    }

    @Test
    public void getFirstNameSuccessTest() {
        IUser user = userFactory.createUser();
        user.setFirstName("Harsh");
        user.setFirstName("Ram");
        assertEquals("Ram", user.getFirstName());
    }

    @Test
    public void getFirstNameFailureTest() {
        IUser user = userFactory.createUser();
        user.setFirstName("Harsh");
        user.setFirstName("Ram");
        assertNotEquals("Harsh", user.getFirstName());
    }

    @Test
    public void setLastNameSuccessTest() {
        IUser user = userFactory.createUser();
        user.setLastName("Shah");
        assertEquals("Shah", user.getLastName());
    }

    @Test
    public void setLastNameFailureTest() {
        IUser user = userFactory.createUser();
        user.setLastName("Shah");
        assertNotEquals("Patel", user.getLastName());
    }

    @Test
    public void getLastNameSuccessTest() {
        IUser user = userFactory.createUser();
        user.setLastName("Shah");
        user.setLastName("Patel");
        assertEquals("Patel", user.getLastName());
    }

    @Test
    public void getLastNameFailureTest() {
        IUser user = userFactory.createUser();
        user.setLastName("Shah");
        user.setLastName("Patel");
        assertNotEquals("Shah", user.getLastName());
    }

    @Test
    public void setPasswordSuccessTest() {
        IUser user = userFactory.createUser();
        user.setPassword("Test12345");
        assertEquals("Test12345", user.getPassword());
    }

    @Test
    public void setPasswordFailureTest() {
        IUser user = userFactory.createUser();
        user.setPassword("Test12345");
        assertNotEquals("Test123456", user.getPassword());
    }

    @Test
    public void getPasswordSuccessTest() {
        IUser user = userFactory.createUser();
        user.setPassword("Test12345");
        user.setPassword("Test123456");
        assertEquals("Test123456", user.getPassword());
    }

    @Test
    public void getPasswordFailureTest() {
        IUser user = userFactory.createUser();
        user.setPassword("Test12345");
        user.setPassword("Test123456");
        assertNotEquals("Test12345", user.getPassword());
    }

    @Test
    public void setMobileNoSuccessTest() {
        IUser user = userFactory.createUser();
        user.setMobileNo("7227098999");
        assertEquals("7227098999", user.getMobileNo());
    }

    @Test
    public void setMobileNoFailureTest() {
        IUser user = userFactory.createUser();
        user.setMobileNo("7227098999");
        assertNotEquals("07227098999", user.getMobileNo());
    }

    @Test
    public void getMobileNoSuccessTest() {
        IUser user = userFactory.createUser();
        user.setMobileNo("7227098999");
        user.setMobileNo("07227098999");
        assertEquals("07227098999", user.getMobileNo());
    }

    @Test
    public void getMobileNoFailureTest() {
        IUser user = userFactory.createUser();
        user.setMobileNo("7227098999");
        user.setMobileNo("07227098999");
        assertNotEquals("7227098999", user.getMobileNo());
    }

    @Test
    public void setDesignationSuccessTest() {
        IUser user = userFactory.createUser();
        user.setDesignation("admin");
        assertEquals("admin", user.getDesignation());
    }

    @Test
    public void setDesignationFailureTest() {
        IUser user = userFactory.createUser();
        user.setDesignation("admin");
        assertNotEquals("user", user.getDesignation());
    }

    @Test
    public void getDesignationSuccessTest() {
        IUser user = userFactory.createUser();
        user.setDesignation("admin");
        user.setDesignation("user");
        assertEquals("user", user.getDesignation());
    }

    @Test
    public void getDesignationFailureTest() {
        IUser user = userFactory.createUser();
        user.setDesignation("admin");
        user.setDesignation("user");
        assertNotEquals("admin", user.getDesignation());
    }
}
