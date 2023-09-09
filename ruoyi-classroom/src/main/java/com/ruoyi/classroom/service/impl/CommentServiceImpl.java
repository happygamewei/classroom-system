package com.ruoyi.classroom.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.classroom.mapper.CommentMapper;
import com.ruoyi.classroom.domain.Comment;
import com.ruoyi.classroom.service.ICommentService;

/**
 * 评论管理Service业务层处理
 * 
 * @author Yuan
 * @date 2023-09-08
 */
@Service
public class CommentServiceImpl implements ICommentService 
{
    @Autowired
    private CommentMapper commentMapper;

    /**
     * 查询评论管理
     * 
     * @param commentId 评论管理主键
     * @return 评论管理
     */
    @Override
    public Comment selectCommentByCommentId(Long commentId)
    {
        return commentMapper.selectCommentByCommentId(commentId);
    }

    /**
     * 查询评论管理列表
     * 
     * @param comment 评论管理
     * @return 评论管理
     */
    @Override
    public List<Comment> selectCommentList(Comment comment)
    {
        return commentMapper.selectCommentList(comment);
    }

    /**
     * 新增评论管理
     * 
     * @param comment 评论管理
     * @return 结果
     */
    @Override
    public int insertComment(Comment comment)
    {
        comment.setCreateTime(DateUtils.getNowDate());
        return commentMapper.insertComment(comment);
    }

    /**
     * 修改评论管理
     * 
     * @param comment 评论管理
     * @return 结果
     */
    @Override
    public int updateComment(Comment comment)
    {
        comment.setUpdateTime(DateUtils.getNowDate());
        return commentMapper.updateComment(comment);
    }

    /**
     * 批量删除评论管理
     * 
     * @param commentIds 需要删除的评论管理主键
     * @return 结果
     */
    @Override
    public int deleteCommentByCommentIds(Long[] commentIds)
    {
        return commentMapper.deleteCommentByCommentIds(commentIds);
    }

    /**
     * 删除评论管理信息
     * 
     * @param commentId 评论管理主键
     * @return 结果
     */
    @Override
    public int deleteCommentByCommentId(Long commentId)
    {
        return commentMapper.deleteCommentByCommentId(commentId);
    }
}
