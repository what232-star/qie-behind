package com.penguin.mind.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import com.penguin.common.annotation.DataScope;
import com.penguin.common.annotation.Excel;
import com.penguin.common.core.domain.BaseEntity;

/**
 * 自动补货任务对象 tb_job
 * 
 * @author nian
 * @date 2026-04-25
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Job extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 警戒值百分比 */
    @Excel(name = "警戒值百分比")
    private Long alertValue;

}
