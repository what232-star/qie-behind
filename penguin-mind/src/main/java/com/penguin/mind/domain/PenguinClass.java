package com.penguin.mind.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import com.penguin.common.annotation.DataScope;
import com.penguin.common.annotation.Excel;
import com.penguin.common.core.domain.BaseEntity;

/**
 * 企鹅类型对象 tb_penguin_class
 * 
 * @author nian
 * @date 2026-04-19
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PenguinClass extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long classId;

    /** 企鹅类型 */
    @Excel(name = "企鹅类型")
    private String className;

    /** 上级id */
    private Long parentId;

}
