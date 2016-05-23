package pac.daoImpl;

import org.springframework.stereotype.Repository;
import pac.daoInter.BookingDAO;
import pac.entities.Account;
import pac.entities.Booking;
import javax.persistence.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by macbookair on 23.05.16.
 */

@Repository
public class BookingDAOImpl implements BookingDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void set(Booking booking) {
        entityManager.merge(booking);
    }

    @Override
    public void delete(Booking booking) {
        entityManager.remove(entityManager.merge(booking));
    }

    @Override
    public Booking find(Integer positionID) {
        return entityManager.find(Booking.class, positionID);
    }

    @Override
    public List<Booking> bookingList(Account accountCustomer) {
        Query query = entityManager.createQuery("select b from Booking b where b.accountCustomer=:accountCustomer");
        query.setParameter("accountCustomer", accountCustomer);
        List<Booking> list = (List<Booking>) query.getResultList();
        if (list != null){
            return list;
        }else {
            System.out.println("Лист booking пустой в BookingDAOImpl");
            return null;
        }
    }
}
