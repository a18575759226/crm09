package com._520it.crm.service;
import java.util.List;
import com._520it.crm.domain.Department;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

public interface IDepartmentService {
	int deleteByPrimaryKey(Long id);
    int insert(Department record);
    Department selectByPrimaryKey(Long id);
    List<Department> selectAll();
    int updateByPrimaryKey(Department record);
	PageResult queryPage(QueryObject qo);

    /**
     * 查询所有未撤销的部门信息
     * @return
     */
    List<Department> selectAllonSteate();
}
