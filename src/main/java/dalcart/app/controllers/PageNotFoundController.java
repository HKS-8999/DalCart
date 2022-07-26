package dalcart.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class PageNotFoundController {
    @GetMapping("/page_not_found")
    public ModelAndView pageNotFound(ModelAndView model) throws IOException {
        model.setViewName("404");
        System.out.println("Page Not Found");
        return model;
    }
}
