package dalcart.app.models;

import dalcart.app.Repository.IUserPersistence;


public interface IUser {

    IUserPersistence.Result createNewUser(IUser user,IUserPersistence userPersistence) throws Exception;

    public Integer getUserID();

    public String getEmail();

    public void setFirstName(String firstName);

    public void setLastName(String lastName);

    public void setMobileNo(String mobileNo);

    public void setUserID(int userID);

    public void setPassword(String password);

    public void setEmail(String emailID);

    public String getPassword();

    public String getFirstName();

    public String getLastName();
    public String getMobileNo();

    public void loadUserAttributes(IUser user, IUserPersistence iUserPersistence);
}
