package com._520it.crm.service;
import java.util.List;
import com._520it.crm.domain.Task;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.query.TaskQueryObject;

public interface ITaskService {
	int deleteByPrimaryKey(Long id);
    int insert(Task record);
    Task selectByPrimaryKey(Long id);
    List<Task> selectAll();
    int updateByPrimaryKey(Task record);
	PageResult queryPage(TaskQueryObject qo);

    /**
     * 指派任务完成失败
     * @param taskId
     */
    void updateDefeat(Long taskId);

    /**
     * 指派任务已完成
     * @param taskId
     */
    void updateFinish(Long taskId);
}
