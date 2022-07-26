package dalcart.app.models;

public interface IValidate {
    public boolean isUserNameValid(IUser user);
    public boolean isPasswordValid(IUser user);

    public boolean isMobileNumberValid(IUser user);
    public boolean isFirstNameValid(IUser user);
    public boolean isLastNameValid(IUser user);


}
