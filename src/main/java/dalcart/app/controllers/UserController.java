package dalcart.app.controllers;

import dalcart.app.Factories.IUserFactory;
import dalcart.app.Factories.IUserPersistanceFactory;
import dalcart.app.Factories.UserFactory;
import dalcart.app.Factories.UserPersistanceFactory;
import dalcart.app.Repository.IUserPersistence;
import dalcart.app.models.IUser;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController{

    IUserFactory userFactory;

    IUserPersistanceFactory userPersistence;

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
            user.createNewUser(newUser, iUserPersistence);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        modelAndView.setViewName("login");
        return modelAndView;
    }
}