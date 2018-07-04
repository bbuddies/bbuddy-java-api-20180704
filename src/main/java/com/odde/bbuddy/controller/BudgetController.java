package com.odde.bbuddy.controller;

import com.odde.bbuddy.domain.Budgets;
import com.odde.bbuddy.repository.Budget;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/budget")
public class BudgetController {
    private Budgets budgets;

    public BudgetController(Budgets budgets) {
        this.budgets = budgets;
    }

    @GetMapping
    public List<Budget> index() {
        return budgets.all();
    }

    @PostMapping
    public Budget add(@Valid @RequestBody Budget budget) {
        return budgets.add(budget);
    }

}
