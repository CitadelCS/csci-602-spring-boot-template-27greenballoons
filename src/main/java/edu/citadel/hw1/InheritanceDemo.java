package edu.citadel.hw1;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Collections;

public class InheritanceDemo {

    public static void main(String[] args){
        ArrayList<Employee> employees = new ArrayList<>();

        HourlyEmployee hourlyEmployee1 = new HourlyEmployee("John Doe", LocalDate.parse("2009-05-21"), 160.0, 50.5) {
            @Override
            public double getWageRate() {
                return super.getWageRate();
            }
        };

        HourlyEmployee hourlyEmployee2 = new HourlyEmployee("Jane Doe", LocalDate.parse("2005-09-01"), 80.0,  150.5) {
            @Override
            public double getWageRate() {
                return super.getWageRate();
            }
        };

        SalariedEmployee salariedEmployee1 = new SalariedEmployee("Moe Howard", LocalDate.parse("2004-01-01"), 75000.0) {
            @Override
            public double getAnnualSalary() {
                return super.getAnnualSalary();
            }
        };

        SalariedEmployee salariedEmployee2 = new SalariedEmployee("Curly Howard", LocalDate.parse("2018-01-01"), 105000.0) {
            @Override
            public double getAnnualSalary() {
                return super.getAnnualSalary();
            }
        };

        employees.add(hourlyEmployee1);
        employees.add(hourlyEmployee2);
        employees.add(salariedEmployee1);
        employees.add(salariedEmployee2);

        System.out.println("List of employees (before sorting)");
        for  (Employee employee : employees){
            System.out.println(employee.toString());
        }

        System.out.println();
        Collections.sort(employees);

        System.out.println("List of employees (after sorting)");
        for  (Employee employee : employees){
            System.out.println(employee.toString());
        }

        System.out.println();

        System.out.println("Monthly Pay");
        double totalPay = 0.0;
        for  (Employee employee : employees){
            System.out.printf("%s: $%,.2f\n", employee.getName(), employee.getMonthlyPay());
            totalPay += employee.getMonthlyPay();
        }

        System.out.printf("%s: $%,.2f\n", "Total Monthly Pay: ", totalPay);

    }
}
