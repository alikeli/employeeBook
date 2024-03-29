package pro.sky.course2.employeebook.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.course2.employeebook.Employee.Employee;
import pro.sky.course2.employeebook.service.DepartmentService;
import pro.sky.course2.employeebook.service.DepartmentServiceImpl;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentServiceImpl departmentService;

    public DepartmentController(DepartmentServiceImpl departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee maxSalary(@RequestParam ("department") int department) {
        return departmentService.maxSalary(department);
    }
    @GetMapping("/min-salary")
    public Employee minSalary(@RequestParam ("department") int department) {
        return departmentService.minSalary(department);
    }
    @GetMapping(value = "/all", params = "department")
    public List<Employee> findAllByDepartment(@RequestParam("department") int department) {
        return departmentService.findAllByDepartment(department);
    }
    @GetMapping(value = "/all")
    public Map<Integer, List<Employee>> groupAllByDepartment() {
        return departmentService.groupAllByDepartment();
    }
}


