package com.penguin.mind.controller;

import java.util.List;

import com.penguin.mind.domain.vo.RegionVo;
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
import com.penguin.mind.domain.Region;
import com.penguin.mind.service.IRegionService;
import com.penguin.common.utils.poi.ExcelUtil;
import com.penguin.common.core.page.TableDataInfo;

/**
 * 区域管理Controller
 * 
 * @author nian
 * @date 2026-04-04
 */
@RestController
@RequestMapping("/mind/region")
public class RegionController extends BaseController
{
    @Autowired
    private IRegionService regionService;

    /**
     * 查询区域管理列表
     */
    @PreAuthorize("@ss.hasPermi('mind:region:list')")
    @GetMapping("/list")
    public TableDataInfo list(Region region)
    {
        startPage();
        List<RegionVo> volist = regionService.selectRegionVoList(region);
        return getDataTable(volist);
    }

    /**
     * 导出区域管理列表
     */
    @PreAuthorize("@ss.hasPermi('mind:region:export')")
    @Log(title = "区域管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Region region)
    {
        List<Region> list = regionService.selectRegionList(region);
        ExcelUtil<Region> util = new ExcelUtil<Region>(Region.class);
        util.exportExcel(response, list, "区域管理数据");
    }

    /**
     * 获取区域管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('mind:region:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(regionService.selectRegionById(id));
    }

    /**
     * 新增区域管理
     */
    @PreAuthorize("@ss.hasPermi('mind:region:add')")
    @Log(title = "区域管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Region region)
    {
        return toAjax(regionService.insertRegion(region));
    }

    /**
     * 修改区域管理
     */
    @PreAuthorize("@ss.hasPermi('mind:region:edit')")
    @Log(title = "区域管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Region region)
    {
        return toAjax(regionService.updateRegion(region));
    }

    /**
     * 删除区域管理
     */
    @PreAuthorize("@ss.hasPermi('mind:region:remove')")
    @Log(title = "区域管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(regionService.deleteRegionByIds(ids));
    }
}
