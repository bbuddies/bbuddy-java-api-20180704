package com.odde.bbuddy.homework;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

 class BudgetTest {
    
    @Test
    public void result_is_zero_when_no_budget() {
        String start = "2018-06-01";
        String end = "2018-06-30";
        Budgets bd = new Budgets();
        int dt = bd.query(start, end);

        assertThat(0).isEqualTo(dt);
    }

}
