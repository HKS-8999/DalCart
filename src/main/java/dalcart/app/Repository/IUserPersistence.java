package dalcart.app.Repository;

import dalcart.app.models.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


public interface IUserPersistence {
    enum StorageResult {
        DOES_NOT_EXIST,
        STORAGE_FAILURE,
        SUCCESS
    }

    public StorageResult save(User u) throws Exception;

    public StorageResult load(String email, User u);
}
