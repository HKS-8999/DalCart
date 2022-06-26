package dalcart.app.controllers;

import dalcart.app.models.Product;
import dalcart.app.models.User;
import dalcart.app.service.IUserService;
import dalcart.app.service.ProductService;
import dalcart.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Map;

@Controller
public class HomeController
{
    @Autowired
    ProductService productService;

    @GetMapping("/home")
    public ArrayList viewProducts(Model model)
    {
        return productService.getProducts();
    }


}
