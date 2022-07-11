package dalcart.app.controllers;

import dalcart.app.Repository.ISessionGenerator;
import dalcart.app.Repository.IUserPersistence;

import dalcart.app.Repository.SessionDB;
import dalcart.app.Repository.UserDB;


import dalcart.app.service.Security;
import dalcart.app.service.SecurityService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    @GetMapping("/login")
    public ModelAndView loginPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView submitForm(@RequestParam("email") String email, @RequestParam("password") String password){
        ModelAndView modelAndView = new ModelAndView();
        try{
            IUserPersistence iUserPersistence = new UserDB();
            Security security = new SecurityService(iUserPersistence);
            ISessionGenerator sessionGenerator = new SessionDB();
            if(security.authenticateUser(email,password) == Security.RESULT.AUTHORIZED){
                sessionGenerator.saveSession(email,iUserPersistence);
                modelAndView.setViewName("home");
                return modelAndView;
            }
            else{
                modelAndView.setViewName("invalidUsernameandPassword");
                return modelAndView;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        modelAndView.setViewName("login");
        return modelAndView;
    }
}
