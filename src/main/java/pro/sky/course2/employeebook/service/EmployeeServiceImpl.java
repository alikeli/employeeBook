package pro.sky.course2.employeebook.service;

import org.springframework.stereotype.Service;
import pro.sky.course2.employeebook.Employee.Employee;
import pro.sky.course2.employeebook.exceptions.EmployeeAlreadyAddedException;
import pro.sky.course2.employeebook.exceptions.EmployeeNotFoundException;
import pro.sky.course2.employeebook.exceptions.EmployeeStorageIsFullException;
import pro.sky.course2.employeebook.exceptions.InvalidInputException;

import java.util.*;

import static org.apache.commons.lang3.StringUtils.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    int SIZE = 10;

    public Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, double salary, Integer department) {
        validateLatter(firstName, lastName);

        Employee employee = new Employee(firstName, lastName, salary, department);
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() >= SIZE) {
            throw new EmployeeStorageIsFullException();
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee deleteEmployee(String firstName, String lastName, double salary, Integer department) {
        validateLatter(firstName, lastName);

        Employee employee = new Employee(firstName, lastName, salary, department);
        if (employees.containsKey(employee.getFullName())) {
            employees.remove(employee.getFullName());
            return employees.remove(employee.getFullName());
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee findEmployee(String firstName, String lastName, double salary, Integer department) {
        validateLatter(firstName, lastName);

        Employee employee = new Employee(firstName, lastName, salary, department);
        if (employees.containsKey(employee.getFullName())) {
            return employees.get(employee.getFullName());
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public void validateLatter(String firstName, String lastName) {
        if (!(isAlpha(firstName) && isAlpha(lastName))) {
            throw new InvalidInputException();
        }

    }

    @Override
    public Collection<Employee> findAll() {
        return new ArrayList<>(employees.values());
    }
}
