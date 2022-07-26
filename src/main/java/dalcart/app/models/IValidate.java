package dalcart.app.models;

public interface IValidate {
    boolean isUserNameValid(IUser user);

    boolean isPasswordValid(IUser user);

    boolean isMobileNumberValid(IUser user);

    boolean isFirstNameValid(IUser user);

    boolean isLastNameValid(IUser user);


}
