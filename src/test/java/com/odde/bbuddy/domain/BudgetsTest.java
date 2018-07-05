package com.odde.bbuddy.domain;

import com.odde.bbuddy.repository.Account;
import com.odde.bbuddy.repository.AccountRepository;
import com.odde.bbuddy.repository.Budget;
import com.odde.bbuddy.repository.BudgetRepository;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class BudgetsTest {
    BudgetRepository repository = mock(BudgetRepository.class);
    Budgets budgets = new Budgets(repository);
    private List<Budget> existingBudgetList;

    private void givenBudgets(Budget... budgetList) {
        existingBudgetList = Arrays.asList(budgetList);
        when(budgets.all()).thenReturn(existingBudgetList);
    }

    @Test
    void add_budget() {
        Budget budget = new Budget("09/2017", 10000);

        budgets.add(budget);

        verify(repository).save(budget);
    }

    @Test
    void add_existing_budget() {
        Budget old = new Budget("09/2017", 10000);
        when(repository.findByMonth("09/2017")).thenReturn(old);

        givenBudgets( old );
        Budget budget = new Budget("09/2017", 5000);

        budgets.add(budget);

        verify(repository).findByMonth(old.getMonth());
        verify(repository).delete(old);
        verify(repository).save(budget);

    }
    @Test
    void get_all_accounts() {
        givenBudgets(new Budget("09/2018", 10000));

        List<Budget> budgetList = budgets.all();

        assertThat(budgetList).isEqualTo(existingBudgetList);
    }
}
