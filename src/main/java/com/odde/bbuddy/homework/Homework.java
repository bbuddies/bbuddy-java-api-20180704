package com.odde.bbuddy.homework;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Homework {
    
    public String getNowString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
        return LocalDateTime.now().format(formatter);
    }
}