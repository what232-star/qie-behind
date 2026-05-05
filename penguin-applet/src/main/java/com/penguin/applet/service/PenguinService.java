package com.penguin.applet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.penguin.applet.domain.Penguin;
import com.penguin.applet.domain.vo.PenguinVo;

import java.util.List;

public interface PenguinService extends IService<Penguin> {

    /**
     * 根据售货机查询商品
     *
     * @param innerCode
     * @return
     */
    List<PenguinVo> findPenguinByInnerCode(String innerCode);

}
