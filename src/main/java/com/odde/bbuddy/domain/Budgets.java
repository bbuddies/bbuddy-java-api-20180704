package com.odde.bbuddy.domain;

import com.odde.bbuddy.repository.Budget;
import com.odde.bbuddy.repository.BudgetRepository;
import org.springframework.stereotype.Service;

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
}
