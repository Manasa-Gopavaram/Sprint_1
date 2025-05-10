package daoimp;

import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import Dao.IncomeDao;
import entity.Income;
import util.HibernateUtil;

public class IncomeDaoImpl implements IncomeDao{

    Scanner sc = new Scanner(System.in);

    @Override
    public Income createIncome(Income income) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.save(income);
            session.getTransaction().commit();
            return income;
        } catch (HibernateException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public List<Income> getAllIncomes() {
        try (Session session = HibernateUtil.getSession()) {
            Query<Income> query = session.createQuery("FROM Income", Income.class);
            return query.list();
        } catch (HibernateException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Income getIncomeById(String incomeId) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(Income.class, incomeId);
        } catch (HibernateException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Income updateIncome(String incomeId, Income updatedIncome) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            Income income = session.get(Income.class, incomeId);
            if (income != null) {
                income.setAmount(updatedIncome.getAmount());
                income.setSource(updatedIncome.getSource());
                income.setDescription(updatedIncome.getDescription());
                session.saveOrUpdate(income);
                session.getTransaction().commit();
                return income;
            }
        } catch (HibernateException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public String deleteIncome(String incomeId) {
        String message = null;
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            Income income = session.get(Income.class, incomeId);
            if (income != null) {
                System.out.println("Are you sure you want to delete?");
                String status = sc.next();
                if (status.equalsIgnoreCase("yes")) {
                    session.delete(income);
                    session.getTransaction().commit();
                    session.evict(income);
                    message = "Income deleted successfully.";
                } else {
                    message = "Income will not be deleted.";
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
