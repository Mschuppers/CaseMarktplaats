package utils;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Scanner;

@Singleton
@Alternative
public class AlternativeProducers {

    @Produces
    public EntityManager em() {
        return Persistence.createEntityManagerFactory("h2").createEntityManager();
    }

    @Produces
    public Scanner sc(){
      return new Scanner(System.in);
    }

}
