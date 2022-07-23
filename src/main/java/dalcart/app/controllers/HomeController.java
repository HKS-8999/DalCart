package dalcart.app.controllers;

import dalcart.app.items.HeaderSetter;
import dalcart.app.models.IProductModel;
import dalcart.app.models.ProductModel;
import dalcart.app.models.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class HomeController {
    IProductModel productModel = new ProductModel();

    @GetMapping("/home")
    public ModelAndView listgetproducts(ModelAndView model, @RequestParam(name = "search", required = false) String keyword, HttpSession session, SessionService sessionService) throws IOException {
        if (sessionService.isUserInSession(session) == false && sessionService.isSessionValid(session) == false) {
            ModelAndView modelAndView = new ModelAndView("redirect:/login");
            return modelAndView;
        }
        ArrayList<IProductModel> lstprodcts = productModel.getProductsToDisplay(keyword);
        model.addObject("listproducts", lstprodcts);
        String message = HeaderSetter.messageToDisplay();
        model.addObject("header", message);
        model.setViewName("home");
        return model;
    }

    @PostMapping("/home")
    public ModelAndView addProductIntoCart(@RequestParam Map<String, String> allParams, ModelAndView model) {
        try {
            productModel.addProductToCart(allParams);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return model;
    }
}
