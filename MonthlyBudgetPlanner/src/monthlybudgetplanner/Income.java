/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monthlybudgetplanner;

import java.time.LocalDate;

/**
 *
 * @author iftekher
 */
public class Income {
   private double amount;
   private IncomeSource source;
   private String date;
   private String month; 

    public Income(double amount, IncomeSource source, String date, String month) {
        this.amount = amount;
        this.source = source;
        this.date = date;
        this.month = month;
    }

    public double getAmount() {
        return amount;
    }

    public IncomeSource getSource() {
        return source;
    }

    public String getDate() {
        return date;
    }

    public String getMonth() {
        return month;
    } 
}
