package dalcart.app.models.Security;

import dalcart.app.Repository.IUserPersistence;
import dalcart.app.models.IUser;

public abstract class Security {
    protected Security nextHandler;
    protected String email;
    protected String password;
    IUserPersistence userPersistence;

    Security(IUserPersistence userPersistence, IUser user) {
        this.userPersistence = userPersistence;
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public enum RESULT {
        USERNAME_INVALID,
        PASSWORD_INVALID,
        IS_NOT_AUTHORIZED,
        AUTHORIZED;
    }

    public void setNextHandler(Security nextHandler) {
        if(this.nextHandler == null) {
            this.nextHandler = nextHandler;
            return;
        }

        this.nextHandler.setNextHandler(nextHandler);
    }
    public RESULT authenticate(IUser user) {
        return authenticateProtocol(user);
    }

    public RESULT passToNext(IUser user) {
        if(nextHandler == null){
            return RESULT.AUTHORIZED;
        }
        return nextHandler.authenticate(user);
    }

    protected abstract RESULT authenticateProtocol(IUser user);
}

