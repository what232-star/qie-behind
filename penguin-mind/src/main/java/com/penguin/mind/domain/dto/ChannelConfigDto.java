package com.penguin.mind.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class ChannelConfigDto {
    private String innerCode;
    private List<ChannelPenguinDto> channelList;//货道Dto集合
}
