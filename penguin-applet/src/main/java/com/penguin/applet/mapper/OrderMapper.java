package com.penguin.applet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.penguin.applet.domain.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
