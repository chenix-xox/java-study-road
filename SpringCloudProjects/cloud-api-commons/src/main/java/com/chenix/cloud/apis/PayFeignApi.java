package com.chenix.cloud.apis;

import com.chenix.cloud.entities.PayDTO;
import com.chenix.cloud.resp.ResultData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Chenix
 * @create_date 2024/4/21 1:44
 */
@FeignClient(value = "cloud-payment-service")
public interface PayFeignApi {
    @GetMapping("/pay/getInfo")
    public String getInfo();

    @GetMapping(value = "/pay/get/{id}")
    public ResultData<PayDTO> getPayById(@PathVariable("id") Integer id);

    @PostMapping(value = "/add")
    public ResultData<String> addPay(@RequestBody PayDTO payDTO);
}