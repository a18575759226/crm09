package com._520it.crm.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.Role;
import com._520it.crm.mapper.EmployeeMapper;
import com._520it.crm.query.TaskQueryObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.crm.domain.Task;
import com._520it.crm.mapper.TaskMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.ITaskService;

@Service
public class TaskServiceImpl implements ITaskService {
    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    public int deleteByPrimaryKey(Long id) {
        return taskMapper.deleteByPrimaryKey(id);
    }

    public int insert(Task record) {
        record.setState(0);
        //获取到对象内的员工对象的ID
        Long empId = record.getProcessor().getId();
        //根据ID获取到员工对象 然后获取到员工对象内的部门ID
        Employee employee = employeeMapper.selectByPrimaryKey(empId);
        //给对象设置部门ID
        if (employee.getDept()!=null){
            record.setDeptId(employee.getDept().getId());
        }
        return taskMapper.insert(record);
    }

    public Task selectByPrimaryKey(Long id) {
        return taskMapper.selectByPrimaryKey(id);
    }

    public List<Task> selectAll() {
        return taskMapper.selectAll();
    }

    public int updateByPrimaryKey(Task record) {
        return taskMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult queryPage(TaskQueryObject qo) {
        //获取到当前登录的员工对象
        Employee currentUser = (Employee) SecurityUtils.getSubject().getPrincipal();

        //如果不是超级管理员的话进行下一步操作
        if (!currentUser.isAdmin()) {
            //获取到所有的角色信息
            List<Role> roles = currentUser.getRoles();
            //定义一个集合来接收所有的角色编码信息
            List<String> roleSn = new ArrayList<>();
            for (Role role : roles) {
                roleSn.add(role.getSn());
            }
            //判断当前用户的角色里面是否含有 主管 角色 含有的话查询出其部门下的所有员工的任务
            if (roleSn.contains("MARKET_CHARGE")) {
                //获取当前登录用户的部门ID
                Long deptId = currentUser.getDept().getId();
                //将部门ID设置到qo对象之内的部门ID字段之内
                qo.setDeptId(deptId);
            } else {
                //如果没有的话就直接按照ID员工ID查询出员工对应的任务 设置qo对象里面的员工的ID字段
                qo.setEmpId(currentUser.getId());
            }
        }
        Long count = taskMapper.queryPageCount(qo);
        if (count <= 0) {
            return new PageResult(0L, Collections.EMPTY_LIST);
        }
        List<Task> result = taskMapper.queryPageData(qo);
        PageResult pageResult = new PageResult(count, result);
        return pageResult;
    }

    @Override
    public void updateDefeat(Long taskId) {
        //首先根据ID获取到当前需要更改的任务
        Task task = taskMapper.selectByPrimaryKey(taskId);
        //将任务状态该为失败
        task.setState(2);
        //更新数据库之内的任务状态
        taskMapper.updateByPrimaryKey(task);
    }

    @Override
    public void updateFinish(Long taskId) {
        //首先根据ID获取到当前需要更改的任务
        Task task = taskMapper.selectByPrimaryKey(taskId);
        //将任务状态该为已完成
        task.setState(1);
        //更新数据库之内的任务状态
        taskMapper.updateByPrimaryKey(task);
    }
}
