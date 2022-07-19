package dalcart.app.controllers;

import dalcart.app.Factories.IProductModelFactory;
import dalcart.app.Factories.IProductPersistenceFactory;
import dalcart.app.Factories.ProductModelFactory;
import dalcart.app.Factories.ProductPersistenceFactory;
import dalcart.app.Repository.IProductPersistence;
import dalcart.app.models.IProductModel;

import dalcart.app.models.SecurityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = {"/admin"})
@Component
public class AdminController
{
    IProductModelFactory productModelFactory = new ProductModelFactory();
    IProductPersistenceFactory productPersistenceFactory = new ProductPersistenceFactory();

    IProductPersistence productDB = productPersistenceFactory.createIProductPersistence();
    IProductModel productModel = productModelFactory.createProductModel();

    @GetMapping(value = {""})
    public ModelAndView index(HttpSession session) {
        //check if user key is valid else rediret to login page

        if(SecurityService.isSessionValid(session) == false){
            ModelAndView modelAndView =  new ModelAndView("redirect:/login");
            modelAndView.addObject("modelAttribute" , modelAndView);
            return modelAndView;
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        Map<Integer, String> listOfProducts = new HashMap<Integer,String>();
        List<IProductModel> mockProducts = new ArrayList<>();

//        String keyword = null;
        ArrayList<IProductModel> products = productModel.getProducts(productDB);
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