package com.ruoyi.classroom.mapper;

import java.util.List;
import com.ruoyi.classroom.domain.Chapter;

/**
 * 章节Mapper接口
 * 
 * @author Yuan
 * @date 2023-09-08
 */
public interface ChapterMapper 
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
    public int insertChapter(Chapter chapter);

    /**
     * 修改章节
     * 
     * @param chapter 章节
     * @return 结果
     */
    public int updateChapter(Chapter chapter);

    /**
     * 删除章节
     * 
     * @param chapterId 章节主键
     * @return 结果
     */
    public int deleteChapterByChapterId(Long chapterId);

    /**
     * 批量删除章节
     * 
     * @param chapterIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteChapterByChapterIds(Long[] chapterIds);

    /**
     * 根据ids查询chapter
     * @param chapterIds
     * @return
     */
    List<Chapter> selectChapterByChapterIds(List<Long> chapterIds);

    /**
     * 根据章节id查询学时
     * @param chapterId
     * @return
     */
    Integer selectCreditHoursByCourseId(Long chapterId);
}
