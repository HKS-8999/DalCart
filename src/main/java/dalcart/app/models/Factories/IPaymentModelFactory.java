package dalcart.app.models.Factories;


import dalcart.app.models.PaymentModel;

public interface IPaymentModelFactory
{
    public PaymentModel createPaymentModel(String cardNumber, String cardCVV, String expiryDate);
    PaymentModel createPaymentModel();
}
