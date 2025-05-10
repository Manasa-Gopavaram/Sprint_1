package serviceimpl;

import java.util.List;
import service.AccountService;
import Dao.AccountDao;
import daoimp.AccountDaoImpl;
import entity.Account;

public class AccountServiceImpl implements AccountService {

    // Reference to the AccountDao
    AccountDao accountDao = new AccountDaoImpl();

    @Override
    public Account createAccount(Account account) {
        // Call the DAO method to save the account
        return accountDao.createAccount(account);
    }

    @Override
    public List<Account> getAllAccounts() {
        // Call the DAO method to retrieve all accounts
        return accountDao.getAllAccounts();
    }

    @Override
    public Account getAccountById(String accountId) {
        // Call the DAO method to retrieve an account by ID
        return accountDao.getAccountById(accountId);
    }

    @Override
    public Account updateAccount(String accountId, Account updatedAccount) {
        // Call the DAO method to update the account
        return accountDao.updateAccount(accountId, updatedAccount);
    }

    @Override
    public String deleteAccount(String accountId) {
        // Call the DAO method to delete an account by ID
        return accountDao.deleteAccount(accountId);
    }
}
