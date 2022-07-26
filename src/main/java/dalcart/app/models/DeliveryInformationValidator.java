package dalcart.app.models;

import java.util.regex.Pattern;

public class DeliveryInformationValidator {
    public boolean isFullNameValid(DeliveryInformationModel information) {
        String regex = "^([a-zA-Z]{2,}\\s[a-zA-Z]{1,}'?-?[a-zA-Z]{2,}\\s?([a-zA-Z]{1,})?)";
        return Pattern.compile(regex).matcher(information.getName()).matches();
    }

    public boolean isEmailAddressValid(DeliveryInformationModel information) {
        String regex = "^\\S+@\\S+$";
        return Pattern.compile(regex).matcher(information.getEmail()).matches();
    }

    public boolean isMobileNumberValid(DeliveryInformationModel information) {
        String regex = "^(\\+\\d{1,3}[- ]?)?\\d{10}$";
        return Pattern.compile(regex).matcher(information.getMobileNumber()).matches();
    }

    public boolean isAddressValid(DeliveryInformationModel information) {
        String regex = "^[#.0-9a-zA-Z\\s,-]+$";
        return Pattern.compile(regex).matcher(information.getAddress()).matches();
    }

    public boolean validateDeliveryDetails(DeliveryInformationModel information) {
        return isFullNameValid(information) && isAddressValid(information) && isEmailAddressValid(information) && isMobileNumberValid(information);
    }
}
