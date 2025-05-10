package serviceimpl;

import java.util.List;
import java.util.Map;

import service.ExpenseService;
import Dao.ExpenseDao;
import daoimp.ExpenseDaoImpl;
import entity.Expense;

public class ExpenseServiceImpl implements ExpenseService {

    // Reference to the ExpenseDao
    ExpenseDao expenseDao = new ExpenseDaoImpl();

    @Override
    public Expense createExpense(Expense expense) {
        // Call the DAO method to save the expense
        return expenseDao.createExpense(expense);
    }

    @Override
    public List<Expense> getAllExpenses() {
        // Call the DAO method to retrieve all expenses
        return expenseDao.getAllExpenses();
    }

    @Override
    public Expense getExpenseById(String expenseId) {
        // Call the DAO method to retrieve an expense by ID
        return expenseDao.getExpenseById(expenseId);
    }

    @Override
    public Expense updateExpense(String expenseId, Expense updatedExpense) {
        // Call the DAO method to update an expense
        return expenseDao.updateExpense(expenseId, updatedExpense);
    }

    @Override
    public String deleteExpense(String expenseId) {
        // Call the DAO method to delete an expense
        return expenseDao.deleteExpense(expenseId);
    }

	public Map<String, Double> getExpenseByCategory() {
		// TODO Auto-generated method stub
		return null;
	}
}
