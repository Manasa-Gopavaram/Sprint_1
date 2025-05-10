package daoimp;

import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import Dao.ExpenseDao;
import entity.Expense;
import util.HibernateUtil;

public class ExpenseDaoImpl implements ExpenseDao {

    Scanner sc = new Scanner(System.in);

    @Override
    public Expense createExpense(Expense expense) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.save(expense);
            session.getTransaction().commit();
            return expense;
        } catch (HibernateException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public List<Expense> getAllExpenses() {
        try (Session session = HibernateUtil.getSession()) {
            Query<Expense> query = session.createQuery("FROM Expense", Expense.class);
            return query.list();
        } catch (HibernateException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Expense getExpenseById(String expenseId) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(Expense.class, expenseId);
        } catch (HibernateException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Expense updateExpense(String expenseId, Expense updatedExpense) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            Expense expense = session.get(Expense.class, expenseId);
            if (expense != null) {
                expense.setAmount(updatedExpense.getAmount());
                expense.setCategory(updatedExpense.getCategory());
                expense.setDescription(updatedExpense.getDescription());
                session.saveOrUpdate(expense);
                session.getTransaction().commit();
                return expense;
            }
        } catch (HibernateException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public String deleteExpense(String expenseId) {
        String message = null;
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            Expense expense = session.get(Expense.class, expenseId);
            if (expense != null) {
                System.out.println("Are you sure you want to delete?");
                String status = sc.next();
                if (status.equalsIgnoreCase("yes")) {
                    session.delete(expense);
                    session.getTransaction().commit();
                    session.evict(expense);
                    message = "Expense deleted successfully.";
                } else {
                    message = "Expense will not be deleted.";
                }
            }
        } catch (HibernateException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return message;
    }
}
