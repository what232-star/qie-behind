package com.penguin.applet.service;

import com.penguin.applet.domain.Order;

public interface OrderService {

    /**
     * 创建订单
     */
    String createOrder(String innerCode, Long penguinId, String openId);

    /**
     * 更新订单状态
     */
    void updateOrderStatus(String orderSn, Integer status);

    /**
     * 根据订单号查询订单
     */
    Order getOrderByOrderNo(String orderNo);
}
