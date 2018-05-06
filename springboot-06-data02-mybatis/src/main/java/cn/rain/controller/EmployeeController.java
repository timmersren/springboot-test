package cn.rain.controller;

import cn.rain.bean.Employee;
import cn.rain.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * description:通过配置文件的方式使用mybatis，这里直接注入EmployeeMapper，省略了中间Service层。
 *
 * @author 任伟
 * @date 2018/5/6 16:38
 */
@RestController
public class EmployeeController {
    @Autowired
    EmployeeMapper employeeMapper;

    @GetMapping("/emp/{id}")
    public Employee getEmp(@PathVariable("id") Integer id){
        return employeeMapper.getEmpById(id);
    }
}
