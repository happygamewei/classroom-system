package com.ruoyi.classroom.service;

import java.util.List;
import com.ruoyi.classroom.domain.Chapter;
import com.ruoyi.classroom.domain.dto.ChapterDto;
import com.ruoyi.classroom.domain.vo.ChapterVo;
import com.ruoyi.classroom.domain.vo.CourseSmallVo;

/**
 * 章节Service接口
 * 
 * @author Yuan
 * @date 2023-09-08
 */
public interface IChapterService 
{
    /**
     * 查询章节
     * 
     * @param chapterId 章节主键
     * @return 章节
     */
    public Chapter selectChapterByChapterId(Long chapterId);

    /**
     * 查询章节列表
     * 
     * @param chapter 章节
     * @return 章节集合
     */
    public List<Chapter> selectChapterList(Chapter chapter);

    /**
     * 新增章节
     * 
     * @param chapter 章节
     * @return 结果
     */
    public int insertChapter(ChapterDto chapter);

    /**
     * 修改章节
     * 
     * @param chapter 章节
     * @return 结果
     */
    public int updateChapter(Chapter chapter);

    /**
     * 批量删除章节
     * 
     * @param chapterIds 需要删除的章节主键集合
     * @return 结果
     */
    public int deleteChapterByChapterIds(Long[] chapterIds);

    /**
     * 删除章节信息
     * 
     * @param chapterId 章节主键
     * @return 结果
     */
    public int deleteChapterByChapterId(Long chapterId);

    /**
     * 查询课程
     * @return
     */
    List<CourseSmallVo> getCourse();

    /**
     * 根据课程id查询章节
     * @param courseId
     * @return
     */
    List<Chapter> getChapterByCourseId(Long courseId);
}
