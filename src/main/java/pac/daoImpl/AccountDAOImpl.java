package pac.daoImpl;

import org.springframework.stereotype.Repository;
import pac.daoInter.AccountDAO;
import pac.entities.Account;

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
    public Account findOne(String login) {
        //либо getReference если нужно будет только ссылка на него,а не его контент
        return entityManager.getReference(Account.class, login);
    }

    @Override
    public List<Account> list() {
        Query query = entityManager.createQuery("select a from Account a ", Account.class);
        return (List<Account>)query.getResultList();
    }


}
