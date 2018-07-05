package com.odde.bbuddy.repository;

import com.odde.bbuddy.domain.Budgets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

@Transactional
public interface BudgetRepository extends JpaRepository<Budget, String> {
    Budget findByMonth(String month);
//    @Query("SELECT CASE WHEN COUNT(budget) > 0 THEN 'true' ELSE 'false' END from Budget budget where budget.month = ?1")
//    boolean existsByMonth(String month);
}
