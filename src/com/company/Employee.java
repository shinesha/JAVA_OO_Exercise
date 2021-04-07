package com.company;

public abstract class Employee {
    int salary;
    String grade;

    public void label(){
        System.out.println("com.company.Employee's data:\n");
    }

    abstract int getSalary();

    abstract void setSalary(int salary);

    abstract String getGrade();

    abstract void setGrade(String grade);
}
