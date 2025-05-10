package service;

import java.util.List;
import entity.Income;

public interface IncomeService {
    Income createIncome(Income income);

    List<Income> getAllIncomes();

    Income getIncomeById(String incomeId);

    Income updateIncome(String incomeId, Income updatedIncome);

    String deleteIncome(String incomeId);
}
