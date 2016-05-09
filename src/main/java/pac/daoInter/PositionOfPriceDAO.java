package pac.daoInter;

import pac.entities.Account;
import pac.entities.PositionOfPrice;

import java.util.List;

/**
 * Created by macbookair on 18.04.16.
 */
public interface PositionOfPriceDAO {
    void add(PositionOfPrice positionOfPrice);
    void delete(PositionOfPrice positionOfPrice);
    PositionOfPrice find(PositionOfPrice positionOfPrice);
    List<PositionOfPrice> listPositions(Account account);
}
