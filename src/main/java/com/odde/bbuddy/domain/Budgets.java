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

        for (Budget budget: allBudget){
            if (isBetweenDate(start, end, budget.getMonth())) {
                amount += calculateAmount(start,end, budget);
            }
        }
        return amount;
    }

    public Double calculateAmount(LocalDate start, LocalDate end, Budget budget) {
        LocalDate budgetDate = budget.getMonth();
        if (!(start.getMonth() == budgetDate.getMonth() ||
                end.getMonth() == budgetDate.getMonth())
                ) {
            return budget.getAmount();
        } else if (start.getMonth() == budgetDate.getMonth()) {
            if (end.getMonth() == budgetDate.getMonth()) {
                return budget.getAmount() * (end.getDayOfMonth() - start.getDayOfMonth() + 1) / start.lengthOfMonth();
            } else {
                return budget.getAmount() * (start.lengthOfMonth() - start.getDayOfMonth() + 1) / start.lengthOfMonth();
            }
        } else {
            return budget.getAmount() * (end.getDayOfMonth()) / end.lengthOfMonth();
        }
    }

    public boolean isBetweenDate(LocalDate start, LocalDate end, LocalDate budgetMonth) {
        LocalDate startBudget = budgetMonth.withDayOfMonth(1);
        LocalDate endBudget = budgetMonth.withDayOfMonth(budgetMonth.lengthOfMonth());

        return (!(start.isBefore(startBudget)) && !(start.isAfter(endBudget))) ||
                (!(end.isBefore(startBudget)) && !(end.isAfter(endBudget)));
    }

}
