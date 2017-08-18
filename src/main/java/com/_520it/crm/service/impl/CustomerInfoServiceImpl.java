package com._520it.crm.service.impl;

import com._520it.crm.domain.CustomerInfo;
import com._520it.crm.domain.Employee;
import com._520it.crm.domain.PotentialCustomer;
import com._520it.crm.domain.Role;
import com._520it.crm.mapper.CustomerInfoMapper;
import com._520it.crm.mapper.EmployeeMapper;
import com._520it.crm.mapper.PotentialCustomerMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.CustomerInfoQueryObject;
import com._520it.crm.service.ICustomerInfoService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
@Service
public class CustomerInfoServiceImpl implements ICustomerInfoService {
	@Autowired
	private CustomerInfoMapper customerInfoMapper;

	@Autowired
	private EmployeeMapper employeeMapper;

	@Autowired
	private PotentialCustomerMapper potentialCustomerMapper;

	public int deleteByPrimaryKey(Long id) {
		return customerInfoMapper.deleteByPrimaryKey(id);
	}

	public int insert(CustomerInfo record) {
		//设置录入时间
		record.setInputTime(new Date());
		//获取到当前登录的员工
		Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
		//设置当前录入员工 默认为当前登录的员工
		record.setInputUser(employee);
		//设置负责人
		record.setPrincipal(employee);
		return customerInfoMapper.insert(record);
	}

	public CustomerInfo selectByPrimaryKey(Long id) {
		return customerInfoMapper.selectByPrimaryKey(id);
	}

	public List<CustomerInfo> selectAll() {
		return customerInfoMapper.selectAll();
	}

	public int updateByPrimaryKey(CustomerInfo record) {
		CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(record.getId());
		record.setInputTime(customerInfo.getInputTime());
		return customerInfoMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryPage(CustomerInfoQueryObject qo) {
		//获取到当前登录的用户
		Employee currentUser = (Employee) SecurityUtils.getSubject().getPrincipal();
		//判断当前用户是不是管理员 是的话不做任何操作 不是的话进行下一步操作
        if (!currentUser.isAdmin()){
            List<Role> roles = currentUser.getRoles();
            List<String> roleName = new ArrayList<>();
            for (Role role : roles) {
                roleName.add(role.getName());
            }
            //判断当前用户的角色里面是否含有 销售主管 角色 有的话不做任何操作 没有的话判断qo对象里面的inputUser_id设置为当前用户的ID
            if (!roleName.contains("销售主管")){
                qo.setInputUserId(currentUser.getId());
            }
        }
        Long count = customerInfoMapper.queryPageCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<CustomerInfo> result = customerInfoMapper.queryPageData(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}

    @Override
    public void becomeFormalStudent(Long customerInfoId) {
		customerInfoMapper.becomeFormalStudent(customerInfoId);
    }

    @Override
    public void moveToPotentialClientPool(Long customerInfoId) {
	    //获取到当前登录的用户
        Employee currentUser = (Employee) SecurityUtils.getSubject().getPrincipal();
	    //先根据ID获取到对应的潜在学员对象
        CustomerInfo info = customerInfoMapper.selectByPrimaryKey(customerInfoId);
        //创建一个学员池对象
        PotentialCustomer potentialCustomer = new PotentialCustomer();
        //给学员池对象设置属性
        potentialCustomer.setAge(info.getAge());//年龄
        potentialCustomer.setCustomerInfo_id(info.getId());//潜在学员时候的id
        potentialCustomer.setEmail(info.getEmail());//邮箱
        potentialCustomer.setInChargeUser(currentUser);//设置负责人
        potentialCustomer.setGender(info.getGender());//性别
        potentialCustomer.setInputTime(new Date());//录入时间
        potentialCustomer.setInputUser(currentUser);//设置录入人
        potentialCustomer.setName(info.getName());//学员姓名
        potentialCustomer.setQq(info.getQq());//设置qq
        potentialCustomer.setCustomerSource(info.getCustomerSource());//设置客户来源
        potentialCustomerMapper.insert(potentialCustomer);//添加到潜在学员池

        customerInfoMapper.moveToPotentialClientPool(customerInfoId);
    }

	@Override
	public void updateClassId(CustomerInfo customerInfo,Long customerInfoId) {
		CustomerInfo customerInfo1 = customerInfoMapper.selectByPrimaryKey(customerInfoId);
		customerInfo1.setClassId(customerInfo.getClassId());
		customerInfoMapper.updateByPrimaryKey(customerInfo1);
	}
	//休学
    /**
     * 更改客户的责任人
     * @param principalId
     * @param customerId
     */
    @Override
    public void updatePrincipal(Long principalId, Long customerId) {
        customerInfoMapper.updatePrincipal(principalId,customerId);
    }
    @Override
    public void quit(Long customerInfoId) {
        CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(customerInfoId);
        customerInfo.setState(4);
        customerInfoMapper.updateByPrimaryKey(customerInfo);


	}
	//贷款
	@Override
	public void loan(Long customerInfoId) {
		CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(customerInfoId);
		customerInfo.setWay("贷款");
		customerInfoMapper.updateByPrimaryKey(customerInfo);
	}

	//付款
	@Override
	public void affirmPay(Long customerInfoId, BigDecimal pay) {
		CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(customerInfoId);
		//已付=原来的已付+目前的付款
		BigDecimal yetPAY = customerInfo.getYetPay().add(pay);
		//已付的金额
		customerInfo.setYetPay(yetPAY);
		//待付金额
		customerInfo.setSurplus(customerInfo.getAllTuitiong().subtract(yetPAY));
		if(yetPAY.equals(customerInfo.getAllTuitiong())){
			customerInfo.setPayState("已缴清");
		}
		//更新
		customerInfoMapper.updateByPrimaryKey(customerInfo);

	}
}


