package pac.daoInter;

import pac.entities.Account;

import java.util.List;

/**
 * Created by macbookair on 28.03.16.
 */
public interface AccountDAO {
    void add(Account account);
    void delete(Account account);
    Account findOne(String login);
    List<Account> list();

}
