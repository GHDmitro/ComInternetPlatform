package pac.daoImpl;

import org.springframework.stereotype.Repository;
import pac.daoInter.PositionOfPriceDAO;
import pac.entities.PositionOfPrice;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    public PositionOfPrice find(PositionOfPrice positionOfPrice) {
        entityManager.find(positionOfPrice.getClass(), positionOfPrice.getId());
        return null;
    }

}
