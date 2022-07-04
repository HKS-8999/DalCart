package dalcart.app.models;

public interface IProduct
{
    public String getProductName();

    public void setProductName(String productName);

    public String getProductDescription();

    public void setProductDescription(String productDescription);

    public int getProductPrice();

    public void setProductPrice(int productPrice);

    public int getProductQuantity();

    public void setProductQuantity(int productQuantity);

    public int getProductId();

    public void setEnabled(boolean enabled);

    public boolean getEnabled();

    public void setProductPictureUrl(String productPictureUrl);

    public String getProductPictureUrl();

}
