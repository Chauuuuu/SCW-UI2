package com.atguigu.scw.ui.service;

import com.atguigu.scw.common.bean.ResponseVo;
import com.atguigu.scw.ui.bean.TOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("SCW-ORDER")
public interface OrderControllerFeign {
    @PostMapping("/order/createOrder")
    public ResponseVo<String> createOrder(@RequestBody TOrder order);

    @PostMapping("/order/updateOrder")
    public String updateOrder(@RequestParam("orderNum") String orderNum, @RequestParam("status") String status);
}
