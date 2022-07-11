package dalcart.app.service;

import dalcart.app.Repository.IUserPersistence;
import dalcart.app.models.User;


public interface IUserService {

    IUserPersistence.Result createNewUser(User user,IUserPersistence userPersistence) throws Exception;

}
