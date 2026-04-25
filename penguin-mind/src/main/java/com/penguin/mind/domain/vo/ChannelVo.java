package com.penguin.mind.domain.vo;

import com.penguin.mind.domain.Channel;
import com.penguin.mind.domain.Penguin;
import lombok.Data;


@Data
public class ChannelVo extends Channel {

    //企鹅对象
    private Penguin penguin;



}
