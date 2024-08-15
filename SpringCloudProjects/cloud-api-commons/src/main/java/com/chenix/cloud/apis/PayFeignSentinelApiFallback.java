package com.chenix.cloud.apis;

import com.chenix.cloud.apis.PayFeignSentinelApi;
import com.chenix.cloud.resp.ResultData;
import com.chenix.cloud.resp.ReturnCodeEnum;
import org.springframework.stereotype.Component;

/**
 * @author chenix
 * @date 2024.08.15 14:06
 * @description 通用api_sentinel服务降级处理
 */
@Component
public class PayFeignSentinelApiFallback implements PayFeignSentinelApi {
    @Override
    public ResultData getPayByOrderNo(String orderNo) {
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(), "对方服务宕机或不可用，FallBack服务降级o(╥﹏╥)o");
    }
}
