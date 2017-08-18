package com._520it.crm.service;

import com._520it.crm.domain.LatentNumber;
import com._520it.crm.query.LatentNumberQueryObject;

import java.util.List;

/**
 * Created by liu on 2017/7/16.
 */
public interface ILatentNumberService {
    List<LatentNumber> queryLatentNumber(LatentNumberQueryObject qo);
}
