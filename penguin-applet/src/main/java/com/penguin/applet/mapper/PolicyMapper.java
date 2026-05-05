package com.penguin.applet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.penguin.applet.domain.Policy;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PolicyMapper extends BaseMapper<Policy> {
}
