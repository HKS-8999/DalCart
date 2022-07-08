package dalcart.app.models;

import dalcart.app.Repository.IUserPersistence;
import dalcart.app.items.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserModel implements IUserModel {

    @Autowired
    private IUserPersistence iUserPersistence;

    public IUserPersistence.Result createNewUser(User user) throws Exception {
        return iUserPersistence.save(user);
    }
}
