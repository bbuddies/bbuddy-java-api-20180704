package com.odde.bbuddy.homework;

import com.odde.bbuddy.repository.Budget;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class BudgetsTest {

    @Test
    public void result_is_zero_when_no_budget() {
        ArrayList<Budget> budgets = new ArrayList<>();
        //budgets.add(new Budget("2018-07", 3100));
        //budgets.add(new Budget("2018-09", 3000));
        String start = "2018-06-01";
        String end = "2018-09-30";
        Budgets bd = new Budgets(budgets);
        int dt = bd.queryByDate(start, end);

        assertThat(0).isEqualTo(dt);
    }

    @Test
    public void result_is_1000_when_have_budget_ten_day_in_one_month() {
        ArrayList<Budget> budgets = new ArrayList<>();
        budgets.add(new Budget("2018-07", 3100));
        //budgets.add(new Budget("2018-09", 3000));
        String start = "2018-07-01";
        String end = "2018-07-10";
        Budgets bd = new Budgets(budgets);
        int dt = bd.queryByDate(start, end);

        assertThat(1000).isEqualTo(dt);
    }

    @Test
    public void result_is_3100_when_have_budget_1_month() {
        ArrayList<Budget> budgets = new ArrayList<>();
        budgets.add(new Budget("2018-07", 3100));
        //budgets.add(new Budget("2018-09", 3000));
        String start = "2018-07-01";
        String end = "2018-12-31";
        Budgets bd = new Budgets(budgets);
        int dt = bd.queryByDate(start, end);

        assertThat(3100).isEqualTo(dt);
    }

    @Test
    public void result_is_6300_when_have_budget_many_month() {
        ArrayList<Budget> budgets = new ArrayList<>();
        budgets.add(new Budget("2018-06", 3000));
        budgets.add(new Budget("2018-07", 3100));
        budgets.add(new Budget("2018-08", 3100));
        //budgets.add(new Budget("2018-09", 3000));
        String start = "2018-06-15";
        String end = "2018-08-15";
        Budgets bd = new Budgets(budgets);
        int dt = bd.queryByDate(start, end);

        assertThat(6200).isEqualTo(dt);
    }

    @Test
    public void result_error_when_no_start_or_end() {
        ArrayList<Budget> budgets = new ArrayList<>();
        budgets.add(new Budget("2018-06", 3000));
        budgets.add(new Budget("2018-07", 3100));
        budgets.add(new Budget("2018-08", 3100));
        //budgets.add(new Budget("2018-09", 3000));
        String start = "2018-06-15";
        String end = "";
        Budgets bd = new Budgets(budgets);
        int dt = bd.queryByDate(start, end);

        assertThat(-1).isEqualTo(dt);
    }
}
