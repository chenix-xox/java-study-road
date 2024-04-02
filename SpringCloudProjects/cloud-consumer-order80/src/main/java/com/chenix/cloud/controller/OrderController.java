package com.chenix.cloud.controller;

import com.alibaba.fastjson2.JSON;
import com.chenix.cloud.entities.PayDTO;
import com.chenix.cloud.resp.ResultData;
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
@RequestMapping("/order/pay")
public class OrderController {
    public static final String PAYMENT_SRV_URL = "http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;

    @PostMapping(value = "/add")
    public ResultData addOrder(PayDTO payDTO) {
        return restTemplate.postForObject(PAYMENT_SRV_URL + "/pay/add", payDTO, ResultData.class);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteOrder(@PathVariable("id") Integer id) {
        restTemplate.delete(PAYMENT_SRV_URL + "/pay/delete/" + id, id);
    }

    @GetMapping(value = "/get/{id}")
    public ResultData getById(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(PAYMENT_SRV_URL + "/pay/get/" + id, ResultData.class, id);
    }

    @PutMapping(value = "/update")
    public ResultData update(@RequestBody PayDTO payDTO) {
        System.out.println("处理的数据：" + payDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(JSON.toJSON(payDTO).toString(), headers);
        ResponseEntity<ResultData> exchange = restTemplate.exchange(
                PAYMENT_SRV_URL + "/pay/update",
                HttpMethod.PUT,
                request,
                ResultData.class
        );
        return exchange.getBody();
    }

    @GetMapping(value = "/getAll")
    public ResultData getAll() {
        return restTemplate.getForObject(PAYMENT_SRV_URL + "/pay/all", ResultData.class);
    }

    @GetMapping(value = "/getInfo")
    public String getInfo() {
        return restTemplate.getForObject(PAYMENT_SRV_URL + "/pay/getInfo", String.class);
    }

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/discovery")
    public String discovery() {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            System.out.println(element);
        }

        System.out.println("===================================");

        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance element : instances) {
            System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t" + element.getUri());
        }

        return instances.get(0).getServiceId() + ":" + instances.get(0).getPort();
    }
}
