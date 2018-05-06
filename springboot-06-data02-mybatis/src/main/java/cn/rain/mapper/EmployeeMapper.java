package cn.rain.mapper;

import cn.rain.bean.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * description: 配置文件的方式使用mybatis。
 *
 * @author 任伟
 * @date 2018/5/6 16:36
 */
@Mapper
public interface EmployeeMapper {

    Employee getEmpById(Integer id);
}
