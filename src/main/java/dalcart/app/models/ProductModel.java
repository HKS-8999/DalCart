package dalcart.app.models;

import dalcart.app.Repository.IProductPersistence;
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


    public ArrayList<IProductModel> getProducts(IProductPersistence productDB)
    {
//        productDB = new ProductDB();
        ArrayList<IProductModel> productDetail;
        productDetail = productDB.getProductDetails();
        return productDetail;
    }

    public ArrayList<IProductModel> getProductsToDisplay(String searchWord, IProductPersistence productDB)
    {
        ArrayList<IProductModel> productDetail;
        productDetail = productDB.getProductDetailsForDisplay(searchWord);
        return productDetail;
    }

    public IProductPersistence.StorageResult addProductToCart(Map<String,String> parameters, IProductPersistence productDB, Integer userId)
    {
        IProductPersistence.StorageResult result;
        result = productDB.addProductToCart(parameters, userId);
        return result;
    }

    public IProductModel getProductById(Integer productId, IProductPersistence productDB)
    {
        IProductModel iProduct;
        iProduct = productDB.getProductById(productId);
        return iProduct;
    }

    public IProductPersistence.StorageResult saveProduct(IProductModel product, IProductPersistence productDB)
    {
        IProductPersistence.StorageResult result;
        result = productDB.saveProduct(product);
        return result;
    }

    public IProductPersistence.StorageResult updateProduct(Integer productId, Integer productQuantity, Boolean productState, IProductPersistence productDB)
    {
        IProductPersistence.StorageResult result;
        result = productDB.updateProduct(productId, productQuantity, productState);
        return result;
    }

    public Integer getLastProductId(IProductPersistence productDB)
    {
        Integer id;
        id = productDB.getLastProductId();
        return id;
    }

    public Integer getProductQuantity(IProductPersistence productDB, Integer productQuantity)
    {
        return productDB.getProductQuantity(productQuantity);
    }

}
