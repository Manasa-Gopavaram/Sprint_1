package Dao;

import entity.Income;
import java.util.List;
public interface IncomeDao {

    Income createIncome(Income income);

    List<Income> getAllIncomes();

    Income getIncomeById(String incomeId);

    Income updateIncome(String incomeId, Income updatedIncome);

    String deleteIncome(String incomeId);
}
