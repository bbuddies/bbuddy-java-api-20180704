package com.odde.bbuddy.domain;

import com.odde.bbuddy.repository.Budget;
import com.odde.bbuddy.repository.BudgetRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BudgetsTest {
    @Mock
    BudgetRepository budgetRepository;
    @InjectMocks
    Budgets budgets;
    private List<Budget> existingBudgetList;

    private void givenBudgets(Budget... budgetList) {
        existingBudgetList = Arrays.asList(budgetList);
        when(budgets.all()).thenReturn(existingBudgetList);
    }

    @Test
    public void add_budget() {
        Budget budget = new Budget("2018-07", 200d);

        budgets.add(budget);

        verify(budgetRepository).save(budget);
    }

    @Test
    public void get_all_budgets() {
        givenBudgets(new Budget("2018-07", 200d));

        List<Budget> budgetList = budgets.all();

        assertThat(budgetList).isEqualTo(existingBudgetList);
    }

}
