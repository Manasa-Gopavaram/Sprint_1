package service;

import java.util.List;
import entity.Account;

public interface AccountService {
    Account createAccount(Account account);

    List<Account> getAllAccounts();

    Account getAccountById(String accountId);

    Account updateAccount(String accountId, Account updatedAccount);

    String deleteAccount(String accountId);
}
