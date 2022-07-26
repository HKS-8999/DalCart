package dalcart.app.models;

import dalcart.app.models.Factories.IUserFactory;
import dalcart.app.models.Factories.UserFactory;
import dalcart.app.models.Repository.IUserPersistence;
import dalcart.app.repository.UserPersistenceMock;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserDBTest {
    IUserFactory userFactory = new UserFactory();
    @Test
    public void loadUserByEmailValidTest(){
        String email = "harsh@dal.ca";
        IUser user = userFactory.createUser();
        user.setEmail(email);
        IUserPersistence userPersistence = new UserPersistenceMock();
        user.loadUserAttributes(userPersistence);
        assertEquals(1, user.getUserID());
    }

    @Test
    public void loadUserByEmailInValidTest(){
        String email = "harsh1@gmail.com";
        IUser user = userFactory.createUser();
        user.setEmail(email);
        IUserPersistence userPersistence = new UserPersistenceMock();
        user.loadUserAttributes(userPersistence);
        assertEquals(null, user.getEmail());
    }

    @Test
    public void saveUserValidTest(){
        int userId = 1;
        IUserFactory iUserFactory = new UserFactory();
        IUser user =  iUserFactory.createUser();
        user.setUserID(userId);
        IUserPersistence userPersistence = new UserPersistenceMock();
        assertEquals(IUserPersistence.Result.SUCCESS, user.createNewUser(user, userPersistence));
    }

    @Test
    public void saveUserInvalidTest(){
        int userId = 0;
        IUserFactory iUserFactory = new UserFactory();
        IUser user =  iUserFactory.createUser();
        user.setUserID(userId);
        IUserPersistence userPersistence = new UserPersistenceMock();
        assertEquals(IUserPersistence.Result.STORAGE_FAILURE, user.createNewUser(user, userPersistence));
    }

    @Test
    public void loadUserByIdValidTest(){
        int id = 1;
        IUser user = userFactory.createUser();
        user.setUserID(id);
        IUserPersistence userPersistence = new UserPersistenceMock();
        user = userPersistence.loadUserAttributesByUserId(id);
        assertEquals("harsh", user.getFirstName());
    }

    @Test
    public void loadUserByIdInvalidTest(){
        int id = 2;
        IUser user = userFactory.createUser();
        user.setUserID(id);
        IUserPersistence userPersistence = new UserPersistenceMock();
        user = userPersistence.loadUserAttributesByUserId(id);
        assertEquals(null, user.getFirstName());
    }
}
