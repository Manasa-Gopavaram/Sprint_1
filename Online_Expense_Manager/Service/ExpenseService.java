package service;

import java.util.List;
import entity.Expense;

public interface ExpenseService {
    Expense createExpense(Expense expense);

    List<Expense> getAllExpenses();

    Expense getExpenseById(String expenseId);

    Expense updateExpense(String expenseId, Expense updatedExpense);

    String deleteExpense(String expenseId);
}
