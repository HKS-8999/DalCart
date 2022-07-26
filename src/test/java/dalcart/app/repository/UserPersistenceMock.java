package dalcart.app.repository;

import dalcart.app.models.Factories.IUserFactory;
import dalcart.app.models.Factories.UserFactory;
import dalcart.app.models.Repository.IUserPersistence;
import dalcart.app.models.IUser;

public class UserPersistenceMock implements IUserPersistence {
    @Override
    public IUser loadUserAttributesbyUsername(String email) {

        IUserFactory userFactory = new UserFactory();
        if(email == null){
            IUser user = userFactory.createUser();
            return user;
        }
        if(email.equals("harsh@dal.ca")){
            IUser user = userFactory.createUser();
            user.setUserID(1);
            user.setEmail("harsh@dal.ca");
            user.setPassword("$2a$12$Z8mrkwETDQFiFmV1axcrL.yADFDwFSuogImgzetOlyToGwib0G23i");
            return user;
        }
        return userFactory.createUser();
    }

    @Override
    public boolean save(IUser u) {
        if(u.getUserID() == 1){
            return true;
        }
        return false;
    }

    @Override
    public IUser loadUserAttributesByUserId(int userId) {
        IUserFactory userFactory = new UserFactory();
        if(userId == 1){
            IUser user = userFactory.createUser();
            user.setFirstName("harsh");
            user.setEmail("harsh@dal.ca");
            return user;
        }
        return userFactory.createUser();
    }
}
