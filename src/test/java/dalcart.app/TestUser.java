package dalcart.app;

import dalcart.app.Factories.*;
import dalcart.app.Repository.IUserPersistence;
import dalcart.app.models.IUser;
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

        IUserPersistanceFactory userPersistanceFactory = new MockUserPersistanceFactory();
        IUserPersistence userPersistence = userPersistanceFactory.createIUserPersistance();

        user.loadUserAttributes(userPersistence);

        assertEquals(email, user.getEmail());

    }

    @Test
    public void loadUserByEmailInValidTest(){
        String email = "harsh1@gmail.com";

        IUser user = userFactory.createUser();
        user.setEmail(email);

        IUserPersistanceFactory userPersistanceFactory = new MockUserPersistanceFactory();
        IUserPersistence userPersistence = userPersistanceFactory.createIUserPersistance();

        user.loadUserAttributes(userPersistence);

        assertEquals(null, user.getEmail());

    }
}
