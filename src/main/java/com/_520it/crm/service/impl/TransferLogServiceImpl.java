package com._520it.crm.service.impl;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.TransferLog;
import com._520it.crm.mapper.CustomerInfoMapper;
import com._520it.crm.mapper.TransferLogMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.TransferLogQueryObject;
import com._520it.crm.service.ITransferLogService;
import com._520it.crm.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class TransferLogServiceImpl implements ITransferLogService {
	@Autowired
	private TransferLogMapper transferLogMapper;
	@Autowired
    private CustomerInfoMapper customerInfoMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return transferLogMapper.deleteByPrimaryKey(id);
	}

	public int insert(TransferLog record) {
		return transferLogMapper.insert(record);
	}

	public TransferLog selectByPrimaryKey(Long id) {
		return transferLogMapper.selectByPrimaryKey(id);
	}

	public List<TransferLog> selectAll() {
		return transferLogMapper.selectAll();
	}

	public int updateByPrimaryKey(TransferLog record) {
		return transferLogMapper.updateByPrimaryKey(record);
	}

    /**
     * 获取移交记录的数据列表,如果当前用户是超级管理员或者市场部主管则可以查看所有的数据,否则只能查看自己移交的数据
     * @param qo
     * @return
     */
    @Override
	public PageResult queryPage(TransferLogQueryObject qo) {
		Long count = transferLogMapper.queryPageCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
        List<TransferLog> result = null;
        Employee currentUser = UserUtils.getCurrentUser();
        Set<String> userAllRoles = UserUtils.getUserAllRoles();
        if (currentUser.isAdmin() || userAllRoles.contains("MarketingManager")) {
            result = transferLogMapper.queryPageData(qo);
        }else {
            qo.setOverUserId(currentUser.getId());
            result = transferLogMapper.queryPageData(qo);
        }
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}

    /**
     * 移交潜在学员
     * @param transferLog
     */
    @Override
    public void transfer(TransferLog transferLog) {
        Employee currentUser = UserUtils.getCurrentUser();
        transferLog.setState(TransferLog.underReview);
        transferLog.setOverTime(new Date());
        transferLog.setCurrentPrincipal(currentUser);
        transferLogMapper.insert(transferLog);
    }

    /**
     * 审核移交记录,如果审核通过,变更潜在学员的负责人为接收人,如果审核不通过,不变更负责人
     * @param transferLog
     */
    @Override
    public void transferCheck(TransferLog transferLog) {
        Employee currentUser = UserUtils.getCurrentUser();
        transferLog.setChecker(currentUser);
        transferLog.setCheckTime(new Date());
        transferLogMapper.updateState(transferLog);
        //获取到审核之后的记录
        transferLog = transferLogMapper.selectByPrimaryKey(transferLog.getId());
        if (transferLog.getState() == TransferLog.approve) {//如果审核通过,修改客户的负责人
            customerInfoMapper.updatePrincipal(transferLog.getAccepter().getId(),transferLog.getCustomer().getId());
        }
    }

    /**
     * 通过客户id查询移交记录
     * @param customerId 客户id
     * @return 移交的记录
     */
    @Override
    public TransferLog getLogByCustomerId(Long customerId) {
        return transferLogMapper.getLogByCustomerId(customerId);
    }
}
