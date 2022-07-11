package dalcart.app.controllers;

import dalcart.app.Repository.ISessionGenerator;
import dalcart.app.Repository.IUserPersistence;
import dalcart.app.Repository.SessionDB;
import dalcart.app.Repository.UserDB;
import dalcart.app.models.Security;
import dalcart.app.models.SecurityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
