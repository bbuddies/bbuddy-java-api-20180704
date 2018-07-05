package com.odde.bbuddy.domain;

import com.odde.bbuddy.repository.Budget;
import com.odde.bbuddy.repository.BudgetRepository;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
public class BudgetsTest {
    @Mock
    BudgetRepository budgetRepository;
    @InjectMocks
    Budgets budgets;
    private List<Budget> existingBudgetList;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    private void givenBudgets(Budget... budgetList) {
        existingBudgetList = Arrays.asList(budgetList);
        when(budgets.all()).thenReturn(existingBudgetList);
    }

    @Test
    public void add_budget() {
        Budget budget = new Budget(LocalDate.of(2018, 7, 1), 200d);

        budgets.add(budget);

        verify(budgetRepository).save(budget);
    }

    @Test
    public void get_all_budgets() {
        givenBudgets(new Budget(LocalDate.of(2018, 7, 1), 200d));

        List<Budget> budgetList = budgets.all();

        assertThat(budgetList).isEqualTo(existingBudgetList);
    }

    @Test
    @Parameters(
            {
                    "2018-07-01, 2018-07-31, 310",
                    "2018-07-10, 2018-07-31, 220",
                    "2018-06-01, 2018-07-31, 610",
                    "2018-06-01, 2018-07-30, 600",
                    "2018-06-02, 2018-07-30, 590",
                    "2018-01-01, 2018-10-31, 920",
            })
    public void search_total_amount(String startSearch, String endSearch, Double expectedAmount) {
        List<Budget> budgetList = new ArrayList<>();
        budgetList.add(new Budget(LocalDate.of(2018, 6, 1), 300d));
        budgetList.add(new Budget(LocalDate.of(2018, 7, 1), 310d));
        budgetList.add(new Budget(LocalDate.of(2018, 8, 1), 310d));

        when(budgetRepository.findAll()).thenReturn(budgetList);

        Double amount = budgets.searchBudget(startSearch, endSearch);

        assertThat(amount).isEqualTo(expectedAmount);
    }


    @Test
    @Parameters(
            {
                    "2018-07-01, 2018-07-31, 2018-07-01, true",
                    "2018-07-10, 2018-07-31, 2018-07-01, true",
                    "2018-07-01, 2018-07-31, 2018-07-01, true",
                    "2018-01-01, 2018-10-31, 2018-08-01, true",
                    "2018-07-01, 2018-07-31, 2018-08-01, false",
                    "2018-07-01, 2018-08-31, 2018-06-01, false"
            })
    public void test_is_between_date(String startSearch, String endSearch, String budgetDate, Boolean expectingResult) {
        LocalDate start = LocalDate.parse(startSearch);
        LocalDate end = LocalDate.parse(endSearch);
        LocalDate target = LocalDate.parse(budgetDate);

        boolean isBetween = budgets.isBetweenDate(start, end, target);
        assertThat(isBetween).isEqualTo(expectingResult);
    }

    @Test
    public void testAbc() {
        System.out.println(LocalDate.of(2018, 7, 1).isBefore(LocalDate.of(2018, 7, 1)));
    }

    @Test
    @Parameters(
            {
                    "2018-07-01, 2018-07-31, 2018-07-01, 310",
                    "2018-07-10, 2018-07-31, 2018-07-01, 220",
                    "2018-07-01, 2018-07-15, 2018-07-01, 150",
                    "2018-07-10, 2018-07-15, 2018-07-01, 60",
                    "2018-06-01, 2018-08-31, 2018-07-01, 310",
                    "2018-06-01, 2018-08-10, 2018-08-01, 100"
            })
    public void test_calculate_amount(String startSearch, String endSearch, String budgetMonth, Double expectedAmount) {
        LocalDate start = LocalDate.parse(startSearch);
        LocalDate end = LocalDate.parse(endSearch);
        LocalDate budget = LocalDate.parse(budgetMonth);
        Double amount = 310d;

        Double resultAmount = budgets.calculateAmount(start, end, new Budget(budget, amount));

        assertThat(resultAmount).isEqualTo(expectedAmount);
    }
}
