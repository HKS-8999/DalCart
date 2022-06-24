package dalcart.app.service;

import dalcart.app.models.IUserPersistence;
import dalcart.app.models.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {

    IUserPersistence.StorageResult createNewUser(User user) throws Exception;
}
