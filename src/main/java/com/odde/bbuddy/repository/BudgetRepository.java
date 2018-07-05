package com.odde.bbuddy.repository;

import com.odde.bbuddy.domain.Budgets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

@Transactional
public interface BudgetRepository extends JpaRepository<Budget, String> {
}
