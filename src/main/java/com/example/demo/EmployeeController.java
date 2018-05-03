package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(value="/employees")
public class EmployeeController {
    // 创建线程安全的Map
    static Map<Long, Employee> employees = Collections.synchronizedMap(new HashMap<>());
    @RequestMapping(value = "",method = RequestMethod.GET)
    public List<Employee> getEmployees(){
        List<Employee> employeeList = new ArrayList<>();
        for (Map.Entry<Long, Employee> e:employees.entrySet()) {
            employeeList.add(e.getValue());
        }
        return employeeList;
    }
    @RequestMapping(value="/", method= RequestMethod.POST)
    public String addEmployee(Employee employee){
        employees.put(employee.getId(),employee);
        return "success";
    }
}
