package com.penguin.applet.controller;

import cn.elegent.pay.ElegentPay;
import cn.elegent.pay.dto.PayRequest;
import cn.elegent.pay.dto.PayResponse;
import com.penguin.applet.domain.Order;
import com.penguin.applet.domain.Penguin;
import com.penguin.applet.domain.dto.PayDto;
import com.penguin.applet.service.OrderService;
import com.penguin.applet.service.PenguinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private ElegentPay elegentPay;

    @Autowired
    private OrderService orderService;

    @Autowired
    private PenguinService penguinService;

    @PostMapping("/requestPay/{tradeType}/{platform}")
    public PayResponse requestPay(@RequestBody PayDto payDto,
                                  @PathVariable("tradeType") String tradeType,
                                  @PathVariable("platform") String platform) {
        log.info("发起支付请求，tradeType: {}, platform: {}, payDto: {}", tradeType, platform, payDto);

        try {
            // 1. 查询商品信息（获取商品名称）
            Penguin penguin = penguinService.getById(payDto.getPenguinId());
            if (penguin == null) {
                throw new RuntimeException("商品不存在");
            }

            // 2. 创建订单（订单创建时会自动应用折扣策略，计算折后价格）
            String orderNo = orderService.createOrder(
                    payDto.getInnerCode(),
                    payDto.getPenguinId(),
                    payDto.getOpenId()
            );

            // 3. 从订单中获取折后价格
            Order order = orderService.getOrderByOrderNo(orderNo);
            if (order == null) {
                throw new RuntimeException("订单创建失败");
            }

            // 4. 构建支付请求（使用订单中的折后价格）
            PayRequest payRequest = new PayRequest();
            payRequest.setOrderSn(orderNo);
            payRequest.setBody(penguin.getPenguinName());
            payRequest.setTotalFee(order.getAmount()); // 使用订单中的折后价格（单位：分）
            payRequest.setOpenid(payDto.getOpenId());

            // 5. 调用支付
            PayResponse payResponse = elegentPay.requestPay(payRequest, tradeType, platform);

            log.info("支付请求成功，订单号: {}, 折后价格: {}分, 返回: {}", orderNo, order.getAmount(), payResponse);
            return payResponse;

        } catch (Exception e) {
            log.error("支付请求失败", e);
            throw new RuntimeException("支付请求失败: " + e.getMessage());
        }
    }
}