package dalcart.app.controllers;

import dalcart.app.Repository.ISessionGenerator;
import dalcart.app.models.User;
import dalcart.app.service.Security;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.util.Map;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @PostMapping("/login")
    public String submitForm(@RequestParam("email") String email,@RequestParam("password") String password, Security security, ISessionGenerator sessionGenerator){
        try{

            if(security.authenticateUser(email,password) == Security.RESULT.AUTHORIZED){
                sessionGenerator.saveSession(email);
                return "home";
            }
            else{
                return "invalidUsernameandPassword";
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return "login";
    }
}
