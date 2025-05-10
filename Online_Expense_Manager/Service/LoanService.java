package service;

import java.util.List;
import entity.Loan;

public interface LoanService {
    Loan createLoan(Loan loan);

    List<Loan> getAllLoans();

    Loan getLoanById(String loanId);

    Loan updateLoan(String loanId, Loan updatedLoan);

    String deleteLoan(String loanId);

	
	Loan updatedLoan(Loan existingLoan);
}
