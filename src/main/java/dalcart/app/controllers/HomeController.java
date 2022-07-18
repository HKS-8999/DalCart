package dalcart.app.controllers;

import dalcart.app.Repository.ProductDB;
import dalcart.app.items.HeaderSetter;
import dalcart.app.models.IProductDB;
import dalcart.app.models.IProductModel;
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
    IProductModel productModel = new ProductModel();
    IProductDB productDB = new ProductDB();
    @GetMapping("/home")
    public ModelAndView listgetproducts (ModelAndView model, @RequestParam(name = "search", required = false) String keyword, @CookieValue(name = "userkey", required = false) String userKey) throws IOException
    {
        ArrayList<IProductModel> lstprodcts = productModel.getProductsToDisplay(keyword, productDB);
        model.addObject("listproducts",lstprodcts);
        String message = HeaderSetter.messageToDisplay();
        model.addObject("header", message);

        model.setViewName("home");
        return model;
    }

    @PostMapping("/home")
    public ModelAndView addProductIntoCart(@RequestParam Map<String,String> allParams, ModelAndView model, @CookieValue(name = "userkey", required = false) String userKey)
    {
        try
        {
            productModel.addProductToCart(allParams, productDB);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return model;
    }

}
