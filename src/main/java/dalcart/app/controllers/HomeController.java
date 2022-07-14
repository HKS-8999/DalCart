package dalcart.app.controllers;

import dalcart.app.items.HeaderSetter;
import dalcart.app.items.IProduct;
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
    public ModelAndView listgetproducts (ModelAndView model,@RequestParam(name = "search", required = false) String keyword, @CookieValue(name = "userkey", required = false) String userKey) throws IOException
    {
//        if(userKey == null || userKey.equals(""))
//        {
//            ModelAndView modelAndView =  new ModelAndView("redirect:/login");
//            modelAndView.addObject("modelAttribute" , modelAndView);
//            return modelAndView;
//        }

        ArrayList<IProduct> lstprodcts = productModel.getProductsToDisplay(keyword);
        model.addObject("listproducts",lstprodcts);
        String message = HeaderSetter.messageToDisplay();
        model.addObject("header", message);
        model.setViewName("home");

        return model;
    }

    @PostMapping("/home")
    public ModelAndView displayProduct(@ModelAttribute Product product,@RequestParam Map<String,String> allParams, ModelAndView model, @CookieValue(name = "userkey", required = false) String userKey){

//        if(userKey == null || userKey.equals(""))
//        {
//            ModelAndView modelAndView =  new ModelAndView("redirect:/login");
//            modelAndView.addObject("modelAttribute" , modelAndView);
//            return modelAndView;
//        }

        try
        {
            productModel.addProductToCart(allParams);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return model;
    }

}
