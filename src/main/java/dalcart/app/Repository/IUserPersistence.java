package dalcart.app.Repository;

import dalcart.app.models.IUser;

public interface IUserPersistence {
    enum Result {
        STORAGE_FAILURE,
        SUCCESS
    }

    IUser loadUserAttributesbyUsername(String email);

    boolean save(IUser u) throws Exception;

    IUser loadUserAttributesByUserId(int userId);

}
