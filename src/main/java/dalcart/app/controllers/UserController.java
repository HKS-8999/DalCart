package dalcart.app.controllers;

import dalcart.app.Factories.*;
import dalcart.app.Repository.IUserPersistence;
import dalcart.app.models.IUser;

import dalcart.app.models.IValidate;
import dalcart.app.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController
{
    IUserFactory userFactory;

    IUserPersistanceFactory userPersistence;
    IValidateFactory validateFactory;

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
            IUser user = userFactory.createUser();
            userPersistence = new UserPersistanceFactory();
            IUserPersistence iUserPersistence = userPersistence.createIUserPersistance();
            validateFactory = new ValidateFactory();
            IValidate validate = validateFactory.createValidations();
            if(validate.isUserNameValid(newUser) && validate.isFirstNameAndLastNameValid(newUser) && validate.isMobileNumberValid(newUser) && validate.isPasswordValid(newUser))
            {
                user.createNewUser(newUser, iUserPersistence);
            }
            else
            {
                return new ModelAndView("invalidUsernameandPassword");
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        modelAndView.setViewName("login");
        return modelAndView;
    }
}