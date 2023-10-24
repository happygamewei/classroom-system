package com.ruoyi.classroom.mapper;

import java.util.List;
import com.ruoyi.classroom.domain.UserTopic;
import org.apache.ibatis.annotations.Param;

/**
 * 用户话题关系管理Mapper接口
 *
 * @author Yuan
 * @date 2023-09-07
 */
public interface UserTopicMapper
{
    /**
     * 查询用户话题关系管理
     *
     * @param userTopicId 用户话题关系管理主键
     * @return 用户话题关系管理
     */
    public UserTopic selectUserTopicByUserTopicId(Long userTopicId);

    /**
     * 查询用户话题关系管理列表
     *
     * @param userTopic 用户话题关系管理
     * @return 用户话题关系管理集合
     */
    public List<UserTopic> selectUserTopicList(UserTopic userTopic);

    /**
     * 新增用户话题关系管理
     *
     * @param userTopic 用户话题关系管理
     * @return 结果
     */
    public int insertUserTopic(UserTopic userTopic);

    /**
     * 修改用户话题关系管理
     *
     * @param userTopic 用户话题关系管理
     * @return 结果
     */
    public int updateUserTopic(UserTopic userTopic);

    /**
     * 删除用户话题关系管理
     *
     * @param userTopicId 用户话题关系管理主键
     * @return 结果
     */
    public int deleteUserTopicByUserTopicId(Long userTopicId);

    /**
     * 批量删除用户话题关系管理
     *
     * @param userTopicIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserTopicByUserTopicIds(Long[] userTopicIds);

    public UserTopic findByTopicId(@Param("userId")Long userId,@Param("topicId") Long topicId);
    public int updateIsLikeInt1(Long userTopicId);
    public int updateIsLikeInt2(Long userTopicId);
    public int updateIsReadLabelInt(Long userTopicId);
    public  Long findUserByTopicId(Long topicId);
    public int deleteByTopicId(Long topicId);

}
