package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by asus on 2017/07/17.
 */
@Setter
@Getter
public class SalesStatement {
    //当前的id
    private Long id;
    //销售时间
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date salesTime;
    //销售金额
    private BigDecimal salesAmount;
}
