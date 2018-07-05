package com.odde.bbuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, LocalDate> {
}
