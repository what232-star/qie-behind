package com.penguin.mind.task;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.penguin.mind.service.IOrderService;

import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * 订单自动清理定时任务
 * 清理24小时前待支付的订单
 * 
 * @author nian
 * @date 2026-05-03
 */
@Slf4j
@Component("orderCleanupJob")
public class OrderCleanupJob {

    @Autowired
    private IOrderService orderService;

    /**
     * 清理24小时前待支付的订单
     * 通过系统管理->定时任务菜单配置调用: orderCleanupJob.cleanupPendingOrders
     */
    public void cleanupPendingOrders() {
        log.info("========== 开始执行订单清理任务 ==========");
        
        try {
            // 计算24小时前的时间
            LocalDateTime expireTime = LocalDateTime.now().minusHours(24);
            Date expireDate = Date.from(expireTime.atZone(ZoneId.systemDefault()).toInstant());
            
            log.info("准备清理创建时间早于 {} 的待支付订单", expireTime);
            
            // 通过Service层执行删除操作
            int deletedCount = orderService.deletePendingOrders(expireDate);
            
            log.info("========== 订单清理任务执行完成,共清理 {} 条订单 ==========", deletedCount);
        } catch (Exception e) {
            log.error("========== 订单清理任务执行失败 ==========", e);
            throw e;
        }
    }
}
