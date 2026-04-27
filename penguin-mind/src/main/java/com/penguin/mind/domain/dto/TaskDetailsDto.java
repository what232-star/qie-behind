package com.penguin.mind.domain.dto;

import lombok.Data;


@Data
public class TaskDetailsDto {

    private String channelCode; // 货道编号
    private Long expectCapacity; //补货数量
    private Long penguinId; // 商品id
    private String penguinName; // 商品名称
    private String penguinImage;    //商品图片
}
