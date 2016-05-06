package pac.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pac.daoInter.PositionOfPriceDAO;
import pac.entities.PositionOfPrice;

/**
 * Created by macbookair on 18.04.16.
 */
@Service
public class PositionOfPriceService {
    @Autowired
    private PositionOfPriceDAO positionOfPriceDAO;

    @Transactional
    public void addPositionOfPrice(PositionOfPrice positionOfPrice){
        positionOfPriceDAO.add(positionOfPrice);
    }
    @Transactional
    public void deletePositionOfPrice(PositionOfPrice positionOfPrice){
        positionOfPriceDAO.delete(positionOfPrice);
    }
    @Transactional
    public PositionOfPrice findPosition(PositionOfPrice positionOfPrice){
        return positionOfPriceDAO.find(positionOfPrice);
    }
}
