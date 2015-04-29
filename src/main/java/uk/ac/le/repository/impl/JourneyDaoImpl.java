package uk.ac.le.repository.impl;

import uk.ac.le.model.Journey;
import uk.ac.le.model.User;
import uk.ac.le.repository.JourneyDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

@Repository(value = "journeyDao")
@Transactional(propagation = Propagation.MANDATORY)
public class JourneyDaoImpl extends BaseDao<Journey> implements JourneyDao {

    public List<Journey> getAll(User user) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Journey> criteria = builder.createQuery(Journey.class);

        Root<Journey> tRoot = criteria.from(Journey.class);

        List<Predicate> wherePredicates = new ArrayList<Predicate>();
        wherePredicates.add(builder.equal(tRoot.get("user"), user));
        wherePredicates.add(builder.equal(tRoot.get("deleted"), false));

        buildWhereClause(criteria, wherePredicates);

        return getEntityManager().createQuery(criteria).getResultList();
    }

    public List<Journey> getAll(Journey journey) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Journey> criteria = builder.createQuery(Journey.class);

        Root<Journey> tRoot = criteria.from(Journey.class);

        List<Predicate> wherePredicates = new ArrayList<Predicate>();
        User u = journey.getUser();
        wherePredicates.add(builder.notEqual(tRoot.get("user"), u));
        wherePredicates.add(builder.equal(tRoot.get("deleted"), false));
        wherePredicates.add(builder.notEqual(tRoot.get("isDriver"), journey.isDriver()));

        buildWhereClause(criteria, wherePredicates);
        
        return getEntityManager().createQuery(criteria).getResultList();
    }

    public List<Journey> getAll(Journey.Frequency frequency) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Journey> criteria = builder.createQuery(Journey.class);

        Root<Journey> tRoot = criteria.from(Journey.class);

        List<Predicate> wherePredicates = new ArrayList<Predicate>();
        wherePredicates.add(builder.equal(tRoot.get("deleted"), false));
        wherePredicates.add(builder.equal(tRoot.get("frequency"), frequency));

        buildWhereClause(criteria, wherePredicates);

        return getEntityManager().createQuery(criteria).getResultList();
    }
}
