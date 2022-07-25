package dalcart.app.models;

public class ProductModelMock
{
    private String productName;
    private Integer productId;
    private String productDescription;
    private Integer productPrice;
    private Integer productQuantity;
    private Boolean enabled;
    private String productImage;

    public ProductModelMock()
    {
        this.productName = "Book";
        this.productId = 1;
        this.productDescription = "A4 sized books are available";
        this.productPrice = 7;
        this.productQuantity = 20;
        this.enabled = true;
        this.productImage = "images/book.jpg";
    }

    public ProductModelMock(String productName, int productId, String productDescription, Integer productPrice,
                            Integer productQuantity, Boolean enabled, String productImage)
    {
        this.productName = productName;
        this.productId = productId;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.enabled = enabled;
        this.productImage = productImage;
    }

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public Integer getProductId()
    {
        return productId;
    }

    public void setProductId(Integer productId)
    {
        this.productId = productId;
    }

    public String getProductDescription()
    {
        return productDescription;
    }

    public void setProductDescription(String productDescription)
    {
        this.productDescription = productDescription;
    }

    public Integer getProductPrice()
    {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice)
    {
        this.productPrice = productPrice;
    }

    public Integer getProductQuantity()
    {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity)
    {
        this.productQuantity = productQuantity;
    }

    public Boolean getEnabled()
    {
        return enabled;
    }

    public void setEnabled(Boolean enabled)
    {
        this.enabled = enabled;
    }

    public String getProductImage()
    {
        return productImage;
    }

    public void setProductImage(String productImage)
    {
        this.productImage = productImage;
    }
}
