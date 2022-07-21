package dalcart.app.controllers;

import dalcart.app.Factories.*;
import dalcart.app.Repository.IUserPersistence;
import dalcart.app.models.IUser;

import dalcart.app.models.IValidate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController{
    IUserFactory userFactory;

    IUserPersistanceFactory userPersistence;
    IValidateFactory validateFactory;

    @GetMapping("/signup")
    public ModelAndView newUserRegistration(){
        return new ModelAndView("signup");
    }

    @PostMapping("/signup")
    public ModelAndView submitForm(@ModelAttribute IUser newUser){
        ModelAndView modelAndView = new ModelAndView();
        try {
            userFactory = new UserFactory();
            IUser user = userFactory.createUser();
            userPersistence = new UserPersistanceFactory();
            IUserPersistence iUserPersistence = userPersistence.createIUserPersistance();
            validateFactory = new ValidateFactory();
            IValidate validate = validateFactory.createValidations();
            if(validate.isUserNameValid(user) && validate.isFirstNameAndLastNameValid(user) && validate.isMobileNumberValid(user) && validate.isPasswordValid(user)) {
                user.createNewUser(newUser, iUserPersistence);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        modelAndView.setViewName("login");
        return modelAndView;
    }
}