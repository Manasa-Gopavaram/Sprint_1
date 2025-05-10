package Dao;

import java.util.List;

import entity.Expense;

public interface ExpenseDao {

    Expense createExpense(Expense expense);

    List<Expense> getAllExpenses();

    Expense getExpenseById(String expenseId);

    Expense updateExpense(String expenseId, Expense updatedExpense);

    String deleteExpense(String expenseId);
}
