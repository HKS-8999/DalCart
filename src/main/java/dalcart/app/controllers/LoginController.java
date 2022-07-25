package dalcart.app.controllers;

import dalcart.app.Factories.*;
import dalcart.app.Repository.IUserPersistence;
import dalcart.app.models.*;

import dalcart.app.models.Security.Security;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController
{
    IUserPersistanceFactory userPersistanceFactory;
    ISecurityFactory securityFactory;

    IValidateFactory validateFactory;

    @GetMapping("/login")
    public ModelAndView loginPage(HttpServletRequest request)
    {
        Logger logger = LogManager.getLogger(this.getClass());
        HttpSession session = request.getSession();
        if(SessionService.isSessionValid(session) == false)
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
    public ModelAndView login(@ModelAttribute User user, HttpServletRequest request)
    {
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();

        userPersistanceFactory = new UserPersistanceFactory();
        securityFactory = new SecurityFactory();
        validateFactory = new ValidateFactory();
        IUserPersistence iUserPersistence = userPersistanceFactory.createIUserPersistance();
        ISecurePassword securePassword = securityFactory.createSecurePassword();
        IValidate validate = validateFactory.createValidations();
        IAuthenticate authentication = securityFactory.createSecurity(iUserPersistence,user);

        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        if(validate.isPasswordValid(user) && validate.isUserNameValid(user))
        {
            securePassword.encrypt(user);
            if (authentication.authenticate(user).equals(Security.RESULT.AUTHORIZED))
            {
                if (user.isAdmin(user.getDesignation()))
                {
                    session.setAttribute("admin", user.getUserID());
                    return new ModelAndView("redirect:/admin");
                }
                else
                {
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

    @GetMapping("/logout")
    public String logout(HttpServletRequest request)
    {
        request.getSession().invalidate();
        return "redirect:/login";
    }
}
