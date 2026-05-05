package com.penguin.applet.service.impl;

import cn.elegent.pay.CallBackService;
import com.penguin.applet.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CallBackServiceImpl implements CallBackService {

    @Autowired
    private OrderService orderService;

    @Override
    public void successPay(String orderSn) {
        log.info("支付成功回调！订单号: {}", orderSn);
        // 更新订单状态为已支付（status=1，payStatus=1）
        orderService.updateOrderStatus(orderSn, 1);
        // 这里可以补充发货等业务逻辑
    }

    @Override
    public void failPay(String orderSn) {
        log.info("支付失败回调！订单号: {}", orderSn);
        // 记录支付失败状态
        orderService.updateOrderStatus(orderSn, 2);
    }

    @Override
    public void successRefund(String orderSn) {
        log.info("退款成功回调！订单号: {}", orderSn);
        // 更新订单状态为退款成功
        orderService.updateOrderStatus(orderSn, 3);
    }

    @Override
    public void failRefund(String orderSn) {
        log.info("退款失败回调！订单号: {}", orderSn);
        // 记录退款失败状态
        orderService.updateOrderStatus(orderSn, 4);
    }
}
