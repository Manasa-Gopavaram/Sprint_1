package daoimp;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import Dao.LoanDao;
import entity.Loan;
import util.HibernateUtil;

public class LoanDaoImpl implements LoanDao {

    @Override
    public Loan createLoan(Loan loan) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.save(loan);
            session.getTransaction().commit();
            return loan;
        } catch (HibernateException e) {
            System.out.println("Error: " + e);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    @Override
    public List<Loan> getAllLoans() {
        try (Session session = HibernateUtil.getSession()) {
            // Execute HQL query to retrieve all loan records
            Query<Loan> query = session.createQuery("FROM Loan", Loan.class);
            return query.list();
        } catch (HibernateException e) {
            System.out.println("Error: " + e);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    @Override
    public Loan getLoanById(String loanId) {
        try (Session session = HibernateUtil.getSession()) {
            // Retrieve the Loan by its ID
            Loan loan = session.get(Loan.class, Integer.parseInt(loanId));
            return loan;
        } catch (HibernateException e) {
            System.out.println("Error: " + e);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    @Override
    public Loan updateLoan(String loanId, Loan updatedLoan) {
        try (Session session = HibernateUtil.getSession()) {
            Loan loan = session.get(Loan.class, Integer.parseInt(loanId));
            if (loan != null) {
                session.beginTransaction();

                loan.setLender_name(updatedLoan.getLender_name());
                loan.setLoan_amount(updatedLoan.getLoan_amount());
                loan.setStart_date(updatedLoan.getStart_date());
                loan.setEnd_date(updatedLoan.getEnd_date());

                session.saveOrUpdate(loan);
                session.getTransaction().commit();
                return loan;
            }
        } catch (HibernateException e) {
            System.out.println("Error: " + e);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    @Override
    public String deleteLoan(String loanId) {
        String message = null;
        try (Session session = HibernateUtil.getSession()) {
            Loan loan = session.get(Loan.class, Integer.parseInt(loanId));
            if (loan != null) {
                session.beginTransaction();
                System.out.println("Are you sure you want to delete this loan?");
                String status = new java.util.Scanner(System.in).next();
                if (status.equalsIgnoreCase("yes")) {
                    session.delete(loan);
                    session.getTransaction().commit();
                    message = "Loan is deleted successfully!";
                } else {
                    message = "User chose to retain the loan.";
                }
            }
        } catch (HibernateException e) {
            System.out.println("Error: " + e);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return message;
    }
}
