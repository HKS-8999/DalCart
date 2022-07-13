package dalcart.app.items;

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

    public void setProductId(int productId);
    public int getProductId();

    public void setProductState(boolean enabled);

    public boolean getProductState();

    public void setProductImage(String productPictureUrl);

    public String getProductImage();

}
