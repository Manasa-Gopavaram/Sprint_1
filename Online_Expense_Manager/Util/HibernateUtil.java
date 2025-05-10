package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import entity.User;
import entity.Expense;
import entity.Income;
import entity.Loan;
import entity.Account;

public class HibernateUtil {

    private final static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure("hibernate.cfg.xml")
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Expense.class)
                    .addAnnotatedClass(Income.class)
                    .addAnnotatedClass(Loan.class)
                    .addAnnotatedClass(Account.class)
                    .buildSessionFactory();
        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getSession() {
        return getSessionFactory().openSession(); // Open a new session
    }
}
