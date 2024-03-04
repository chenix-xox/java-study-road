package com.xxx.springboot03web.dao;

import com.xxx.springboot03web.pojo.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 部门dao
 *
 * @author Chenix
 * @create 2024-02-20 22:14
 */
@Repository
public class DepartmentDao {
    private static Map<Integer, Department> departments = null;

    private static Integer initId = 107;

    static {
        departments = new LinkedHashMap<>();
        departments.put(101, new Department(101, "101部"));
        departments.put(102, new Department(102, "102部"));
        departments.put(103, new Department(103, "103部"));
        departments.put(104, new Department(104, "104部"));
        departments.put(105, new Department(105, "105部"));
        departments.put(106, new Department(106, "106部"));
    }

    /**
     * 获取所有数据
     */
    public Collection<Department> getDepartments() {
        return departments.values();
    }

    /**
     * 根据id获取部门信息
     */
    public Department getDepartmentById(Integer id) {
        return departments.get(id);
    }

    /**
     * 根据id删除部门信息
     */
    public void deleteDepartment(Integer id) {
        departments.remove(id);
    }


    /**
     * 新增部门信息
     */
    public void updateDepartment(Department department) {
        if (department.getId() == null) {
            // 没有id，说明不是修改，只是新增
            department.setId(initId++);
        }
        departments.put(department.getId(), department);
    }

}
