package dalcart.app.items;

public interface IUser {
    public int getUserID();
    public String getPassword();
    public void setPassword(String password);
    public String getLastName();
    public void setLastName(String lastName);
    public String getFirstName();
    public void setFirstName(String firstName);
    public String getMobileNo();
    public void setMobileNo(String mobileNo);
    public void setEmail(String email);
    public String getEmail();
}
