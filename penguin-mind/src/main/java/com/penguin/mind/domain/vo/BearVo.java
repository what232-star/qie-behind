package com.penguin.mind.domain.vo;

import com.penguin.mind.domain.Bear;
import lombok.Data;


@Data
public class BearVo extends Bear {
    //点位数量
    private Integer nodeCount;
}
