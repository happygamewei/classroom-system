package com.ruoyi.classroom.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.ruoyi.classroom.domain.*;
import com.ruoyi.classroom.domain.dto.ChapterDto;
import com.ruoyi.classroom.domain.vo.ChapterVo;
import com.ruoyi.classroom.domain.vo.ContentVo;
import com.ruoyi.classroom.domain.vo.CourseSmallVo;
import com.ruoyi.classroom.mapper.*;
import com.ruoyi.classroom.utils.ClassRoomConstants;
import com.ruoyi.classroom.mapper.CourseChapterMapper;
import com.ruoyi.classroom.mapper.CourseMapper;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    @Autowired
    private ChapterContentMapper chapterContentMapper;

    @Autowired
    private HomeworkMapper homeworkMapper;

    @Autowired
    private TestMapper testMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private NoticeMapper noticeMapper;

    @Autowired
    private TopicMapper topicMapper;

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
        //检测学时数是否已经超过了课程总的
        Long creditHours = chapterDto.getCreditHours();
        Long courseId = chapterDto.getCourseId();
        Integer allCredit = courseMapper.selectCreditHoursByCourseId(courseId);
        List<CourseChapter> courseChapters = courseChapterMapper.selectChaptersByCourserId(courseId);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        courseChapters.forEach(courseChapter -> {
            Integer allocatedCredit = chapterMapper.selectCreditHoursByCourseId(courseChapter.getCourseId());
            atomicInteger.addAndGet(allocatedCredit);
        });
        if(creditHours + atomicInteger.get() > allCredit){
            throw new RuntimeException("所添加的学时数已经超过了课程的学时数");
        }


        //将chapterDto的内容copy到chapter中
        Chapter chapter = new Chapter();
        BeanUtils.copyProperties(chapterDto, chapter);

        // 设置章节类型
        if(chapterDto.getParentId() == 0){
            chapter.setType("1");
        }else {
            chapter.setType("2");
        }

        // 设置创建者
        chapter.setUserId(SecurityUtils.getUserId());
        chapter.setCreateBy(SecurityUtils.getUsername());
        chapter.setCreateTime(DateUtils.getNowDate());
        int insertChapter = chapterMapper.insertChapter(chapter);

        //设置课程和章节关系
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

    /**
     * 根据课程id获取章节
     * @param courseId
     * @return
     */
    @Override
    public List<ChapterVo> getChapterByCourseId(Long courseId) {
        // 查询课程章节关系
        List<CourseChapter> courseChapters = courseChapterMapper.selectChaptersByCourserId(courseId);

        // 根据chapterIds查询章节
        List<Long> chapterIds = courseChapters.stream().map(CourseChapter::getChapterId).collect(Collectors.toList());
        List<Chapter> chapterList = chapterMapper.selectChapterByChapterIds(chapterIds);

        // 转换成chapterVoList
        List<ChapterVo> chapterVoList = new ArrayList<>();
        chapterList.forEach(chapter -> {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);
            chapterVoList.add(chapterVo);
        });

        //将子目录放在父目录下
        // 将chapterList转换为Map，其中键为parentId，值为具有相同parentId的Chapter对象列表
        Map<Long, List<ChapterVo>> chapterMap = chapterVoList.stream().filter(chapter -> chapter.getParentId() != 0)
                .collect(Collectors.groupingBy(Chapter::getParentId));

        // 遍历chapterList,将具有相同parentId的Chapter对象添加到其父目录的children字段中
        chapterVoList.forEach(chapterVo -> {
            Long chapterId = chapterVo.getChapterId();

            // 进行添加目录的活动
            List<ChapterContent> chapterContents = chapterContentMapper.selectByChapterId(chapterId);
            List<ContentVo> contentVoList = new ArrayList<>();
            chapterContents.stream().forEach(chapterContent -> {
                if(ClassRoomConstants.HOMEWORK_CONTENT.equals(chapterContent.getContentType())){
                    Homework homework = homeworkMapper.selectHomeworkByHomeworkId(chapterContent.getContentId());
                    ContentVo contentVo = new ContentVo(homework);
                    contentVo.setTypeContent(ClassRoomConstants.HOMEWORK_CONTENT);
                    contentVoList.add(contentVo);
                } else if (ClassRoomConstants.TEST_CONTENT.equals(chapterContent.getContentType())) {
                    Test test = testMapper.selectTestByTestId(chapterContent.getContentId());
                    ContentVo contentVo = new ContentVo(test);
                    contentVo.setTypeContent(ClassRoomConstants.TEST_CONTENT);
                    contentVoList.add(contentVo);
                } else if (ClassRoomConstants.RESOURCE_CONTENT.equals(chapterContent.getContentType())) {
                    Resource resource = resourceMapper.selectResourceByResourceId(chapterContent.getContentId());
                    ContentVo contentVo = new ContentVo(resource);
                    contentVo.setTypeContent(ClassRoomConstants.RESOURCE_CONTENT);
                    contentVoList.add(contentVo);
                } else if (ClassRoomConstants.NOTICE_CONTENT.equals(chapterContent.getContentType())) {
                    Notice notice = noticeMapper.selectNoticeByNoticeId(chapterContent.getContentId());
                    ContentVo contentVo = new ContentVo(notice);
                    contentVo.setTypeContent(ClassRoomConstants.NOTICE_CONTENT);
                    contentVoList.add(contentVo);
                } else if (ClassRoomConstants.TOPIC_CONTENT.equals(chapterContent.getContentType())) {
                    Topic topic = topicMapper.selectTopicByTopicId(chapterContent.getContentId());
                    ContentVo contentVo = new ContentVo(topic);
                    contentVo.setTypeContent(ClassRoomConstants.TOPIC_CONTENT);
                    contentVoList.add(contentVo);
                }
            });

            if(chapterContents.size() > 0){
                chapterVo.setActiveNumber(chapterContents.size());
                chapterVo.setContent(chapterContents);
                if(chapterVo.getParentId() != 0){
                    chapterVo.setChildren(contentVoList);
                }
            }

            // 将父目录的id拿到map中比较得到它的子目录
            List<ChapterVo> children = chapterMap.get(chapterId);
            if(children != null){
                chapterVo.setChildren(children);
            }
        });

        // 去除子目录
        List<ChapterVo> collect = chapterVoList.stream()
                .filter(chapterVo -> chapterVo.getParentId() == 0)
                .collect(Collectors.toList());

        // 累加得到章节的活动数
        collect.stream().forEach(chapterVo -> {
            Integer activeNumber = chapterVo.getActiveNumber();
            AtomicInteger activeN = new AtomicInteger(0);
            List<ChapterVo> children = (List<ChapterVo>) chapterVo.getChildren();
            if(children.size() > 0){
                children.stream().forEach(item -> {
                    activeN.addAndGet(item.getActiveNumber());
                });
            }
            chapterVo.setActiveNumber(activeNumber + activeN.get());
        });

        return collect;
    }
}
