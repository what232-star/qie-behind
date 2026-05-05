package com.penguin.applet.controller;

import com.penguin.applet.domain.vo.PenguinVo;
import com.penguin.applet.service.PenguinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vm")
@Slf4j
public class VendingMachineController {

    @Autowired
    private PenguinService penguinService;

    /**
     * 获取售货机商品列表
     *
     * @param innerCode
     * @return
     */
    @RequestMapping("/penguinList/{innerCode}")
    public List<PenguinVo> getPenguinListByInnerCode(@PathVariable String innerCode) {
        return penguinService.findPenguinByInnerCode(innerCode);
    }

}
