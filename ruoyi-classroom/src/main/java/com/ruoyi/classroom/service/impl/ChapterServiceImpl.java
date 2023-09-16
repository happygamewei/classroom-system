package com.ruoyi.classroom.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ruoyi.classroom.domain.CourseChapter;
import com.ruoyi.classroom.domain.dto.ChapterDto;
import com.ruoyi.classroom.domain.vo.ChapterVo;
import com.ruoyi.classroom.domain.vo.CourseSmallVo;
import com.ruoyi.classroom.mapper.CourseChapterMapper;
import com.ruoyi.classroom.mapper.CourseMapper;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.BeanUtils;
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

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CourseChapterMapper courseChapterMapper;

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
     * @param chapterDto 章节
     * @return 结果
     */
    @Override
    public int insertChapter(ChapterDto chapterDto)
    {
        //将chapterDto的内容copy到chapter中
        Chapter chapter = new Chapter();
        BeanUtils.copyProperties(chapterDto, chapter);
        // 设置创建者
        chapter.setUserId(SecurityUtils.getUserId());
        chapter.setCreateBy(SecurityUtils.getUsername());
        chapter.setCreateTime(DateUtils.getNowDate());
        int insertChapter = chapterMapper.insertChapter(chapter);

        //设置课程和章节关系
        Long courseId = chapterDto.getCourseId();
        CourseChapter courseChapter = new CourseChapter();
        courseChapter.setCourseId(courseId);

        courseChapter.setChapterId(chapter.getChapterId());
        courseChapterMapper.insertCourseChapter(courseChapter);

        return insertChapter;
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
        courseChapterMapper.deleteByChapterId(chapterId);
        return chapterMapper.deleteChapterByChapterId(chapterId);
    }

    /**
     * 查询课程
     * @return
     */
    @Override
    public List<CourseSmallVo> getCourse() {
        List<CourseSmallVo> courseSmallVos = courseMapper.selectCourseInChapter();
        return courseSmallVos;
    }

    @Override
    public List<Chapter> getChapterByCourseId(Long courseId) {
        // 查询课程章节关系
        List<CourseChapter> courseChapters = courseChapterMapper.selectChaptersByCourserId(courseId);

        // 根据chapterIds查询章节
        List<Long> chapterIds = courseChapters.stream().map(CourseChapter::getChapterId).collect(Collectors.toList());
        List<Chapter> chapterList = chapterMapper.selectChapterByChapterIds(chapterIds);
        // 将子目录放在父目录下
        // 将chapterList转换为Map，其中键为parentId，值为具有相同parentId的Chapter对象列表
        Map<Long, List<Chapter>> chapterMap = chapterList.stream().filter(chapter -> chapter.getParentId() != 0)
                .collect(Collectors.groupingBy(Chapter::getParentId));
        // 遍历chapterList,将具有相同parentId的Chapter对象添加到其父目录的children字段中
        chapterList.forEach(chapter -> {
            Long chapterId = chapter.getChapterId();
            // 将父目录的id拿到map中比较得到它的子目录
            List<Chapter> children = chapterMap.get(chapterId);
            if(children != null){
                chapter.setChildren(children);
            }
        });

        // 去除子目录
        List<Chapter> collect = chapterList.stream()
                .filter(chapter -> chapter.getChildren().size() > 0)
                .collect(Collectors.toList());

        return collect;
    }
}
