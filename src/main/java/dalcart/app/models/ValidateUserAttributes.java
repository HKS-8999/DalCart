package dalcart.app.models;

import java.util.regex.Pattern;

public class ValidateUserAttributes implements IValidate{
    public boolean isUserNameValid(IUser user, String regexForEmailValidation){

        //"(^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$)";
        return Pattern.compile(regexForEmailValidation)
                .matcher(user.getEmail())
                .matches();
    }

    public boolean isPasswordValid(IUser user){
        return true;
    }
}
