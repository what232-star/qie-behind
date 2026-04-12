package com.penguin.mind.service.impl;

import java.util.List;
import com.penguin.common.utils.DateUtils;
import com.penguin.mind.domain.Region;
import com.penguin.mind.domain.Role;
import com.penguin.mind.mapper.RegionMapper;
import com.penguin.mind.mapper.RoleMapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.penguin.mind.mapper.EmpMapper;
import com.penguin.mind.domain.Emp;
import com.penguin.mind.service.IEmpService;

/**
 * 人员列表Service业务层处理
 * 
 * @author nian
 * @date 2026-04-09
 */
@Service
public class EmpServiceImpl implements IEmpService 
{
    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private RegionMapper regionMapper;

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 查询人员列表
     * 
     * @param id 人员列表主键
     * @return 人员列表
     */
    @Override
    public Emp selectEmpById(Long id)
    {
        return empMapper.selectEmpById(id);
    }

    /**
     * 查询人员列表列表
     * 
     * @param emp 人员列表
     * @return 人员列表
     */
    @Override
    public List<Emp> selectEmpList(Emp emp)
    {
        return empMapper.selectEmpList(emp);
    }

    /**
     * 新增人员列表
     * 
     * @param emp 人员列表
     * @return 结果
     */
    @Override
    public int insertEmp(Emp emp)
    {
        //补充区域名称
        //补充角色信息
        Region region = regionMapper.selectRegionById(emp.getRegionId());
        if (region != null) {
            emp.setRegionName(region.getRegionName());
        }

        Role role = roleMapper.selectRoleByRoleId(emp.getRoleId());
        if (role != null) {
            emp.setRoleName(role.getRoleName());
            emp.setRoleCode(role.getRoleCode());
        }
        emp.setCreateTime(DateUtils.getNowDate());
        return empMapper.insertEmp(emp);
    }

    /**
     * 修改人员列表
     * 
     * @param emp 人员列表
     * @return 结果
     */
    @Override
    public int updateEmp(Emp emp)
    {
        Region region = regionMapper.selectRegionById(emp.getRegionId());

        if (region != null) {
            emp.setRegionName(region.getRegionName());
        }

        Role role = roleMapper.selectRoleByRoleId(emp.getRoleId());
        if (role != null) {
            emp.setRoleName(role.getRoleName());
            emp.setRoleCode(role.getRoleCode());
        }
        emp.setUpdateTime(DateUtils.getNowDate());
        return empMapper.updateEmp(emp);
    }

    /**
     * 批量删除人员列表
     * 
     * @param ids 需要删除的人员列表主键
     * @return 结果
     */
    @Override
    public int deleteEmpByIds(Long[] ids)
    {
        return empMapper.deleteEmpByIds(ids);
    }

    /**
     * 删除人员列表信息
     * 
     * @param id 人员列表主键
     * @return 结果
     */
    @Override
    public int deleteEmpById(Long id)
    {
        return empMapper.deleteEmpById(id);
    }



}
