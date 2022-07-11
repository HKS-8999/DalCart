package dalcart.app.models;

import dalcart.app.Repository.IUserPersistence;


public interface IUser {

    IUserPersistence.Result createNewUser(User user,IUserPersistence userPersistence) throws Exception;
    public Integer getUserID();

}
