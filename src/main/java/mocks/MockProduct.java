//package mocks;
//
//import dalcart.app.items.IProduct;
//
//public class MockProduct implements IProduct
//{
//    private String productName;
//    private int productId;
//    private String productDescription;
//    private int productPrice;
//    private int productQuantity;
//    private boolean enabled;
//
//    private String productPictureUrl;
//
//    public MockProduct(int id, String productName,int quantity, boolean enabled){
//        this.productId = id;
//        this.productName = productName;
//        this.productQuantity = quantity;
//        this.enabled = enabled;
//    }
//
//    public MockProduct() {
//
//    }
//
//    public String getProductName() {
//        return productName;
//    }
//
//    public void setProductName(String productName) {
//        this.productName = productName;
//    }
//
//    public String getProductDescription() {
//        return productDescription;
//    }
//
//    public void setProductDescription(String productDescription) {
//        this.productDescription = productDescription;
//    }
//
//    public int getProductPrice() {
//        return productPrice;
//    }
//
//    public void setProductPrice(int productPrice) {
//        this.productPrice = productPrice;
//    }
//
//    public int getProductQuantity() {
//        return productQuantity;
//    }
//
//    public void setProductQuantity(int productQuantity) {
//        this.productQuantity = productQuantity;
//    }
//
//    public int getProductId() {
//        return productId;
//    }
//
//    public void setProductId(int productId) {
//        this.productId = productId;
//    }
//
//    public boolean getEnabled() {
//        return enabled;
//    }
//
//    public void setEnabled(boolean enabled) {
//        this.enabled = enabled;
//    }
//
//    public void setProductImage(String productPictureUrl)
//    {
//        this.productPictureUrl = productPictureUrl;
//    }
//    public String getProductImage()
//    {
//        return productPictureUrl;
//    }
//
//}