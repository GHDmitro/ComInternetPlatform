package pac.entities;




import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by macbookair on 28.03.16.
 */

@Entity
@Table(name = "Account")
//@NamedEntityGraph(name = "Account.pricePositions",
//        attributeNodes = @NamedAttributeNode("pricePositions"))
public class Account {

    @Id
    @Column(name = "login")
    private String login;
    @Column(name = "pass")
    private String pass;
    @Column(name = "email")
    private String email;
    @Column(name = "telNumber")
    private String telNumber;
    @Column(name = "state")
    private boolean state;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<PositionOfPrice> pricePositions  = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "accountType_typeName")
    private AccountType accountType;

    @OneToMany(mappedBy = "accountCustomer" , fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Booking> bookingList = new ArrayList<>();

    public Account() {

    }

    public Account(String telNumber, String email, String pass, String login, AccountType accountType) {
        this.accountType = accountType;
        this.telNumber = telNumber;
        this.email = email;
        this.pass = pass;
        this.login = login;
    }

    public void deleteBooking(Booking booking){
        bookingList.remove(booking);
    }
    public void setBookingList(Booking booking){
        bookingList.add(booking);
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public List<PositionOfPrice> getPricePositions() {
        return pricePositions;
    }

    public void deletePricePosition(PositionOfPrice positionOfPrice){
//        if (pricePositions.contains(positionOfPrice)){
            pricePositions.remove(positionOfPrice);
//        }
    }

    public void addPricePositions(PositionOfPrice position){
        pricePositions.add(position);
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
