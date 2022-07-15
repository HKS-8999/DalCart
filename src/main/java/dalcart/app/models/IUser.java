package dalcart.app.models;

import dalcart.app.Repository.IUserPersistence;


public abstract class IUser {
    protected String email;
    protected String firstName;
    protected String lastName;
    protected String password;
    protected String mobileNo;

    protected String designation;

    protected int userID;

    public abstract IUserPersistence.Result createNewUser(IUser user, IUserPersistence userPersistence) throws Exception;

    public abstract Integer getUserID();

    public abstract String getEmail();

    public abstract void setFirstName(String firstName);

    public abstract void setLastName(String lastName);

    public abstract void setMobileNo(String mobileNo);

    public abstract void setUserID(int userID);

    public abstract void setPassword(String password);

    public abstract void setEmail(String emailID);

    public abstract String getPassword();

    public abstract String getFirstName();

    public abstract String getLastName();
    public abstract String getMobileNo();
    public abstract void loadUserAttributes(IUserPersistence iUserPersistence);

    public abstract void setDesignation(String designation);

    public abstract String getDesignation();
}
