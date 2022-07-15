package dalcart.app.models;

import dalcart.app.Repository.ProductDB;
import java.util.ArrayList;
import java.util.Map;

public class ProductModel implements IProductModel
{
    private String productName;
    private Integer productId;
    private String productDescription;
    private Integer productPrice;
    private Integer productQuantity;
    private Boolean enabled;
    private String productImage;
//    public Product product;
    ProductDB productDB = new ProductDB();

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
    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    @Override
    public Boolean getEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(Boolean productState) {
        this.enabled = productState;
    }

    @Override
    public String getProductImage() {
        return productImage;
    }

    @Override
    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }


    public ArrayList getProducts()
    {
//        productDB = new ProductDB();
        ArrayList<IProductModel> productDetail;
        productDetail = productDB.getProductDetails();
        return productDetail;
    }

    public ArrayList getProductsToDisplay(String searchWord)
    {
        ArrayList<IProductModel> productDetail;
        productDetail = productDB.getProductDetailsForDisplay(searchWord);
        return productDetail;
    }

    public void addProductToCart(Map<String,String> parameters)
    {
        productDB.addProductToCart(parameters);
    }

    public IProductModel getProductById(Integer productId)
    {
        IProductModel iproduct;
        iproduct = productDB.getProductById(productId);
        return iproduct;
    }

    public void saveProduct(IProductModel product)
    {
        productDB.saveProduct(product);
    }

    public void updateProduct(Integer productId, Integer productQuantity, Boolean productState)
    {
        productDB.updateProduct(productId, productQuantity, productState);
    }

    public Integer getLastProductId()
    {
        Integer id;
        id = productDB.getLastProductId();
        return id;
    }

}
