package dalcart.app.models;

import dalcart.app.Repository.IUserPersistence;
import dalcart.app.items.User;


public interface IUserModel {

    IUserPersistence.Result createNewUser(User user) throws Exception;

}
