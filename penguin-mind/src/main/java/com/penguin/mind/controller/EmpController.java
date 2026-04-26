package com.penguin.mind.controller;

import java.util.List;

import com.penguin.common.constant.PenguinConstants;
import com.penguin.mind.domain.VendingMachine;
import com.penguin.mind.service.IVendingMachineService;
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
import com.penguin.mind.domain.Emp;
import com.penguin.mind.service.IEmpService;
import com.penguin.common.utils.poi.ExcelUtil;
import com.penguin.common.core.page.TableDataInfo;

/**
 * 人员列表Controller
 * 
 * @author nian
 * @date 2026-04-09
 */
@RestController
@RequestMapping("/mind/emp")
public class EmpController extends BaseController
{
    @Autowired
    private IEmpService empService;

    @Autowired
    private IVendingMachineService  vendingMachineService;

    /**
     * 查询人员列表列表
     */
    @PreAuthorize("@ss.hasPermi('mind:emp:list')")
    @GetMapping("/list")
    public TableDataInfo list(Emp emp)
    {
        startPage();
        List<Emp> list = empService.selectEmpList(emp);
        return getDataTable(list);
    }

    /**
     * 导出人员列表列表
     */
    @PreAuthorize("@ss.hasPermi('mind:emp:export')")
    @Log(title = "人员列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Emp emp)
    {
        List<Emp> list = empService.selectEmpList(emp);
        ExcelUtil<Emp> util = new ExcelUtil<Emp>(Emp.class);
        util.exportExcel(response, list, "人员列表数据");
    }

    /**
     * 获取人员列表详细信息
     */
    @PreAuthorize("@ss.hasPermi('mind:emp:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(empService.selectEmpById(id));
    }

    /**
     * 新增人员列表
     */
    @PreAuthorize("@ss.hasPermi('mind:emp:add')")
    @Log(title = "人员列表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Emp emp)
    {
        // 根据角色名称自动修正 role_code，确保统一为数字编码
        if (emp.getRoleName() != null) {
            if (emp.getRoleName().contains("运营")) {
                emp.setRoleCode(PenguinConstants.ROLE_CODE_BUSINESS); // 设置为 1002
            } else if (emp.getRoleName().contains("运维")) {
                emp.setRoleCode(PenguinConstants.ROLE_CODE_OPERATOR); // 设置为 1003
            }
        }
        return toAjax(empService.insertEmp(emp));
    }

    /**
     * 修改人员列表
     */
    @PreAuthorize("@ss.hasPermi('mind:emp:edit')")
    @Log(title = "人员列表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Emp emp)
    {
        return toAjax(empService.updateEmp(emp));
    }

    /**
     * 删除人员列表
     */
    @PreAuthorize("@ss.hasPermi('mind:emp:remove')")
    @Log(title = "人员列表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(empService.deleteEmpByIds(ids));
    }


    //根据售货机查询运营人员列表
    @PreAuthorize("@ss.hasPermi('mind:emp:list')")
    @GetMapping("/businessList/{innerCode}")
    public AjaxResult businessList(@PathVariable String innerCode)
    {
//        1. 根据售货机编号查询售货机信息
        VendingMachine vendingMachine = vendingMachineService.selectVendingMachineByInnerCode(innerCode);
        if(vendingMachine == null)
        {
            return error("售货机不存在");
        }
//        2. 根据售货机信息（区域id，角色编号，员工状态）查询人员列表
        Emp emp = new Emp();
        emp.setRegionId((String.valueOf(vendingMachine.getRegionId())));//区域id
        emp.setRoleCode(PenguinConstants.ROLE_CODE_BUSINESS);//角色编号：运营员
        emp.setStatus(PenguinConstants.EMP_STATUS_NORMAL);//员工启用状态
        return success(empService.selectEmpList(emp));
    }


    //根据售货机查询运维人员列表
    @PreAuthorize("@ss.hasPermi('mind:emp:list')")
    @GetMapping("/operationList/{innerCode}")
    public AjaxResult operationList(@PathVariable String innerCode)
    {
//        1. 根据售货机编号查询售货机信息
        VendingMachine vendingMachine = vendingMachineService.selectVendingMachineByInnerCode(innerCode);
        if(vendingMachine == null)
        {
            return error("售货机不存在");
        }
//        2. 根据售货机信息（区域id，角色编号，员工状态）查询人员列表
        Emp emp = new Emp();
        emp.setRegionId((String.valueOf(vendingMachine.getRegionId())));//区域id
        emp.setRoleCode(PenguinConstants.ROLE_CODE_OPERATOR);//角色编号：运营员
        emp.setStatus(PenguinConstants.EMP_STATUS_NORMAL);//员工启用状态
        return success(empService.selectEmpList(emp));
    }
}
