package pro.sky.course2.employeebook.Employee;

import java.util.Objects;

public class Employee {
    private final String firstName;
    private final String lastName;
    private final Double salary;
    private final Integer department;


    public Employee(String firstName, String lastName, double salary, Integer department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.department = department;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getFullName() {
        return firstName + " " + lastName;
    }
    public Double getSalary() {
        return salary;
    }
    public Integer getDepartment() {
        return department;
    }
    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary='" + salary + '\'' +
                ", department='" + department + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return getFirstName().equals(employee.getFirstName()) && getLastName().equals(employee.getLastName()) && getSalary().equals(employee.getSalary()) && getDepartment().equals(employee.getDepartment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getSalary(), getDepartment());
    }
}

