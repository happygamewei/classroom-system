package com.ruoyi.classroom.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.classroom.domain.Comment;
import org.quartz.SchedulerException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/list")
    public TableDataInfo list(Notice notice)
    {
         startPage();
        List<Notice> list = noticeService.selectNoticeList(notice);
        return getDataTable(list);
    }
//    @GetMapping("/byId/{noticeId}")
//    public AjaxResult getNoticeByCourseId(@PathVariable("noticeId") Long noticeId){
//        return success(noticeService.getNoticeById(noticeId));
//    }

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

    @GetMapping(value = "byId/{noticeId}")
    public AjaxResult getInfo(@PathVariable("noticeId") Long noticeId)
    {
        return success(noticeService.selectNoticeByNoticeId(noticeId));
    }

    /**
     * 新增公告
     */
    @CrossOrigin
    @PostMapping("/add")
    public AjaxResult add(@RequestBody Notice notice) throws SchedulerException {
        if (notice.getPublishDate()!=null){
            return toAjax(noticeService.TimingIssue(notice));
        }
        System.out.println("----------"+notice);
        return toAjax(noticeService.insertNotice(notice));
    }

    /**
     * 修改公告
     */
    @CrossOrigin
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody Notice notice)
    {
        return toAjax(noticeService.updateNotice(notice));
    }

    /**
     * 删除公告
     */
	@DeleteMapping("/delete/{noticeId}")
    public AjaxResult remove(@PathVariable("noticeId") Long noticeId)
    {
        return toAjax(noticeService.deleteNoticeByNoticeId(noticeId));
    }

    @GetMapping("/commentInNotice/{noticeId}")
    public AjaxResult listInNotice(@PathVariable("noticeId") Long noticeId)
    {
        return success(noticeService.processComments(noticeId));
    }




}
