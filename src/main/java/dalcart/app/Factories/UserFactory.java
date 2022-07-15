package dalcart.app.Factories;

import dalcart.app.models.IUser;
import dalcart.app.models.User;

public class UserFactory implements IUserFactory {

    @Override
    public IUser createUser() {
        return new User();
    }
}
