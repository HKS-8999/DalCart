package dalcart.app.controllers;

import dalcart.app.items.Product;
import dalcart.app.models.ProductModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class HomeController
{
    ProductModel productModel = new ProductModel();
    @GetMapping("/home")
    public ModelAndView listgetproducts (ModelAndView model, @CookieValue(name = "userkey", required = false) String userKey) throws IOException
    {

//        if(userKey == null || userKey.equals(""))
//        {
//            ModelAndView modelAndView =  new ModelAndView("redirect:/login");
//            modelAndView.addObject("modelAttribute" , modelAndView);
//            return modelAndView;
//        }

        ArrayList<Product> lstprodcts = productModel.getProducts();
        model.addObject("listproducts",lstprodcts);
        model.setViewName("home");

        return model;
    }

    @PostMapping("/home")
    public ModelAndView submitForm(@ModelAttribute Product product,@RequestParam Map<String,String> allParams, ModelAndView model, @CookieValue(name = "userkey", required = false) String userKey){

//        if(userKey == null || userKey.equals(""))
//        {
//            ModelAndView modelAndView =  new ModelAndView("redirect:/login");
//            modelAndView.addObject("modelAttribute" , modelAndView);
//            return modelAndView;
//        }

        try
        {
//            for()
//            allParams.forEach((k,v) -> System.out.println("Key = " + k + ", Value = " + v));
            productModel.addProductToCart(allParams);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return model;
    }

}
