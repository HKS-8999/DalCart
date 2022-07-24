package dalcart.app.controllers;

import dalcart.app.Factories.*;
import dalcart.app.Repository.IProductPersistence;
import dalcart.app.Repository.IUserPersistence;
import dalcart.app.items.HeaderSetter;
import dalcart.app.models.IProductModel;
import dalcart.app.models.IUser;
import dalcart.app.models.SecurityService;
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
    public ModelAndView homepage (HttpSession session) throws IOException
    {
        if (SecurityService.isSessionValid(session) == false) {
            ModelAndView modelAndView = new ModelAndView("redirect:/login");
            return modelAndView;
        }

        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        return modelAndView;
    }

    @GetMapping("/home")
    public ModelAndView viewProducts (ModelAndView model, @RequestParam(name = "search", required = false) String keyword, HttpSession session) throws IOException
    {
        if (SecurityService.isSessionValid(session) == false)
        {
            ModelAndView modelAndView = new ModelAndView("redirect:/login");
            return modelAndView;
        }

        ArrayList<IProductModel> listOfProducts = productModel.getProductsToDisplay(keyword,productDB);
        model.addObject("listproducts",listOfProducts);
        String message = HeaderSetter.messageToDisplay();
        model.addObject("header", message);

        if(listOfProducts.size() == 0)
        {
            model.addObject("nothingToShow","No Products to Display.");
        }

        model.setViewName("home");
        return model;
    }

    @PostMapping("/addToCart")
    public ModelAndView addProductIntoCart(@RequestParam Map<String,String> allParams, ModelAndView model, HttpSession session)
    {
        if (SecurityService.isSessionValid(session) == false) {
            ModelAndView modelAndView = new ModelAndView("redirect:/login");
            return modelAndView;
        }
        try
        {
            Integer userId = (Integer) session.getAttribute("user");
            IUserPersistanceFactory iUserPersistanceFactory = new UserPersistanceFactory();
            IUserPersistence userPersistence = iUserPersistanceFactory.createIUserPersistance();
            IUser user = userPersistence.loadUserAttributesByUserId(userId);
            Integer productId = Integer.valueOf(allParams.get("id"));
            IProductModel[] products = new IProductModel[1];
            products[0] = productModel.getProductById(productId,productDB);
            OrderController orderController = new OrderController();
            orderController.addToOrder(user, products);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return model;
    }

}
