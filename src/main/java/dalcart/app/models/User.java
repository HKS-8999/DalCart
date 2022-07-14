package dalcart.app.models;

import dalcart.app.Repository.IUserPersistence;


public class User implements IUser {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String mobileNo;

    private int userID;

    public int getUserId(){return userID;}

    public void setUserId(int userID){ this.userID = userID; }
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public IUserPersistence.Result createNewUser(User user, IUserPersistence userPersistence) throws Exception {

        this.userID = userPersistence.save(user);
        return IUserPersistence.Result.SUCCESS;
    }
    public Integer getUserID(){
        return this.userID;
    }
}
