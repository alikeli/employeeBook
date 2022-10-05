package pro.sky.course2.employeebook.service;

import org.springframework.stereotype.Service;
import pro.sky.course2.employeebook.Employee.Employee;

import java.util.List;
import java.util.Map;

@Service
public interface DepartmentService {

    Employee maxSalary(Integer department);

    Employee minSalary(Integer department);


    Map<Integer, List<Employee>> findAllByDepartment();

    List<Employee> findAllByDepartment(Integer department);

}
