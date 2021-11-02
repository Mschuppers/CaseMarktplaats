package sysFiles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Scanner;

@Singleton
public class Producers {

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
