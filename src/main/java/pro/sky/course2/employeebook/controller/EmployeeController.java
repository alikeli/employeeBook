package pro.sky.course2.employeebook.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.course2.employeebook.Employee.Employee;
import pro.sky.course2.employeebook.service.EmployeeService;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }
    @GetMapping("/add")
    public Employee addEmployee(@RequestParam String firstName,
                                @RequestParam String lastName){
        return service.addEmployee(firstName,lastName);
    }
    @GetMapping("/delete")
    public Employee deleteEmployee(@RequestParam String firstName,
                                @RequestParam String lastName){
        return service.deleteEmployee(firstName,lastName);
    }
    @GetMapping("/find")
    public Employee findEmployee(@RequestParam String firstName,
                                @RequestParam String lastName){
        return service.findEmployee(firstName,lastName);
    }

    @GetMapping
    public Collection<Employee> findAll() {
        return service.findAll();
    }
}
