package dalcart.app.controllers;

import dalcart.app.models.Factories.IProductModelFactory;
import dalcart.app.models.Factories.IProductPersistenceFactory;
import dalcart.app.models.Factories.ProductModelFactory;
import dalcart.app.models.Factories.ProductPersistenceFactory;
import dalcart.app.models.Repository.ConnectionManager;
import dalcart.app.models.Repository.IProductPersistence;
import dalcart.app.models.IProductModel;
import dalcart.app.models.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class AdminController {
    IProductModelFactory productModelFactory = new ProductModelFactory();
    IProductPersistenceFactory productPersistenceFactory = new ProductPersistenceFactory();

    IProductPersistence productDB = productPersistenceFactory.createIProductPersistence();
    IProductModel productModel = productModelFactory.createProductModel();

    @GetMapping(value = {"/admin"})
    public ModelAndView index(HttpSession session, SessionService sessionService, ModelAndView model) {
        if (sessionService.isAdminInSession(session) == false || sessionService.isSessionValid(session) == false) {
            ModelAndView modelAndView = new ModelAndView("redirect:/logout");
            return modelAndView;
        }


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        ArrayList<IProductModel> products = productModel.getProducts(productDB);
        if (products != null) {
            modelAndView.addObject("products", products);
        }
        return modelAndView;
    }

    @PostMapping(value = {"/admin/submit_product_data"})
    @ResponseBody
    public String updateProductData(@RequestParam Map<String, String> allParams) throws SQLException {
        System.out.println("product Update Request Received");
        IProductModel productModel = productModelFactory.createProductModel();
        ConnectionManager connectionManager = ConnectionManager.getInstance();
        connectionManager.begin();
        allParams.forEach((keyName, value) -> {
            IProductModel product = productModel.getProductById(Integer.parseInt(keyName.split("-")[2]), productDB);
            product.setEnabled(false);
            if (keyName.contains("product-inventory")) {
                System.out.println("Updating Product Quantity By: " + value);
                product.setProductQuantity(product.getProductQuantity() + Integer.parseInt(value));
            } else {
                product.setEnabled(value.equals("on"));
            }
            product.updateProduct(product.getProductId(), product.getProductQuantity(), product.getEnabled(), productDB);
        });
        connectionManager.commit();
        return "success";
    }

    @PostMapping(value = {"/admin/submit_product_creation_data"})
    @ResponseBody
    public String updateProductCteationData(@RequestParam Map<String, String> allParams) throws SQLException {
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
        productModel.saveProduct(productModel, productDB);
        connectionManager.commit();
        return "success";
    }
}