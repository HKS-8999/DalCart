package dalcart.app.Factories;

import dalcart.app.Repository.IUserPersistence;

public class MockUserPersistanceFactory implements IUserPersistanceFactory{
    @Override
    public IUserPersistence createIUserPersistance() {
      //  return new MockUserPersistance();
        return null;
    }
}
