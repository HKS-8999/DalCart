package dalcart.app.controllers;

import dalcart.app.Factories.IProductModelFactory;
import dalcart.app.Factories.IProductPersistenceFactory;
import dalcart.app.Factories.ProductModelFactory;
import dalcart.app.Factories.ProductPersistenceFactory;
import dalcart.app.Repository.IProductPersistence;
import dalcart.app.models.IProductModel;

import dalcart.app.Repository.ConnectionManager;

import dalcart.app.models.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
//@RequestMapping(value = {"/admin"})
@Component
public class AdminController
{
    IProductModelFactory productModelFactory = new ProductModelFactory();
    IProductPersistenceFactory productPersistenceFactory = new ProductPersistenceFactory();

    IProductPersistence productDB = productPersistenceFactory.createIProductPersistence();
    IProductModel productModel = productModelFactory.createProductModel();

<<<<<<< HEAD
    @GetMapping(value = {""})
    public ModelAndView index(HttpSession session, SessionService sessionService) {
        //check if user key is valid else rediret to login page


        if (sessionService.isAdminInSession(session) == false || sessionService.isSessionValid(session) == false) {
            ModelAndView modelAndView = new ModelAndView("redirect:/login");
=======
    @GetMapping(value = {"/admin"})
    public ModelAndView index(HttpSession session, SessionService sessionService, ModelAndView model)
    {
        if (sessionService.isAdminInSession(session) == false || sessionService.isSessionValid(session) == false)
        {
            ModelAndView modelAndView = new ModelAndView("redirect:/logout");
>>>>>>> bef197c67d096e218f542be865ec4058b5b49408
            return modelAndView;
        }


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        Map<Integer, String> listOfProducts = new HashMap<Integer,String>();
        List<IProductModel> mockProducts = new ArrayList<>();

        ArrayList<IProductModel> products = productModel.getProducts(productDB);
        if(products != null) {
            modelAndView.addObject("products", products);
        }
        return modelAndView;
    }

    @PostMapping(value = {"/submit_product_data"})
    @ResponseBody
    public String updateProductData(@RequestParam Map<String,String> allParams) throws SQLException {
        System.out.println("product Update Request Received");
        IProductModel productModel = productModelFactory.createProductModel();
        ConnectionManager connectionManager = ConnectionManager.getInstance();
        connectionManager.begin();
        allParams.forEach((keyName,value) -> {
            IProductModel product = productModel.getProductById(Integer.parseInt(keyName.split("-")[2]),productDB);
            product.setEnabled(false);
            if(keyName.contains("product-inventory")){
                System.out.println("Updating Product Quantity By: " + value);
                product.setProductQuantity(product.getProductQuantity() + Integer.parseInt(value));
            }else{
                product.setEnabled(value.equals("on"));
            }
            product.updateProduct(product.getProductId(),product.getProductQuantity(),product.getEnabled(), productDB);
        });
        connectionManager.commit();
        return "success";
    }

    @PostMapping(value = {"/submit_product_creation_data"})
    @ResponseBody
    public String updateProductCteationData(@RequestParam Map<String,String> allParams) throws SQLException
    {
        System.out.println("product Create Request Received");
        IProductModel productModel = productModelFactory.createProductModel();
        ConnectionManager connectionManager = ConnectionManager.getInstance();
        connectionManager.begin();
        productModel.setProductName(allParams.get("product-name"));
        productModel.setProductDescription(allParams.get("product-description"));
        productModel.setProductQuantity(1);
        productModel.setProductPrice(Integer.parseInt(allParams.get("product-price")));
        productModel.setProductImage(allParams.get("product-image"));
        productModel.setEnabled((allParams.get("product-enabled") != null));
        productModel.saveProduct(productModel,productDB);
        connectionManager.commit();
        return "success";
    }
}