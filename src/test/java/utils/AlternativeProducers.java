package utils;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@Singleton
@Alternative
public class AlternativeProducers {

    @Produces
    public EntityManager em() {
        return Persistence.createEntityManagerFactory("h2").createEntityManager();
    }


}
