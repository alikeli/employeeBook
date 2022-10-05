package pro.sky.course2.employeebook.controller;

import pro.sky.course2.employeebook.service.DepartmentServiceImpl;

public class DepartmentControllerBuilder {
    private DepartmentServiceImpl departmentService;

    public DepartmentControllerBuilder setDepartmentService(DepartmentServiceImpl departmentService) {
        this.departmentService = departmentService;
        return this;
    }

    public DepartmentController createDepartmentController() {
        return new DepartmentController(departmentService);
    }
}