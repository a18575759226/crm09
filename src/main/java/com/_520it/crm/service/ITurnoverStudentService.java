package com._520it.crm.service;
import java.util.List;
import com._520it.crm.domain.TurnoverStudent;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

public interface ITurnoverStudentService {
	int deleteByPrimaryKey(Long id);
    int insert(TurnoverStudent record);
    TurnoverStudent selectByPrimaryKey(Long id);
    List<TurnoverStudent> selectAll();
    int updateByPrimaryKey(TurnoverStudent record);
	PageResult queryPage(QueryObject qo);

    void lose(TurnoverStudent turnoverStudent, Long customerInfoId);
}
