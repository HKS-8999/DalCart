package dalcart.app.controllers;

import dalcart.app.Repository.IUserPersistence;
import dalcart.app.Repository.UserDB;
import dalcart.app.models.Security;

import dalcart.app.models.SecurityService;
import dalcart.app.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



@Controller
public class LoginController {
    @GetMapping("/login")
    public ModelAndView loginPage(HttpServletRequest request)
    {
        HttpSession session = request.getSession();

        if(SecurityService.isSessionValid(session) == false){
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("login");
            return modelAndView;
        }
        else{
            return new ModelAndView("redirect:/home");
        }

    }

    @PostMapping("/login")
    public ModelAndView submitForm(@ModelAttribute User user, HttpServletRequest request ){
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        try
        {
            IUserPersistence iUserPersistence = new UserDB();
            Security security = new SecurityService(iUserPersistence);

            if(security.authenticateUser(user).equals(Security.RESULT.AUTHORIZED)) {
                user.loadUserAttributes(iUserPersistence);
                System.out.println(user.isAdmin(user.getDesignation()));
                if (user.isAdmin(user.getDesignation())) {
                    session.setAttribute("admin", user.getUserID());
                    return new ModelAndView("redirect:/admin");

                }
                else
                {
                    session.setAttribute("user",user.getUserID());
//                    modelAndView.setViewName("redirect:/home");
                    return new ModelAndView("redirect:/home");
                }
            }
            else
                {
//                    modelAndView.setViewName("redirect:/invalidUsernameandPassword");
                    return new ModelAndView("invalidUsernameandPassword");

                }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        modelAndView.setViewName("login");
        return modelAndView;
    }
    @GetMapping("/logout")
    public String destroySession(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/home";
    }

}
