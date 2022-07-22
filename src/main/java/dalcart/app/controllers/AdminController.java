package dalcart.app.controllers;

import dalcart.app.Repository.ConnectionManager;
import dalcart.app.models.IProductModel;
import dalcart.app.models.ISecurity;
import dalcart.app.models.ProductModel;
//import mocks.MockProduct;

import dalcart.app.models.SecurityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
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

    @GetMapping(value = {""})
    public ModelAndView index(HttpSession session) {
        //check if user key is valid else rediret to login page
        //|| ISecurity.isUserRoleAdmin() == false
        if(SecurityService.isSessionValid(session) == false ){
            //ModelAndView modelAndView =  new ModelAndView("redirect:/login");
            //modelAndView.addObject("modelAttribute" , modelAndView);
            //return modelAndView;
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        Map<Integer, String> listOfProducts = new HashMap<Integer,String>();
        List<IProductModel> mockProducts = new ArrayList<>();

//        String keyword = null;
        ArrayList<IProductModel> products = productModel.getProducts();
        if(products != null) {
            modelAndView.addObject("products", products);
        }
        return modelAndView;
    }


    @PostMapping(value = {"/submit_product_data"})
    @ResponseBody
    public String updateProductData(@RequestParam Map<String,String> allParams) throws SQLException {
        IProductModel productModel = new ProductModel();
        ConnectionManager connectionManager = ConnectionManager.getInstance();
        connectionManager.begin();
        allParams.forEach((keyName,value) -> {
            IProductModel product = productModel.getProductById(Integer.parseInt(keyName.split("-")[2]));
            if(keyName.contains("product-inventory")){
                System.out.println("Updating Product Quantity By: " + value);
                product.setProductQuantity(product.getProductQuantity() + Integer.parseInt(value));
            }else{
                product.setEnabled(value.equals("on"));
            }
            product.updateProduct(product.getProductId(),product.getProductQuantity(),product.getEnabled());
        });
        connectionManager.commit();
        return "success";
    }

    @PostMapping(value = {"/submit_product_creation_data"})
    @ResponseBody
    public String updateProductCteationData(@RequestParam Map<String,String> allParams) throws SQLException {
        IProductModel productModel = new ProductModel();
        ConnectionManager connectionManager = ConnectionManager.getInstance();
        connectionManager.begin();
//        productModel.setProductName();
//        productModel.setProductDescription();
//        productModel.setProductQuantity();
//        productModel.setProductPrice();
//        productModel.setProductImage();
//        productModel.setEnabled();
        connectionManager.commit();
        return "success";
    }


}