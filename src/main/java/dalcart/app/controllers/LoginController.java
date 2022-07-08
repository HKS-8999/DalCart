package dalcart.app.controllers;

import dalcart.app.Repository.ISessionGenerator;
import dalcart.app.Repository.IUserPersistence;

import dalcart.app.Repository.SessionDB;
import dalcart.app.Repository.UserDB;
import dalcart.app.models.Security;
import dalcart.app.models.SecurityModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @PostMapping("/login")
    public String submitForm(@RequestParam("email") String email, @RequestParam("password") String password){
        try{
            IUserPersistence iUserPersistence = new UserDB();
            System.out.println("000000");
            Security security = new SecurityModel(iUserPersistence);
            System.out.println("1111111");
            ISessionGenerator sessionGenerator = new SessionDB();
            System.out.println("222222222");
            if(security.authenticateUser(email,password) == Security.RESULT.AUTHORIZED){
                sessionGenerator.saveSession(email);
                System.out.println("333333");
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
