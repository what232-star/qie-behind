package com.penguin.mind.controller;

import java.util.List;

import com.penguin.mind.domain.dto.ChannelConfigDto;
import com.penguin.mind.domain.vo.ChannelVo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.penguin.common.annotation.Log;
import com.penguin.common.core.controller.BaseController;
import com.penguin.common.core.domain.AjaxResult;
import com.penguin.common.enums.BusinessType;
import com.penguin.mind.domain.Channel;
import com.penguin.mind.service.IChannelService;
import com.penguin.common.utils.poi.ExcelUtil;
import com.penguin.common.core.page.TableDataInfo;

/**
 * 售货机货道管理Controller
 * 
 * @author nian
 * @date 2026-04-12
 */
@RestController
@RequestMapping("/mind/channel")
public class ChannelController extends BaseController
{
    @Autowired
    private IChannelService channelService;



    /**
     * 查询售货机货道管理列表
     */
    @PreAuthorize("@ss.hasPermi('mind:channel:list')")
    @GetMapping("/list")
    public TableDataInfo list(Channel channel)
    {
        startPage();
        List<Channel> list = channelService.selectChannelList(channel);
        return getDataTable(list);
    }

    /**
     * 导出售货机货道管理列表
     */
    @PreAuthorize("@ss.hasPermi('mind:channel:export')")
    @Log(title = "售货机货道管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Channel channel)
    {
        List<Channel> list = channelService.selectChannelList(channel);
        ExcelUtil<Channel> util = new ExcelUtil<Channel>(Channel.class);
        util.exportExcel(response, list, "售货机货道管理数据");
    }

    /**
     * 获取售货机货道管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('mind:channel:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(channelService.selectChannelById(id));
    }

    /**
     * 新增售货机货道管理
     */
    @PreAuthorize("@ss.hasPermi('mind:channel:add')")
    @Log(title = "售货机货道管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Channel channel)
    {
        return toAjax(channelService.insertChannel(channel));
    }

    /**
     * 修改售货机货道管理
     */
    @PreAuthorize("@ss.hasPermi('mind:channel:edit')")
    @Log(title = "售货机货道管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Channel channel)
    {
        return toAjax(channelService.updateChannel(channel));
    }

    /**
     * 删除售货机货道管理
     */
    @PreAuthorize("@ss.hasPermi('mind:channel:remove')")
    @Log(title = "售货机货道管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(channelService.deleteChannelByIds(ids));
    }


    @PreAuthorize("@ss.hasPermi('mind:channel:list')")
    @GetMapping("/list/{innerCode}")
    public AjaxResult listByInnerCode(@PathVariable String innerCode)
    {
        List < ChannelVo> list = channelService.selectChannelVoListByInnerCode(innerCode);
        return success(list);
    }

    //货道关联商品
    @PutMapping ("/config")
    public AjaxResult setChannelConfig(@RequestBody ChannelConfigDto channelConfigDto)
    {
        return toAjax(channelService.setChannelConfig(channelConfigDto));
    }

}
