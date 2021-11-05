package application;

import application.exception.UserAbortedAction;
import application.exception.ZeroValue;
import application.validator.Validator;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.AddPackages;
import org.jboss.weld.junit5.auto.EnableAlternatives;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@EnableAutoWeld
@AddPackages(App.class)
@AddBeanClasses(utils.AlternativeProducers.class)
@EnableAlternatives(utils.AlternativeProducers.class)
class ValidatorTest {

    Validator v = new Validator();

    @Test
    void validatePrice2BehindComma() throws ZeroValue, UserAbortedAction {

        //Given
        String price = "14.99";
        // When
        Double result = Double.parseDouble(v.validatePrice("14.99"));
        //  Then
        assertEquals(Double.parseDouble(price), result);
    }

    @Test
    void validatePrice0BehindComma() throws ZeroValue, UserAbortedAction {
        //Given
        double price = Double.parseDouble("14");
        // When
        Double result = Double.parseDouble(v.validatePrice("14"));
        //  Then
        assertEquals(price, result);
    }

    @Test
    void validatePriceGreaterEqual() throws ZeroValue, UserAbortedAction {
        //Given
        String price = "5";
        // When
        double result = Double.parseDouble(v.validatePrice(price));
        //  Then
        assert (result >= 0);
    }


    @Test
    void validatePriceLesserEqual() {
        //Given
        String price = "-1";

        assertThrows(ZeroValue.class, () -> v.validatePrice(price));
    }
}


