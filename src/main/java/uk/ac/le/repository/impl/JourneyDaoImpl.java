package uk.ac.le.repository.impl;

import uk.ac.le.model.Journey;
import uk.ac.le.repository.JourneyDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository(value = "journeyDao")
@Transactional(propagation = Propagation.MANDATORY)
public class JourneyDaoImpl extends BaseDao<Journey> implements JourneyDao {

}
