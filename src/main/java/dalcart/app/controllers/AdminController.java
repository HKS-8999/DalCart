package dalcart.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import dalcart.app.models.UserModel;

import java.util.Map;

@Controller
@RequestMapping(value = {"/admin"})
@Component
public class AdminController {

    @Autowired
    UserModel userModel;

    @GetMapping(value = {""})
    public ModelAndView index(@CookieValue(name = "userkey") String userKey) {
        System.out.println("**********************" + userKey);
        //System.out.println(userModel.getData() + "******************************************");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModel().put("username", "Tarash");
        modelAndView.setViewName("admin");
        return modelAndView;
    }

}