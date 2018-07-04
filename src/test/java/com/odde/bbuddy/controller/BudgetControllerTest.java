package com.odde.bbuddy.controller;

import com.odde.bbuddy.domain.Accounts;
import com.odde.bbuddy.domain.Budgets;
import com.odde.bbuddy.repository.Budget;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BudgetControllerTest {
    private Budgets budgets = mock(Budgets.class);
    private BudgetController controller = new BudgetController(budgets);
    private List<Budget> existingBudgetList;

    private void givenBudgets(Budget... budgetList) {
        existingBudgetList = Arrays.asList(budgetList);
        when(budgets.all()).thenReturn(existingBudgetList);
    }

    @Test
    void fetch_all_budgets() {
        givenBudgets(new Budget("08/2017", 10000));

        List<Budget> budgetList = controller.index();

        assertThat(budgetList).isEqualTo(existingBudgetList);
    }
}
