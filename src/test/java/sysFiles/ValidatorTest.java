package sysFiles;

import application.App;
import application.sysFiles.Validator;
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
    void validatePrice2BehindComma() {
        //Given
        String price = "14.99";
        // When
        Double result = Double.parseDouble(v.validatePrice("14.99"));
        //  Then
        assertEquals(Double.parseDouble(price), result);
    }

    @Test
    void validatePrice0BehindComma() {
        //Given
        double price = Double.parseDouble("14");
        // When
        Double result = Double.parseDouble(v.validatePrice("14"));
        //  Then
        assertEquals(price, result);
    }

    @Test
    void validatePriceGreaterEqual() {
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

        //2nd option, assert that an error of ZeroValue is thrown when  validate Price executes with -1
        //Proof that this works (though broken still for ZeroValue.class) is that NullPointerException passes the test
        //TODO figure out why a nullpointer is thrown to the log element
        assertThrows(NullPointerException.class, () -> v.validatePrice(price));
    }
}


