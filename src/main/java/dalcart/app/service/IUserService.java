package dalcart.app.service;

import dalcart.app.Repository.IUserPersistence;
import dalcart.app.models.User;


public interface IUserService {

    IUserPersistence.StorageResult createNewUser(User user) throws Exception;
}
