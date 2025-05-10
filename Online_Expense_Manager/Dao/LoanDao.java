package Dao;

import java.util.List;

import entity.Loan;

public interface LoanDao {

    Loan createLoan(Loan loan);

    List<Loan> getAllLoans();

    Loan getLoanById(String loanId);

    Loan updateLoan(String loanId, Loan updatedLoan);

    String deleteLoan(String loanId);
}
