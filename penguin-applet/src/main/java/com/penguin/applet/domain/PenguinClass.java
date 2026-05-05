package com.penguin.applet.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "tb_penguin_class")
public class PenguinClass implements Serializable{

    @TableId(value = "class_id",type = IdType.AUTO)
    private Integer classId;

    @TableField(value = "class_name")
    private String className;

    @TableField(value = "parent_id")
    private Integer parentId;

}
