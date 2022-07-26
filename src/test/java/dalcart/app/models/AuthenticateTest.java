package dalcart.app.models;

import dalcart.app.models.Factories.ISecurityFactory;
import dalcart.app.models.Factories.IUserFactory;
import dalcart.app.models.Factories.SecurityFactory;
import dalcart.app.models.Factories.UserFactory;
import dalcart.app.models.SecurityChain.Security;
import dalcart.app.repository.UserPersistenceMock;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@SpringBootTest
public class AuthenticateTest {

    @Test
    public void authenticateSuccessTest() {
        IUserFactory userFactory = new UserFactory();
        IUser user = userFactory.createUser();
        ISecurityFactory securityFactory = new SecurityFactory();
        UserPersistenceMock userPersistenceMock = new UserPersistenceMock();
        user.setEmail("harsh@dal.ca");
        user.setPassword("Test12345");
        IAuthenticate authenticate = securityFactory.createSecurity(userPersistenceMock, user);
        assertEquals(Security.RESULT.AUTHORIZED, authenticate.authenticate(user));
    }

    @Test
    public void authenticateNullUsernameFailureTest() {
        IUserFactory userFactory = new UserFactory();
        IUser user = userFactory.createUser();
        ISecurityFactory securityFactory = new SecurityFactory();
        UserPersistenceMock userPersistenceMock = new UserPersistenceMock();
        user.setEmail(null);
        user.setPassword("Test12345");
        IAuthenticate authenticate = securityFactory.createSecurity(userPersistenceMock, user);
        assertEquals(Security.RESULT.USERNAME_INVALID, authenticate.authenticate(user));
    }

    @Test
    public void authenticateEmptyUsernameFailureTest() {
        IUserFactory userFactory = new UserFactory();
        IUser user = userFactory.createUser();
        ISecurityFactory securityFactory = new SecurityFactory();
        UserPersistenceMock userPersistenceMock = new UserPersistenceMock();
        user.setEmail("");
        user.setPassword("Test12345");
        IAuthenticate authenticate = securityFactory.createSecurity(userPersistenceMock, user);
        assertEquals(Security.RESULT.USERNAME_INVALID, authenticate.authenticate(user));
    }

    @Test
    public void authenticateInvalidUsernameFailureTest() {
        IUserFactory userFactory = new UserFactory();
        IUser user = userFactory.createUser();
        ISecurityFactory securityFactory = new SecurityFactory();
        UserPersistenceMock userPersistenceMock = new UserPersistenceMock();
        user.setEmail("test@dal.ca");
        user.setPassword("Test12345");
        IAuthenticate authenticate = securityFactory.createSecurity(userPersistenceMock, user);
        assertEquals(Security.RESULT.USERNAME_INVALID, authenticate.authenticate(user));
    }

    @Test
    public void authenticateInvalidPasswordFailureTest() {
        IUserFactory userFactory = new UserFactory();
        IUser user = userFactory.createUser();
        ISecurityFactory securityFactory = new SecurityFactory();
        UserPersistenceMock userPersistenceMock = new UserPersistenceMock();
        user.setEmail("harsh@dal.ca");
        user.setPassword("Test1234");
        IAuthenticate authenticate = securityFactory.createSecurity(userPersistenceMock, user);
        assertEquals(Security.RESULT.IS_NOT_AUTHORIZED, authenticate.authenticate(user));
    }

    @Test
    public void authenticateNullPasswordFailureTest() {
        IUserFactory userFactory = new UserFactory();
        IUser user = userFactory.createUser();
        ISecurityFactory securityFactory = new SecurityFactory();
        UserPersistenceMock userPersistenceMock = new UserPersistenceMock();
        user.setEmail("harsh@dal.ca");
        user.setPassword(null);
        IAuthenticate authenticate = securityFactory.createSecurity(userPersistenceMock, user);
        assertEquals(Security.RESULT.PASSWORD_INVALID, authenticate.authenticate(user));
    }

    @Test
    public void authenticateEmptyPasswordFailureTest() {
        IUserFactory userFactory = new UserFactory();
        IUser user = userFactory.createUser();
        ISecurityFactory securityFactory = new SecurityFactory();
        UserPersistenceMock userPersistenceMock = new UserPersistenceMock();
        user.setEmail("harsh@dal.ca");
        user.setPassword("");
        IAuthenticate authenticate = securityFactory.createSecurity(userPersistenceMock, user);
        assertEquals(Security.RESULT.PASSWORD_INVALID, authenticate.authenticate(user));
    }

    @Test
    public void authenticateInvalidUsernamePasswordFailureTest() {
        IUserFactory userFactory = new UserFactory();
        IUser user = userFactory.createUser();
        ISecurityFactory securityFactory = new SecurityFactory();
        UserPersistenceMock userPersistenceMock = new UserPersistenceMock();
        user.setEmail("Test@dal.ca");
        user.setPassword("harsh");
        IAuthenticate authenticate = securityFactory.createSecurity(userPersistenceMock, user);
        assertNotEquals(Security.RESULT.AUTHORIZED, authenticate.authenticate(user));
    }
}
