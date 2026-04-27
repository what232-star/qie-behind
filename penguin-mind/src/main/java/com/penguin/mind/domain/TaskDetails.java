package com.penguin.mind.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import com.penguin.common.annotation.DataScope;
import com.penguin.common.annotation.Excel;
import com.penguin.common.core.domain.BaseEntity;

/**
 * 工单详情对象 tb_task_details
 * 
 * @author nian
 * @date 2026-04-25
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TaskDetails extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 详情ID */
    private Long detailsId;

    /** 工单ID */
    @Excel(name = "工单ID")
    private Long taskId;

    /** 货道编号 */
    @Excel(name = "货道编号")
    private String channelCode;

    /** 补货期望容量 */
    @Excel(name = "补货期望容量")
    private Long expectCapacity;

    /** 企鹅ID */
    @Excel(name = "企鹅ID")
    private Long penguinId;

    /** 企鹅名称 */
    @Excel(name = "企鹅名称")
    private String penguinName;

    /** 企鹅图片 */
    @Excel(name = "企鹅图片")
    private String penguinImage;

}
