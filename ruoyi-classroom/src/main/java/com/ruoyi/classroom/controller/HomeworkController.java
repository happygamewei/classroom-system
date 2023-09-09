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
import com.ruoyi.classroom.domain.Homework;
import com.ruoyi.classroom.service.IHomeworkService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 发布作业Controller
 * 
 * @author rain
 * @date 2023-09-08
 */
@RestController
@RequestMapping("/classroom/homework")
public class HomeworkController extends BaseController
{
    @Autowired
    private IHomeworkService homeworkService;

    /**
     * 查询发布作业列表
     */
    @PreAuthorize("@ss.hasPermi('classroom:homework:list')")
    @GetMapping("/list")
    public TableDataInfo list(Homework homework)
    {
        startPage();
        List<Homework> list = homeworkService.selectHomeworkList(homework);
        return getDataTable(list);
    }

    /**
     * 导出发布作业列表
     */
    @PreAuthorize("@ss.hasPermi('classroom:homework:export')")
    @Log(title = "发布作业", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Homework homework)
    {
        List<Homework> list = homeworkService.selectHomeworkList(homework);
        ExcelUtil<Homework> util = new ExcelUtil<Homework>(Homework.class);
        util.exportExcel(response, list, "发布作业数据");
    }

    /**
     * 获取发布作业详细信息
     */
    @PreAuthorize("@ss.hasPermi('classroom:homework:query')")
    @GetMapping(value = "/{homeworkId}")
    public AjaxResult getInfo(@PathVariable("homeworkId") Long homeworkId)
    {
        return success(homeworkService.selectHomeworkByHomeworkId(homeworkId));
    }

    /**
     * 新增发布作业
     */
    @PreAuthorize("@ss.hasPermi('classroom:homework:add')")
    @Log(title = "发布作业", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Homework homework)
    {
        return toAjax(homeworkService.insertHomework(homework));
    }

    /**
     * 修改发布作业
     */
    @PreAuthorize("@ss.hasPermi('classroom:homework:edit')")
    @Log(title = "发布作业", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Homework homework)
    {
        return toAjax(homeworkService.updateHomework(homework));
    }

    /**
     * 删除发布作业
     */
    @PreAuthorize("@ss.hasPermi('classroom:homework:remove')")
    @Log(title = "发布作业", businessType = BusinessType.DELETE)
	@DeleteMapping("/{homeworkIds}")
    public AjaxResult remove(@PathVariable Long[] homeworkIds)
    {
        return toAjax(homeworkService.deleteHomeworkByHomeworkIds(homeworkIds));
    }
}
