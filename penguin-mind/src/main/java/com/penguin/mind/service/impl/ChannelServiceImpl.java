package com.penguin.mind.service.impl;

import java.util.List;
import com.penguin.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.penguin.mind.mapper.ChannelMapper;
import com.penguin.mind.domain.Channel;
import com.penguin.mind.service.IChannelService;

/**
 * 售货机货道管理Service业务层处理
 * 
 * @author nian
 * @date 2026-04-12
 */
@Service
public class ChannelServiceImpl implements IChannelService 
{
    @Autowired
    private ChannelMapper channelMapper;

    /**
     * 查询售货机货道管理
     * 
     * @param id 售货机货道管理主键
     * @return 售货机货道管理
     */
    @Override
    public Channel selectChannelById(Long id)
    {
        return channelMapper.selectChannelById(id);
    }

    /**
     * 查询售货机货道管理列表
     * 
     * @param channel 售货机货道管理
     * @return 售货机货道管理
     */
    @Override
    public List<Channel> selectChannelList(Channel channel)
    {
        return channelMapper.selectChannelList(channel);
    }

    /**
     * 新增售货机货道管理
     * 
     * @param channel 售货机货道管理
     * @return 结果
     */
    @Override
    public int insertChannel(Channel channel)
    {
        channel.setCreateTime(DateUtils.getNowDate());
        return channelMapper.insertChannel(channel);
    }

    /**
     * 修改售货机货道管理
     * 
     * @param channel 售货机货道管理
     * @return 结果
     */
    @Override
    public int updateChannel(Channel channel)
    {
        channel.setUpdateTime(DateUtils.getNowDate());
        return channelMapper.updateChannel(channel);
    }

    /**
     * 批量删除售货机货道管理
     * 
     * @param ids 需要删除的售货机货道管理主键
     * @return 结果
     */
    @Override
    public int deleteChannelByIds(Long[] ids)
    {
        return channelMapper.deleteChannelByIds(ids);
    }

    /**
     * 删除售货机货道管理信息
     * 
     * @param id 售货机货道管理主键
     * @return 结果
     */
    @Override
    public int deleteChannelById(Long id)
    {
        return channelMapper.deleteChannelById(id);
    }
}
