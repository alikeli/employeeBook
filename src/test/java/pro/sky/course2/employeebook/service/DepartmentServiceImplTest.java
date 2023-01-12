package pro.sky.course2.employeebook.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.course2.employeebook.Employee.Employee;
import pro.sky.course2.employeebook.exceptions.EmployeeNotFoundException;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {
    @Mock
    private EmployeeServiceImpl employeeServiceImpl;
    @InjectMocks
    private DepartmentServiceImpl departmentServiceImpl;

    @BeforeEach
    public void beforeEach() {
        when(employeeServiceImpl.findAll()).thenReturn(
                List.of(
                        new Employee("John", "Dae", 4200, 1),
                        new Employee("Jane", "Doe", 1500, 4),
                        new Employee("John", "Dee", 4500, 4),
                        new Employee("Jane", "Dare", 5500, 3),
                        new Employee("Dan", "Dlo", 9500, 4),
                        new Employee("Jane", "Dael", 8500, 3)
                )
        );

    }

    @ParameterizedTest
    @MethodSource("provideParamsForMaxSalaryTest")
    public void shouldFindMaxSalaryTest(int department, Employee employee) {
        assertThat(departmentServiceImpl.maxSalary(department)).isEqualTo(employee);
    }

    public static Stream<Arguments> provideParamsForMaxSalaryTest() {
        return Stream.of(
                Arguments.of(1, new Employee("John", "Dae", 4200, 1)),
                Arguments.of(4, new Employee("Dan", "Dlo", 9500, 4)),
                Arguments.of(3, new Employee("Jane", "Dael", 8500, 3)));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForMinSalaryTest")
    public void shouldFindMinSalaryTest(int department, Employee employee) {
        assertThat(departmentServiceImpl.minSalary(department)).isEqualTo(employee);
    }


    public static Stream<Arguments> provideParamsForMinSalaryTest() {
        return Stream.of(
                Arguments.of(1, new Employee("John", "Dae", 4200, 1)),
                Arguments.of(4, new Employee("Jane", "Doe", 1500, 4)),
                Arguments.of(3, new Employee("Jane", "Dare", 5500, 3)));
    }

    @Test
    public void shouldThrowEmployeeNotFoundExceptionWhenMaxSalaryTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class).isThrownBy(() -> departmentServiceImpl.maxSalary(5));
    }

    @Test
    public void shouldThrowEmployeeNotFoundExceptionWhenTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class).isThrownBy(() -> departmentServiceImpl.minSalary(2));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForFindAllByDepartmentTest")
    public void shouldFindAllByDepartmentTest(int department, Collection<Employee> employee) {
        assertThat(departmentServiceImpl.findAllByDepartment(department)).containsExactlyInAnyOrderElementsOf(employee);
    }

    public static Stream<Arguments> provideParamsForFindAllByDepartmentTest() {
        return Stream.of(
                Arguments.of(2, Collections.emptyList()),
                Arguments.of(4, List.of(
                        new Employee("Jane", "Doe", 1500, 4),
                        new Employee("John", "Dee", 4500, 4),
                        new Employee("Dan", "Dlo", 9500, 4)

                )),
                Arguments.of(3, List.of(
                        new Employee("Jane", "Dare", 5500, 3),
                        new Employee("Jane", "Dael", 8500, 3)))
        );
    }

    @Test
    public void shouldGroupAllByDepartmentTest() {
        assertThat(departmentServiceImpl.groupAllByDepartment()).containsExactlyInAnyOrderEntriesOf(
                Map.of(4, List.of(
                        new Employee("Jane", "Doe", 1500, 4),
                        new Employee("John", "Dee", 4500, 4),
                        new Employee("Dan", "Dlo", 9500, 4)

                ),
                        3, List.of(
                        new Employee("Jane", "Dare", 5500, 3),
                        new Employee("Jane", "Dael", 8500, 3)
                        ),
                        1, List.of(new Employee("John", "Dae", 4200, 1))
                )
        );

    }


}
