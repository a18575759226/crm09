package com._520it.crm.service.impl;

import com._520it.crm.domain.LatentNumber;
import com._520it.crm.mapper.LatentNumberMapper;
import com._520it.crm.query.LatentNumberQueryObject;
import com._520it.crm.service.ILatentNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.SavepointManager;

import java.util.List;

@Service
public class LatentNumberServiceImpl implements ILatentNumberService {
    @Autowired
    private LatentNumberMapper latentNumberMapper;
    @Override
    public List<LatentNumber> queryLatentNumber(LatentNumberQueryObject qo) {
        return latentNumberMapper.queryLatentNumber(qo);
    }
}
