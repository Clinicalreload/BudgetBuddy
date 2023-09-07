package com.cody.finalprojectfinanceapp;

public class Data {
    private String expenses;
    private String sal;

    private static Data instance = new Data();

    private Data() {}

    public static Data getInstance() {
        return instance;
    }

    public String getExpenses() {
        return expenses;
    }

    public void setExpenses(String expenses) {
        this.expenses = expenses;
    }

    public String getSalary() {
        return sal;
    }

    public void setSalary(String salary) {
        this.sal = salary;
    }
}