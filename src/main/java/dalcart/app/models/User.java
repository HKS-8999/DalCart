package dalcart.app.models;

import dalcart.app.Repository.IUserPersistence;

public class User extends IUser {

    @Override
    public void loadUserAttributes(IUserPersistence userPersistence) {
        IUser user = userPersistence.loadUserAttributesbyUsername(this.email);
        this.setEmail(user.getEmail());
        this.setPassword(user.getPassword());
        this.setUserID(user.getUserID());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setMobileNo(user.getMobileNo());
        this.setDesignation(user.getDesignation());
    }

    public IUserPersistence.Result createNewUser(IUser user, IUserPersistence userPersistence) throws Exception {
        this.userID = userPersistence.save(user);
        return IUserPersistence.Result.SUCCESS;
    }

    public Security.RESULT hasAccess(Security security, IUser user) {
        return security.authenticateUser(user);
    }

    public boolean isAdmin(String designation){
        if(designation.toLowerCase().trim().equals("admin")){
            return true;
        }
        return false;
    }
}
