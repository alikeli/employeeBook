package pro.sky.course2.employeebook.service;

import org.springframework.stereotype.Service;
import pro.sky.course2.employeebook.Employee.Employee;
import pro.sky.course2.employeebook.exceptions.EmployeeAlreadyAddedException;
import pro.sky.course2.employeebook.exceptions.EmployeeNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
@Service

public class EmployeeServiceImpl implements EmployeeService {

    private final List<Employee> employeeList;

    public EmployeeServiceImpl() {
        this.employeeList = new ArrayList<>();
    }

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName,lastName);
        if (employeeList.contains(employee)){
            throw new EmployeeAlreadyAddedException();
        }
        employeeList.add(employee);
        return employee;
    }

    @Override
    public Employee deleteEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName,lastName);
        if (employeeList.contains(employee)){
            employeeList.remove(employee);
            return employee;
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName,lastName);
        if (employeeList.contains(employee)){
            return employee;
        }

        throw new EmployeeNotFoundException();
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableList(employeeList);
    }
}
