package com.penguin.applet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.penguin.applet.domain.Policy;
import com.penguin.applet.domain.Penguin;
import com.penguin.applet.domain.VendingMachine;
import com.penguin.applet.domain.vo.PenguinVo;
import com.penguin.applet.mapper.PenguinMapper;
import com.penguin.applet.service.PolicyService;
import com.penguin.applet.service.PenguinService;
import com.penguin.applet.service.VendingMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PenguinServiceImpl extends ServiceImpl<PenguinMapper, Penguin> implements PenguinService {

    @Autowired
    private PolicyService policyService;

    @Autowired
    private VendingMachineService vendingMachineService;

    @Override
    public List<PenguinVo> findPenguinByInnerCode(String innerCode) {
        // 查询商品列表
        List<PenguinVo> penguinVOList = baseMapper.findPenguinByInnerCode(innerCode);
        // 查询设备
        VendingMachine vm = vendingMachineService.getVendingMachineByInnerCode(innerCode);
        // 查询该售货机的策略
        Policy policy = policyService.getById(vm.getPolicyId());
        int discount = 100; //折扣率
        if (policy != null) {
            discount = policy.getDiscount();
        }
        // 循环，得到真实价格
        for (PenguinVo penguinVO : penguinVOList) {
            penguinVO.setRealPrice(penguinVO.getPrice() * discount / 100);
            if (penguinVO.getRealPrice() < penguinVO.getPrice()) {
                penguinVO.setDiscount(true);
            } else {
                penguinVO.setDiscount(false);
            }
        }
        return penguinVOList;
    }
}
