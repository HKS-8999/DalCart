package dalcart.app.models.SecurityChain;

import dalcart.app.models.Repository.IUserPersistence;
import dalcart.app.models.IUser;

public class PasswordAuthenticate extends Security {
    public PasswordAuthenticate(IUserPersistence userPersistence, IUser user) {
        super(userPersistence, user);
    }

    @Override
    protected RESULT authenticateProtocol(IUser user) {
        user.loadUserAttributes(userPersistence);
        if (super.password == null || user.getPassword() == null || super.password.trim().isEmpty() || user.getPassword().trim().isEmpty()) {
            return RESULT.PASSWORD_INVALID;
        }
        return super.passToNext(user);
    }
}
