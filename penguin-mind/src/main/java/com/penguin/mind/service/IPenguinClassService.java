package com.penguin.mind.service;

import java.util.List;
import com.penguin.mind.domain.PenguinClass;

/**
 * 企鹅类型Service接口
 * 
 * @author nian
 * @date 2026-04-19
 */
public interface IPenguinClassService 
{
    /**
     * 查询企鹅类型
     * 
     * @param classId 企鹅类型主键
     * @return 企鹅类型
     */
    public PenguinClass selectPenguinClassByClassId(Long classId);

    /**
     * 查询企鹅类型列表
     * 
     * @param penguinClass 企鹅类型
     * @return 企鹅类型集合
     */
    public List<PenguinClass> selectPenguinClassList(PenguinClass penguinClass);

    /**
     * 新增企鹅类型
     * 
     * @param penguinClass 企鹅类型
     * @return 结果
     */
    public int insertPenguinClass(PenguinClass penguinClass);

    /**
     * 修改企鹅类型
     * 
     * @param penguinClass 企鹅类型
     * @return 结果
     */
    public int updatePenguinClass(PenguinClass penguinClass);

    /**
     * 批量删除企鹅类型
     * 
     * @param classIds 需要删除的企鹅类型主键集合
     * @return 结果
     */
    public int deletePenguinClassByClassIds(Long[] classIds);

    /**
     * 删除企鹅类型信息
     * 
     * @param classId 企鹅类型主键
     * @return 结果
     */
    public int deletePenguinClassByClassId(Long classId);
}
