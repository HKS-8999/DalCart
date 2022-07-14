package dalcart.app.controllers;

import dalcart.app.items.Product;
import dalcart.app.models.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class HomeController
{
    @Autowired
    ProductModel productService;

    @GetMapping("/home")
    public ModelAndView listgetproducts (ModelAndView model, @CookieValue(name = "userkey", required = false) String userKey, HttpServletRequest request) throws IOException
    {

        HttpSession session = request.getSession();

        if(session.getAttribute("user") == null){
            //redirect to login
        }

        if(userKey == null || userKey.equals(""))
        {
            ModelAndView modelAndView =  new ModelAndView("redirect:/login");
            modelAndView.addObject("modelAttribute" , modelAndView);
            return modelAndView;
        }

        ArrayList<Product> lstprodcts = productService.getProducts();
        model.addObject("listproducts",lstprodcts);
        model.setViewName("home");

        return model;
    }

    @PostMapping("/home")
    public ModelAndView submitForm(@ModelAttribute Product product,@RequestParam Map<String,String> allParams, ModelAndView model, @CookieValue(name = "userkey", required = false) String userKey){

        if(userKey == null || userKey.equals(""))
        {
            ModelAndView modelAndView =  new ModelAndView("redirect:/login");
            modelAndView.addObject("modelAttribute" , modelAndView);
            return modelAndView;
        }

        try
        {
//            for()
//            allParams.forEach((k,v) -> System.out.println("Key = " + k + ", Value = " + v));
            productService.addToCart(allParams);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return model;
    }

}
