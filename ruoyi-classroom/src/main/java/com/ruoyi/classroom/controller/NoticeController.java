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
import com.ruoyi.classroom.domain.Notice;
import com.ruoyi.classroom.service.INoticeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 公告Controller
 * 
 * @author Qiao
 * @date 2023-09-08
 */
@RestController
@RequestMapping("/classroom/notice")
public class NoticeController extends BaseController
{
    @Autowired
    private INoticeService noticeService;

    /**
     * 查询公告列表
     */
    @PreAuthorize("@ss.hasPermi('classroom:notice:list')")
    @GetMapping("/list")
    public TableDataInfo list(Notice notice)
    {
        startPage();
        List<Notice> list = noticeService.selectNoticeList(notice);
        return getDataTable(list);
    }

    /**
     * 导出公告列表
     */
    @PreAuthorize("@ss.hasPermi('classroom:notice:export')")
    @Log(title = "公告", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Notice notice)
    {
        List<Notice> list = noticeService.selectNoticeList(notice);
        ExcelUtil<Notice> util = new ExcelUtil<Notice>(Notice.class);
        util.exportExcel(response, list, "公告数据");
    }

    /**
     * 获取公告详细信息
     */
    @PreAuthorize("@ss.hasPermi('classroom:notice:query')")
    @GetMapping(value = "/{noticeId}")
    public AjaxResult getInfo(@PathVariable("noticeId") Long noticeId)
    {
        return success(noticeService.selectNoticeByNoticeId(noticeId));
    }

    /**
     * 新增公告
     */
    @PreAuthorize("@ss.hasPermi('classroom:notice:add')")
    @Log(title = "公告", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Notice notice)
    {
        return toAjax(noticeService.insertNotice(notice));
    }

    /**
     * 修改公告
     */
    @PreAuthorize("@ss.hasPermi('classroom:notice:edit')")
    @Log(title = "公告", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Notice notice)
    {
        return toAjax(noticeService.updateNotice(notice));
    }

    /**
     * 删除公告
     */
    @PreAuthorize("@ss.hasPermi('classroom:notice:remove')")
    @Log(title = "公告", businessType = BusinessType.DELETE)
	@DeleteMapping("/{noticeIds}")
    public AjaxResult remove(@PathVariable Long[] noticeIds)
    {
        return toAjax(noticeService.deleteNoticeByNoticeIds(noticeIds));
    }
}
