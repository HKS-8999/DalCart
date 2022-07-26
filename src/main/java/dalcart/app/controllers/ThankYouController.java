package dalcart.app.controllers;

import dalcart.app.models.SessionService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@Component
public class ThankYouController {
    @GetMapping(value = {"/thankyou"})
    public ModelAndView thankyoupage(ModelAndView model, HttpSession session){
        if (SessionService.isSessionValid(session) == false) {
            ModelAndView modelAndView = new ModelAndView("redirect:/login");
            return modelAndView;
        }
        model.setViewName("thankyou");
        return model;
    }
}
