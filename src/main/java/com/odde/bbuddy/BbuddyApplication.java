package com.odde.bbuddy;

import com.odde.bbuddy.homework.Budgets;
import com.odde.bbuddy.repository.Budget;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import com.odde.bbuddy.homework.Homework;;import java.util.ArrayList;

@SpringBootApplication
public class BbuddyApplication {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    public static void main(String[] args) {
        // Homework hw = new Homework();
        // System.out.println("Get date now");
        // System.out.print(hw.getNowString());
        ArrayList<Budget> budgets = new ArrayList<>();
        budgets.add(new Budget("2018-07", 3100));
        budgets.add(new Budget("2018-09", 3000));
        Budgets b = new Budgets(budgets);
        int result = b.queryByDate("2017-06-01","2018-09-15");
        System.out.println("Total = "+ result);

        //SpringApplication.run(BbuddyApplication.class, args);
    }
}


