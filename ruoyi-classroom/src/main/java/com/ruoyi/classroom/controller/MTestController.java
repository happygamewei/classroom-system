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
import com.ruoyi.classroom.domain.Test;
import com.ruoyi.classroom.service.ITestService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 测试管理Controller
 * 
 * @author Yuan
 * @date 2023-09-08
 */
@RestController
@RequestMapping("/classroom/test")
public class MTestController extends BaseController
{
    @Autowired
    private ITestService testService;

    /**
     * 查询测试管理列表
     */
    @PreAuthorize("@ss.hasPermi('classroom:test:list')")
    @GetMapping("/list")
    public TableDataInfo list(Test test)
    {
        startPage();
        List<Test> list = testService.selectTestList(test);
        return getDataTable(list);
    }

    /**
     * 导出测试管理列表
     */
    @PreAuthorize("@ss.hasPermi('classroom:test:export')")
    @Log(title = "测试管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Test test)
    {
        List<Test> list = testService.selectTestList(test);
        ExcelUtil<Test> util = new ExcelUtil<Test>(Test.class);
        util.exportExcel(response, list, "测试管理数据");
    }

    /**
     * 获取测试管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('classroom:test:query')")
    @GetMapping(value = "/{testId}")
    public AjaxResult getInfo(@PathVariable("testId") Long testId)
    {
        return success(testService.selectTestByTestId(testId));
    }

    /**
     * 新增测试管理
     */
    @PreAuthorize("@ss.hasPermi('classroom:test:add')")
    @Log(title = "测试管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Test test)
    {
        return toAjax(testService.insertTest(test));
    }

    /**
     * 修改测试管理
     */
    @PreAuthorize("@ss.hasPermi('classroom:test:edit')")
    @Log(title = "测试管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Test test)
    {
        return toAjax(testService.updateTest(test));
    }

    /**
     * 删除测试管理
     */
    @PreAuthorize("@ss.hasPermi('classroom:test:remove')")
    @Log(title = "测试管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{testIds}")
    public AjaxResult remove(@PathVariable Long[] testIds)
    {
        return toAjax(testService.deleteTestByTestIds(testIds));
    }
}
