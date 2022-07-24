package dalcart.app.controllers;

import dalcart.app.Factories.IProductModelFactory;
import dalcart.app.Factories.IProductPersistenceFactory;
import dalcart.app.Factories.ProductModelFactory;
import dalcart.app.Factories.ProductPersistenceFactory;
import dalcart.app.Repository.IProductPersistence;
import dalcart.app.Repository.ProductDB;
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
public class HomeController
{
    IProductModelFactory productModelFactory = new ProductModelFactory();
    IProductModel productModel = productModelFactory.createProductModel();
    IProductPersistenceFactory productPersistenceFactory = new ProductPersistenceFactory();
    IProductPersistence productDB = productPersistenceFactory.createIProductPersistence();

    @GetMapping("")
    public ModelAndView homepage (ModelAndView model, @RequestParam(name = "search", required = false) String keyword, HttpSession session, SessionService sessionService) throws IOException
    {

        if (sessionService.isUserInSession(session) == false && sessionService.isSessionValid(session) == false) {
            ModelAndView modelAndView = new ModelAndView("redirect:/login");
            return modelAndView;
        }

        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        return modelAndView;
    }

    @GetMapping("/home")
    public ModelAndView viewProducts (ModelAndView model, @RequestParam(name = "search", required = false) String keyword, HttpSession session, SessionService sessionService) throws IOException
    {
        if (sessionService.isUserInSession(session) == false && sessionService.isSessionValid(session) == false) {
            ModelAndView modelAndView = new ModelAndView("redirect:/login");
            return modelAndView;
        }

        ArrayList<IProductModel> lstprodcts = productModel.getProductsToDisplay(keyword,productDB);
        model.addObject("listproducts",lstprodcts);
        String message = HeaderSetter.messageToDisplay();
        model.addObject("header", message);
        model.setViewName("home");
        return model;
    }

    @PostMapping("/home")
    public ModelAndView addProductIntoCart(@RequestParam Map<String, String> allParams, ModelAndView model) {
        try {
          //  productModel.addProductToCart(allParams);
        } catch (Exception e) {

        }
        return model;
    }
    @PostMapping("/addToCart")
    public ModelAndView addProductIntoCart(@RequestParam Map<String,String> allParams, ModelAndView model, HttpSession session)
    {
        if (SessionService.isSessionValid(session) == false) {
            ModelAndView modelAndView = new ModelAndView("redirect:/login");
            return modelAndView;
        }
        try
        {
            Integer userId = (Integer) session.getAttribute("user");
            productModel.addProductToCart(allParams,  productDB, userId);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return model;
    }
}
