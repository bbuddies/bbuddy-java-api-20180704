package com.odde.bbuddy.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BudgetRepositoryTest {
    @Autowired
    BudgetRepository budgetRepository;

    @Test
    public void should_be_update_exiting_budget() {
        // arrange
        Budget budget = new Budget("2018-07", 20d);
        budgetRepository.save(budget);
        assertThat(budgetRepository.findById("2018-07")).isPresent().hasValue(budget);

        // act
        Budget updateBudget = new Budget("2018-07", 30d);
        budgetRepository.save(updateBudget);

        // assert
        assertThat(budgetRepository.findById("2018-07")).isPresent().hasValue(updateBudget);
    }
}