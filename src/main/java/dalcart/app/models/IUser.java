package dalcart.app.models;

import dalcart.app.models.Repository.IUserPersistence;

public abstract class IUser {

    protected String email;

    protected String firstName;

    protected String lastName;

    protected String password;

    protected String mobileNo;

    protected String designation;

    protected int userID;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Integer getUserID() {
        return this.userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public abstract IUserPersistence.Result createNewUser(IUser user, IUserPersistence userPersistence);

    public abstract void loadUserAttributes(IUserPersistence userPersistence);

    public abstract boolean isAdmin(String designation);
}
