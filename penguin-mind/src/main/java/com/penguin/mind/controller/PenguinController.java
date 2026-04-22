package com.penguin.mind.controller;

import java.io.IOException;
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
import com.penguin.mind.domain.Penguin;
import com.penguin.mind.service.IPenguinService;
import com.penguin.common.utils.poi.ExcelUtil;
import com.penguin.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 企鹅盲盒管理Controller
 * 
 * @author nian
 * @date 2026-04-19
 */
@RestController
@RequestMapping("/mind/penguin")
public class PenguinController extends BaseController
{
    @Autowired
    private IPenguinService penguinService;

    /**
     * 查询企鹅盲盒管理列表
     */
    @PreAuthorize("@ss.hasPermi('mind:penguin:list')")
    @GetMapping("/list")
    public TableDataInfo list(Penguin penguin)
    {
        startPage();
        List<Penguin> list = penguinService.selectPenguinList(penguin);
        return getDataTable(list);
    }

    /**
     * 导出企鹅盲盒管理列表
     */
    @PreAuthorize("@ss.hasPermi('mind:penguin:export')")
    @Log(title = "企鹅盲盒管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Penguin penguin)
    {
        List<Penguin> list = penguinService.selectPenguinList(penguin);
        ExcelUtil<Penguin> util = new ExcelUtil<Penguin>(Penguin.class);
        util.exportExcel(response, list, "企鹅盲盒管理数据");
    }

    //导入企鹅盲盒列表
    @PreAuthorize("@ss.hasPermi('mind:penguin:add')")
    @Log(title = "企鹅盲盒管理", businessType = BusinessType.IMPORT)

    @PostMapping("/import")
    public AjaxResult excelImport(MultipartFile file) throws Exception {
        ExcelUtil<Penguin> util = new ExcelUtil<Penguin>(Penguin.class);//解析获取excel数据
        List<Penguin> list = util.importExcel(file.getInputStream());
        return toAjax(penguinService.insertPenguins(list));
    }



    /**
     * 获取企鹅盲盒管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('mind:penguin:query')")
    @GetMapping(value = "/{penguinId}")
    public AjaxResult getInfo(@PathVariable("penguinId") Long penguinId)
    {
        return success(penguinService.selectPenguinByPenguinId(penguinId));
    }

    /**
     * 新增企鹅盲盒管理
     */
    @PreAuthorize("@ss.hasPermi('mind:penguin:add')")
    @Log(title = "企鹅盲盒管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Penguin penguin)
    {
        return toAjax(penguinService.insertPenguin(penguin));
    }

    /**
     * 修改企鹅盲盒管理
     */
    @PreAuthorize("@ss.hasPermi('mind:penguin:edit')")
    @Log(title = "企鹅盲盒管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Penguin penguin)
    {
        return toAjax(penguinService.updatePenguin(penguin));
    }

    /**
     * 删除企鹅盲盒管理
     */
    @PreAuthorize("@ss.hasPermi('mind:penguin:remove')")
    @Log(title = "企鹅盲盒管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{penguinIds}")
    public AjaxResult remove(@PathVariable Long[] penguinIds)
    {
        return toAjax(penguinService.deletePenguinByPenguinIds(penguinIds));
    }
}
