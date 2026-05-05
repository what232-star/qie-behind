package com.penguin.applet.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName(value = "tb_penguin")
public class Penguin implements Serializable {

    @TableId(value = "penguin_id", type = IdType.ASSIGN_ID)
    private Long penguinId;

    @TableField(value = "penguin_name")
    private String penguinName;

    @TableField(value = "penguin_image")
    private String penguinImage;

    @TableField(value = "price")
    private Integer price;

    @TableField(value = "class_id")
    private Integer classId;

    @TableField(value = "is_discount")
    private boolean discount;

    @TableField(value = "unit")
    private String unit;

    @TableField(value = "brand_name")
    private String brandName;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    protected LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    protected LocalDateTime updateTime;

    @TableField(exist = false)
    private PenguinClass penguinClass;

}
