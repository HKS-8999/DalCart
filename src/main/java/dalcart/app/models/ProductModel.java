package dalcart.app.models;

import dalcart.app.Repository.ProductDB;
import dalcart.app.controllers.OrderController;
import dalcart.app.items.*;
import dalcart.app.utils.CommonUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class ProductModel implements IProductModel
{
    private String productName;
    private Integer productId;
    private String productDescription;
    private Integer productPrice;
    private Integer productQuantity;
    private Boolean productState;
    private String productImage;
    public Product product;
    ProductDB productDB = new ProductDB();
    IProductModel.productState state;


    @Override
    public String getProductName() {
        return productName;
    }

    @Override
    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public Integer getProductId() {
        return productId;
    }

    @Override
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public String getProductDescription() {
        return productDescription;
    }

    @Override
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    @Override
    public Integer getProductPrice() {
        return productPrice;
    }

    @Override
    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public Integer getProductQuantity() {
        return productQuantity;
    }

    @Override
    public void setProductQuantity() {
        this.productQuantity = productQuantity;
    }

    @Override
    public Boolean getProductState() {
        return productState;
    }

    @Override
    public void setProductState(Boolean productState) {
        this.productState = productState;
    }

    @Override
    public String getProductImage() {
        return productImage;
    }

    @Override
    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public ArrayList getProducts(String searchWord)
    {
//        productDB = new ProductDB();
        ArrayList<Product> productDetail;
        productDetail = productDB.getProductDetails(searchWord);
        return productDetail;
    }

    public void updateProduct(Integer productId, Integer productQuantity, Boolean productState)
    {
//        productDB = new ProductDB();
        productDB.updateProduct(productId, productQuantity, productState);
    }

    public void addProductToCart(Map<String,String> parameters) throws SQLException
    {
//        OrderController.addToOrder(user, products);
        productDB.addProductToCart(parameters);
    }

}
