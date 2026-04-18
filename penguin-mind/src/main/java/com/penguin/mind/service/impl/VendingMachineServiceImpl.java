package com.penguin.mind.service.impl;

import java.util.ArrayList;
import java.util.List;
import cn.hutool.core.bean.BeanUtil;
import com.penguin.common.constant.PenguinConstants;
import com.penguin.common.utils.DateUtils;
import com.penguin.common.utils.uuid.UUIDUtils;
import com.penguin.mind.domain.Channel;
import com.penguin.mind.domain.Node;
import com.penguin.mind.domain.VmType;
import com.penguin.mind.service.IChannelService;
import com.penguin.mind.service.INodeService;
import com.penguin.mind.service.IVmTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.penguin.mind.mapper.VendingMachineMapper;
import com.penguin.mind.domain.VendingMachine;
import com.penguin.mind.service.IVendingMachineService;
import org.springframework.transaction.annotation.Transactional;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.cn;


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

    @Autowired
    private IVmTypeService vmTypeService;

    @Autowired
    private INodeService nodeService;

    @Autowired
    private IChannelService channelService;

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
    @Transactional
    @Override
    public int insertVendingMachine(VendingMachine vendingMachine)
    {

        //新增设备
        //1.准备编号
        String innerCode = UUIDUtils.getUUID();
        vendingMachine.setInnerCode(innerCode);
        //2.通过设备类型表来补充设备容量，所以需要注入设备类型表的service来查询
        //在 RuoYi 框架中，一个 Service 需要访问其他业务模块时，
        // 统一通过 Service 接口调用
        //获取设备类型实体
        VmType vmType = vmTypeService.selectVmTypeById(vendingMachine.getVmTypeId());

        vendingMachine.setChannelMaxCapacity(vmType.getChannelMaxCapacity());

        //3.通过点位id，来查询补充点位的信息
        Node node = nodeService.selectNodeById(String.valueOf(vendingMachine.getNodeId()));
        // 将点位的商圈类型、区域、合作商等信息复制到设备对象中
        // 忽略id字段，保留设备自身的主键
        BeanUtil.copyProperties(node, vendingMachine, "id");
        vendingMachine.setAddr(node.getAddress());
        
        //1-4 设置设备状态为未投放
        vendingMachine.setVmStatus(PenguinConstants.VM_STATUS_NODEPLOY);//0：未投放
        vendingMachine.setCreateTime(DateUtils.getNowDate());//设置创建时间
        vendingMachine.setUpdateTime(DateUtils.getNowDate());//设置更新时间
        //1-5 保存
        int result =vendingMachineMapper.insertVendingMachine(vendingMachine);

        //2.新增货道
        List<Channel> channels = new ArrayList<>();
        for (int i = 1; i <= vmType.getVmRow(); i++) {
            for (int j = 1; j <= vmType.getVmCol(); j++){
                Channel channel = new Channel();
                channel.setChannelCode(i + "-" + j);//拼接货道编号
                channel.setVmId(vendingMachine.getId());//设置售货机id
                channel.setMaxCapacity(vmType.getChannelMaxCapacity());//设置货道容量
                channel.setInnerCode(vendingMachine.getInnerCode());//设置售货机编号
                channel.setCreateTime(DateUtils.getNowDate());//设置创建时间
                channel.setUpdateTime(DateUtils.getNowDate());//设置更新时间
                //保存货道
                //channelService.insertChannel(channel);
                channels.add(channel);

            }
        }
        //批量保存
        channelService.batchInsertChannel(channels);

        return result;
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
        Node node = nodeService.selectNodeById(String.valueOf(vendingMachine.getNodeId()));
        // 将点位的商圈类型、区域、合作商等信息复制到设备对象中
        // 忽略id字段，保留设备自身的主键
        BeanUtil.copyProperties(node, vendingMachine, "id");
        vendingMachine.setAddr(node.getAddress());//设备地址
        vendingMachine.setUpdateTime(DateUtils.getNowDate());//设置更新时间
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
