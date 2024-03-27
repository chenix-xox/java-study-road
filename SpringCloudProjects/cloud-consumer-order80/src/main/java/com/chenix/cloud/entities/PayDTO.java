package com.chenix.cloud.entities;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(title = "Pay实体类暴露表")
public class PayDTO implements Serializable {
    @Schema(title = "主键：支付记录ID")
    private Integer id;

    @Schema(title = "支付流水号")
    private String payNo;

    @Schema(title = "订单流水号")
    private String orderNo;

    @Schema(title = "用户账号ID")
    private Integer userId;

    @Schema(title = "交易金额")
    private BigDecimal amount;
}

