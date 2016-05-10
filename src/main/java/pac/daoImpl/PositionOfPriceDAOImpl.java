package pac.daoImpl;

import org.springframework.stereotype.Repository;
import pac.daoInter.PositionOfPriceDAO;
import pac.entities.Account;
import pac.entities.PositionOfPrice;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.*;
import java.util.List;

/**
 * Created by macbookair on 18.04.16.
 */
@Repository
public class PositionOfPriceDAOImpl implements PositionOfPriceDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(PositionOfPrice positionOfPrice) {
        entityManager.merge(positionOfPrice);
    }

    @Override
    public void delete(PositionOfPrice positionOfPrice) {
        entityManager.remove(positionOfPrice);
    }

    @Override
    public PositionOfPrice find(Integer positionID) {
        entityManager.find(PositionOfPrice.class, positionID);
        return null;
    }

    @Override
    public List<PositionOfPrice> listPositions(Account account) {
        Query query = entityManager.createQuery("select p from PositionOfPrice p where p.account =:account_login");
        query.setParameter("account_login", account);
        return (List<PositionOfPrice>) query.getResultList() ;
    }

}
