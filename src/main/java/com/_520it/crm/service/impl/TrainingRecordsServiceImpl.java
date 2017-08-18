package com._520it.crm.service.impl;

import com._520it.crm.domain.TrainingRecords;
import com._520it.crm.mapper.TrainingRecordsMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.ITrainingRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class TrainingRecordsServiceImpl implements ITrainingRecordsService {
	@Autowired
	private TrainingRecordsMapper trainingRecordsMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return trainingRecordsMapper.deleteByPrimaryKey(id);
	}

	public int insert(TrainingRecords record) {

		return trainingRecordsMapper.insert(record);
	}

	public TrainingRecords selectByPrimaryKey(Long id) {
		return trainingRecordsMapper.selectByPrimaryKey(id);
	}

	public List<TrainingRecords> selectAll() {
		return trainingRecordsMapper.selectAll();
	}

	public int updateByPrimaryKey(TrainingRecords record) {
		return trainingRecordsMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryPage(QueryObject qo) {
		Long count = trainingRecordsMapper.queryPageCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<TrainingRecords> result = trainingRecordsMapper.queryPageData(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}

    @Override
    public List<TrainingRecords> listRecordsByBigCustomerId(Long id) {
        return trainingRecordsMapper.listRecordsByBigCustomerId(id);
    }
}
