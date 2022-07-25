package dalcart.app.models;

import dalcart.app.Factories.*;
import dalcart.app.Repository.IUserPersistence;
import dalcart.app.models.IUser;
import dalcart.app.repository.MockUserPersistance;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TestUser {
    IUserFactory userFactory = new UserFactory();
    @Test
    public void loadUserByEmailValidTest(){
        String email = "harsh@gmail.com";
        IUser user = userFactory.createUser();
        user.setEmail(email);
        IUserPersistence userPersistence = new MockUserPersistance();
        user.loadUserAttributes(userPersistence);
        assertEquals(email, user.getEmail());
    }

    @Test
    public void loadUserByEmailInValidTest(){
        String email = "harsh1@gmail.com";
        IUser user = userFactory.createUser();
        user.setEmail(email);
        IUserPersistence userPersistence = new MockUserPersistance();
        user.loadUserAttributes(userPersistence);
        assertEquals(null, user.getEmail());
    }

    @Test
    public void saveUserValidTest(){
        int userId = 1;
        IUserFactory iUserFactory = new UserFactory();
        IUser user =  iUserFactory.createUser();
        user.setUserID(userId);
        IUserPersistence userPersistence = new MockUserPersistance();
        assertEquals(IUserPersistence.Result.SUCCESS, user.createNewUser(user, userPersistence));
    }

    @Test
    public void saveUserInvalidTest(){
        int userId = 0;
        IUserFactory iUserFactory = new UserFactory();
        IUser user =  iUserFactory.createUser();
        user.setUserID(userId);
        IUserPersistence userPersistence = new MockUserPersistance();
        assertEquals(IUserPersistence.Result.STORAGE_FAILURE, user.createNewUser(user, userPersistence));
    }
}
