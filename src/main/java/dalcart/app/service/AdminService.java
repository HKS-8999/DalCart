package dalcart.app.service;

import dalcart.app.Repository.IUserPersistence;
import dalcart.app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdminService implements IAdminService{

    @Autowired
    private IUserPersistence iUserPersistence;

    public static boolean isUserAdmin(String userKey){
        //return User.isUserAdmin();
        return false;
    }
}
