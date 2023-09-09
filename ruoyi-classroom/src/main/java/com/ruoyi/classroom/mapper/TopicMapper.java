package com.ruoyi.classroom.mapper;

import java.util.List;
import com.ruoyi.classroom.domain.Topic;

/**
 * 话题Mapper接口
 * 
 * @author Yuan
 * @date 2023-09-07
 */
public interface TopicMapper 
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
     * 删除话题
     * 
     * @param topicId 话题主键
     * @return 结果
     */
    public int deleteTopicByTopicId(Long topicId);

    /**
     * 批量删除话题
     * 
     * @param topicIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTopicByTopicIds(Long[] topicIds);
}
