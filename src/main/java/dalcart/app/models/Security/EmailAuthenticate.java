package dalcart.app.models.Security;

import dalcart.app.Repository.IUserPersistence;
import dalcart.app.models.IUser;

public class EmailAuthenticate extends Security {

    public EmailAuthenticate(IUserPersistence userPersistence, IUser user) {
        super(userPersistence, user);
    }
    @Override
    protected RESULT authenticateProtocol(IUser user) {

        if(super.email == null || super.email.trim().isEmpty() || user.getEmail() == null || user.getEmail().trim().isEmpty()){
            return RESULT.USERNAME_INVALID;
        }
        return super.passToNext(user);
    }
}
