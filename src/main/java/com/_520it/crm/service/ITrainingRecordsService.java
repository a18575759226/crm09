package com._520it.crm.service;
import com._520it.crm.domain.TrainingRecords;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface ITrainingRecordsService {
	int deleteByPrimaryKey(Long id);
    int insert(TrainingRecords record);
    TrainingRecords selectByPrimaryKey(Long id);
    List<TrainingRecords> selectAll();
    int updateByPrimaryKey(TrainingRecords record);
	PageResult queryPage(QueryObject qo);

    /**
     * 根据大客户id查询实训记录
     * @param id
     * @return
     */
    List<TrainingRecords> listRecordsByBigCustomerId(Long id);
}
