package dalcart.app.models;

import dalcart.app.models.Repository.IDeliveryInformationPersistence;

public class DeliveryInformationModel {
    private String name;
    private String email;
    private String address;
    private String mobileNumber;

    public DeliveryInformationModel(){

    }

    public DeliveryInformationModel(String name, String email, String address, String mobileNumber) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public IDeliveryInformationPersistence.StorageResult addDeliveryAddress(DeliveryInformationModel deliveryInformationModel, IDeliveryInformationPersistence deliveryInformationPersistence, Integer orderId) {
        return deliveryInformationPersistence.saveDeliveryInformation(deliveryInformationModel, orderId);
    }
}
