package com.odde.bbuddy.repository;

import com.odde.bbuddy.validator.Unique;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Budget {

    @Id
    private String month;
    private Integer amount;
}


