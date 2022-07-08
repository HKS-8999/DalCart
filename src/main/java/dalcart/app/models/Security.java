package dalcart.app.models;

public interface Security {
    public enum RESULT{
        USERNAME_INVALID,
        PASSWORD_INVALID,
        IS_NOT_AUTHORIZED,
        AUTHORIZED;
    }

    RESULT authenticateUser(String email, String password);
}
