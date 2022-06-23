package dalcart.app.controllers;

import org.springframework.stereotype.Controller;
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

    @GetMapping(value = {""})
    public ModelAndView index() {
        UserModel userModel = new UserModel();
        System.out.println(userModel.getData() + "******************************************");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        return modelAndView;
    }

}