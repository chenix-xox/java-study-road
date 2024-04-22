package com.chenix.cloud.controller;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSON;
import com.chenix.cloud.apis.PayFeignApi;
import com.chenix.cloud.entities.PayDTO;
import com.chenix.cloud.resp.ResultData;
import com.chenix.cloud.resp.ReturnCodeEnum;
import jakarta.annotation.Resource;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Chenix
 * @create_date 2024/3/28 3:32
 */
@RestController
@RequestMapping("/feign/pay")
public class OrderController {
    @Resource
    private PayFeignApi payFeignApi;

    @PostMapping(value = "/add")
    public ResultData addOrder(PayDTO payDTO) {
        System.out.println("假装新增");
        return payFeignApi.addPay(payDTO);
    }

    @GetMapping(value = "/mylb")
    public String getInfo() {
        System.out.println("模拟getInfo");
        return payFeignApi.getInfo();
    }

    @GetMapping(value = "/get/{id}")
    public ResultData getPayById(@PathVariable("id") Integer id) {
        System.out.println("模拟getById");
        ResultData resultData = null;
        try {
            System.out.println("start at ： " + DateUtil.now());
            resultData = payFeignApi.getPayById(id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("end at ： " + DateUtil.now());
            ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
        }
        return resultData;
    }
}
