package dalcart.app.models.Factories;

import dalcart.app.models.IValidate;
import dalcart.app.models.ValidateUserAttributes;

public class ValidateFactory implements IValidateFactory {

    @Override
    public IValidate createValidations() {
        return new ValidateUserAttributes();
    }
}
