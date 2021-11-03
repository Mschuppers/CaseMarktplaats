package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Scanner;

@Singleton
@Alternative
public class AlternativeProducers {

    @Produces
    public EntityManager em() {
        return Persistence.createEntityManagerFactory("MySQL").createEntityManager();
    }

    @Produces
    public Logger logger(InjectionPoint ip) {
        return LoggerFactory.getLogger(ip.getMember().getDeclaringClass());
    }

    @Produces
    public Scanner sc = new Scanner(System.in);

}
