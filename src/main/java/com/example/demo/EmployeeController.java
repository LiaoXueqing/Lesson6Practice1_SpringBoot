package com.example.demo;

import org.springframework.web.bind.annotation.*;

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
//        Employee e1 = new Employee(0,"王小明",20,"男");
//        Employee e2 = new Employee(1,"李小红",19,"女");
//        Employee e3 = new Employee(2,"张小智",15,"男");
//        Employee e4 = new Employee(3,"黄小刚",16,"男");
//        Employee e5 = new Employee(4,"周小霞",15,"女");
//        employeeList.add(e1);
//        employeeList.add(e2);
//        employeeList.add(e3);
//        employeeList.add(e4);
//        employeeList.add(e5);
        return employeeList;
    }
    @RequestMapping(value="/", method= RequestMethod.POST)
    public String addEmployee(Employee employee){
        employees.put(employee.getId(),employee);
        return "success";
    }
    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public Employee getEmployeeById(@PathVariable Long id){
        return employees.get(id);
    }
    @RequestMapping(value="/{id}", method= RequestMethod.DELETE)
    public String deleteEmployeeById(@PathVariable Long id){
        employees.remove(id);
        return "success";
    }
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public String updateEmployee(@PathVariable Long id, @ModelAttribute Employee employee) {

        // 处理"/employees/{id}"的PUT请求，用来更新Employee信息

        Employee e = employees.get(id);

        e.setId(employee.getId());
        e.setName(employee.getName());
        e.setAge(employee.getAge());
        e.setGender(employee.getGender());

        employees.put(id, e);
        return "success";
    }
}
