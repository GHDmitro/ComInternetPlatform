package pac.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pac.daoInter.BookingDAO;
import pac.entities.Account;
import pac.entities.Booking;

import java.util.List;

/**
 * Created by macbookair on 23.05.16.
 */

@Service
public class BookingService {

    @Autowired
    private BookingDAO bookingDAO;

    @Transactional
    public void setBooking(Booking booking){
        if (booking != null){
            bookingDAO.set(booking);
        }else {
            System.out.println("Заказ в null в BookingService");
        }
    }

    @Transactional
    public void deleteBooking(Booking booking){
        if (booking != null){
            bookingDAO.delete(booking);
        }else {
            System.out.println("Заказ в null в BookingService");
        }
    }

    @Transactional
    public Booking findBooking(Integer bookingID){
        return bookingDAO.find(bookingID);
    }

    @Transactional
    public List<Booking> bookingList(Account accountCustomer){
        return bookingDAO.bookingList(accountCustomer);
    }



}
