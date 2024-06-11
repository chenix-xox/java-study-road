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

    /**
     * @param id
     * @return java.lang.String
     * @description Resilience4j CircuitBreaker例子
     * @author chenix
     * @date 2024/6/6 16:01
     */
    @GetMapping(value = "/pay/circuit/{id}")
    public String myCircuit(@PathVariable("id") Integer id);

    /**
     * @param id
     * @return java.lang.String
     * @description Resilience4j Bulkhead例子
     * @author chenix
     * @date 2024/6/11 16:28
     */
    @GetMapping(value = "/pay/bulkhead/{id}")
    public String myBulkhead(@PathVariable("id") Integer id);
}
