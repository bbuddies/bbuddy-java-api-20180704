package com.odde.bbuddy.repository;

import com.odde.bbuddy.domain.Accounts;
import com.odde.bbuddy.validator.Unique;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"month"})
)
public class Budget {

    public Budget(String mount, int amount){
        this.amount = amount;
        this.month = mount;
        splitDate(this.month,this.amount);
    }

    private void splitDate(String dateStr, int amount) {
        String[] strDate = dateStr.split("-");
        int m = Integer.parseInt(strDate[1]);
        if(m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 1 ) this.perdate = amount/31;
        else if(m == 4 || m == 6 || m == 9 || m == 11) this.perdate = amount/30;
        else this.perdate = amount/28;
    }

    public String month;

    public Integer amount;

    public Integer perdate;


    @Override
    public boolean equals(Object o){
        Budget b = (Budget)o;
        //System.out.println(b.month+"-"+this.month);
        if(b.month.equals(this.month)) return true;
        return false;
    }
}
