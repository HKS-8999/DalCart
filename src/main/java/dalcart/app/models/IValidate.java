package dalcart.app.models;

public interface IValidate {
    public boolean isUserNameValid(IUser user, String regexForEmailValidation);

}
