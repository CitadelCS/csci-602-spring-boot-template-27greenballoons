package edu.citadel.hw1;

import edu.citadel.hw1.Employee;

import java.time.LocalDate;
import java.util.Objects;

abstract class HourlyEmployee extends Employee {

    private double wageRate;
    private double hoursWorked;

    // Constructor
    public HourlyEmployee(String name, LocalDate hireDate, double hoursWorked, double wageRate){
        super(name, hireDate);
        this.hoursWorked = hoursWorked;
        this.wageRate = wageRate;
    }

    public double getWageRate() {
        return wageRate;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    @Override
    public double getMonthlyPay() {
        return hoursWorked * wageRate;
    }

    // actually format this
    @Override
    public String toString(){
        String name = getName();
        LocalDate date = getHireDate();
        double wageRate = getWageRate();
        double hoursWorked = getHoursWorked();

        // turn into sout if needed.
        return "HourlyEmployee[name=" + name + ", hireDate=" + date + ", wageRate=" +  wageRate + ", hoursWorked=" + hoursWorked + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        HourlyEmployee that = (HourlyEmployee) o;
        return Double.compare(wageRate, that.wageRate) == 0 && Double.compare(hoursWorked, that.hoursWorked) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), wageRate, hoursWorked);
    }
}
