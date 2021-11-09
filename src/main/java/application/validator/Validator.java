package application.validator;
import java.util.regex.Pattern;

public class Validator {
    public boolean validatePrice(String price) {
        return (Double.parseDouble(price.replace(',', '.')) > 0) && Pattern.matches("\\d*[.]\\d{2}", price) || (Pattern.matches("\\d*", price));
    }

    public boolean validateText(String textInput, int max) {
        return textInput.length() <= max;
    }
}