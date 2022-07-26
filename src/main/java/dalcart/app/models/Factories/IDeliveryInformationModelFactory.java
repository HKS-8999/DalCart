package dalcart.app.models.Factories;

import dalcart.app.models.DeliveryInformationModel;

public interface IDeliveryInformationModelFactory
{
    DeliveryInformationModel createDeliveryInformationModel();
    DeliveryInformationModel createDeliveryInformation(String name, String email, String address, String mobileNumber);
}
