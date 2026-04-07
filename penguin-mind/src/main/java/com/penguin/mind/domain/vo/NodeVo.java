package com.penguin.mind.domain.vo;

import com.penguin.mind.domain.Bear;
import com.penguin.mind.domain.Node;
import com.penguin.mind.domain.Region;
import lombok.Data;

@Data
public class NodeVo extends Node {
    //设备数量
    private Integer vmCount;

    //区域信息
    private Region region;

    //熊熊合作商信息
    private Bear bear;


}
