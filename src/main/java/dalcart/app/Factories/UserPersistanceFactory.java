package dalcart.app.Factories;

import dalcart.app.Repository.IUserPersistence;
import dalcart.app.Repository.UserDB;

public class UserPersistanceFactory implements IUserPersistanceFactory{

    @Override
    public IUserPersistence createIUserPersistance() {
        return new UserDB();
    }
}
