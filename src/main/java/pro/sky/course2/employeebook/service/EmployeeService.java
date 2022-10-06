package pro.sky.course2.employeebook.service;

import pro.sky.course2.employeebook.Employee.Employee;

import java.util.Collection;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName, double salary, Integer department);
    Employee deleteEmployee(String firstName, String lastName, double salary, Integer department);
    Employee findEmployee(String firstName, String lastName, double salary, Integer department);

    void validateFirstLatter(String firstName, String lastName);

    Collection<Employee> findAll();
}
