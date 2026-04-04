package com.penguin.mind.mapper;

import java.util.List;
import com.penguin.mind.domain.Bear;

/**
 * 熊熊合作商管理Mapper接口
 * 
 * @author nian
 * @date 2026-04-04
 */
public interface BearMapper 
{
    /**
     * 查询熊熊合作商管理
     * 
     * @param id 熊熊合作商管理主键
     * @return 熊熊合作商管理
     */
    public Bear selectBearById(String id);

    /**
     * 查询熊熊合作商管理列表
     * 
     * @param bear 熊熊合作商管理
     * @return 熊熊合作商管理集合
     */
    public List<Bear> selectBearList(Bear bear);

    /**
     * 新增熊熊合作商管理
     * 
     * @param bear 熊熊合作商管理
     * @return 结果
     */
    public int insertBear(Bear bear);

    /**
     * 修改熊熊合作商管理
     * 
     * @param bear 熊熊合作商管理
     * @return 结果
     */
    public int updateBear(Bear bear);

    /**
     * 删除熊熊合作商管理
     * 
     * @param id 熊熊合作商管理主键
     * @return 结果
     */
    public int deleteBearById(String id);

    /**
     * 批量删除熊熊合作商管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBearByIds(String[] ids);
}
