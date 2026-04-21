package com.penguin.mind.service;

import java.util.List;
import com.penguin.mind.domain.Penguin;

/**
 * 企鹅盲盒管理Service接口
 * 
 * @author nian
 * @date 2026-04-19
 */
public interface IPenguinService 
{
    /**
     * 查询企鹅盲盒管理
     * 
     * @param penguinId 企鹅盲盒管理主键
     * @return 企鹅盲盒管理
     */
    public Penguin selectPenguinByPenguinId(Long penguinId);

    /**
     * 查询企鹅盲盒管理列表
     * 
     * @param penguin 企鹅盲盒管理
     * @return 企鹅盲盒管理集合
     */
    public List<Penguin> selectPenguinList(Penguin penguin);

    /**
     * 新增企鹅盲盒管理
     * 
     * @param penguin 企鹅盲盒管理
     * @return 结果
     */
    public int insertPenguin(Penguin penguin);

    /**
     * 修改企鹅盲盒管理
     * 
     * @param penguin 企鹅盲盒管理
     * @return 结果
     */
    public int updatePenguin(Penguin penguin);

    /**
     * 批量删除企鹅盲盒管理
     * 
     * @param penguinIds 需要删除的企鹅盲盒管理主键集合
     * @return 结果
     */
    public int deletePenguinByPenguinIds(Long[] penguinIds);

    /**
     * 删除企鹅盲盒管理信息
     * 
     * @param penguinId 企鹅盲盒管理主键
     * @return 结果
     */
    public int deletePenguinByPenguinId(Long penguinId);
}
