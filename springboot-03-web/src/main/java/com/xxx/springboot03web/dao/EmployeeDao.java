package com.xxx.springboot03web.dao;

import com.xxx.springboot03web.pojo.Department;
import com.xxx.springboot03web.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 部门dao
 *
 * @author Chenix
 * @create 2024-02-20 22:14
 */
@Repository
public class EmployeeDao {
    private static Map<Integer, Employee> employees = null;


    private static DepartmentDao departmentDao = new DepartmentDao();

    public EmployeeDao(DepartmentDao departmentDao) {
        EmployeeDao.departmentDao = departmentDao;
    }

    private static Integer initId = 1007;

    static {
        employees = new LinkedHashMap<>();
        employees.put(1001, new Employee(1001, "1001员工", departmentDao.getDepartmentById(101), new Date()));
        employees.put(1002, new Employee(1002, "1002员工", departmentDao.getDepartmentById(102), new Date()));
        employees.put(1003, new Employee(1003, "1003员工", departmentDao.getDepartmentById(103), new Date()));
        employees.put(1004, new Employee(1004, "1004员工", departmentDao.getDepartmentById(104), new Date()));
        employees.put(1005, new Employee(1005, "1005员工", departmentDao.getDepartmentById(105), new Date()));
        employees.put(1006, new Employee(1006, "1006员工", departmentDao.getDepartmentById(106), new Date()));
    }



    /**
     * 获取所有数据
     */
    public Collection<Employee> getEmployees() {
        return employees.values();
    }

    /**
     * 根据id获取信息
     */
    public Employee getEmployeeById(Integer id) {
        return employees.get(id);
    }

    /**
     * 根据id删除信息
     */
    public void deleteEmployee(Integer id) {
        employees.remove(id);
    }

    /**
     * 修改信息
     */
    public void updateEmployee(Employee employee) {
        if (employee.getId() == null) {
            // 没有id，说明不是修改，只是新增
            employee.setId(initId++);
        }
        employees.put(employee.getId(), employee);
    }
}
