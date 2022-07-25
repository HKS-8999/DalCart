package dalcart.app.controllers;

import dalcart.app.Factories.*;
import dalcart.app.Repository.IUserPersistence;
import dalcart.app.models.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@Controller
public class SignUpController
{
    IUserFactory userFactory;
    IUserPersistanceFactory userPersistence;
    IValidateFactory validateFactory;

    ISecurityFactory securityFactory;

    @GetMapping("/signup")
    public ModelAndView newUserRegistration()
    {
        return new ModelAndView("signup");
    }

    @PostMapping("/signup")
    public ModelAndView submitForm(@ModelAttribute User newUser)
    {
        ModelAndView modelAndView = new ModelAndView();
        try {
            userFactory = new UserFactory();
            IUser user;
            userPersistence = new UserPersistanceFactory();
            IUserPersistence iUserPersistence = userPersistence.createIUserPersistance();
            validateFactory = new ValidateFactory();
            IValidate validate = validateFactory.createValidations();
            securityFactory = new SecurityFactory();
            ISecurePassword securePassword = securityFactory.createSecurePassword();

            if(validate.isUserNameValid(newUser) && validate.isFirstNameAndLastNameValid(newUser) && validate.isMobileNumberValid(newUser) && validate.isPasswordValid(newUser)) {
                user = securePassword.encrypt(newUser);
                IUserPersistence.Result result = user.createNewUser(newUser, iUserPersistence);
                if (result.equals(IUserPersistence.Result.SUCCESS)){
                    modelAndView.setViewName("redirect:/login");
                }
                else{
                    throw new SQLException();
                }
            }
            else
            {
                return new ModelAndView("redirect:/invalidUsernameandPassword");
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

        return modelAndView;
    }
}