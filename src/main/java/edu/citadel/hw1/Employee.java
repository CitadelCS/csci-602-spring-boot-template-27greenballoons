package edu.citadel.hw1;

import java.time.LocalDate;
import java.util.Objects;

// add implements Comparable<Employee>
abstract class Employee implements Comparable<Employee> {
    // Private Variables
    private String name;
    private LocalDate hireDate;

    public Employee(String name, LocalDate hireDate){
        this.name = name;
        this.hireDate = hireDate;
    }

    // Get Methods
    public String getName(){
        return name;
    }

    public LocalDate getHireDate(){
        return hireDate;
    }

    // abstract class
    public abstract double getMonthlyPay();

    public int compareTo(Employee o) {
        // compare salaries
        return Double.compare(this.getMonthlyPay(), o.getMonthlyPay());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) && Objects.equals(hireDate, employee.hireDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, hireDate);
    }
}
