package dalcart.app.controllers;

import dalcart.app.items.User;
import dalcart.app.models.IUserModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController{

    @GetMapping("/signup")
    public String newUserRegistration(){
        return "signup";
    }

    @PostMapping("/signup")
    public String submitForm(@ModelAttribute User user, IUserModel userService){
        try {
            userService.createNewUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "new user created";
    }
}