package dalcart.app.controllers;

import dalcart.app.models.Product;
import dalcart.app.models.User;
import dalcart.app.service.IUserService;
import dalcart.app.service.ProductService;
import dalcart.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class HomeController
{
    @Autowired
    ProductService productService;

    @GetMapping("/home")
    public ModelAndView listgetproducts (ModelAndView model) throws IOException
    {
//        ModelAndView model = new ModelAndView();
//        lstprodcts = productService.getProducts();
        ArrayList<Product> lstprodcts=productService.getProducts();
        model.addObject("listproducts",lstprodcts);
        model.setViewName("home");

        return model;
    }

    @PostMapping("/home")
    public String submitForm(@ModelAttribute Product product){
        try
        {
            productService.addToCart(product);
//            userservice.createNewUser(user);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return "Item added to cart...";
    }

}
