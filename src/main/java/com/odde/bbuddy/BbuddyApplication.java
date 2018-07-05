package com.odde.bbuddy;

import com.odde.bbuddy.homework.Budgets;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import com.odde.bbuddy.homework.Homework;;
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
        Budgets b = new Budgets();
        int result = b.queryByDate("2017-06-01","2018-09-15");
        System.out.println("Total = "+ result);

        //SpringApplication.run(BbuddyApplication.class, args);
    }
}


