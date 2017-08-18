package com._520it.crm.service.impl;
import com._520it.crm.domain.CheckTerm;
import com._520it.crm.domain.Employee;
import com._520it.crm.mapper.CheckTermMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.CheckTermQueryObject;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.ICheckTermService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com._520it.crm.util.DateUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class CheckTermServiceImpl implements ICheckTermService {
    @Autowired
    private CheckTermMapper checkTermMapper;

    public int deleteByPrimaryKey(Long id) {
        return checkTermMapper.deleteByPrimaryKey(id);
    }

    //签到业务
    public void signIn(HttpServletRequest request) {
        //拿到session中的用户名
        Employee currentEmp = (Employee) SecurityUtils.getSubject().getPrincipal();
        Date date = new Date();
        //去数据库查询判断当天是否有同个用户签到,有的话不让签到,没有的话签到
        List<CheckTerm> checks = checkTermMapper.querySignInTimeByEmpIdAndDayTime(DateUtil.getBeginDate(date), DateUtil.getEndDate(date), currentEmp.getId());
        if (checks == null || checks.size() == 0) {
            CheckTerm checkTerm = new CheckTerm();
            checkTerm.setE(currentEmp);
            checkTerm.setName(currentEmp.getUsername());
            //设置签到时间
            checkTerm.setSignInTime(date);
            //设置签到ip
            String empIp = request.getRemoteAddr();
            checkTerm.setSignIp(empIp);
            //设置状态  如果小时在9-18点之间 是正常考勤,否则迟到早退
            if (checkTerm.getSignInTime().getHours() > 9) {
                checkTerm.setState(CheckTerm.LATE);
            } else {
                checkTerm.setState(CheckTerm.NORMAL);
            }
            checkTermMapper.insert(checkTerm);
        } else {
            throw new RuntimeException("亲,您今日已经签到,请不要重复签到欧!^~^");
        }
    }

    //签退业务
    @Override
    public void signOut() {
//拿到session中的用户名
        Employee currentEmp = (Employee) SecurityUtils.getSubject().getPrincipal();
        Date date = new Date();
        //去数据库查询判断当天是否有同个用户签到,有的话提醒不要重复签退,没有的话签退
        List<CheckTerm> checks = checkTermMapper.querySignOutTimeByEmpIdAndDayTime(DateUtil.getBeginDate(date), DateUtil.getEndDate(date), currentEmp.getId());
        if (checks == null || checks.size() == 0) {//确认当天没有签退过
            //通过登录用户的id empId查询出用户的信息,下面进行更新操作
            CheckTerm checkTerm = checkTermMapper.selectByEmpIdAndDayTime(DateUtil.getBeginDate(date), DateUtil.getEndDate(date), currentEmp.getId());
          /*  if(checkTerm==null){
                checkTerm=new CheckTerm();
            }*/
            checkTerm.setSignOutTime(date);
            //设置状态  如果小时在9-18点之间 是正常考勤,否则迟到早退
            if(checkTerm.getState()!=CheckTerm.LATE){
                if (checkTerm.getSignOutTime().getHours() < 18) {
                    checkTerm.setState(CheckTerm.LATE);
                } else {
                    checkTerm.setState(CheckTerm.NORMAL);
                }
            }
            checkTermMapper.updateForSignOut(checkTerm);
        } else {
            throw new RuntimeException("亲,您今日已经签退,请不要重复签退欧!^~^");
        }
    }

    //补签业务
    @Override
    public void patch() {
        Employee currentEmp = (Employee) SecurityUtils.getSubject().getPrincipal();

        Date date = new Date();
        CheckTerm checkTerm = checkTermMapper.selectByEmpIdAndDayTime(DateUtil.getBeginDate(date), DateUtil.getEndDate(date), currentEmp.getId());
        checkTerm.setPatchTime(date);
        checkTermMapper.updateForPatch(checkTerm);
    }

    @Override
    public List<CheckTerm> selectByCondition(CheckTermQueryObject qo) {
        return checkTermMapper.selectByCondition(qo);
    }

    public CheckTerm selectByPrimaryKey(Long id) {
        return checkTermMapper.selectByPrimaryKey(id);
    }

    public List<CheckTerm> selectAll() {
        return checkTermMapper.selectAll();
    }

    @Override
    public PageResult queryPage(QueryObject qo) {
        Long count = checkTermMapper.queryPageCount(qo);
        if (count <= 0) {
            return new PageResult(0L, Collections.EMPTY_LIST);
        }
        List<CheckTerm> result = checkTermMapper.queryPageData(qo);
        PageResult pageResult = new PageResult(count, result);
        return pageResult;
    }


}
