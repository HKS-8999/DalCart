package dalcart.app.models.Validation;

import dalcart.app.Repository.IUserPersistence;
import dalcart.app.models.IUser;

public class PasswordAuthenticate extends Security {
    public PasswordAuthenticate(IUserPersistence userPersistence, IUser user) {
        super(userPersistence, user);
    }

    @Override
    protected RESULT authenticateProtocol(IUser user) {
        if (super.password == null || super.password.trim().isEmpty() || user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            return RESULT.PASSWORD_INVALID;
        }
        return super.passToNext(user);
    }
}
