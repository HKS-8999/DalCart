package dalcart.app.models.Factories;

import dalcart.app.models.DeliveryInformationModel;

public class DeliveryInformationModelFactory implements IDeliveryInformationModelFactory
{
    @Override
    public DeliveryInformationModel createDeliveryInformationModel() {
        return new DeliveryInformationModel();
    }

    @Override
    public DeliveryInformationModel createDeliveryInformation(String name, String email, String address, String mobileNumber) {
        return new DeliveryInformationModel(name, email, address, mobileNumber);
    }
}
