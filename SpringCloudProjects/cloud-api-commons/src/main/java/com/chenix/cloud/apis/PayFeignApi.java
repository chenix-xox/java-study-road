package com.chenix.cloud.apis;

import cn.hutool.core.util.IdUtil;
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
@FeignClient(value = "cloud-gateway")
//@FeignClient(value = "cloud-payment-service")
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

    /**
     * @description 测试限流接口
     * @param id 随便传ID
     * @return java.lang.String
     * @author chenix
     * @date 2024/7/25 10:53
     */
    @GetMapping(value = "/pay/ratelimit/{id}")
    public String myRateLimit(@PathVariable("id") Integer id);

    /**
     * Micrometer(Sleuth)进行链路监控的例子
     * @param id 传入ID
     */
    @GetMapping(value = "/pay/micrometer/{id}")
    public String myMicrometer(@PathVariable("id") Integer id);

    /**
     * @description 网关测试接口1
     * @author chenix
     * @date 2024/7/30 23:13
     */
    @GetMapping(value = "/pay/gateway/get/{id}")
    public ResultData getById(@PathVariable("id") Integer id);

    /**
     * @description 网关测试接口2
     * @author chenix
     * @date 2024/7/30 23:13
     */
    @GetMapping(value = "/pay/gateway/info")
    public ResultData<String> getGatewayInfo();
}
