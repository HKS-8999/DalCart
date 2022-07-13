package dalcart.app.items;

import dalcart.app.models.IProductModel;

public class Product implements IProduct
{
    private String productName;
    private Integer productId;
    private String productDescription;
    private Integer productPrice;
    private Integer productQuantity;
    private Boolean productState;
    private String productImage;


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public boolean getProductState() {
        return productState;
    }

    public void setProductState(boolean enabled) {
        this.productState = enabled;
    }

    public void setProductImage(String productPictureUrl)
    {
        this.productImage = productPictureUrl;
    }
    public String getProductImage()
    {
        return productImage;
    }
}
