package dalcart.app.models;

import javax.servlet.http.HttpSession;

public interface ISecurity {
    public enum RESULT{
        USERNAME_INVALID,
        PASSWORD_INVALID,
        IS_NOT_AUTHORIZED,
        AUTHORIZED;
    }

    RESULT authenticateUser(IUser user);

    public boolean isUserRoleAdmin(HttpSession session);

    public boolean isUserRoleUser(HttpSession session);

}
