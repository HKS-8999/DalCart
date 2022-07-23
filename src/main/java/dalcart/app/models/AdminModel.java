package dalcart.app.models;

import org.springframework.stereotype.Service;


@Service
public class AdminModel
{

    public static boolean isUserAdmin(String userKey)
    {
        //return User.isUserAdmin();
        return false;
    }
}
