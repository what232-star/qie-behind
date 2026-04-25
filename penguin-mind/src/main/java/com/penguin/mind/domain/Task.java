package com.penguin.mind.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import com.penguin.common.annotation.DataScope;
import com.penguin.common.annotation.Excel;
import com.penguin.common.core.domain.BaseEntity;

/**
 * 工单对象 tb_task
 * 
 * @author nian
 * @date 2026-04-25
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Task extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工单ID */
    private Long taskId;

    /** 工单编号 */
    @Excel(name = "工单编号")
    private String taskCode;

    /** 工单状态 */
    @Excel(name = "工单状态")
    private Long taskStatus;

    /** 创建类型 */
    @Excel(name = "创建类型")
    private Long createType;

    /** 售货机编码 */
    @Excel(name = "售货机编码")
    private String innerCode;

    /** 执行人ID */
    @Excel(name = "执行人ID")
    private Long userId;

    /** 执行人名称 */
    @Excel(name = "执行人名称")
    private String userName;

    /** 所属区域ID */
    @Excel(name = "所属区域ID")
    private Long regionId;

    /** 备注 */
    @Excel(name = "备注")
    private String desc;

    /** 工单类型ID */
    @Excel(name = "工单类型ID")
    private Long productTypeId;

    /** 指派人ID */
    @Excel(name = "指派人ID")
    private Long assignorId;

    /** 地址 */
    @Excel(name = "地址")
    private String addr;

}
