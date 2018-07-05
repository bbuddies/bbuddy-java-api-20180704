package com.odde.bbuddy.domain;

import com.odde.bbuddy.repository.Budget;
import com.odde.bbuddy.repository.BudgetRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class Budgets {
    private BudgetRepository budgetRepository;

    public Budgets(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    public Budget add(Budget budget) {
        return budgetRepository.save(budget);
    }

    public List<Budget> all() {
        return budgetRepository.findAll();
    }

    public Double searchBudget(String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        List<Budget> allBudget = budgetRepository.findAll();
        Double amount = 0d;

        for (Budget budget : allBudget) {
            if (isDateBetweenSearch(start, end, budget.getMonth())) {
                amount += calculateAmount(start, end, budget);
            }
        }
        return amount;
    }

    public Double calculateAmount(LocalDate startSearch, LocalDate endSearch, Budget budget) {
        LocalDate budgetDate = budget.getMonth();
        if (!(matchMonth(startSearch, budgetDate) || matchMonth(endSearch, budgetDate))) {
            return budget.getAmount();
        } else if (matchMonth(startSearch, budgetDate)) {
            if (matchMonth(endSearch, budgetDate)) {
                return budget.getAmount() * (endSearch.getDayOfMonth() - startSearch.getDayOfMonth() + 1) / startSearch.lengthOfMonth();
            } else {
                return budget.getAmount() * (startSearch.lengthOfMonth() - startSearch.getDayOfMonth() + 1) / startSearch.lengthOfMonth();
            }
        } else {
            return budget.getAmount() * (endSearch.getDayOfMonth()) / endSearch.lengthOfMonth();
        }
    }

    private boolean matchMonth(LocalDate date, LocalDate checkDate) {
        return date.getMonth() == checkDate.getMonth() && date.getYear() == checkDate.getYear();
    }

    public boolean isDateBetweenSearch(LocalDate startSearch, LocalDate endSearch, LocalDate budgetMonth) {
        LocalDate startBudget = budgetMonth.withDayOfMonth(1);
        LocalDate endBudget = budgetMonth.withDayOfMonth(budgetMonth.lengthOfMonth());

        return !(endBudget.isBefore(startSearch) || startBudget.isAfter(endSearch)) && startSearch.isBefore(endSearch);
    }

}
