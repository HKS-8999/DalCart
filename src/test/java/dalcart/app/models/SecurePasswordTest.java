package dalcart.app.models;

import dalcart.app.models.Factories.IUserFactory;
import dalcart.app.models.Factories.UserFactory;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SecurePasswordTest {
    IUserFactory iUserFactory = new UserFactory();

    @Test
    public void encryptSuccessTest() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        ISecurePassword securePassword = new SecurePassword();
        IUser user = iUserFactory.createUser();
        String password = "Test12345";
        user.setPassword(password);
        securePassword.encrypt(user);
        boolean matches = bCryptPasswordEncoder.matches(password, user.getPassword());
        assertEquals(true, matches);
    }

    @Test
    public void encryptFailureTest() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        IUser user = iUserFactory.createUser();
        String password = "Test12345";
        user.setPassword(password);
        boolean matches = bCryptPasswordEncoder.matches(password, user.getPassword());
        assertEquals(false, matches);
    }
}
