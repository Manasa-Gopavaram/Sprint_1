package serviceimpl;

import java.util.List;

import entity.Loan;
import service.LoanService;
import Dao.LoanDao;
import daoimp.LoanDaoImpl;
import serviceimpl.LoanServiceImpl;

public class LoanServiceImpl implements LoanService {

    // Create a reference to the LoanDao
    LoanDao loanDao = new LoanDaoImpl();
    
    @Override
    public Loan createLoan(Loan loan) {
        // Call the DAO method to save the loan
        return loanDao.createLoan(loan);
    }

    @Override
    public List<Loan> getAllLoans() {
        // Call the DAO method to get all loans
        return loanDao.getAllLoans();
    }

    @Override
    public Loan getLoanById(String loanId) {
        // Call the DAO method to get a loan by ID
        return loanDao.getLoanById(loanId);
    }

    @Override
    public Loan updateLoan(String loanId, Loan updatedLoan) {
        // Call the DAO method to update the loan
        return loanDao.updateLoan(loanId, updatedLoan);
    }

    @Override
    public String deleteLoan(String loanId) {
        // Call the DAO method to delete the loan
        return loanDao.deleteLoan(loanId);
    }

	@Override
	public Loan updatedLoan(Loan existingLoan) {
		// TODO Auto-generated method stub
		return null;
	}
}
