package dalcart.app.models.SecurityChain;

import dalcart.app.models.Repository.IUserPersistence;
import dalcart.app.models.IUser;

public class EmailAuthenticate extends Security {

    public EmailAuthenticate(IUserPersistence userPersistence, IUser user) {
        super(userPersistence, user);
    }

    @Override
    protected RESULT authenticateProtocol(IUser user) {
        user.loadUserAttributes(userPersistence);
        if (super.email == null || user.getEmail() == null || super.email.trim().isEmpty() || user.getEmail().trim().isEmpty()) {
            return RESULT.USERNAME_INVALID;
        }
        return super.passToNext(user);
    }
}
