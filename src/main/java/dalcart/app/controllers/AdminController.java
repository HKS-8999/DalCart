package dalcart.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import dalcart.app.models.UserModel;

@Controller
@RequestMapping(value = {"/admin"})
@Component
public class AdminController {

    @Autowired
    UserModel userModel;

    @GetMapping(value = {""})
    public ModelAndView index(@CookieValue(name = "userkey") String userKey) {
        //check if user key is valid else rediret to login page
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
//        if(IAdminService.isUserAdmin(userKey)){
//            User user = IUserService.getUserByKey(userKey);
//            modelAndView.getModel().put("username", "Tarash");
//        }
        //System.out.println(userModel.getData() + "******************************************"
        return modelAndView;
    }

}