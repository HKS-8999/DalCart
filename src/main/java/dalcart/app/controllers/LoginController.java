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
    public ModelAndView loginPage()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView submitForm(@ModelAttribute User user, HttpServletRequest request){
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        try
        {
            IUserPersistence iUserPersistence = new UserDB();
            Security security = new SecurityService(iUserPersistence);

            if(security.authenticateUser(user).equals(Security.RESULT.AUTHORIZED))
            {
//                List<String> notes = (List<String>) request.getSession().getAttribute("NOTES_SESSION");
//                //check if notes is present in session or not
//                if (notes == null) {
//                    notes = new ArrayList<>();
//                    // if notes object is not present in session, set notes in the request session
//                    request.getSession().setAttribute("NOTES_SESSION", notes);
//                }
                //session.setAttribute("user", user);
                modelAndView.setViewName("home");
                return modelAndView;
            }
            else
            {
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
