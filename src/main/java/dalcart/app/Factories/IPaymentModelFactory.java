package dalcart.app.Factories;


import dalcart.app.models.PaymentModel;

public interface IPaymentModelFactory
{
    public PaymentModel createPaymentModel(String cardNumber, String cardCVV, String expiryDate);
}
