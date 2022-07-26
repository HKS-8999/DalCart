package dalcart.app.controllers;

import dalcart.app.Factories.*;
import dalcart.app.Repository.IProductPersistence;
import dalcart.app.Repository.IUserPersistence;
import dalcart.app.items.HeaderSetter;
import dalcart.app.models.IProductModel;
import dalcart.app.models.IUser;
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
            ModelAndView modelAndView = new ModelAndView("redirect:/logout");
            return modelAndView;
        }

        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        return modelAndView;
    }

    @GetMapping("/home")
    public ModelAndView viewProducts (ModelAndView model, @RequestParam(name = "search", required = false) String keyword, HttpSession session, SessionService sessionService) throws IOException
    {
        if (sessionService.isUserInSession(session) == false || sessionService.isSessionValid(session) == false)
        {
            ModelAndView modelAndView = new ModelAndView("redirect:/logout");
            return modelAndView;
        }

            ArrayList<IProductModel> listOfProducts = productModel.getProductsToDisplay(keyword, productDB);
            model.addObject("listproducts", listOfProducts);
            String message = HeaderSetter.messageToDisplay();
            model.addObject("header", message);
            model.setViewName("home");
            return model;
    }

    @PostMapping("/addToCart")
    public ModelAndView addProductIntoCart(@RequestParam Map<String,String> allParams, ModelAndView model, HttpSession session, SessionService sessionService)
    {
//        if (sessionService.isUserInSession(session) == false || sessionService.isSessionValid(session) == false)
//        {
//            ModelAndView modelAndView = new ModelAndView("redirect:/logout");
//            return modelAndView;
//        }
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
