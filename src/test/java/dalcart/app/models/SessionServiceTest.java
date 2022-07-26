package dalcart.app.models;

import dalcart.app.models.Repository.IUserPersistence;

import dalcart.app.repository.UserPersistenceMock;
import org.springframework.mock.web.MockHttpSession;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


import javax.servlet.http.HttpSession;
@SpringBootTest
public class SessionServiceTest {

    @Test
    public void isAdminInSessionSuccessTest(){
        HttpSession session = new MockHttpSession();
        IUserPersistence iUserPersistence = new UserPersistenceMock();
        SessionService sessionService = new SessionService(iUserPersistence);
        IUser user = new User();
        user.setUserID(1);
        session.setAttribute("admin", user.getUserID());
        assertEquals(true, sessionService.isAdminInSession(session)) ;
    }
    @Test
    public void isAdminInSessionFailureTest(){
        HttpSession session = new MockHttpSession();
        IUserPersistence iUserPersistence = new UserPersistenceMock();
        SessionService sessionService = new SessionService(iUserPersistence);
        IUser user = new User();
        user.setUserID(1);
        session.setAttribute("user", user.getUserID());
        assertNotEquals(true, sessionService.isAdminInSession(session)); ;
    }

    @Test
    public void isUserInSessionSuccessTest(){
        HttpSession session = new MockHttpSession();
        IUserPersistence iUserPersistence = new UserPersistenceMock();
        SessionService sessionService = new SessionService(iUserPersistence);
        IUser user = new User();
        user.setUserID(1);
        session.setAttribute("user", user.getUserID());
        assertEquals(true, sessionService.isUserInSession(session)); ;
    }

    @Test
    public void isUserInSessionFailureTest(){
        HttpSession session = new MockHttpSession();
        IUserPersistence iUserPersistence = new UserPersistenceMock();
        SessionService sessionService = new SessionService(iUserPersistence);
        IUser user = new User();
        user.setUserID(1);
        session.setAttribute("admin", user.getUserID());
        assertNotEquals(true, sessionService.isUserInSession(session)); ;
    }
}
