package dalcart.app.repository;

import dalcart.app.Factories.IUserFactory;
import dalcart.app.Factories.UserFactory;
import dalcart.app.Repository.IUserPersistence;
import dalcart.app.models.IUser;

public class MockUserPersistance implements IUserPersistence {
    @Override
    public IUser loadUserAttributesbyUsername(String email) {
        IUserFactory userFactory = new UserFactory();
        if(email.equals("harsh@gmail.com")){
            IUser user = userFactory.createUser();
            user.setUserID(1);
            user.setEmail("harsh@gmail.com");
            return user;
        }
        return userFactory.createUser();
    }

    @Override
    public boolean save(IUser u) {
        if(u.getUserId() == 1){
            return true;
        }
        return false;
    }

    @Override
    public IUser loadUserAttributesByUserId(int userId) {
        return null;
    }
}
