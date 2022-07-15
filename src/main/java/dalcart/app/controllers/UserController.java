package dalcart.app.controllers;

import dalcart.app.Repository.IUserPersistence;
import dalcart.app.Repository.UserDB;
import dalcart.app.models.User;
import dalcart.app.models.IUser;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController{

    @GetMapping("/signup")
    public String newUserRegistration(){
        return "signup";
    }

    @PostMapping("/signup")
    public ModelAndView submitForm(@ModelAttribute IUser user){
        ModelAndView modelAndView = new ModelAndView();
        try {
            IUser userService = new User();
            IUserPersistence iUserPersistence = new UserDB();
            userService.createNewUser(user, iUserPersistence);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        modelAndView.setViewName("login");
        return modelAndView;
    }
}