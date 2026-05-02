package com.penguin.mind.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.penguin.common.constant.PenguinConstants;
import com.penguin.mind.service.ITaskService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * 工单自动清理定时任务
 * 清理24小时前已完成的工单
 * 
 * @author nian
 * @date 2026-05-02
 */
@Slf4j
@Component("taskCleanupJob")
public class TaskCleanupJob {

    @Autowired
    private ITaskService taskService;

    /**
     * 清理24小时前已完成的工单
     * 通过系统管理->定时任务菜单配置调用: taskCleanupJob.cleanupCompletedTasks
     */
    public void cleanupCompletedTasks() {
        log.info("========== 开始执行工单清理任务 ==========");
        
        try {
            // 计算24小时前的时间
            LocalDateTime expireTime = LocalDateTime.now().minusHours(24);
            Date expireDate = Date.from(expireTime.atZone(ZoneId.systemDefault()).toInstant());
            
            log.info("准备清理完成时间早于 {} 的已完成工单", expireTime);
            
            // 通过Service层执行删除操作
            int deletedCount = taskService.deleteCompletedTasksByTime(expireDate, PenguinConstants.TASK_STATUS_FINISH);
            
            log.info("========== 工单清理任务执行完成,共清理 {} 条工单 ==========", deletedCount);
        } catch (Exception e) {
            log.error("========== 工单清理任务执行失败 ==========", e);
            throw e;
        }
    }
}
