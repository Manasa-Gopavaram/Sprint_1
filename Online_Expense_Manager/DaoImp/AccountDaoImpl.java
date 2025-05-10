package daoimp;

import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import Dao.AccountDao;
import entity.Account;
import ResourceNotFoundException.ResourceNotFoundException;
import util.HibernateUtil;

public class AccountDaoImpl implements AccountDao {

    Scanner sc = new Scanner(System.in);

    @Override
    public Account createAccount(Account account) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.save(account);
            session.getTransaction().commit();
            return account;
        } catch (HibernateException e) {
            System.out.println("HibernateException: " + e);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return null;
    }

    @Override
    public List<Account> getAllAccounts() {
        try (Session session = HibernateUtil.getSession()) {
            Query<Account> query = session.createQuery("FROM Account", Account.class);
            return query.list();
        } catch (HibernateException e) {
            System.out.println("HibernateException: " + e);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return null;
    }

    @Override
    public Account getAccountById(String accountId) {
        try (Session session = HibernateUtil.getSession()) {
            int accId = Integer.parseInt(accountId); // converting String to int
            Account account = session.get(Account.class, accId);
            if (account != null) {
                return account;
            } else {
                throw new ResourceNotFoundException("Account with ID " + accountId + " not found");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid Account ID format: " + e);
        } catch (HibernateException e) {
            System.out.println("HibernateException: " + e);
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return null;
    }

    @Override
    public Account updateAccount(String accountId, Account updatedAccount) {
        try (Session session = HibernateUtil.getSession()) {
            int accId = Integer.parseInt(accountId);
            Account existingAccount = session.get(Account.class, accId);
            if (existingAccount != null) {
                session.beginTransaction();
                existingAccount.setAccount_type(updatedAccount.getAccount_type());
                existingAccount.setBalance(updatedAccount.getBalance());
                session.update(existingAccount);
                session.getTransaction().commit();
                return existingAccount;
            } else {
                throw new ResourceNotFoundException("Account with ID " + accountId + " not found");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid Account ID format: " + e);
        } catch (HibernateException e) {
            System.out.println("HibernateException: " + e);
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return null;
    }

    @Override
    public String deleteAccount(String accountId) {
        String message = null;
        try (Session session = HibernateUtil.getSession()) {
            int accId = Integer.parseInt(accountId);
            Account account = session.get(Account.class, accId);
            if (account != null) {
                session.beginTransaction();
                System.out.println("Are you sure you want to delete? (yes/no)");
                String status = sc.next();
                if (status.equalsIgnoreCase("yes")) {
                    session.delete(account);
                    session.getTransaction().commit();
                    session.evict(account);
                    message = "Account deleted successfully.";
                } else {
                    message = "User chose not to delete the account.";
                }
            } else {
                throw new ResourceNotFoundException("Account with ID " + accountId + " not found");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid Account ID format: " + e);
        } catch (HibernateException e) {
            System.out.println("HibernateException: " + e);
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return message;
    }
}
