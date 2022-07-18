package dalcart.app.controllers;

import dalcart.app.Repository.ProductDB;
import dalcart.app.models.IProductDB;
import dalcart.app.models.IProductModel;
import dalcart.app.models.ProductModel;
//import mocks.MockProduct;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = {"/admin"})
@Component
public class AdminController {

//    @Autowired
    ProductModel productModel = new ProductModel();
    IProductDB productDB = new ProductDB();

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