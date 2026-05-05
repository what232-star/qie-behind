package com.penguin.applet.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class PenguinVo implements Serializable{
    /**
     * 商品Id
     */
    private long penguinId;

    /**
     * 商品名称
     */
    private String penguinName;

    /**
     * 商品类别Id
     */
    private Integer classId;

    /**
     * 商品价格
     */
    private int price;

    /**
     * 真实售价
     */
    private int realPrice;

    /**
     * 货道余量
     */
    private int capacity;

    /**
     * 商品图片
     */
    private String image;

    /**
     * 净含量
     */
    private String unit;

    /**
     * 是否打折
     */
    private boolean discount;
}
