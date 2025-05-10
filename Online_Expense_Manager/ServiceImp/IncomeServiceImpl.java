package serviceimpl;

import java.util.List;
import service.IncomeService;
import Dao.IncomeDao;
import daoimp.IncomeDaoImpl;
import entity.Income;

public class IncomeServiceImpl implements IncomeService {

    // Create a reference to the IncomeDao
    IncomeDao incomeDao = new IncomeDaoImpl();

    @Override
    public Income createIncome(Income income) {
        // Call the DAO method to save the income
        return incomeDao.createIncome(income);
    }

    @Override
    public List<Income> getAllIncomes() {
        // Call the DAO method to get all incomes
        return incomeDao.getAllIncomes();
    }

    @Override
    public Income getIncomeById(String incomeId) {
        // Call the DAO method to get an income by ID
        return incomeDao.getIncomeById(incomeId);
    }

    @Override
    public Income updateIncome(String incomeId, Income updatedIncome) {
        // Call the DAO method to update an income
        return incomeDao.updateIncome(incomeId, updatedIncome);
    }

    @Override
    public String deleteIncome(String incomeId) {
        // Call the DAO method to delete the income
        return incomeDao.deleteIncome(incomeId);
    }
}
