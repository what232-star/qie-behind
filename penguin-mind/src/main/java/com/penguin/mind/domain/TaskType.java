package com.penguin.mind.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import com.penguin.common.annotation.DataScope;
import com.penguin.common.annotation.Excel;
import com.penguin.common.core.domain.BaseEntity;

/**
 * 工单类型对象 tb_task_type
 * 
 * @author nian
 * @date 2026-04-25
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TaskType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工单类型ID */
    private Long typeId;

    /** 工单类型名称 */
    @Excel(name = "工单类型名称")
    private String typeName;

    /** 工单类型分类 */
    @Excel(name = "工单类型分类")
    private Long type;

}
