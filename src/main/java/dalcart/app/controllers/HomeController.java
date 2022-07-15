package dalcart.app.controllers;

import dalcart.app.items.HeaderSetter;
import dalcart.app.models.IProductModel;
import dalcart.app.models.ProductModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class HomeController
{
    IProductModel productModel = new ProductModel();
    @GetMapping("/home")
    public ModelAndView listgetproducts (ModelAndView model, @RequestParam(name = "search", required = false) String keyword, @CookieValue(name = "userkey", required = false) String userKey) throws IOException
    {
//        if(userKey == null || userKey.equals(""))
//        {
//            ModelAndView modelAndView =  new ModelAndView("redirect:/login");
//            modelAndView.addObject("modelAttribute" , modelAndView);
//            return modelAndView;
//        }

        ArrayList<IProductModel> lstprodcts = productModel.getProductsToDisplay(keyword);
        model.addObject("listproducts",lstprodcts);
        String message = HeaderSetter.messageToDisplay();
        model.addObject("header", message);


//        ClassPathResource imgFile = new ClassPathResource("/WEB-INF/images/1.jpg");
//        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
//        StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
//        model.addObject("imageFolder", StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream()));


        model.setViewName("home");
        return model;
    }

    @PostMapping("/home")
    public ModelAndView addProductIntoCart(@RequestParam Map<String,String> allParams, ModelAndView model, @CookieValue(name = "userkey", required = false) String userKey){

//        if(userKey == null || userKey.equals(""))
//        {
//            ModelAndView modelAndView =  new ModelAndView("redirect:/login");
//            modelAndView.addObject("modelAttribute" , modelAndView);
//            return modelAndView;
//        }

        try
        {
            productModel.addProductToCart(allParams);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return model;
    }

}
