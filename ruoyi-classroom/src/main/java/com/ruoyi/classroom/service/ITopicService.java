package com.ruoyi.classroom.service;

import java.util.List;
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
}
