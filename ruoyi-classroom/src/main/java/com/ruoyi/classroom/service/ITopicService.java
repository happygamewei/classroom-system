package com.ruoyi.classroom.service;

import java.util.List;

import com.ruoyi.classroom.domain.Chapter;
import com.ruoyi.classroom.domain.ChapterContent;
import com.ruoyi.classroom.domain.Comment;
import com.ruoyi.classroom.domain.Topic;
import com.ruoyi.classroom.domain.vo.ChapterVo;
import com.ruoyi.classroom.domain.vo.CommentVo;
import com.ruoyi.classroom.domain.vo.TopicVo;
import com.ruoyi.common.core.domain.entity.SysUser;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.userdetails.User;

/**
 * 话题Service接口
 *
 * @author Yuan
 * @date 2023-09-07
 */
public interface ITopicService
{
    /**
     * 查询话题
     *
     * @param topicId 话题主键
     * @return 话题
     */
    public Topic selectTopicByTopicId(Long topicId);

    /**
     * 查询话题列表
     *
     * @param topic 话题
     * @return 话题集合
     */
    public List<Topic> selectTopicList(Topic topic);

    /**
     * 新增话题
     *
     * @param topic 话题
     * @return 结果
     */
    public void insertTopic(Topic topic,Long userId);

    /**
     * 修改话题
     *
     * @param topic 话题
     * @return 结果
     */
    public int updateTopic(Topic topic);

    /**
     * 批量删除话题
     *
     * @param topicIds 需要删除的话题主键集合
     * @return 结果
     */
    public int deleteTopicByTopicIds(Long[] topicIds);

    /**
     * 删除话题信息
     *
     * @param topicId 话题主键
     * @return 结果
     */
    public int deleteTopicByTopicId(Long topicId);

    /**
     * 查找并统计话题的评论数
     */
    public int findContentCountByTopic(Long topicId);

    /**
     * 查询属于该课程的所有话题
     * @param courseId
     * @return
     */
    public List<TopicVo> findChapterByCourseById(Long courseId);


    /**
     * 查询该话题的没有父节点的所有评论
     * @param topicId
     * @return
     */
    public List<Comment> findContentsByTopic(Long topicId);
    public List<Comment> replyComment(Long parentId);
    /**
     * 话题点赞
     * @param userId
     * @param topicId
     */
    public void likeClick(Long userId,Long topicId);
    public Long noParticipation(Long courseId,Long topicId);
    public void addTopicComment(Long userId,Long topicId,String comment,Long parentId);
    public void isJoinTopic(Long userId,Long topicId);
    public List<Chapter> processChapters(Long courseId);
    public SysUser findUserByTopicId(Long topic);
    public  List<CommentVo> processComments(Long topicId);
    public int deleteComment(CommentVo commentVo);
    public void CommentLikes(Long userId,Long commentId);
    public Boolean likeState(Long userId,Long commentId);
    public List<ChapterVo> getChapterByCourseId(Long courseId);

}
