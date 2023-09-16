package com.ruoyi.classroom.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.classroom.domain.*;
import com.ruoyi.classroom.mapper.*;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.classroom.service.ITopicService;

/**
 * 话题Service业务层处理
 *
 * @author Yuan
 * @date 2023-09-07
 */
@Service
public class TopicServiceImpl implements ITopicService
{
    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private CommentContentMapper commentContentMapper;
    @Autowired
    private CourseChapterMapper courseChapterMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CourseMapper courseMapper;

    /**
     * 查询话题
     *
     * @param topicId 话题主键
     * @return 话题
     */
    @Override
    public Topic selectTopicByTopicId(Long topicId)
    {
        return topicMapper.selectTopicByTopicId(topicId);
    }

    /**
     * 查询话题列表
     *
     * @param topic 话题
     * @return 话题
     */
    @Override
    public List<Topic> selectTopicList(Topic topic)
    {
        return topicMapper.selectTopicList(topic);
    }

    /**
     * 新增话题
     *
     * @param topic 话题
     * @return 结果
     */
    @Override
    public int insertTopic(Topic topic)
    {
        topic.setCreateTime(DateUtils.getNowDate());
        return topicMapper.insertTopic(topic);
    }

    /**
     * 修改话题
     *
     * @param topic 话题
     * @return 结果
     */
    @Override
    public int updateTopic(Topic topic)
    {
        topic.setUpdateTime(DateUtils.getNowDate());
        return topicMapper.updateTopic(topic);
    }

    /**
     * 批量删除话题
     *
     * @param topicIds 需要删除的话题主键
     * @return 结果
     */
    @Override
    public int deleteTopicByTopicIds(Long[] topicIds)
    {
        return topicMapper.deleteTopicByTopicIds(topicIds);
    }

    /**
     * 删除话题信息
     *
     * @param topicId 话题主键
     * @return 结果
     */
    @Override
    public int deleteTopicByTopicId(Long topicId)
    {
        return topicMapper.deleteTopicByTopicId(topicId);
    }

    /**
     * 查找该课程的全部话题
     * @param courseId
     * @return
     */
    @Override
    public List<Topic> findChapterByCourseById(Long courseId) {
        List<CourseChapter> courseChapters=courseChapterMapper.findChapterByCourseById(courseId);

        List<Topic> topics=new ArrayList<>();
        for (int i = 0; i <courseChapters.size() ; i++) {
            topics.add(topicMapper.findTopicByChapterId(courseChapters.get(i).getChapterId()));
        }
        return topics;
    }

    /**
     * 查询该话题评论的数量
     * @param topicId
     * @return
     */
    @Override
    public int findContentCountByTopic(Long topicId) {
           List<CommentContent> commentContents=commentContentMapper.findContentCountByTopic(topicId);
        return commentContents.size();
    }

    /**
     * 查询该话题所有的评论
     * @param topicId
     * @return
     */
    @Override
    public List<Comment> findContentsByTopic(Long topicId) {
        List<CommentContent> commentContents=commentContentMapper.findContentCountByTopic(topicId);
        List<Comment> comments=new ArrayList<>();
        for (int i = 0; i <commentContents.size() ; i++) {
            comments.set(i,commentMapper.selectCommentByCommentId(commentContents.get(i).getCommentId()));
        }
        return comments;
    }

    /**
     * 该话题未参加人的数量
     * @param courseId
     * @param topicId
     * @return
     */
    @Override
    public Long noParticipation(Long courseId,Long topicId) {
             Long Join=topicMapper.selectTopicByTopicId(topicId).getJoinNumber();
             Long All=courseMapper.selectCourseByCourseId(courseId).getJoinNumber();
        return All-Join ;
    }
}
