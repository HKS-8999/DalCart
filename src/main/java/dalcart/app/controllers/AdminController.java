package dalcart.app.controllers;

import dalcart.app.items.IProduct;
import dalcart.app.models.ProductModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import dalcart.app.items.UserModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = {"/admin"})
@Component
public class AdminController {

    @Autowired
    UserModel userModel;

    @Autowired
    ProductModel productService;

    @GetMapping(value = {""})
    public ModelAndView index(@CookieValue(name = "userkey", required = false) String userKey) {

        //check if user key is valid else rediret to login page
        if(userKey == null){
            ModelAndView modelAndView =  new ModelAndView("redirect:/login");
            modelAndView.addObject("modelAttribute" , modelAndView);
            return modelAndView;
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        Map<Integer, String> listOfProducts = new HashMap<Integer,String>();
        List<IProduct> mockProducts = new ArrayList<>();

        ArrayList<IProduct> products =productService.getProducts();
        if(products != null) {
            modelAndView.addObject("products", products);
        }
        return modelAndView;
    }


    @PostMapping(value = {"/submit_product_data"})
    @ResponseBody
    public String updateProductData(@RequestParam Map<String,String> allParams){
        allParams.forEach((k,v) -> System.out.println("Key = "
                + k + ", Value = " + v));
        return "success";
    }


}