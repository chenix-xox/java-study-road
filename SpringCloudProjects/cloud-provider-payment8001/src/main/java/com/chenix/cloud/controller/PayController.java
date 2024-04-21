package com.chenix.cloud.controller;

import com.chenix.cloud.entities.Pay;
import com.chenix.cloud.entities.PayDTO;
import com.chenix.cloud.resp.ResultData;
import com.chenix.cloud.resp.ReturnCodeEnum;
import com.chenix.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
    public ResultData<String> addPay(@RequestBody Pay pay) {
        log.info("新增支付记录：" + pay.toString());
        int result = payService.add(pay);
        return ResultData.success("成功插入记录，返回值：" + result);
    }

    // 删除支付记录
    @Operation(summary = "删除支付记录", description = "根据ID删除一个支付记录并返回操作结果")
    @DeleteMapping(value = "/delete/{id}")
    public ResultData<String> deletePay(@PathVariable("id") Integer id) {
        int result = payService.delete(id);
        return ResultData.success("成功删除记录，返回值：" + result);
    }

    // 更新支付记录
    @Operation(summary = "更新支付记录", description = "更新一个支付记录并返回操作结果")
    @PutMapping(value = "/update")
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO) {
        log.info("修改的数据：" + payDTO);
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO, pay);
        int result = payService.update(pay);
        return ResultData.success("成功更新记录，返回值：" + result);
    }

    // 根据ID获取支付记录
    @Operation(summary = "根据ID获取支付记录", description = "根据ID检索支付记录详情")
    @GetMapping(value = "/get/{id}")
    public ResultData<Pay> getPayById(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("id不能为负数");
        }
        try {
            TimeUnit.SECONDS.sleep(61);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Pay pay = payService.getById(id);
        return ResultData.success(pay, "获取支付记录成功");
    }

    // 获取所有支付记录
    @Operation(summary = "获取所有支付记录", description = "检索所有支付记录列表")
    @GetMapping(value = "/all")
    public ResultData<List<Pay>> getAllPays() {
        List<Pay> pays = payService.getAll();
        return ResultData.success(pays, "成功获取所有支付记录");
    }

    @Operation(summary = "测试错误代码", description = "必报错")
    @GetMapping("/error")
    public ResultData<String> getError() {
        try {
            int age = 10 / 0;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.fail(ReturnCodeEnum.RC500);
        }
        return ResultData.success("谢谢测试");
    }

    @Value("${server.port}")
    private String port;


    @GetMapping("/getInfo")
    public String getInfo(@Value("${chenix.info}") String msg) {
        return msg + ":" + port;
    }
}
