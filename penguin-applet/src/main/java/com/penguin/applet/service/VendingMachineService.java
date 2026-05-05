package com.penguin.applet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.penguin.applet.domain.VendingMachine;

public interface VendingMachineService extends IService<VendingMachine> {

    // 根据innerCode查询
    VendingMachine getVendingMachineByInnerCode(String innerCode);
}
