package com._520it.crm.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.crm.domain.Schoolroom;
import com._520it.crm.mapper.SchoolroomMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.ISchoolroomService;
@Service
public class SchoolroomServiceImpl implements ISchoolroomService {
	@Autowired
	private SchoolroomMapper schoolroomMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return schoolroomMapper.deleteByPrimaryKey(id);
	}

	public int insert(Schoolroom record) {
		record.setState(0);
		return schoolroomMapper.insert(record);
	}

	public Schoolroom selectByPrimaryKey(Long id) {
		return schoolroomMapper.selectByPrimaryKey(id);
	}

	public List<Schoolroom> selectAll() {
		return schoolroomMapper.selectAll();
	}

	public int updateByPrimaryKey(Schoolroom record) {
		return schoolroomMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryPage(QueryObject qo) {
		Long count = schoolroomMapper.queryPageCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<Schoolroom> result = schoolroomMapper.queryPageData(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}

	@Override
	public void checkByschoolroomId(Long roomId) {
		//1表示启用教室
		schoolroomMapper.checkByschoolroomId(roomId,1);
	}

	@Override
	public List<Schoolroom> selectListForSchoolroomlistForm() {
		return schoolroomMapper.selectListForSchoolroomlistForm();
	}

	@Override
	public Long selectByPrimaryRealname(String roomname) {
		return schoolroomMapper.selectByPrimaryRealname(roomname);
	}
}
