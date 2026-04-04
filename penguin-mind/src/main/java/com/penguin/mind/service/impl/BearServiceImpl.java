package com.penguin.mind.service.impl;

import java.util.List;
import com.penguin.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.penguin.mind.mapper.BearMapper;
import com.penguin.mind.domain.Bear;
import com.penguin.mind.service.IBearService;

/**
 * 熊熊合作商管理Service业务层处理
 * 
 * @author nian
 * @date 2026-04-04
 */
@Service
public class BearServiceImpl implements IBearService 
{
    @Autowired
    private BearMapper bearMapper;

    /**
     * 查询熊熊合作商管理
     * 
     * @param id 熊熊合作商管理主键
     * @return 熊熊合作商管理
     */
    @Override
    public Bear selectBearById(String id)
    {
        return bearMapper.selectBearById(id);
    }

    /**
     * 查询熊熊合作商管理列表
     * 
     * @param bear 熊熊合作商管理
     * @return 熊熊合作商管理
     */
    @Override
    public List<Bear> selectBearList(Bear bear)
    {
        return bearMapper.selectBearList(bear);
    }

    /**
     * 新增熊熊合作商管理
     * 
     * @param bear 熊熊合作商管理
     * @return 结果
     */
    @Override
    public int insertBear(Bear bear)
    {
        bear.setCreateTime(DateUtils.getNowDate());
        return bearMapper.insertBear(bear);
    }

    /**
     * 修改熊熊合作商管理
     * 
     * @param bear 熊熊合作商管理
     * @return 结果
     */
    @Override
    public int updateBear(Bear bear)
    {
        return bearMapper.updateBear(bear);
    }

    /**
     * 批量删除熊熊合作商管理
     * 
     * @param ids 需要删除的熊熊合作商管理主键
     * @return 结果
     */
    @Override
    public int deleteBearByIds(String[] ids)
    {
        return bearMapper.deleteBearByIds(ids);
    }

    /**
     * 删除熊熊合作商管理信息
     * 
     * @param id 熊熊合作商管理主键
     * @return 结果
     */
    @Override
    public int deleteBearById(String id)
    {
        return bearMapper.deleteBearById(id);
    }
}
