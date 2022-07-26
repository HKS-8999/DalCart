package dalcart.app.models;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class ValidateUserAttributes implements IValidate {

    public boolean isUserNameValid(IUser user) {
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            return false;
        } else {
            String regexForEmailValidation = "(^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$)";
            return Pattern.compile(regexForEmailValidation)
                    .matcher(user.getEmail())
                    .matches();
        }
    }

    public boolean isPasswordValid(IUser user) {
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            return false;
        } else if (user.getPassword().trim().length() >= 8) {
            String regexForPasswordValidation = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}";
            return Pattern.compile(regexForPasswordValidation)
                    .matcher(user.getPassword())
                    .matches();
        }
        return false;
    }

    public boolean isMobileNumberValid(IUser user) {
        if (user.getMobileNo().trim().length() == 10) {
            return true;
        }
        return false;
    }

    public boolean isFirstNameValid(IUser user) {
        if (user.getFirstName() == null || user.getFirstName().trim().isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean isLastNameValid(IUser user) {
        if (user.getLastName() == null || user.getLastName().trim().isEmpty()) {
            return false;
        }
        return true;
    }
}
