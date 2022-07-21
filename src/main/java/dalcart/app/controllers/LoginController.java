package dalcart.app.controllers;

import dalcart.app.Factories.*;
import dalcart.app.Repository.IUserPersistence;
import dalcart.app.models.ISecurity;

import dalcart.app.models.IValidate;
import dalcart.app.models.SecurityService;
import dalcart.app.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    IUserPersistanceFactory userPersistanceFactory;
    ISecurityFactory securityFactory;
    IUserFactory newUserFactory;

    IValidateFactory validateFactory;
    @RequestMapping("/login")
    public ModelAndView loginPage(HttpServletRequest request)
    {
        Logger logger = LogManager.getLogger(this.getClass());
        HttpSession session = request.getSession();
        if(SecurityService.isSessionValid(session) == false)
        {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("login");
            return modelAndView;
        }
        else
        {
            return new ModelAndView("redirect:/home");
        }
    }

    @PostMapping("/login")
    public ModelAndView submitForm(@ModelAttribute User user, HttpServletRequest request ){
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        try
        {
            userPersistanceFactory = new UserPersistanceFactory();
            newUserFactory = new UserFactory();
            securityFactory = new SecurityFactory();
            validateFactory = new ValidateFactory();
            IUserPersistence iUserPersistence = userPersistanceFactory.createIUserPersistance();
            ISecurity security = securityFactory.createSecurity(iUserPersistence);
            IValidate validate = validateFactory.createValidations();
            if(validate.isPasswordValid(user) && validate.isUserNameValid(user)) {
                if (security.authenticateUser(user).equals(ISecurity.RESULT.AUTHORIZED)) {
                    user.loadUserAttributes(iUserPersistence);
                    System.out.println(user.isAdmin(user.getDesignation()));
                    if (user.isAdmin(user.getDesignation())) {
                        session.setAttribute("admin", user.getUserID());
                        return new ModelAndView("redirect:/admin");
                    } else {
                        session.setAttribute("user", user.getUserID());
                        return new ModelAndView("redirect:/home");
                    }
                }
                else
                {
                    return new ModelAndView("invalidUsernameandPassword");
                }
            }
            return new ModelAndView("invalidUsernameandPassword");
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
