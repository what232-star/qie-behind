package com.penguin.applet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.penguin.applet.domain.Penguin;
import com.penguin.applet.domain.vo.PenguinVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PenguinMapper extends BaseMapper<Penguin> {

    @Select("SELECT p.penguin_id,penguin_name,penguin_image image,price,class_id,unit,c.capacity FROM tb_penguin p ," +
            "( " +
            "SELECT sku_id, SUM(`current_capacity`) capacity  FROM `tb_channel` WHERE `inner_code`=#{innerCode} AND sku_id>0 GROUP BY `sku_id` " +
            ") c " +
            "WHERE p.penguin_id=c.sku_id " +
            "ORDER BY c.capacity DESC")
    List<PenguinVo> findPenguinByInnerCode(String innerCode);

}
