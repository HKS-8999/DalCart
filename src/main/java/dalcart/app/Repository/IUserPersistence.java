package dalcart.app.Repository;

import dalcart.app.models.User;

public interface IUserPersistence {
    enum Result {
        DOES_NOT_EXIST,
        STORAGE_FAILURE,
        SUCCESS
    }

    Integer save(User u) throws Exception;

    int loadUserID(String email);
    public String loadUserPasswordbyUsername(String email);

    public String loadUsername(String email);
}
