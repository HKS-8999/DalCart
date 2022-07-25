package dalcart.app.Factories;

import dalcart.app.models.DeliveryInformationModel;

public interface IDeliveryInformationModelFactory
{
    public DeliveryInformationModel createDeliveryInformationMode();
    public DeliveryInformationModel createDeliveryInformation(String name, String email, String address, String mobileNumber);
}
