package com.penguin.mind.controller;

import java.util.List;

import com.penguin.common.utils.SecurityUtils;
import com.penguin.mind.domain.vo.BearVo;
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
import com.penguin.mind.domain.Bear;
import com.penguin.mind.service.IBearService;
import com.penguin.common.utils.poi.ExcelUtil;
import com.penguin.common.core.page.TableDataInfo;

/**
 * 熊熊合作商管理Controller
 * 
 * @author nian
 * @date 2026-04-04
 */
@RestController
@RequestMapping("/mind/bear")
public class BearController extends BaseController
{
    @Autowired
    private IBearService bearService;

    /**
     * 查询熊熊合作商管理列表
     */
    @PreAuthorize("@ss.hasPermi('mind:bear:list')")
    @GetMapping("/list")
    public TableDataInfo list(Bear bear)
    {
        startPage();
        List<BearVo> volist = bearService.selectBearVoList(bear);
        return getDataTable(volist);
    }

    /**
     * 导出熊熊合作商管理列表
     */
    @PreAuthorize("@ss.hasPermi('mind:bear:export')")
    @Log(title = "熊熊合作商管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Bear bear)
    {
        List<Bear> list = bearService.selectBearList(bear);
        ExcelUtil<Bear> util = new ExcelUtil<Bear>(Bear.class);
        util.exportExcel(response, list, "熊熊合作商管理数据");
    }

    /**
     * 获取熊熊合作商管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('mind:bear:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(bearService.selectBearById(id));
    }

    /**
     * 新增熊熊合作商管理
     */
    @PreAuthorize("@ss.hasPermi('mind:bear:add')")
    @Log(title = "熊熊合作商管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Bear bear)
    {
        return toAjax(bearService.insertBear(bear));
    }

    /**
     * 修改熊熊合作商管理
     */
    @PreAuthorize("@ss.hasPermi('mind:bear:edit')")
    @Log(title = "熊熊合作商管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Bear bear)
    {
        return toAjax(bearService.updateBear(bear));
    }

    /**
     * 删除熊熊合作商管理
     */
    @PreAuthorize("@ss.hasPermi('mind:bear:remove')")
    @Log(title = "熊熊合作商管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(bearService.deleteBearByIds(ids));
    }

    /**
     * 重置密码
     *
     *
     */
    @PreAuthorize("@ss.hasPermi('mind:bear:edit')")
    @Log(title = "重置熊熊合作商密码", businessType = BusinessType.UPDATE)
    @PutMapping("resetPwd/{id}")
    public AjaxResult resetPwd(@PathVariable String id)//接收参数
    {
        Bear bear = new Bear();
        bear.setId(id);//设置id
        bear.setPassword(SecurityUtils.encryptPassword("123456"));//设置加密后的初始密码
        return toAjax(bearService.updateBear(bear));

    }
}

