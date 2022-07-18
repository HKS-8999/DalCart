package dalcart.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class InvalidUsernameAndPasswordController {


    @PostMapping("/invalidUsernameAndPassword")
    public ModelAndView backToLogin(){
        System.out.println("dsa");
        return new ModelAndView("login");
    }

}
