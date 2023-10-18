package com.ruoyi.classroom.mapper;

import java.util.List;
import com.ruoyi.classroom.domain.CommentContent;

/**
 *  评论内容关系Mapper接口
 *
 * @author Yuan
 * @date 2023-09-08
 */
public interface CommentContentMapper
{
    /**
     * 查询 评论内容关系
     *
     * @param id  评论内容关系主键
     * @return  评论内容关系
     */
    public CommentContent selectCommentContentById(Long id);

    /**
     * 查询 评论内容关系列表
     *
     * @param commentContent  评论内容关系
     * @return  评论内容关系集合
     */
    public List<CommentContent> selectCommentContentList(CommentContent commentContent);

    /**
     * 新增 评论内容关系
     *
     * @param commentContent  评论内容关系
     * @return 结果
     */
    public int insertCommentContent(CommentContent commentContent);

    /**
     * 修改 评论内容关系
     *
     * @param commentContent  评论内容关系
     * @return 结果
     */
    public int updateCommentContent(CommentContent commentContent);

    /**
     * 删除 评论内容关系
     *
     * @param id  评论内容关系主键
     * @return 结果
     */
    public int deleteCommentContentById(Long id);

    /**
     * 批量删除 评论内容关系
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCommentContentByIds(Long[] ids);

    /**
     * 查找属于该话题的所有评论
     * @param topicId
     * @return
     */
    public List<CommentContent> findContentCountByTopic(Long topicId);

    /**
     * 查找属于该公告的所有评论
     * @param noticeId
     * @return
     */
    public List<CommentContent> findContentCountByNotice(Long noticeId);
    Long getCommentListTotal(Long noticeId);
    public  int deleteByCommentId (Long commentId);
}
