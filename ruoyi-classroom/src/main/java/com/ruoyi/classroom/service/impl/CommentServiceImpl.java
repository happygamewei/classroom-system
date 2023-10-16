package com.ruoyi.classroom.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.ruoyi.classroom.domain.CommentContent;
import com.ruoyi.classroom.domain.vo.CommentVo;
import com.ruoyi.classroom.mapper.CommentContentMapper;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import org.springframework.stereotype.Service;
import com.ruoyi.classroom.mapper.CommentMapper;
import com.ruoyi.classroom.domain.Comment;
import com.ruoyi.classroom.service.ICommentService;

import javax.annotation.Resource;

/**
 * 评论管理Service业务层处理
 *
 * @author Yuan
 * @date 2023-09-08
 */
@Service
public class CommentServiceImpl implements ICommentService {
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private CommentContentMapper commentContentMapper;

    /**
     * 查询评论管理
     *
     * @param commentId 评论管理主键
     * @return 评论管理
     */
    @Override
    public Comment selectCommentByCommentId(Long commentId) {
        return commentMapper.selectCommentByCommentId(commentId);
    }

    /**
     * 查询评论管理列表
     *
     * @param comment 评论管理
     * @return 评论管理
     */
    @Override
    public List<Comment> selectCommentList(Comment comment) {
        return commentMapper.selectCommentList(comment);
    }

    /**
     * 新增评论管理
     *
     * @param comment 评论管理
     * @return 结果
     */
    @Override
    public int insertComment(Comment comment, Long NoticeId) {

        comment.setCreateBy(SecurityUtils.getUsername());
        comment.setCreateTime(DateUtils.getNowDate());

        commentMapper.insertComment(comment);
        Long lastId = commentMapper.getLastId();
        CommentContent commentContent = new CommentContent();
        commentContent.setCommentId(lastId);
        commentContent.setContentId(NoticeId);
        commentContent.setType("3");
        System.out.println("+++++++++++++++++++++++++++" + commentContent);
        int i = commentContentMapper.insertCommentContent(commentContent);
        System.out.println("==================  " + commentContentMapper.selectCommentContentList(commentContent));
        return i;

    }

    /**
     * 修改评论管理
     *
     * @param comment 评论管理
     * @return 结果
     */
    @Override
    public int updateComment(Comment comment) {
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
    public int deleteCommentByCommentIds(Long[] commentIds) {
        return commentMapper.deleteCommentByCommentIds(commentIds);
    }

    /**
     * 删除评论管理信息
     *
     * @param commentId 评论管理主键
     * @return 结果
     */
    @Override
    public int deleteCommentByCommentId(Long commentId) {


        int i = commentMapper.deleteCommentByCommentId(commentId);

//        commentContentMapper.deleteCommentContentById(commentId);
        return commentContentMapper.deleteCommentContentById(commentId);
    }

    @Override
    public boolean removeComment(Comment comment) {
        Queue<CommentVo> queue = new LinkedList<>();
        CommentVo commentVo = new CommentVo();
        BeanUtils.copyProperties(comment, commentVo);
        queue.offer(commentVo);
        while (!queue.isEmpty()) {
            CommentVo cur = queue.poll();
            int resultNum = commentMapper.deleteCommentByCommentId(cur.getCommentId());
            if (resultNum <= 0) return false;
            if (cur.getChild() != null) {
                List<Comment> child = cur.getChild();
                for (Comment tmp : child)
                    queue.offer((CommentVo) tmp);
            }
        }
        return true;
    }

    @Override
    public Long getCommentListTotal(Long noticeId) {
        return commentContentMapper.getCommentListTotal(noticeId);
    }
}
