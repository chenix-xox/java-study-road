package com.chenix.cloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Chenix
 * @create_date 2024/3/28 1:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayDTO implements Serializable {
    // 主键：支付记录ID
    private Integer id;

    // 支付流水号
    private String payNo;

    // 订单流水号
    private String orderNo;

    // 用户账号ID
    private Integer userId;

    // 交易金额
    private BigDecimal amount;
}

