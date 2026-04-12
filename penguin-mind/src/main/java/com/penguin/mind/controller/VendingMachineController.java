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
import com.penguin.mind.domain.VendingMachine;
import com.penguin.mind.service.IVendingMachineService;
import com.penguin.common.utils.poi.ExcelUtil;
import com.penguin.common.core.page.TableDataInfo;

/**
 * 自动售货机设备管理Controller
 * 
 * @author nian
 * @date 2026-04-12
 */
@RestController
@RequestMapping("/mind/machine")
public class VendingMachineController extends BaseController
{
    @Autowired
    private IVendingMachineService vendingMachineService;

    /**
     * 查询自动售货机设备管理列表
     */
    @PreAuthorize("@ss.hasPermi('mind:machine:list')")
    @GetMapping("/list")
    public TableDataInfo list(VendingMachine vendingMachine)
    {
        startPage();
        List<VendingMachine> list = vendingMachineService.selectVendingMachineList(vendingMachine);
        return getDataTable(list);
    }

    /**
     * 导出自动售货机设备管理列表
     */
    @PreAuthorize("@ss.hasPermi('mind:machine:export')")
    @Log(title = "自动售货机设备管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VendingMachine vendingMachine)
    {
        List<VendingMachine> list = vendingMachineService.selectVendingMachineList(vendingMachine);
        ExcelUtil<VendingMachine> util = new ExcelUtil<VendingMachine>(VendingMachine.class);
        util.exportExcel(response, list, "自动售货机设备管理数据");
    }

    /**
     * 获取自动售货机设备管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('mind:machine:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(vendingMachineService.selectVendingMachineById(id));
    }

    /**
     * 新增自动售货机设备管理
     */
    @PreAuthorize("@ss.hasPermi('mind:machine:add')")
    @Log(title = "自动售货机设备管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VendingMachine vendingMachine)
    {
        return toAjax(vendingMachineService.insertVendingMachine(vendingMachine));
    }

    /**
     * 修改自动售货机设备管理
     */
    @PreAuthorize("@ss.hasPermi('mind:machine:edit')")
    @Log(title = "自动售货机设备管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VendingMachine vendingMachine)
    {
        return toAjax(vendingMachineService.updateVendingMachine(vendingMachine));
    }

    /**
     * 删除自动售货机设备管理
     */
    @PreAuthorize("@ss.hasPermi('mind:machine:remove')")
    @Log(title = "自动售货机设备管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(vendingMachineService.deleteVendingMachineByIds(ids));
    }
}
