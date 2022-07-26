package dalcart.app.models;

import dalcart.app.models.Repository.IPaymentPersistence;

public class PaymentModel {
    private String cardNumber;
    private String cardCVV;
    private String expiryDate;

    public PaymentModel(String cardNumber, String cardCVV, String expiryDate) {
        this.cardNumber = cardNumber;
        this.cardCVV = cardCVV;
        this.expiryDate = expiryDate;
    }

    public PaymentModel() {

    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardCVV() {
        return cardCVV;
    }

    public void setCardCVV(String cardCVV) {
        this.cardCVV = cardCVV;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public IPaymentPersistence.StorageResult savePaymentInformation(IPaymentPersistence iPaymentPersistence, Integer orderId, Integer userId, Integer total) {
        return iPaymentPersistence.savePaymentInformation(orderId, userId, total);
    }
}
