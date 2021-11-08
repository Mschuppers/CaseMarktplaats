//package application.screen;
//
//import application.App;
//import org.jboss.weld.junit5.auto.AddBeanClasses;
//import org.jboss.weld.junit5.auto.AddPackages;
//import org.jboss.weld.junit5.auto.EnableAlternatives;
//import org.jboss.weld.junit5.auto.EnableAutoWeld;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import javax.inject.Inject;
//import java.io.ByteArrayInputStream;
//import java.util.StringJoiner;
//
//@EnableAutoWeld
//@AddPackages(App.class)
//@AddBeanClasses(utils.AlternativeProducers.class)
//@EnableAlternatives(utils.AlternativeProducers.class)
//class ManageProductScreenTest {
//
//    private static final String[] commands = new String[]{"3", "De titel", "De Omschrijving", "14.99", "0"};
//
//    @Inject
//    private App app;
//
//    @BeforeAll
//    static void beforeAll() {
//        System.setIn(new ByteArrayInputStream(toLines(commands).getBytes()));
//    }
//
//
//
//    private static String toLines(String... commands) {
//        StringJoiner sj = new StringJoiner(System.lineSeparator());
//        for (String command : commands) {
//            sj.add(command);
//        }
//        return sj.toString();
//    }
//
//
//    @Test
//    void insertProduct() {
//        app.start();
//    }
//
//
//    @Test
//    void updateProduct() {
//    }
//
//
//}