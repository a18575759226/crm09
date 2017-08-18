package com._520it.crm.mapper;

import com._520it.crm.domain.CheckTerm;
import com._520it.crm.query.CheckTermQueryObject;
import com._520it.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface CheckTermMapper {
    int deleteByPrimaryKey(Long id);
    int insert(CheckTerm record);
    CheckTerm selectByPrimaryKey(Long id);
    List<CheckTerm> selectAll();
	Long queryPageCount(QueryObject qo);
	List<CheckTerm> queryPageData(QueryObject qo);
    List<CheckTerm> querySignInTimeByEmpIdAndDayTime(@Param("beginDate") Date beginDate, @Param("endDate")Date endDate, @Param("empId")Long empId);
    List<CheckTerm> querySignOutTimeByEmpIdAndDayTime(@Param("beginDate") Date beginDate, @Param("endDate")Date endDate, @Param("empId")Long empId);
    void updateForSignOut(CheckTerm checkTerm);

    CheckTerm selectByEmpId(Long empId);

    void updateForPatch(CheckTerm checkTerm);

    CheckTerm selectByEmpIdAndDayTime(@Param("beginDate") Date beginDate, @Param("endDate")Date endDate, @Param("empId")Long empId);

    List<CheckTerm> selectByCondition(CheckTermQueryObject qo);
}