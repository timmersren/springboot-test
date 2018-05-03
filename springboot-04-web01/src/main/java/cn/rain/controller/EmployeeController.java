package cn.rain.controller;

import cn.rain.dao.EmployeeDao;
import cn.rain.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

/**
 * description: 处理员工CRUD的Controller。
 *
 * @author 任伟
 * @date 2018/5/3 9:51
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    /**
     * 查询所有员工
     * @return 返回员工列表emp/list.html
     */
    @GetMapping("/emps")
    public String getEmpList(Model model){
        // 查出所有员工
        Collection<Employee> employees = employeeDao.getAll();
        // 将查询的结果放在请求域中
        model.addAttribute("emps", employees);

        // Thymeleaf默认会拼串：classpath:/templates/emp/xxx.html
        return "emp/list";
    }
}
