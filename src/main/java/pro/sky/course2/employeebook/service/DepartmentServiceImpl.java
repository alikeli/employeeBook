package pro.sky.course2.employeebook.service;

import pro.sky.course2.employeebook.Employee.Employee;
import pro.sky.course2.employeebook.exceptions.EmployeeNotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeServiceImpl employeeServiceImpl;

    public DepartmentServiceImpl(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;


    }

    @Override
    public Employee maxSalary(Integer department) {
        return employeeServiceImpl.findAll().stream()
                .filter(employee -> employee.getDepartment().equals(department))
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Employee minSalary(Integer department) {
        return employeeServiceImpl.findAll().stream()
                .filter(employee -> employee.getDepartment().equals(department))
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public List<Employee> findAllByDepartment(Integer department) {
        return employeeServiceImpl.findAll().stream()
                .filter(employee -> employee.getDepartment().equals(department))
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> findAllByDepartment() {
        return employeeServiceImpl.findAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
