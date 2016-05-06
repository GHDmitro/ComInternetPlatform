package pac.daoImpl;

import org.springframework.stereotype.Repository;
import pac.daoInter.AccountDAO;
import pac.entities.Account;
import pac.entities.PositionOfPrice;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by macbookair on 28.03.16.
 */
@Repository
public class AccountDAOImpl implements AccountDAO {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void add(Account account) {
        entityManager.persist(account);
    }

    @Override
    public void delete(Account account) {
        entityManager.remove(account);
    }

    @Override
    public void update(Account account) {
        entityManager.merge(account);
    }

    @Override
    public Account findOne(String login) {
        //либо getReference если нужно будет только ссылка на него,а не его контент
        System.out.println("ищет в AccountDAOImpl " +login);
//        Account account = entityManager.find(Account.class, login);
//        account.getPricePositions().iterator();
        return entityManager.find(Account.class, login);
    }

    @Override
    public List<Account> list() {
        Query query = entityManager.createQuery("select a from Account a ", Account.class);
        return (List<Account>)query.getResultList();
    }

    @Override
    public List<PositionOfPrice> listPositions(Account account) {
//        List<PositionOfPrice> list = findOne(account.getLogin());

        return null;
    }

//    @Override
//    public List<PositionOfPrice> listPositions(Account account) {
//        Query query = entityManager.createQuery("select ")
//        return null;
//    }


}
