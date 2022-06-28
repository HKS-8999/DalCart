package dalcart.app.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dalcart.app.models.IProduct;
import mocks.MockProduct;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import dalcart.app.models.UserModel;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = {"/admin"})
@Component
public class AdminController {

    @Autowired
    UserModel userModel;

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
//        if(IAdminService.isUserAdmin(userKey)){
//            User user = IUserService.getUserByKey(userKey);
//            modelAndView.getModel().put("username", "Tarash");
//        }
        //System.out.println(userModel.getData() + "******************************************"
        Map<Integer, String> listOfProducts = new HashMap<Integer,String>();
        List<IProduct> mockProducts = new ArrayList<>();

        IProduct p1 = new MockProduct(1,"Tshirts",25,true);
        IProduct p2 = new MockProduct(2,"Notebooks",50,false);

        mockProducts.add(p1);
        mockProducts.add(p2);
        //listOfProducts.put(1,"iphone,10,true");
//        String json = "";
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            json = objectMapper.writeValueAsString(listOfProducts);
//            System.out.println(json);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        modelAndView.addObject("products",mockProducts);
        return modelAndView;
    }

//    @GetMapping(value = {""})
//    public ModelAndView index() {
//
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("index");
//        return modelAndView;
//    }

    @PostMapping(value = {"/submit_product_data"})
    @ResponseBody
    public String updateProductData(@RequestParam Map<String,String> allParams){
        allParams.forEach((k,v) -> System.out.println("Key = "
                + k + ", Value = " + v));
        return "success";
    }


}