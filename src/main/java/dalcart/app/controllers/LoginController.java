package dalcart.app.controllers;

import dalcart.app.models.Factories.*;
import dalcart.app.models.*;
import dalcart.app.models.Repository.IUserPersistence;
import dalcart.app.models.SecurityChain.Security;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    IUserPersistanceFactory userPersistanceFactory;
    ISecurityFactory securityFactory;
    IValidateFactory validateFactory;

    @GetMapping("/login")
    public ModelAndView loginPage(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (SessionService.isSessionValid(session) == false) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("login");
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/home");
        }
    }

    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute User user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        userPersistanceFactory = new UserPersistanceFactory();
        securityFactory = new SecurityFactory();
        validateFactory = new ValidateFactory();
        IUserPersistence iUserPersistence = userPersistanceFactory.createIUserPersistance();
        ISecurePassword securePassword = securityFactory.createSecurePassword();
        IValidate validate = validateFactory.createValidations();
        IAuthenticate authentication = securityFactory.createSecurity(iUserPersistence, user);
        if (validate.isPasswordValid(user) && validate.isUserNameValid(user)) {
            securePassword.encrypt(user);
            if (authentication.authenticate(user).equals(Security.RESULT.AUTHORIZED)) {
                if (user.isAdmin(user.getDesignation())) {
                    session.setAttribute("admin", user.getUserID());
                    return new ModelAndView("redirect:/admin");
                } else {
                    session.setAttribute("user", user.getUserID());
                    return new ModelAndView("redirect:/home");
                }
            } else {
                return new ModelAndView("invalidUsernameandPassword");
            }
        }
        return new ModelAndView("invalidUsernameandPassword");
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return new ModelAndView("redirect:/login");
    }
}
