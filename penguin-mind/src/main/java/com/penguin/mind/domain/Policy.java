package com.penguin.mind.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import com.penguin.common.annotation.DataScope;
import com.penguin.common.annotation.Excel;
import com.penguin.common.core.domain.BaseEntity;

/**
 * 策略管理对象 tb_policy
 * 
 * @author nian
 * @date 2026-04-18
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Policy extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 策略id */
    private Long policyId;

    /** 策略名称 */
    @Excel(name = "策略名称")
    private String policyName;

    /** 策略方案，如：80代表8折 */
    @Excel(name = "策略方案，如：80代表8折")
    private Long discount;

}
