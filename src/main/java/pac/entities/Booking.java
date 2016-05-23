package pac.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by macbookair on 23.05.16.
 */

@Entity
@Table(name = "Booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "account_client")
    private Account accountClient;

    @ManyToOne
    @JoinColumn(name = "account_customer")
    private Account accountCustomer;

    @OneToMany(mappedBy = "booking", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<BookingPosition> bookingPositions = new ArrayList<>();

    public Booking() {
    }

    public Booking(Account accountClient, Account accountCustomer, List<BookingPosition> bookingPositions) {
        this.accountClient = accountClient;
        this.accountCustomer = accountCustomer;
        this.bookingPositions = bookingPositions;
    }


    public void addBookingPosition(BookingPosition bookingPosition){
        bookingPositions.add(bookingPosition);
    }

    public void deleteBookingPosition(BookingPosition bookingPosition){
        bookingPositions.remove(bookingPosition);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccountClient() {
        return accountClient;
    }

    public void setAccountClient(Account accountClient) {
        this.accountClient = accountClient;
    }

    public Account getAccountCustomer() {
        return accountCustomer;
    }

    public void setAccountCustomer(Account accountCustomer) {
        this.accountCustomer = accountCustomer;
    }

    public List<BookingPosition> getBookingPositions() {
        return bookingPositions;
    }

    public void setBookingPositions(List<BookingPosition> bookingPositions) {
        this.bookingPositions = bookingPositions;
    }
}

