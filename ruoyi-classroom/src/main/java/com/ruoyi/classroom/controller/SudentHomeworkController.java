package com.ruoyi.classroom.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.classroom.domain.SudentHomework;
import com.ruoyi.classroom.service.ISudentHomeworkService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学生作业Controller
 * 
 * @author rain
 * @date 2023-09-08
 */
@RestController
@RequestMapping("/classroom/studentHomework")
public class SudentHomeworkController extends BaseController
{
    @Autowired
    private ISudentHomeworkService sudentHomeworkService;

    /**
     * 查询学生作业列表
     */
    @PreAuthorize("@ss.hasPermi('classroom:studentHomework:list')")
    @GetMapping("/list")
    public TableDataInfo list(SudentHomework sudentHomework)
    {
        startPage();
        List<SudentHomework> list = sudentHomeworkService.selectSudentHomeworkList(sudentHomework);
        return getDataTable(list);
    }

    /**
     * 导出学生作业列表
     */
    @PreAuthorize("@ss.hasPermi('classroom:studentHomework:export')")
    @Log(title = "学生作业", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SudentHomework sudentHomework)
    {
        List<SudentHomework> list = sudentHomeworkService.selectSudentHomeworkList(sudentHomework);
        ExcelUtil<SudentHomework> util = new ExcelUtil<SudentHomework>(SudentHomework.class);
        util.exportExcel(response, list, "学生作业数据");
    }

    /**
     * 获取学生作业详细信息
     */
    @PreAuthorize("@ss.hasPermi('classroom:studentHomework:query')")
    @GetMapping(value = "/{shId}")
    public AjaxResult getInfo(@PathVariable("shId") Long shId)
    {
        return success(sudentHomeworkService.selectSudentHomeworkByShId(shId));
    }

    /**
     * 新增学生作业
     */
    @PreAuthorize("@ss.hasPermi('classroom:studentHomework:add')")
    @Log(title = "学生作业", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SudentHomework sudentHomework)
    {
        return toAjax(sudentHomeworkService.insertSudentHomework(sudentHomework));
    }

    /**
     * 修改学生作业
     */
    @PreAuthorize("@ss.hasPermi('classroom:studentHomework:edit')")
    @Log(title = "学生作业", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SudentHomework sudentHomework)
    {
        return toAjax(sudentHomeworkService.updateSudentHomework(sudentHomework));
    }

    /**
     * 删除学生作业
     */
    @PreAuthorize("@ss.hasPermi('classroom:studentHomework:remove')")
    @Log(title = "学生作业", businessType = BusinessType.DELETE)
	@DeleteMapping("/{shIds}")
    public AjaxResult remove(@PathVariable Long[] shIds)
    {
        return toAjax(sudentHomeworkService.deleteSudentHomeworkByShIds(shIds));
    }
}
