package dalcart.app.models;

import dalcart.app.Repository.IUserPersistence;
import dalcart.app.models.User;

import javax.servlet.http.HttpSession;


public interface Security {
    public enum RESULT{
        USERNAME_INVALID,
        PASSWORD_INVALID,
        IS_NOT_AUTHORIZED,
        AUTHORIZED;
    }

    RESULT authenticateUser(IUser user);

//    public boolean isSessionValid(HttpSession session);

}
