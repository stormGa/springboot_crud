package cn.lkhcoding.springboot.dao;

import cn.lkhcoding.springboot.entities.Department;
import cn.lkhcoding.springboot.entities.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {
    private static Map<Integer, Employee> employees = null;

    @Autowired
    private DepartmentDao departmentDao;


    static {

        employees = new HashMap<Integer, Employee>();
        employees.put(1001,new Employee(1001, "jack", "aa@163.com", 1, new Department(101, "AA"), new Date()));
        employees.put(1002,new Employee(1002, "mary", "bb@163.com", 1, new Department(102, "BB"), new Date()));
        employees.put(1003,new Employee(1003, "alice", "cc@163.com", 0, new Department(103, "CC"), new Date()));
        employees.put(1004,new Employee(1004, "abel", "dd@163.com", 0, new Department(104, "DD"), new Date()));
        employees.put(1005,new Employee(1005, "emma", "ee@163.com", 1, new Department(105, "EE"), new Date()));
    }
    private static Integer initId= 1006;

    public void save(Employee employee){
        if(employee.getId() == null){
            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.getDepartment(employee.getDepartment().getId()));
        employees.put(employee.getId(), employee);
    }
    //查询所有员工
    public Collection<Employee> getAll(){return employees.values();}
    //根据id员工
    public Employee getEmpById(Integer id){return employees.get(id);}
    //根据id删除员工
    public void deleteEmpById(Integer id){employees.remove(id);}
}
