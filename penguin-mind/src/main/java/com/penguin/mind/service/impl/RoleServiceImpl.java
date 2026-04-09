package com.penguin.mind.service.impl;

import java.util.List;
import com.penguin.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.penguin.mind.mapper.RoleMapper;
import com.penguin.mind.domain.Role;
import com.penguin.mind.service.IRoleService;

/**
 * 工单角色Service业务层处理
 * 
 * @author nian
 * @date 2026-04-09
 */
@Service
public class RoleServiceImpl implements IRoleService 
{
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 查询工单角色
     * 
     * @param roleId 工单角色主键
     * @return 工单角色
     */
    @Override
    public Role selectRoleByRoleId(Long roleId)
    {
        return roleMapper.selectRoleByRoleId(roleId);
    }

    /**
     * 查询工单角色列表
     * 
     * @param role 工单角色
     * @return 工单角色
     */
    @Override
    public List<Role> selectRoleList(Role role)
    {
        return roleMapper.selectRoleList(role);
    }

    /**
     * 新增工单角色
     * 
     * @param role 工单角色
     * @return 结果
     */
    @Override
    public int insertRole(Role role)
    {
        role.setCreateTime(DateUtils.getNowDate());
        return roleMapper.insertRole(role);
    }

    /**
     * 修改工单角色
     * 
     * @param role 工单角色
     * @return 结果
     */
    @Override
    public int updateRole(Role role)
    {
        role.setUpdateTime(DateUtils.getNowDate());
        return roleMapper.updateRole(role);
    }

    /**
     * 批量删除工单角色
     * 
     * @param roleIds 需要删除的工单角色主键
     * @return 结果
     */
    @Override
    public int deleteRoleByRoleIds(Long[] roleIds)
    {
        return roleMapper.deleteRoleByRoleIds(roleIds);
    }

    /**
     * 删除工单角色信息
     * 
     * @param roleId 工单角色主键
     * @return 结果
     */
    @Override
    public int deleteRoleByRoleId(Long roleId)
    {
        return roleMapper.deleteRoleByRoleId(roleId);
    }
}
