package com.penguin.mind.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.penguin.mind.mapper.PenguinClassMapper;
import com.penguin.mind.domain.PenguinClass;
import com.penguin.mind.service.IPenguinClassService;

/**
 * 企鹅类型Service业务层处理
 * 
 * @author nian
 * @date 2026-04-19
 */
@Service
public class PenguinClassServiceImpl implements IPenguinClassService 
{
    @Autowired
    private PenguinClassMapper penguinClassMapper;

    /**
     * 查询企鹅类型
     * 
     * @param classId 企鹅类型主键
     * @return 企鹅类型
     */
    @Override
    public PenguinClass selectPenguinClassByClassId(Long classId)
    {
        return penguinClassMapper.selectPenguinClassByClassId(classId);
    }

    /**
     * 查询企鹅类型列表
     * 
     * @param penguinClass 企鹅类型
     * @return 企鹅类型
     */
    @Override
    public List<PenguinClass> selectPenguinClassList(PenguinClass penguinClass)
    {
        return penguinClassMapper.selectPenguinClassList(penguinClass);
    }

    /**
     * 新增企鹅类型
     * 
     * @param penguinClass 企鹅类型
     * @return 结果
     */
    @Override
    public int insertPenguinClass(PenguinClass penguinClass)
    {
        return penguinClassMapper.insertPenguinClass(penguinClass);
    }

    /**
     * 修改企鹅类型
     * 
     * @param penguinClass 企鹅类型
     * @return 结果
     */
    @Override
    public int updatePenguinClass(PenguinClass penguinClass)
    {
        return penguinClassMapper.updatePenguinClass(penguinClass);
    }

    /**
     * 批量删除企鹅类型
     * 
     * @param classIds 需要删除的企鹅类型主键
     * @return 结果
     */
    @Override
    public int deletePenguinClassByClassIds(Long[] classIds)
    {
        return penguinClassMapper.deletePenguinClassByClassIds(classIds);
    }

    /**
     * 删除企鹅类型信息
     * 
     * @param classId 企鹅类型主键
     * @return 结果
     */
    @Override
    public int deletePenguinClassByClassId(Long classId)
    {
        return penguinClassMapper.deletePenguinClassByClassId(classId);
    }
}
