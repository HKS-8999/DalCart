package dalcart.app.models.Factories;

import dalcart.app.models.Repository.IUserPersistence;
import dalcart.app.models.Repository.UserDB;

public class UserPersistanceFactory implements IUserPersistanceFactory {

    @Override
    public IUserPersistence createIUserPersistance() {
        return new UserDB();
    }
}
