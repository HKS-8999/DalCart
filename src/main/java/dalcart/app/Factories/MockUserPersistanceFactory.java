package dalcart.app.Factories;

import dalcart.app.Repository.IUserPersistence;
import mocks.MockUserPersistance;

public class MockUserPersistanceFactory implements IUserPersistanceFactory{
    @Override
    public IUserPersistence createIUserPersistance() {
        return new MockUserPersistance();
    }
}
