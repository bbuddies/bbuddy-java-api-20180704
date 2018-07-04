package com.odde.bbuddy.repository;

import com.odde.bbuddy.validator.Unique;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class Budget {
    public Budget(String month, Integer amount) {
        this.month = month;
        this.amount = amount;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String month;
    private Integer amount;
}

