package com._520it.crm.mapper;

import com._520it.crm.domain.Department;
import com._520it.crm.query.QueryObject;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Long id);
    int insert(Department record);
    Department selectByPrimaryKey(Long id);
    List<Department> selectAll();
    int updateByPrimaryKey(Department record);
	Long queryPageCount(QueryObject qo);
	List<Department> queryPageData(QueryObject qo);

    /**
     * 查询所有未撤销的部门信息
     * @return
     */
    List<Department> selectAllonSteate();
}