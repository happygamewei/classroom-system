package com.ruoyi.classroom.mapper;

import java.util.List;
import com.ruoyi.classroom.domain.ChapterContent;

/**
 * 章节内容管理Mapper接口
 *
 * @author Yuan
 * @date 2023-09-08
 */
public interface ChapterContentMapper
{
    /**
     * 查询章节内容管理
     *
     * @param id 章节内容管理主键
     * @return 章节内容管理
     */
    public ChapterContent selectChapterContentById(Long id);

    /**
     * 查询章节内容管理列表
     *
     * @param chapterContent 章节内容管理
     * @return 章节内容管理集合
     */
    public List<ChapterContent> selectChapterContentList(ChapterContent chapterContent);

    /**
     * 新增章节内容管理
     *
     * @param chapterContent 章节内容管理
     * @return 结果
     */
    public int insertChapterContent(ChapterContent chapterContent);

    /**
     * 修改章节内容管理
     *
     * @param chapterContent 章节内容管理
     * @return 结果
     */
    public int updateChapterContent(ChapterContent chapterContent);

    /**
     * 删除章节内容管理
     *
     * @param id 章节内容管理主键
     * @return 结果
     */
    public int deleteChapterContentById(Long id);

    /**
     * 批量删除章节内容管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteChapterContentByIds(Long[] ids);

    /**
     * 根据章节id查询数据
     * @param chapterId
     * @return
     */
    List<ChapterContent> selectByChapterId(Long chapterId);

    int deleteByContentId(Long topicId);


    /**
     * 根据资料id删除章节内容管理
     * @param resourceId
     * @return
     */
    public int deleteChapterContentByResourceId(Long resourceId);
}
