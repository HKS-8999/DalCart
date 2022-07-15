package dalcart.app.Repository;

import dalcart.app.models.IUser;
import dalcart.app.models.User;

import java.sql.ResultSet;

public interface IUserPersistence {
    IUser loadUserAttributesbyUsername(String email);

    enum Result {
        DOES_NOT_EXIST,
        STORAGE_FAILURE,
        SUCCESS
    }

    Integer save(IUser u) throws Exception;

    int loadUserID(String email);
    public String loadUserPasswordbyUsername(String email);

}
