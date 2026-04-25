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
import com.penguin.mind.domain.Job;
import com.penguin.mind.service.IJobService;
import com.penguin.common.utils.poi.ExcelUtil;
import com.penguin.common.core.page.TableDataInfo;

/**
 * 自动补货任务Controller
 * 
 * @author nian
 * @date 2026-04-25
 */
@RestController
@RequestMapping("/mind/job")
public class JobController extends BaseController
{
    @Autowired
    private IJobService jobService;

    /**
     * 查询自动补货任务列表
     */
    @PreAuthorize("@ss.hasPermi('mind:job:list')")
    @GetMapping("/list")
    public TableDataInfo list(Job job)
    {
        startPage();
        List<Job> list = jobService.selectJobList(job);
        return getDataTable(list);
    }

    /**
     * 导出自动补货任务列表
     */
    @PreAuthorize("@ss.hasPermi('mind:job:export')")
    @Log(title = "自动补货任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Job job)
    {
        List<Job> list = jobService.selectJobList(job);
        ExcelUtil<Job> util = new ExcelUtil<Job>(Job.class);
        util.exportExcel(response, list, "自动补货任务数据");
    }

    /**
     * 获取自动补货任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('mind:job:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(jobService.selectJobById(id));
    }

    /**
     * 新增自动补货任务
     */
    @PreAuthorize("@ss.hasPermi('mind:job:add')")
    @Log(title = "自动补货任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Job job)
    {
        return toAjax(jobService.insertJob(job));
    }

    /**
     * 修改自动补货任务
     */
    @PreAuthorize("@ss.hasPermi('mind:job:edit')")
    @Log(title = "自动补货任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Job job)
    {
        return toAjax(jobService.updateJob(job));
    }

    /**
     * 删除自动补货任务
     */
    @PreAuthorize("@ss.hasPermi('mind:job:remove')")
    @Log(title = "自动补货任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(jobService.deleteJobByIds(ids));
    }
}
