package com.penguin.applet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.penguin.applet.domain.Order;
import com.penguin.applet.domain.Penguin;
import com.penguin.applet.domain.Policy;
import com.penguin.applet.domain.VendingMachine;
import com.penguin.applet.mapper.OrderMapper;
import com.penguin.applet.service.OrderService;
import com.penguin.applet.service.PenguinService;
import com.penguin.applet.service.PolicyService;
import com.penguin.applet.service.VendingMachineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private PenguinService penguinService;

    @Autowired
    private VendingMachineService vendingMachineService;

    @Autowired
    private PolicyService policyService;

    @Override
    public String createOrder(String innerCode, Long penguinId, String openId) {
        // 1. 生成订单编号
        String orderNo = "ORD" + System.currentTimeMillis() + UUID.randomUUID().toString().substring(0, 4);
        
        // 2. 查询商品信息
        Penguin penguin = penguinService.getById(penguinId);
        if (penguin == null) {
            throw new RuntimeException("商品不存在");
        }
        
        // 3. 查询售货机信息（为了获取 addr 等点位信息）
        VendingMachine vm = vendingMachineService.lambdaQuery()
                .eq(VendingMachine::getInnerCode, innerCode)
                .one();
        if (vm == null) {
            throw new RuntimeException("售货机不存在");
        }

        // 4. 查询折扣策略
        int discount = 100; // 默认无折扣（100表示100%，即原价）
        if (vm.getPolicyId() != null) {
            Policy policy = policyService.getById(vm.getPolicyId());
            if (policy != null && policy.getDiscount() != null) {
                discount = policy.getDiscount();
            }
        }

        // 5. 计算折后价格（单位：分）
        Integer originalPrice = penguin.getPrice();
        Integer finalPrice = originalPrice * discount / 100;

        // 6. 创建订单对象
        Order order = new Order();
        order.setOrderNo(orderNo);
        order.setInnerCode(innerCode);
        order.setPenguinId(penguinId);
        order.setPenguinName(penguin.getPenguinName());
        order.setAmount(finalPrice); // 支付金额（折后价格，单位：分）
        order.setPrice(originalPrice);  // 商品原价
        order.setBill(discount); // 保存折扣信息到bill字段
        order.setPayStatus(0);               // 0-未支付
        order.setStatus(0);                  // 0-创建
        order.setPayType("2");               // 默认微信，后续可根据实际支付平台动态设置
        order.setOpenId(openId);
        order.setAddr(vm.getAddr());         // 设置点位地址
        order.setNodeId(vm.getNodeId());     // 设置点位ID
        
        // 7. 保存订单
        orderMapper.insert(order);
        
        log.info("订单创建成功，订单号: {}, 商品: {}, 原价: {}分, 折扣: {}%, 折后价: {}分", 
                orderNo, penguin.getPenguinName(), originalPrice, discount, finalPrice);
        
        return orderNo;
    }

    @Override
    public void updateOrderStatus(String orderNo, Integer status) {
        Order order = orderMapper.selectOne(
                new LambdaQueryWrapper<Order>()
                        .eq(Order::getOrderNo, orderNo)
        );
        if (order != null) {
            order.setStatus(status);
            orderMapper.updateById(order);
            log.info("更新订单状态成功，订单号: {}, 新状态: {}", orderNo, status);
        }
    }

    @Override
    public Order getOrderByOrderNo(String orderNo) {
        return orderMapper.selectOne(
                new LambdaQueryWrapper<Order>()
                        .eq(Order::getOrderNo, orderNo)
        );
    }
}
