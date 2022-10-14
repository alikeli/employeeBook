package pro.sky.course2.employeebook.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.course2.employeebook.Employee.Employee;
import pro.sky.course2.employeebook.exceptions.EmployeeAlreadyAddedException;
import pro.sky.course2.employeebook.exceptions.EmployeeNotFoundException;
import pro.sky.course2.employeebook.exceptions.EmployeeStorageIsFullException;
import pro.sky.course2.employeebook.exceptions.InvalidInputException;

import java.util.Random;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class EmployeeServiceImplTest {
    private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
    //    @AfterEach
//    public void afterEach()  {
//        employeeService.findAll().forEach(employee -> employeeService.deleteEmployee(employee.getFirstName(),
//                employee.getLastName(),employee.getSalary(), employee.getDepartment()));
//    }
    @Test
    public void shouldThrowEmployeeNotFoundExceptionWhenListIsEmptyTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class).isThrownBy(() -> employeeService.findEmployee("empty", "empty", 0, 0));

    }

    @Test
    public void shouldAddEmployeeTest() {
        Employee employee = new Employee("Ivan", "Ivanov", 200, 2);
        assertThat(employeeService.findAll()).isEmpty();
        employeeService.addEmployee(employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment());
        assertThat(employeeService.findAll())
                .isNotEmpty()
                .hasSize(1)
                .contains(employee);
        assertThat(employeeService.findEmployee(employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment())).isEqualTo(employee);
    }


    private Employee addOneEmployeeForCheck(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName, 200, 2);
        int sizeBefore = employeeService.findAll().size();
        employeeService.addEmployee(employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment());
        assertThat(employeeService.findAll())
                .isNotEmpty()
                .hasSize(sizeBefore + 1)
                .contains(employee);
        assertThat(employeeService.findEmployee(employee.getFirstName(), employee.getLastName(),
                employee.getSalary(), employee.getDepartment())).isEqualTo(employee);
        return employee;
    }

    private Employee addOneEmployeeForCheck() {
        return addOneEmployeeForCheck("Ivan", "Ivanov");
    }

    @Test
    public void shouldThrowEmployeeAlreadyAddedExceptionWhenEmployeeIsExistTest() {
        Employee employee = addOneEmployeeForCheck();
        assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> employeeService.addEmployee(employee.getFirstName(), employee.getLastName(),
                        employee.getSalary(), employee.getDepartment()));


    }

    @Test
    public void shouldThrowEmployeeNotFoundExceptionWhenEmployeeIsNotExistTest() {
        Employee employee = addOneEmployeeForCheck();
        employeeService.deleteEmployee(employee.getFirstName(), employee.getLastName(),
                employee.getSalary(), employee.getDepartment());
        assertThat(employeeService.findAll()).isEmpty();
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.deleteEmployee(employee.getFirstName(), employee.getLastName(),
                        employee.getSalary(), employee.getDepartment()));

    }

    @Test
    public void shouldFindEmployeeTest() {
        Employee employee1 = addOneEmployeeForCheck("Ivan", "Petrov");

        assertThat(employeeService.findEmployee(employee1.getFirstName(), employee1.getLastName(), employee1.getSalary(), employee1.getDepartment()))
                .isEqualTo(employee1);

    }

    @Test
    public void shouldDeleteEmployeeTest() {
        Employee employee1 = addOneEmployeeForCheck("Ivan", "Petrov");
        employeeService.deleteEmployee(employee1.getFirstName(), employee1.getLastName(), employee1.getSalary(), employee1.getDepartment());

        assertThat(employeeService.findAll()).isEmpty();
    }

    public static Stream<Arguments> provideParamsForTest() {
        return Stream.of(
                Arguments.of("Ivan2", "Ivanov", 200, 2, InvalidInputException.class),
                Arguments.of("Ivan", "Ivan%", 200, 2, InvalidInputException.class),
                Arguments.of("Ivan", "Ivan-Ivanov1", 200, 2, InvalidInputException.class),
                Arguments.of("Ivan", "Ivan1", 200, 2, InvalidInputException.class),
                Arguments.of("Ivan", "Ivan@", 200, 2, InvalidInputException.class)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTest")
    public void shouldValidateLatterTest(String firstName, String lastName, double salary, int department, Class<Throwable> InvalidInputException) {
        assertThatExceptionOfType(InvalidInputException).isThrownBy(() -> employeeService.addEmployee(firstName, lastName, salary, department));

    }

    @Test
    public void ThrowEmployeeStorageIsFullExceptionWhenAdd11Employee() {

        for (int i = 0; i < 10; i++) {
            String c = RandomStringUtils.randomAlphabetic(1);
            String d = RandomStringUtils.randomAlphabetic(1);
            addOneEmployeeForCheck(c, d);
        }
        assertThatExceptionOfType(EmployeeStorageIsFullException.class).isThrownBy(() -> employeeService.addEmployee("Ivan", "Ivanov", 200, 2));
    }

}
