package dalcart.app.controllers;

import dalcart.app.models.User;
import dalcart.app.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController{

    @GetMapping("/signup")
    public String newUserRegistration(){
        return "signup";
    }

    @PostMapping("/signup")
    public String submitForm(@ModelAttribute User user, IUserService userService){
        try {
            userService.createNewUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "new user created";
    }
}