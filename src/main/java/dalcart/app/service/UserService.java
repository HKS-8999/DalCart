package dalcart.app.service;

import dalcart.app.Repository.IUserPersistence;
import dalcart.app.Repository.UserDB;
import dalcart.app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService implements IUserService{

    public IUserPersistence.Result createNewUser(User user, IUserPersistence userPersistence) throws Exception {
        return userPersistence.save(user);
    }
}
