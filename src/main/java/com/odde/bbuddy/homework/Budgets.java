package com.odde.bbuddy.homework;

import com.odde.bbuddy.repository.Budget;
import sun.util.resources.LocaleData;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Budgets {
    ArrayList<Budget> budgets = new ArrayList<Budget>();
    public Budgets(){

        budgets.add(new Budget("2018-07", 3100));
        budgets.add(new Budget("2018-09", 3000));
        for (Budget object: budgets) {
            System.out.println("Add : " + object.month + " " + object.amount );
        }

    }
    public int queryByDate (String start, String end){
        System.out.println("Query from " + start + " to " + end);
        Date startDate = converToDate(start);
        Date endDate = converToDate(end);
        int total = 0;
        Calendar cEnd = Calendar.getInstance();
        cEnd.setTime(endDate);
        cEnd.add(Calendar.DATE, 1);
        endDate = cEnd.getTime();
        while (!startDate.equals(endDate)){
            Calendar c = Calendar.getInstance();
            c.setTime(startDate);
            String m = c.get(Calendar.YEAR) + "-" + padding(c.get(Calendar.MONTH));
            Budget b = new Budget(m,0);
            //System.out.println(startDate);
            for (Budget budget: budgets) {
                if(budget.equals(b)) total += budget.perdate;
                //System.out.println("total="+total);
            }
            c.add(Calendar.DATE, 1);
            startDate = c.getTime();
        }

        return total;
    }

    private String padding(int i) {
        i += 1;
        if(i< 10) return "0" + i;
        return i+"";
    }

    public int query (String start, String end){
        System.out.println("Query from " + start + " to " + end);
        int[] startDate = splitDate(start);
        int[] endDate = splitDate(end);
        //System.out.println(startDate[0]);
        //System.out.println(endDate[0]);
        if((startDate[0] == endDate[0]) && (startDate[1] == endDate[1]))
        {
            return 0;
        }
        else
        for (Budget object: budgets) {
            System.out.println(object);
        }

        //if(startDate.)
        return 0;
    }

    private Date converToDate(String dateStr) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(dateStr);
            //System.out.println("date="+date);
        } catch (ParseException e) {
            return new Date();
        }
        return date;
    }

    private int[] splitDate(String dateStr) {
        String[] strDate = dateStr.split("-");
        int[] intDate = new int[3];
        intDate[0] = Integer.parseInt(strDate[0]);
        intDate[1] = Integer.parseInt(strDate[1]);
        intDate[2] = Integer.parseInt(strDate[2]);
        return intDate;
    }


}
