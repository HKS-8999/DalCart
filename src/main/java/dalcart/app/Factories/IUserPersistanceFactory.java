package dalcart.app.Factories;

import dalcart.app.Repository.IUserPersistence;

public interface IUserPersistanceFactory {
    IUserPersistence createIUserPersistance();
}
