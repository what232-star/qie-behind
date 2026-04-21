package com.penguin.mind.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import com.penguin.common.annotation.DataScope;
import com.penguin.common.annotation.Excel;
import com.penguin.common.core.domain.BaseEntity;

/**
 * 企鹅盲盒管理对象 tb_penguin
 * 
 * @author nian
 * @date 2026-04-19
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Penguin extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long penguinId;

    /** 企鹅名称 */
    @Excel(name = "企鹅名称")
    private String penguinName;

    /** 企鹅图片 */
    @Excel(name = "企鹅图片")
    private String penguinImage;

    /** 品牌 */
    @Excel(name = "品牌")
    private String brandName;

    /** 规格(净含量) */
    @Excel(name = "规格(净含量)")
    private String unit;

    /** 企鹅价格，单位分 */
    @Excel(name = "企鹅价格，单位分")
    private Long price;

    /** 企鹅类型id */
    @Excel(name = "企鹅类型id")
    private Long classId;

    /** 是否打折促销 */
    private Integer isDiscount;

}
