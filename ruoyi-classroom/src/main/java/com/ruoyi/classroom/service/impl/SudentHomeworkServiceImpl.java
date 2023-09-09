package com.ruoyi.classroom.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.classroom.mapper.SudentHomeworkMapper;
import com.ruoyi.classroom.domain.SudentHomework;
import com.ruoyi.classroom.service.ISudentHomeworkService;

/**
 * 学生作业Service业务层处理
 * 
 * @author rain
 * @date 2023-09-08
 */
@Service
public class SudentHomeworkServiceImpl implements ISudentHomeworkService 
{
    @Autowired
    private SudentHomeworkMapper sudentHomeworkMapper;

    /**
     * 查询学生作业
     * 
     * @param shId 学生作业主键
     * @return 学生作业
     */
    @Override
    public SudentHomework selectSudentHomeworkByShId(Long shId)
    {
        return sudentHomeworkMapper.selectSudentHomeworkByShId(shId);
    }

    /**
     * 查询学生作业列表
     * 
     * @param sudentHomework 学生作业
     * @return 学生作业
     */
    @Override
    public List<SudentHomework> selectSudentHomeworkList(SudentHomework sudentHomework)
    {
        return sudentHomeworkMapper.selectSudentHomeworkList(sudentHomework);
    }

    /**
     * 新增学生作业
     * 
     * @param sudentHomework 学生作业
     * @return 结果
     */
    @Override
    public int insertSudentHomework(SudentHomework sudentHomework)
    {
        sudentHomework.setCreateTime(DateUtils.getNowDate());
        return sudentHomeworkMapper.insertSudentHomework(sudentHomework);
    }

    /**
     * 修改学生作业
     * 
     * @param sudentHomework 学生作业
     * @return 结果
     */
    @Override
    public int updateSudentHomework(SudentHomework sudentHomework)
    {
        sudentHomework.setUpdateTime(DateUtils.getNowDate());
        return sudentHomeworkMapper.updateSudentHomework(sudentHomework);
    }

    /**
     * 批量删除学生作业
     * 
     * @param shIds 需要删除的学生作业主键
     * @return 结果
     */
    @Override
    public int deleteSudentHomeworkByShIds(Long[] shIds)
    {
        return sudentHomeworkMapper.deleteSudentHomeworkByShIds(shIds);
    }

    /**
     * 删除学生作业信息
     * 
     * @param shId 学生作业主键
     * @return 结果
     */
    @Override
    public int deleteSudentHomeworkByShId(Long shId)
    {
        return sudentHomeworkMapper.deleteSudentHomeworkByShId(shId);
    }
}
