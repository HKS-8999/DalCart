package dalcart.app.controllers;

import dalcart.app.items.HeaderSetter;
import dalcart.app.models.IProductModel;
import dalcart.app.models.SecurityService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@Controller
public class PageNotFoundController {
    @GetMapping("/page_not_found")
    public ModelAndView pageNotFound (ModelAndView model, @RequestParam(name = "search", required = false) String keyword, HttpSession session) throws IOException {
        model.setViewName("404");
        System.out.println("Page Not Found");
        return model;
    }

}
