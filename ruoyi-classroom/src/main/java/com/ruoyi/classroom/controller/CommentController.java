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
import com.ruoyi.classroom.domain.Comment;
import com.ruoyi.classroom.service.ICommentService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 评论管理Controller
 * 
 * @author Yuan
 * @date 2023-09-08
 */
@RestController
@RequestMapping("/classroom/comment")
public class CommentController extends BaseController
{
    @Autowired
    private ICommentService commentService;

    /**
     * 查询评论管理列表
     */
    @PreAuthorize("@ss.hasPermi('classroom:comment:list')")
    @GetMapping("/list")
    public AjaxResult list(Comment comment)
    {
        List<Comment> list = commentService.selectCommentList(comment);
        return success(list);
    }

    /**
     * 导出评论管理列表
     */
    @PreAuthorize("@ss.hasPermi('classroom:comment:export')")
    @Log(title = "评论管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Comment comment)
    {
        List<Comment> list = commentService.selectCommentList(comment);
        ExcelUtil<Comment> util = new ExcelUtil<Comment>(Comment.class);
        util.exportExcel(response, list, "评论管理数据");
    }

    /**
     * 获取评论管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('classroom:comment:query')")
    @GetMapping(value = "/{commentId}")
    public AjaxResult getInfo(@PathVariable("commentId") Long commentId)
    {
        return success(commentService.selectCommentByCommentId(commentId));
    }

    /**
     * 新增评论管理
     */
    @PreAuthorize("@ss.hasPermi('classroom:comment:add')")
    @Log(title = "评论管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Comment comment)
    {
        return toAjax(commentService.insertComment(comment));
    }

    /**
     * 修改评论管理
     */
    @PreAuthorize("@ss.hasPermi('classroom:comment:edit')")
    @Log(title = "评论管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Comment comment)
    {
        return toAjax(commentService.updateComment(comment));
    }

    /**
     * 删除评论管理
     */
    @PreAuthorize("@ss.hasPermi('classroom:comment:remove')")
    @Log(title = "评论管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{commentIds}")
    public AjaxResult remove(@PathVariable Long[] commentIds)
    {
        return toAjax(commentService.deleteCommentByCommentIds(commentIds));
    }
}
