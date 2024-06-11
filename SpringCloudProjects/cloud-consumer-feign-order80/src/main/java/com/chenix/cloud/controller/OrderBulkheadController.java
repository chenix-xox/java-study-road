package com.chenix.cloud.controller;

import com.chenix.cloud.apis.PayFeignApi;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author chenix
 * @date 2024.06.11 16:29
 * @description 隔离器测试controller
 */
@RestController
public class OrderBulkheadController {
    @Autowired
    private PayFeignApi payFeignApi;


//    /**
//     *(船的)舱壁,隔离
//     * @param id
//     * @return
//     */
//    @GetMapping(value = "/feign/pay/bulkhead/{id}")
//    @Bulkhead(name = "cloud-payment-service",fallbackMethod = "myBulkheadFallback",type = Bulkhead.Type.SEMAPHORE)
//    public String myBulkhead(@PathVariable("id") Integer id)
//    {
//        return payFeignApi.myBulkhead(id);
//    }
//    public String myBulkheadFallback(Throwable t)
//    {
//        return "myBulkheadFallback，隔板超出最大数量限制，系统繁忙，请稍后再试-----/(ㄒoㄒ)/~~";
//    }


    /**
     * (船的)舱壁,隔离,THREADPOOL
     * @param id
     * @return
     */
    @GetMapping(value = "/feign/pay/bulkhead/{id}")
    @Bulkhead(name = "cloud-payment-service",fallbackMethod = "myBulkheadPoolFallback",type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<String> myBulkheadTHREADPOOL(@PathVariable("id") Integer id)
    {
        System.out.println(Thread.currentThread().getName()+"\t"+"enter the method!!!");
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println(Thread.currentThread().getName()+"\t"+"exist the method!!!");

        return CompletableFuture.supplyAsync(() -> payFeignApi.myBulkhead(id) + "\t" + " Bulkhead.Type.THREADPOOL");
    }
    public CompletableFuture<String> myBulkheadPoolFallback(Integer id,Throwable t)
    {
        return CompletableFuture.supplyAsync(() -> "Bulkhead.Type.THREADPOOL，系统繁忙，请稍后再试-----/(ㄒoㄒ)/~~");
    }
}
