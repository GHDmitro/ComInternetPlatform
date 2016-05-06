package pac.daoInter;

import pac.entities.PositionOfPrice;

/**
 * Created by macbookair on 18.04.16.
 */
public interface PositionOfPriceDAO {
    void add(PositionOfPrice positionOfPrice);
    void delete(PositionOfPrice positionOfPrice);
    PositionOfPrice find(PositionOfPrice positionOfPrice);
}
