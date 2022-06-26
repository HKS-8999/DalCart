package dalcart.app.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import mocks.MockProduct;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import dalcart.app.models.UserModel;

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
    public ModelAndView index(@CookieValue(name = "userkey") String userKey) {
        //check if user key is valid else rediret to login page
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
//        if(IAdminService.isUserAdmin(userKey)){
//            User user = IUserService.getUserByKey(userKey);
//            modelAndView.getModel().put("username", "Tarash");
//        }
        //System.out.println(userModel.getData() + "******************************************"
        Map<Integer, String> listOfProducts = new HashMap<Integer,String>();
        List<MockProduct> mockProducts = new ArrayList<>();
        mockProducts.add(new MockProduct("iphone",10,false));//id,productname,inventory,enabled
        mockProducts.add(new MockProduct("micromax",20,true));
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

}