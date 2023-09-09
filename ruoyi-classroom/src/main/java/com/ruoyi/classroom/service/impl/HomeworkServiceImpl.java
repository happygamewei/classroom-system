package com.ruoyi.classroom.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.classroom.mapper.HomeworkMapper;
import com.ruoyi.classroom.domain.Homework;
import com.ruoyi.classroom.service.IHomeworkService;

/**
 * 发布作业Service业务层处理
 * 
 * @author rain
 * @date 2023-09-08
 */
@Service
public class HomeworkServiceImpl implements IHomeworkService 
{
    @Autowired
    private HomeworkMapper homeworkMapper;

    /**
     * 查询发布作业
     * 
     * @param homeworkId 发布作业主键
     * @return 发布作业
     */
    @Override
    public Homework selectHomeworkByHomeworkId(Long homeworkId)
    {
        return homeworkMapper.selectHomeworkByHomeworkId(homeworkId);
    }

    /**
     * 查询发布作业列表
     * 
     * @param homework 发布作业
     * @return 发布作业
     */
    @Override
    public List<Homework> selectHomeworkList(Homework homework)
    {
        return homeworkMapper.selectHomeworkList(homework);
    }

    /**
     * 新增发布作业
     * 
     * @param homework 发布作业
     * @return 结果
     */
    @Override
    public int insertHomework(Homework homework)
    {
        homework.setCreateTime(DateUtils.getNowDate());
        return homeworkMapper.insertHomework(homework);
    }

    /**
     * 修改发布作业
     * 
     * @param homework 发布作业
     * @return 结果
     */
    @Override
    public int updateHomework(Homework homework)
    {
        homework.setUpdateTime(DateUtils.getNowDate());
        return homeworkMapper.updateHomework(homework);
    }

    /**
     * 批量删除发布作业
     * 
     * @param homeworkIds 需要删除的发布作业主键
     * @return 结果
     */
    @Override
    public int deleteHomeworkByHomeworkIds(Long[] homeworkIds)
    {
        return homeworkMapper.deleteHomeworkByHomeworkIds(homeworkIds);
    }

    /**
     * 删除发布作业信息
     * 
     * @param homeworkId 发布作业主键
     * @return 结果
     */
    @Override
    public int deleteHomeworkByHomeworkId(Long homeworkId)
    {
        return homeworkMapper.deleteHomeworkByHomeworkId(homeworkId);
    }
}
