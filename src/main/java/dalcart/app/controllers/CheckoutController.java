package dalcart.app.controllers;

import dalcart.app.Factories.IProductModelFactory;
import dalcart.app.Factories.IProductPersistenceFactory;
import dalcart.app.Factories.ProductModelFactory;
import dalcart.app.Factories.ProductPersistenceFactory;
import dalcart.app.Repository.IProductPersistence;
import dalcart.app.Repository.OrderDB;
import dalcart.app.Repository.OrderProducts;
import dalcart.app.models.IOrderModel;
import dalcart.app.models.IProductModel;
import dalcart.app.models.SecurityService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CheckoutController
{
    IProductModelFactory productModelFactory = new ProductModelFactory();
    IProductModel productModel = productModelFactory.createProductModel();
    IProductPersistenceFactory productPersistenceFactory = new ProductPersistenceFactory();
    IProductPersistence productDB = productPersistenceFactory.createIProductPersistence();
    OrderProducts o = new OrderProducts();
    OrderDB db = new OrderDB();
    @GetMapping("/cart")
    public ModelAndView listgetproducts (ModelAndView model, HttpSession session) throws IOException
    {
        if (SecurityService.isSessionValid(session) == false)
        {
            ModelAndView modelAndView = new ModelAndView("redirect:/login");
            return modelAndView;
        }

        HashMap<Integer, Integer> productIds = new HashMap<>();
        Integer userId = (Integer) session.getAttribute("user");
        productIds = o.getProductsOfUser(userId);
        ArrayList<IProductModel> allProducts = new ArrayList<>();
        for(Map.Entry<Integer, Integer> val : productIds.entrySet())
        {
            Integer id = val.getKey();
            allProducts.add(productModel.getProductById(id, productDB));
        }
        Integer total = productModel.getTotalOfProducts(productDB,productIds);
        model.addObject("products",allProducts);
        model.addObject("quantity",productIds);
        model.addObject("total", total);
        model.setViewName("checkout");
        return model;
    }

    @PostMapping("/increaseQuantityOfProduct")
    public ModelAndView increaseProductQuantity(@RequestParam Map<String,String> allParams, ModelAndView model, HttpSession session)
    {
        if(SecurityService.isSessionValid(session) == false)
        {
            ModelAndView modelAndView = new ModelAndView("redirect:/login");
            return modelAndView;
        }
        Integer userId = (Integer) session.getAttribute("user");
        IOrderModel order = db.findOrderInCartByUserId(userId);
        Integer orderId =order.getOrderId();
        Boolean b = o.increaseProductQuantity(Integer.valueOf(allParams.get("id")),Integer.valueOf(allParams.get("quantity")), orderId);
        if(b == false)
        {
            model.addObject("message","Product is not available in this quantity.");
        }
        return model;
    }

    @PostMapping("/decreaseQuantityOfProduct")
    public ModelAndView decreaseProductQuantity(@RequestParam Map<String,String> allParams, ModelAndView model, HttpSession session)
    {
        if(SecurityService.isSessionValid(session) == false)
        {
            ModelAndView modelAndView = new ModelAndView("redirect:/login");
            return modelAndView;
        }
        Integer userId = (Integer) session.getAttribute("user");
        IOrderModel order = db.findOrderInCartByUserId(userId);
        Integer orderId =order.getOrderId();
        Boolean b = o.decreaseProductQuantity(Integer.valueOf(allParams.get("id")),Integer.valueOf(allParams.get("quantity")), orderId);
        if(b == false)
        {
            model.addObject("message","Product quantity is 1.");
        }
        return model;
    }

    @PostMapping("/removeFromCart")
    public ModelAndView removeFromCart(@RequestParam Map<String,String> allParams, ModelAndView model, HttpSession session)
    {
        if (SecurityService.isSessionValid(session) == false) {
            ModelAndView modelAndView = new ModelAndView("redirect:/login");
            return modelAndView;
        }
        try
        {
            Integer userId = (Integer) session.getAttribute("user");
            IOrderModel order = db.findOrderInCartByUserId(userId);
            Integer orderId =order.getOrderId();
            db.removeProductFromCart(orderId,Integer.valueOf(allParams.get("id")));
//            productModel.addProductToCart(allParams,  productDB, userId);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return model;
    }

}
