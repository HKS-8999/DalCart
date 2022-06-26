package mocks;

public class MockProduct {//implements IUser{
    public String productName;
    public int quantity;
    public boolean enabled;

    public MockProduct(String productName,int quantity, boolean enabled){
        this.productName = productName;
        this.quantity = quantity;
        this.enabled = enabled;
    }
}