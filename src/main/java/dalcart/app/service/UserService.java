package dalcart.app.service;

import dalcart.app.Repository.IUserPersistence;
import dalcart.app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class newUserService implements IUserService{

    @Autowired
    private IUserPersistence iUserPersistence;

    public IUserPersistence.StorageResult createNewUser(User user) throws Exception {
        return iUserPersistence.save(user);
    }
}
