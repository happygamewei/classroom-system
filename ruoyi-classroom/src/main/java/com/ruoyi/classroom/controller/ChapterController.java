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
import com.ruoyi.classroom.domain.Chapter;
import com.ruoyi.classroom.service.IChapterService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 章节Controller
 * 
 * @author Yuan
 * @date 2023-09-08
 */
@RestController
@RequestMapping("/classroom/chapter")
public class ChapterController extends BaseController
{
    @Autowired
    private IChapterService chapterService;

    /**
     * 查询章节列表
     */
    @PreAuthorize("@ss.hasPermi('classroom:chapter:list')")
    @GetMapping("/list")
    public AjaxResult list(Chapter chapter)
    {
        List<Chapter> list = chapterService.selectChapterList(chapter);
        return success(list);
    }

    /**
     * 导出章节列表
     */
    @PreAuthorize("@ss.hasPermi('classroom:chapter:export')")
    @Log(title = "章节", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Chapter chapter)
    {
        List<Chapter> list = chapterService.selectChapterList(chapter);
        ExcelUtil<Chapter> util = new ExcelUtil<Chapter>(Chapter.class);
        util.exportExcel(response, list, "章节数据");
    }

    /**
     * 获取章节详细信息
     */
    @PreAuthorize("@ss.hasPermi('classroom:chapter:query')")
    @GetMapping(value = "/{chapterId}")
    public AjaxResult getInfo(@PathVariable("chapterId") Long chapterId)
    {
        return success(chapterService.selectChapterByChapterId(chapterId));
    }

    /**
     * 新增章节
     */
    @PreAuthorize("@ss.hasPermi('classroom:chapter:add')")
    @Log(title = "章节", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Chapter chapter)
    {
        return toAjax(chapterService.insertChapter(chapter));
    }

    /**
     * 修改章节
     */
    @PreAuthorize("@ss.hasPermi('classroom:chapter:edit')")
    @Log(title = "章节", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Chapter chapter)
    {
        return toAjax(chapterService.updateChapter(chapter));
    }

    /**
     * 删除章节
     */
    @PreAuthorize("@ss.hasPermi('classroom:chapter:remove')")
    @Log(title = "章节", businessType = BusinessType.DELETE)
	@DeleteMapping("/{chapterIds}")
    public AjaxResult remove(@PathVariable Long[] chapterIds)
    {
        return toAjax(chapterService.deleteChapterByChapterIds(chapterIds));
    }
}
