package com.penguin.mind.mapper;

import java.util.List;
import com.penguin.mind.domain.Channel;
import com.penguin.mind.domain.vo.ChannelVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 售货机货道管理Mapper接口
 * 
 * @author nian
 * @date 2026-04-12
 */
public interface ChannelMapper 
{
    /**
     * 查询售货机货道管理
     * 
     * @param id 售货机货道管理主键
     * @return 售货机货道管理
     */
    public Channel selectChannelById(Long id);

    /**
     * 查询售货机货道管理列表
     * 
     * @param channel 售货机货道管理
     * @return 售货机货道管理集合
     */
    public List<Channel> selectChannelList(Channel channel);

    /**
     * 新增售货机货道管理
     * 
     * @param channel 售货机货道管理
     * @return 结果
     */
    public int insertChannel(Channel channel);

    /**
     * 修改售货机货道管理
     * 
     * @param channel 售货机货道管理
     * @return 结果
     */
    public int updateChannel(Channel channel);

    /**
     * 删除售货机货道管理
     * 
     * @param id 售货机货道管理主键
     * @return 结果
     */
    public int deleteChannelById(Long id);

    /**
     * 批量删除售货机货道管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteChannelByIds(@Param("ids") Long[] ids);

    /**
     * 批量新增售货机货道管理
     *
     * @param channelList 售货机货道管理列表
     * @return 结果
     */
    public int batchInsertChannel(@Param("channelList") List<Channel> channelList);

    /**
     * 批量修改售货机货道管理
     *
     * @param channelList 售货机货道管理列表
     * @return 结果
     */
    int batchUpdateChannels( List<Channel> channelList);

    /**
     * 根据商品id来查询货道数量
     * @param penguinIds 商品ID数组
     * @return 货道数量
     */
    int countChannelByPenguinIds(@Param("penguinIds") Long[] penguinIds);

    /**
     * 根据设备编号来查询货道列表
     * @param innerCode 设备编号
     * @return 货道列表
     */
    List<ChannelVo> selectChannelVoListByInnerCode(@Param("innerCode") String innerCode);


    //根据售货机编号和货道编号来
    @Select("select * from tb_channel where inner_code=#{innerCode} and channel_code=#{channelCode}")
    Channel getChannelInfo(@Param("innerCode") String innerCode,@Param("channelCode") String channelCode );
}
