package dalcart.app.models.SecurityChain;

import dalcart.app.models.Repository.IUserPersistence;
import dalcart.app.models.IUser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EmailAndPasswordAuthenticate extends Security {
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public EmailAndPasswordAuthenticate(IUserPersistence userPersistence, IUser user) {
        super(userPersistence, user);
    }

    @Override
    protected RESULT authenticateProtocol(IUser user) {
        user.loadUserAttributes(userPersistence);
        boolean isPasswordCorrect = bCryptPasswordEncoder.matches(super.password, user.getPassword());
        if (user.getEmail().equals(super.email) && isPasswordCorrect) {
            return RESULT.AUTHORIZED;
        }
        return RESULT.IS_NOT_AUTHORIZED;
    }
}
