package dalcart.app.models;

public interface IOnlinePayment {
    public String makePayment(String cardNumber, String cVV, String Expiry);
}
