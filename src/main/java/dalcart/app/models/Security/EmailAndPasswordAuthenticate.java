package dalcart.app.models.Security;

import dalcart.app.Repository.IUserPersistence;
import dalcart.app.models.IUser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EmailAndPasswordAuthenticate extends Security {
    public EmailAndPasswordAuthenticate(IUserPersistence userPersistence, IUser user) {
        super(userPersistence, user);
    }

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @Override
    protected RESULT authenticateProtocol(IUser user) {
        boolean isPasswordCorrect = bCryptPasswordEncoder.matches(super.password,user.getPassword());
        System.out.println(isPasswordCorrect);
        if (user.getEmail().equals(super.email)  && isPasswordCorrect)
        {
            return RESULT.AUTHORIZED;
        }
        return RESULT.IS_NOT_AUTHORIZED;
    }
}
