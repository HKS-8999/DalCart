package mocks;

public class MockProduct {//implements IUser{
    public int id;
    public String productName;
    public int quantity;
    public boolean enabled;

    public MockProduct(int id, String productName,int quantity, boolean enabled){
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.enabled = enabled;
    }
}