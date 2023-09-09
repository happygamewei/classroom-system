package com.ruoyi.classroom.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.classroom.mapper.TopicMapper;
import com.ruoyi.classroom.domain.Topic;
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
}
