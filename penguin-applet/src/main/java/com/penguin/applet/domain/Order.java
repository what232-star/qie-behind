package com.penguin.applet.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName(value = "tb_order")
public class Order implements Serializable{
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;
    @TableField(value = "order_no")
    private String orderNo;
    @TableField(value = "third_no")
    private String thirdNo;
    @TableField(value = "inner_code")
    private String innerCode;

    @TableField(value = "channel_code")
    private String channelCode;
    
    private String addr;
    private Long penguinId;
    @TableField(value = "penguin_name")
    private String penguinName;
    
    private Integer classId;
    @TableField(value = "status")
    private Integer status;
    @TableField(value = "amount")
    private Integer amount;
    @TableField(value = "bill")
    private Integer bill;
    @TableField(value = "price")
    private Integer price;
    @TableField(value = "pay_type")
    private String payType;
    @TableField(value = "pay_status")
    private Integer payStatus;
    
    private Long nodeId;
    
    @TableField(value = "node_name")
    private String nodeName;
    
    private Long regionId;
    
    @TableField(value = "region_name")
    private String regionName;
    
    @TableField(value = "business_type")
    private Integer businessType;
    
    @TableField(value = "partner_id")
    private Integer partnerId;
    
    private String openId;
    
    @TableField(value = "cancel_desc")
    private String cancelDesc;
    
    @TableField(value = "create_time")
    private LocalDateTime createTime;
    
    @TableField(value = "update_time")
    private LocalDateTime updateTime;
}
