package com._520it.crm.mapper;

import com._520it.crm.domain.LatentNumber;
import com._520it.crm.query.LatentNumberQueryObject;

import java.util.List;

public interface LatentNumberMapper {
    List<LatentNumber> queryLatentNumber(LatentNumberQueryObject qo);
}
