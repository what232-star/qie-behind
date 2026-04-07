package com.penguin.mind.mapper;

import java.util.List;
import com.penguin.mind.domain.Region;
import com.penguin.mind.domain.vo.RegionVo;

/**
 * 区域管理Mapper接口
 * 
 * @author nian
 * @date 2026-04-04
 */
public interface RegionMapper 
{
    /**
     * 查询区域管理
     * 
     * @param id 区域管理主键
     * @return 区域管理
     */
    public Region selectRegionById(String id);

    /**
     * 查询区域管理列表
     * 
     * @param region 区域管理
     * @return 区域管理集合
     */
    public List<Region> selectRegionList(Region region);

    /**
     * 新增区域管理
     * 
     * @param region 区域管理
     * @return 结果
     */
    public int insertRegion(Region region);

    /**
     * 修改区域管理
     * 
     * @param region 区域管理
     * @return 结果
     */
    public int updateRegion(Region region);

    /**
     * 删除区域管理
     * 
     * @param id 区域管理主键
     * @return 结果
     */
    public int deleteRegionById(String id);

    /**
     * 批量删除区域管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRegionByIds(String[] ids);


    /**
     * 查询区域管理视图对象列表
     *
     * @param region 区域管理查询条件
     * @return 区域管理视图对象集合
     */
    public List<RegionVo> selectRegionVoList(Region region);
}
