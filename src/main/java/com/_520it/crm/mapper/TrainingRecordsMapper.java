package com._520it.crm.mapper;

import com._520it.crm.domain.TrainingRecords;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface TrainingRecordsMapper {
    int deleteByPrimaryKey(Long id);
    int insert(TrainingRecords record);
    TrainingRecords selectByPrimaryKey(Long id);
    List<TrainingRecords> selectAll();
    int updateByPrimaryKey(TrainingRecords record);
	Long queryPageCount(QueryObject qo);
	List<TrainingRecords> queryPageData(QueryObject qo);

    /**
     * 通过大客户id查询对应大客户的所有实训记录
     * @param id
     * @return
     */
	List<TrainingRecords> listRecordsByBigCustomerId(Long id);
}