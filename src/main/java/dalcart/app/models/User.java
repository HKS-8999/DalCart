package dalcart.app.models;

import dalcart.app.Repository.IUserPersistence;


public class User implements IUser {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String mobileNo;

    private int userID;




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

    @Override
    public void loadUserAttributes(IUser user ,IUserPersistence userPersistence) {
        user = userPersistence.loadUserAttributesbyUsername(this.email);
        this.setEmail(user.getEmail());
        this.setPassword(user.getPassword());
        this.setUserID(user.getUserID());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setMobileNo(user.getMobileNo());
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }



    public IUserPersistence.Result createNewUser(IUser user, IUserPersistence userPersistence) throws Exception {

        this.userID = userPersistence.save(user);
        return IUserPersistence.Result.SUCCESS;
    }
    public Integer getUserID(){
        return this.userID;
    }

    public void setUserID(int userID){
        this.userID = userID;
    }



    public Security.RESULT hasAccess(Security security, User user){
        return security.authenticateUser(user);
    }
}
