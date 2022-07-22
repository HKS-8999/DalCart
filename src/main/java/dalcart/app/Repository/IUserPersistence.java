package dalcart.app.Repository;

import dalcart.app.models.IUser;
import dalcart.app.models.User;

import java.sql.ResultSet;

public interface IUserPersistence {

    enum Result {
        DOES_NOT_EXIST,
        STORAGE_FAILURE,
        SUCCESS
    }

    IUser loadUserAttributesbyUsername(String email);
    void save(IUser u) throws Exception;

    IUser loadUserAttributesByUserId(int userId);

}
