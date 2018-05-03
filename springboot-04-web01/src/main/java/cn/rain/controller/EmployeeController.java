package cn.rain.controller;

import cn.rain.dao.DepartmentDao;
import cn.rain.dao.EmployeeDao;
import cn.rain.entities.Department;
import cn.rain.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private DepartmentDao departmentDao;

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

    /**
     * 来到添加员工的页面。
     * @return 返回到添加页面
     */
    @GetMapping("/emp")
    public String toAddPage(Model model){
        // 查出所有的部门，在添加页面进行显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        // 返回添加页面emp/add.html
        return "emp/add";
    }

    /**
     * 添加员工，通过POST请求方式提交。
     * @param employee SpringMVC自动将请求参数和入参对象的属性进行绑定，要求请求参数的参数名
     *                 和javaBean中的属性名要一致。
     * @return 员工添加完成后，应该返回员工列表，而且应该是通过发送/emps请求来到员工列表页面，
     *         这就要求我们的返回值不能让Thymeleaf以为我们是要返回到某个静态资源，因此这里我们
     *         使用重定向来到/emps请求到的页面下（就相当于重新发送以便"localhost:8080/emps"请求），
     *         其中"/"表示当前项目路径，使用重定向应该以redirect:开始。
     *         另外，请求转发是以forward:开始。
     */
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        // 保存员工数据
        employeeDao.save(employee);
        // 重定向到员工列表
        return "redirect:/emps";
    }

    /**
     * 来到修改页面（即员工添加页面add.html），将员工信息查出并在页面回显。
     * 我们在@GetMapping("/emp/{id}")中写的"/{id}"是路径变量，可以通过@PathVariable获取。
     * @param id 路径变量，员工的id。
     * @param model 用于在页面中取出数据的模型对象。
     * @return 返回到修改页面（add.html修改和添加二合一的页面）
     */
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model){
        // 查出路径变量的员工信息
        Employee employee = employeeDao.get(id);
        // 将员工信息保存到session中用于回显
        model.addAttribute("emp", employee);
        //查出部门列表并保存
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        // 返回到修改页面（add.html修改和添加二合一的页面）
        return "emp/add";
    }

    /**
     * 修改员工，通过Put请求方式提交请求。
     * @param employee 修改后的员工信息
     * @return 返回员工列表
     */
    @PutMapping("/emp")
    public String updateEmp(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    /**
     * 删除员工，通过DELETE请求方式提交请求。
     * @param id
     * @return
     */
    @DeleteMapping("/emp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
