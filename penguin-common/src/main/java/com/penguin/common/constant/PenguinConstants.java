package com.penguin.common.constant;

/**
 * 设备状态常量
 */
public class PenguinConstants {

    /**
     * 工单状态：完成
     */
    public static final Long TASK_STATUS_FINISH = 4L;




//     员工启用

     public static final Long EMP_STATUS_NORMAL = 1L;

//     员工禁用

     public static final Long EMP_STATUS_DISABLE = 0L;

    /**
     * 售货机状态：未投放
     */
    public static final Long VM_STATUS_NODEPLOY = 0L;

    /**
     * 售货机状态：运营中
     */
    public static final Long VM_STATUS_RUNNING = 1L;

    /**
     * 售货机状态：撤机
     */
    public static final Long VM_STATUS_REVOKE = 3L;


    //角色编码:运营员

    public static final String ROLE_CODE_BUSINESS = "1002";
    //角色编码:维修员

    public static final String ROLE_CODE_OPERATOR = "1003";
}
