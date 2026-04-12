package com.penguin.mind.service;

import java.util.List;
import com.penguin.mind.domain.VendingMachine;

/**
 * 自动售货机设备管理Service接口
 * 
 * @author nian
 * @date 2026-04-12
 */
public interface IVendingMachineService 
{
    /**
     * 查询自动售货机设备管理
     * 
     * @param id 自动售货机设备管理主键
     * @return 自动售货机设备管理
     */
    public VendingMachine selectVendingMachineById(Long id);

    /**
     * 查询自动售货机设备管理列表
     * 
     * @param vendingMachine 自动售货机设备管理
     * @return 自动售货机设备管理集合
     */
    public List<VendingMachine> selectVendingMachineList(VendingMachine vendingMachine);

    /**
     * 新增自动售货机设备管理
     * 
     * @param vendingMachine 自动售货机设备管理
     * @return 结果
     */
    public int insertVendingMachine(VendingMachine vendingMachine);

    /**
     * 修改自动售货机设备管理
     * 
     * @param vendingMachine 自动售货机设备管理
     * @return 结果
     */
    public int updateVendingMachine(VendingMachine vendingMachine);

    /**
     * 批量删除自动售货机设备管理
     * 
     * @param ids 需要删除的自动售货机设备管理主键集合
     * @return 结果
     */
    public int deleteVendingMachineByIds(Long[] ids);

    /**
     * 删除自动售货机设备管理信息
     * 
     * @param id 自动售货机设备管理主键
     * @return 结果
     */
    public int deleteVendingMachineById(Long id);
}
