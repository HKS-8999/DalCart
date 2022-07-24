package dalcart.app.controllers;

import dalcart.app.models.SecurityService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
@Controller
@Component

public class ThankYouController {
    @GetMapping(value = {"/thankyou"})
    public ModelAndView thankyoupage (ModelAndView model, @RequestParam(name = "search", required = false) String keyword, HttpSession session) throws IOException
    {
        if (SecurityService.isSessionValid(session) == false) {
            ModelAndView modelAndView = new ModelAndView("redirect:/login");
            return modelAndView;
        }

        model.setViewName("thankyou");
        return model;
    }
}
