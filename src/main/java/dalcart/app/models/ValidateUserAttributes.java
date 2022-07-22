package dalcart.app.models;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class ValidateUserAttributes implements IValidate
{
    public boolean isUserNameValid(IUser user)
    {
        String regexForEmailValidation = "(^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$)";
        return Pattern.compile(regexForEmailValidation)
                .matcher(user.getEmail())
                .matches();
    }

    public boolean isPasswordValid(IUser user)
    {
        if(user.getPassword().trim().length() >= 8)
        {
            for (char ch : user.getPassword().trim().toCharArray())
            {
//                if (Character.isLowerCase(ch) == false && Character.isDigit(ch) == false)
//                {
//                    return false;
//                }
            }
            return true;
        }
        return false;
    }

    public boolean isMobileNumberValid(IUser user)
    {
        if (user.getMobileNo().trim().length() == 10)
        {
            return true;
        }
        return false;
    }

    public boolean isFirstNameAndLastNameValid(IUser user)
    {
        if (user.getFirstName().isEmpty() || user.getLastName().isEmpty() || user.getFirstName() == null || user.getLastName() == null)
        {
            return false;
        }
        return true;
    }
}
