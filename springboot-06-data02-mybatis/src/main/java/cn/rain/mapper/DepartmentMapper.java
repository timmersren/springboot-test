package cn.rain.mapper;

import cn.rain.bean.Department;
import org.apache.ibatis.annotations.*;

/**
 * description: 注解版的方式使用mybatis。
 * 注解版方式无需xxxMapper.xml编写sql语句，而是使用@Select、@Delete、@Update、@Insert注解编写sql。
 *
 * @author 任伟
 * @date 2018/5/6 15:15
 */
@Mapper
public interface DepartmentMapper {

    /**
     * 如果是表中的主键是自增主键，那么不做任何操作的情况下我们是无法获取新插入的那条数据的主键。
     * 使用@Options注解可以在插入完成后将新增的数据的主键给我们封装进插入操作时传入的javaBean中。
     * 其中@Options中的useGeneratedKeys属性默认值是false，即不不使用主键自生成，我们需要更改为true，
     * 另外，它的keyProperty属性是指定表中的主键的属性名，默认是"id"，如果我们表中的主键命名是"id"则
     * 可以省略该属性，否则要将主键的属性名进行配置。
     */
    @Options(useGeneratedKeys=true, keyProperty = "id")
    @Insert(" insert into department(department_name) values(#{departmentName}) ")
    int insertDept(Department department);

    @Delete(" delete from department where id = #{id} ")
    int deleteDept(Integer id);

    @Update(" update department set department_name = #{departmentName} where id = #{id} ")
    int updateDept(Department department);

    @Select(" select * from department where id = #{id} ")
    Department selectDeptById(Integer id);
}
