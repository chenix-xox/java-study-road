package com.chenix.cloud.controller;

import com.chenix.cloud.entities.Pay;
import com.chenix.cloud.entities.PayDTO;
import com.chenix.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Chenix
 * @create_date 2024/3/28 1:15
 */
@RestController
@RequestMapping("/pay")
@Slf4j
@Tag(name = "支付微服务模块", description = "支付CRUD")
public class PayController {
    @Resource
    private PayService payService;

    @PostMapping(value = "/add")
    @Operation(summary = "新增", description = "新增支付流水方法，请求体参数为json")
    public String addPay(@RequestBody Pay pay) {
        log.info("新增支付记录：" + pay.toString());
        return "成功插入记录，返回值：" + payService.add(pay);
    }

    // 删除支付记录
    @Operation(summary = "删除支付记录", description = "根据ID删除一个支付记录并返回操作结果")
    @DeleteMapping(value = "/delete/{id}")
    public String deletePay(@PathVariable("id") Integer id) {
        int result = payService.delete(id);
        return "成功删除记录，返回值：" + result;
    }

    // 更新支付记录
    @Operation(summary = "更新支付记录", description = "更新一个支付记录并返回操作结果")
    @PutMapping(value = "/update")
    public String updatePay(@RequestBody PayDTO payDTO) {
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO, pay);
        int result = payService.update(pay);
        return "成功更新记录，返回值：" + result;
    }

    // 根据ID获取支付记录
    @Operation(summary = "根据ID获取支付记录", description = "根据ID检索支付记录详情")
    @GetMapping(value = "/get/{id}")
    public Pay getPayById(@PathVariable("id") Integer id) {
        log.info("获取支付记录：id：" + id);
        return payService.getById(id);
    }

    // 获取所有支付记录
    @Operation(summary = "获取所有支付记录", description = "检索所有支付记录列表")
    @GetMapping(value = "/all")
    public List<Pay> getAllPays() {
        return payService.getAll();
    }
}
