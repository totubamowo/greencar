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

    private Class<Journey> clazz
            = (Class<Journey>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    public List<Journey> getAll(User user) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Journey> criteria = builder.createQuery(clazz);

        Root<Journey> tRoot = criteria.from(clazz);

        List<Predicate> wherePredicates = new ArrayList<Predicate>();
        wherePredicates.add(builder.equal(tRoot.get("user"), user));
        wherePredicates.add(builder.equal(tRoot.get("deleted"), false));

        buildWhereClause(criteria, wherePredicates);

        return getEntityManager().createQuery(criteria).getResultList();
    }
}
