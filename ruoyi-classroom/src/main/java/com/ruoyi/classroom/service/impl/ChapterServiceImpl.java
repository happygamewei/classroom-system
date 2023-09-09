package com.ruoyi.classroom.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.classroom.mapper.ChapterMapper;
import com.ruoyi.classroom.domain.Chapter;
import com.ruoyi.classroom.service.IChapterService;

/**
 * 章节Service业务层处理
 * 
 * @author Yuan
 * @date 2023-09-08
 */
@Service
public class ChapterServiceImpl implements IChapterService 
{
    @Autowired
    private ChapterMapper chapterMapper;

    /**
     * 查询章节
     * 
     * @param chapterId 章节主键
     * @return 章节
     */
    @Override
    public Chapter selectChapterByChapterId(Long chapterId)
    {
        return chapterMapper.selectChapterByChapterId(chapterId);
    }

    /**
     * 查询章节列表
     * 
     * @param chapter 章节
     * @return 章节
     */
    @Override
    public List<Chapter> selectChapterList(Chapter chapter)
    {
        return chapterMapper.selectChapterList(chapter);
    }

    /**
     * 新增章节
     * 
     * @param chapter 章节
     * @return 结果
     */
    @Override
    public int insertChapter(Chapter chapter)
    {
        chapter.setCreateTime(DateUtils.getNowDate());
        return chapterMapper.insertChapter(chapter);
    }

    /**
     * 修改章节
     * 
     * @param chapter 章节
     * @return 结果
     */
    @Override
    public int updateChapter(Chapter chapter)
    {
        chapter.setUpdateTime(DateUtils.getNowDate());
        return chapterMapper.updateChapter(chapter);
    }

    /**
     * 批量删除章节
     * 
     * @param chapterIds 需要删除的章节主键
     * @return 结果
     */
    @Override
    public int deleteChapterByChapterIds(Long[] chapterIds)
    {
        return chapterMapper.deleteChapterByChapterIds(chapterIds);
    }

    /**
     * 删除章节信息
     * 
     * @param chapterId 章节主键
     * @return 结果
     */
    @Override
    public int deleteChapterByChapterId(Long chapterId)
    {
        return chapterMapper.deleteChapterByChapterId(chapterId);
    }
}
