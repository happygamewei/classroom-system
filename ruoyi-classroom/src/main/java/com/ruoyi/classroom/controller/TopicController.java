package com.ruoyi.classroom.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.classroom.domain.Comment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.classroom.domain.Topic;
import com.ruoyi.classroom.service.ITopicService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 话题Controller
 *
 * @author Yuan
 * @date 2023-09-07
 */
@RestController
@RequestMapping("/classroom/topic")
public class TopicController extends BaseController
{
    @Autowired
    private ITopicService topicService;

    /**
     * 查询话题列表
     */
    //@PreAuthorize("@ss.hasPermi('classroom:topic:list')")
    @GetMapping("/list")
    public TableDataInfo list(Topic topic)
    {
        startPage();
        List<Topic> list = topicService.selectTopicList(topic);
        return getDataTable(list);
    }

    /**
     * 导出话题列表
     */
    //@PreAuthorize("@ss.hasPermi('classroom:topic:export')")
   // @Log(title = "话题", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Topic topic)
    {
        List<Topic> list = topicService.selectTopicList(topic);
        ExcelUtil<Topic> util = new ExcelUtil<Topic>(Topic.class);
        util.exportExcel(response, list, "话题数据");
    }

    /**
     * 获取话题详细信息
     */
    //@PreAuthorize("@ss.hasPermi('classroom:topic:query')")
    @GetMapping(value = "/{topicId}")
    public AjaxResult getInfo(@PathVariable("topicId") Long topicId)
    {
        return success(topicService.selectTopicByTopicId(topicId));
    }

    /**
     * 新增话题
     */
    //@PreAuthorize("@ss.hasPermi('classroom:topic:add')")
    //@Log(title = "话题", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Topic topic)
    {
        return toAjax(topicService.insertTopic(topic));
    }

    /**
     * 修改话题
     */
    //@PreAuthorize("@ss.hasPermi('classroom:topic:edit')")
    //@Log(title = "话题", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Topic topic)
    {
        return toAjax(topicService.updateTopic(topic));
    }

    /**
     * 删除话题
     */
    //@PreAuthorize("@ss.hasPermi('classroom:topic:remove')")
    //@Log(title = "话题", businessType = BusinessType.DELETE)
	@DeleteMapping("/{topicIds}")
    public AjaxResult remove(@PathVariable Long[] topicIds)
    {
        return toAjax(topicService.deleteTopicByTopicIds(topicIds));
    }

    /**
     * 根据课程id查询该课程的所有话题
     * @param courseId
     * @return
     */

    @GetMapping("/allTopics/{courseId}")
    public List<Topic> findChapterByCourseById(@PathVariable("courseId") Long courseId){
        System.out.println("查询话题执行了");
            return topicService.findChapterByCourseById(courseId);
    }

    /**
     * 查找这个课程话题的数量
     * @param courseId
     * @return
     */
    @GetMapping("/allTopicCount/{courseId}")
    public int findChapterByCourseCountById(@PathVariable("courseId") Long courseId){
        System.out.println("查询话题数量执行了");
        System.out.println(topicService.findChapterByCourseById(courseId).size());
        return topicService.findChapterByCourseById(courseId).size();
    }
    /**
     * 返回话题的评论数量
     * @param topicId
     * @return
     */


    @GetMapping("/commentCount/{topicsId}")
    public int findCommentsCountByTopicsId(@PathVariable("topicsId") Long topicId){

        return topicService.findContentCountByTopic(topicId);
    }

    /**
     * 返回该话题的所有评论的内容
     * @param topicId
     * @return
     */


    @GetMapping("/commentContent/{topicid}")
    public List<Comment> findCommentsByTopic(@PathVariable("topicid") Long topicId){
        return topicService.findContentsByTopic(topicId);
    }

    /**
     * 返回未参与的数量
     * @param courseId
     * @param topicId
     * @return
     */


    @GetMapping("/{courseId}/{topicId}")
    public Long noParticipation(@PathVariable("courseId") Long courseId,@PathVariable("topicId") Long topicId){
     return topicService.noParticipation(courseId,topicId);
    }

}
