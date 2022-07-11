package dalcart.app.controllers;

import dalcart.app.Repository.IUserPersistence;
import dalcart.app.Repository.UserDB;
import dalcart.app.models.User;
import dalcart.app.models.IUser;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController{

    @GetMapping("/signup")
    public String newUserRegistration(){
        return "signup";
    }

    @PostMapping("/signup")
    public String submitForm(@ModelAttribute User user){
        try {
            IUser userService = new User();
            IUserPersistence iUserPersistence = new UserDB();
            userService.createNewUser(user, iUserPersistence);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "new user created";
    }
}