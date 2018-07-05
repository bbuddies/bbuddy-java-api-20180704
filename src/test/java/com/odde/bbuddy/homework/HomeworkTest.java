package com.odde.bbuddy.homework;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class HomeworkTest {
    
    @Test
    void date_should_be_23_digit() {
        Homework hw = new Homework();        
        String dt = hw.getNowString();

        assertThat(23).isEqualTo(dt.length());
    }

}
