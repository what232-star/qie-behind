package com.penguin.mind.controller;

import java.util.List;
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
import com.penguin.mind.domain.PenguinClass;
import com.penguin.mind.service.IPenguinClassService;
import com.penguin.common.utils.poi.ExcelUtil;
import com.penguin.common.core.page.TableDataInfo;

/**
 * 企鹅类型Controller
 * 
 * @author nian
 * @date 2026-04-19
 */
@RestController
@RequestMapping("/mind/penguinClass")
public class PenguinClassController extends BaseController
{
    @Autowired
    private IPenguinClassService penguinClassService;

    /**
     * 查询企鹅类型列表
     */
    @PreAuthorize("@ss.hasPermi('mind:penguinClass:list')")
    @GetMapping("/list")
    public TableDataInfo list(PenguinClass penguinClass)
    {
        startPage();
        List<PenguinClass> list = penguinClassService.selectPenguinClassList(penguinClass);
        return getDataTable(list);
    }

    /**
     * 导出企鹅类型列表
     */
    @PreAuthorize("@ss.hasPermi('mind:penguinClass:export')")
    @Log(title = "企鹅类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PenguinClass penguinClass)
    {
        List<PenguinClass> list = penguinClassService.selectPenguinClassList(penguinClass);
        ExcelUtil<PenguinClass> util = new ExcelUtil<PenguinClass>(PenguinClass.class);
        util.exportExcel(response, list, "企鹅类型数据");
    }

    /**
     * 获取企鹅类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('mind:penguinClass:query')")
    @GetMapping(value = "/{classId}")
    public AjaxResult getInfo(@PathVariable("classId") Long classId)
    {
        return success(penguinClassService.selectPenguinClassByClassId(classId));
    }

    /**
     * 新增企鹅类型
     */
    @PreAuthorize("@ss.hasPermi('mind:penguinClass:add')")
    @Log(title = "企鹅类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PenguinClass penguinClass)
    {
        return toAjax(penguinClassService.insertPenguinClass(penguinClass));
    }

    /**
     * 修改企鹅类型
     */
    @PreAuthorize("@ss.hasPermi('mind:penguinClass:edit')")
    @Log(title = "企鹅类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PenguinClass penguinClass)
    {
        return toAjax(penguinClassService.updatePenguinClass(penguinClass));
    }

    /**
     * 删除企鹅类型
     */
    @PreAuthorize("@ss.hasPermi('mind:penguinClass:remove')")
    @Log(title = "企鹅类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{classIds}")
    public AjaxResult remove(@PathVariable Long[] classIds)
    {
        return toAjax(penguinClassService.deletePenguinClassByClassIds(classIds));
    }
}
