package com.ruoyi.classroom.service;

import java.util.List;

import com.ruoyi.classroom.domain.Comment;
import com.ruoyi.classroom.domain.Topic;

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
    public int insertTopic(Topic topic);

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
    public List<Topic> findChapterByCourseById(Long courseId);


    /**
     * 查询该话题的所有评论
     * @param topicId
     * @return
     */
    public List<Comment> findContentsByTopic(Long topicId);


    public Long noParticipation(Long courseId,Long topicId);

}
