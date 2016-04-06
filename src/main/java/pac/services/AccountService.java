package pac.services;

import pac.daoInter.AccountDAO;
import pac.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by macbookair on 28.03.16.
 */
@Service
public class AccountService {

    @Autowired
    private AccountDAO accountDAO;

    @Transactional
    public void addAccount(Account account){
        accountDAO.add(account);
    }

    @Transactional
    public void deleteAccount(Account account){
        accountDAO.delete(account);
    }

    @Transactional(readOnly = true)
    public List<Account> listAccount(){
        return accountDAO.list();
    }

    @Transactional(readOnly = true)
    public Account findAccount(String login){
        return accountDAO.findOne(login);
    }



}
