package application;

import application.exception.UserAbortedAction;
import application.exception.ZeroValue;
import application.validator.Validator;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.AddPackages;
import org.jboss.weld.junit5.auto.EnableAlternatives;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@EnableAutoWeld
@AddPackages(App.class)
@AddBeanClasses(utils.AlternativeProducers.class)
@EnableAlternatives(utils.AlternativeProducers.class)
class ValidatorTest {

    Validator v = new Validator();

    @Test
    void validatePrice2BehindComma() {

        //Given
        String price = "14.99";

        //  Then
        assertTrue(v.validatePrice(price));
    }

    @Test
    void validatePrice0BehindComma()  {
        //Given
        String price = ("14");

        //  Then
        assertTrue(v.validatePrice(price));
    }

    @Test
    void validatePriceGreaterEqual()  {
        //Given
        String price = "5";

        //  Then
        assertTrue(v.validatePrice(price));
    }


    @Test
    void validatePriceLesserEqual() {
        //Given
        String price = "-1";

        assertFalse(v.validatePrice(price));
    }
}


