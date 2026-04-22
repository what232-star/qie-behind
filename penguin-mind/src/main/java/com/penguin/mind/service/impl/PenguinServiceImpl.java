package com.penguin.mind.service.impl;

import java.util.List;

import com.penguin.common.exception.ServiceException;
import com.penguin.common.utils.DateUtils;
import com.penguin.mind.service.IChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.penguin.mind.mapper.PenguinMapper;
import com.penguin.mind.domain.Penguin;
import com.penguin.mind.service.IPenguinService;

/**
 * 企鹅盲盒管理Service业务层处理
 * 
 * @author nian
 * @date 2026-04-19
 */
@Service
public class PenguinServiceImpl implements IPenguinService 
{
    @Autowired
    private PenguinMapper penguinMapper;

    @Autowired
    private IChannelService channelService;

    /**
     * 查询企鹅盲盒管理
     * 
     * @param penguinId 企鹅盲盒管理主键
     * @return 企鹅盲盒管理
     */
    @Override
    public Penguin selectPenguinByPenguinId(Long penguinId)
    {
        return penguinMapper.selectPenguinByPenguinId(penguinId);
    }

    /**
     * 查询企鹅盲盒管理列表
     * 
     * @param penguin 企鹅盲盒管理
     * @return 企鹅盲盒管理
     */
    @Override
    public List<Penguin> selectPenguinList(Penguin penguin)
    {
        return penguinMapper.selectPenguinList(penguin);
    }

    /**
     * 新增企鹅盲盒管理
     * 
     * @param penguin 企鹅盲盒管理
     * @return 结果
     */
    @Override
    public int insertPenguin(Penguin penguin)
    {
        penguin.setCreateTime(DateUtils.getNowDate());
        return penguinMapper.insertPenguin(penguin);
    }

    /**
     * 修改企鹅盲盒管理
     * 
     * @param penguin 企鹅盲盒管理
     * @return 结果
     */
    @Override
    public int updatePenguin(Penguin penguin)
    {
        penguin.setUpdateTime(DateUtils.getNowDate());
        return penguinMapper.updatePenguin(penguin);
    }

    /**
     * 批量删除企鹅盲盒管理
     * 
     * @param penguinIds 需要删除的企鹅盲盒管理主键
     * @return 结果
     */
    @Override
    public int deletePenguinByPenguinIds(Long[] penguinIds)
    {
        //1.判断是否关联了货道
        int count = channelService.countChannelByPenguinIds(penguinIds);
        if (count > 0){
            throw new ServiceException("企鹅盲盒关联了货道，无法删除");
        }


        //2.没有关联货道，才能删除

        return penguinMapper.deletePenguinByPenguinIds(penguinIds);
    }

    /**
     * 删除企鹅盲盒管理信息
     * 
     * @param penguinId 企鹅盲盒管理主键
     * @return 结果
     */
    @Override
    public int deletePenguinByPenguinId(Long penguinId)
    {
        return penguinMapper.deletePenguinByPenguinId(penguinId);
    }

    //批量新增企鹅
    @Override
    public int insertPenguins(List<Penguin> penguinList) {
        return penguinMapper.insertPenguins(penguinList);
    }
}
