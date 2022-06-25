package dalcart.app.controllers;

import dalcart.app.models.User;
import dalcart.app.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController{

    @Autowired
    IUserService userservice;

    @GetMapping("/signup")
    public String newUserRegistration()
    {
        return "signup";
    }

    @PostMapping("/signup")
    public String submitForm(@ModelAttribute User user){
        try {
            userservice.createNewUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "new user created";
    }

}