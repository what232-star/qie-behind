package com.penguin.mind.service.impl;

import java.util.List;
import com.penguin.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.penguin.mind.mapper.VendingMachineMapper;
import com.penguin.mind.domain.VendingMachine;
import com.penguin.mind.service.IVendingMachineService;

/**
 * 自动售货机设备管理Service业务层处理
 * 
 * @author nian
 * @date 2026-04-12
 */
@Service
public class VendingMachineServiceImpl implements IVendingMachineService 
{
    @Autowired
    private VendingMachineMapper vendingMachineMapper;

    /**
     * 查询自动售货机设备管理
     * 
     * @param id 自动售货机设备管理主键
     * @return 自动售货机设备管理
     */
    @Override
    public VendingMachine selectVendingMachineById(Long id)
    {
        return vendingMachineMapper.selectVendingMachineById(id);
    }

    /**
     * 查询自动售货机设备管理列表
     * 
     * @param vendingMachine 自动售货机设备管理
     * @return 自动售货机设备管理
     */
    @Override
    public List<VendingMachine> selectVendingMachineList(VendingMachine vendingMachine)
    {
        return vendingMachineMapper.selectVendingMachineList(vendingMachine);
    }

    /**
     * 新增自动售货机设备管理
     * 
     * @param vendingMachine 自动售货机设备管理
     * @return 结果
     */
    @Override
    public int insertVendingMachine(VendingMachine vendingMachine)
    {
        vendingMachine.setCreateTime(DateUtils.getNowDate());
        return vendingMachineMapper.insertVendingMachine(vendingMachine);
    }

    /**
     * 修改自动售货机设备管理
     * 
     * @param vendingMachine 自动售货机设备管理
     * @return 结果
     */
    @Override
    public int updateVendingMachine(VendingMachine vendingMachine)
    {
        vendingMachine.setUpdateTime(DateUtils.getNowDate());
        return vendingMachineMapper.updateVendingMachine(vendingMachine);
    }

    /**
     * 批量删除自动售货机设备管理
     * 
     * @param ids 需要删除的自动售货机设备管理主键
     * @return 结果
     */
    @Override
    public int deleteVendingMachineByIds(Long[] ids)
    {
        return vendingMachineMapper.deleteVendingMachineByIds(ids);
    }

    /**
     * 删除自动售货机设备管理信息
     * 
     * @param id 自动售货机设备管理主键
     * @return 结果
     */
    @Override
    public int deleteVendingMachineById(Long id)
    {
        return vendingMachineMapper.deleteVendingMachineById(id);
    }
}
