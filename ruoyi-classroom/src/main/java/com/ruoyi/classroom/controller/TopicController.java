package com.ruoyi.classroom.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.classroom.domain.vo.CommentVo;
import com.ruoyi.system.service.impl.SysDictDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
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
    @Autowired
    private SysDictDataServiceImpl sysDictDataService;

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
    @GetMapping("/{topicId}")
    public AjaxResult getInfo(@PathVariable("topicId") Long topicId)
    {

        return success(topicService.selectTopicByTopicId(topicId));
    }

    /**
     * 新增话题
     */
    //@PreAuthorize("@ss.hasPermi('classroom:topic:add')")
    //@Log(title = "话题", businessType = BusinessType.INSERT)
    @PostMapping("/{courseId}/{userId}")
    public void add(@PathVariable Long courseId,@PathVariable Long userId,@RequestBody Topic topic)
    {
        topicService.insertTopic(courseId,topic,userId);
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
     @DeleteMapping("/delete/{topicId}")
     public AjaxResult deleteTopic(@PathVariable Long topicId){
        return toAjax(topicService.deleteTopicByTopicId(topicId));
     }
    /**
     * 根据课程id查询该课程的所有话题
     * @param courseId
     * @return
     */

    @GetMapping("/list/{courseId}")
    public AjaxResult findChapterByCourseById(@PathVariable("courseId") Long courseId){

        return success(topicService.findChapterByCourseById(courseId));
    }

    /**
     * 查找这个课程话题的数量
     * @param courseId
     * @return
     */
    @GetMapping("/allTopicCount/{courseId}")
    public AjaxResult findChapterByCourseCountById(@PathVariable("courseId") Long courseId){
        return success(topicService.findChapterByCourseById(courseId).size());
    }
    /**
     * 返回话题的评论数量
     * @param topicId
     * @return
     */


    @GetMapping("/commentCount/{topicId}")
    public AjaxResult findCommentsCountByTopicsId(@PathVariable("topicId") Long topicId){

        return success(topicService.findContentCountByTopic(topicId));
    }

    /**
     * 返回该话题的所有评论
     * @param topicId
     * @return
     */


    @GetMapping("/getAllComments/{topicid}")
    public AjaxResult findCommentsByTopic(@PathVariable("topicid") Long topicId){

        return success(topicService.processComments(topicId));
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

    /**
     * 添加评论
     * @param userId
     * @param topicId
     * @param comment
     */
    @PostMapping("/addTopicComment/{courseId}/{userId}/{topicId}/{comment}")
    public void addTopicComment(@PathVariable Long courseId,@PathVariable("userId") Long userId,@PathVariable("topicId") Long topicId,@PathVariable("comment") String comment){
        Long parentId=null;
        topicService.addTopicComment(courseId,userId,topicId,comment,parentId);

    }

    /**
     * 添加回复的评论
     * @param userId
     * @param topicId
     * @param comment
     * @param parentId
     */
    @PostMapping("/addTopicReplyComment/{courseId}/{userId}/{topicId}/{comment}/{parentId}")
    public void addTopicReplyComment(@PathVariable Long courseId,@PathVariable("userId") Long userId,@PathVariable("topicId") Long topicId,@PathVariable("comment") String comment,@PathVariable("parentId")Long parentId){
        topicService.addTopicComment(courseId,userId,topicId,comment,parentId);

    }
    /**
     * 点赞
     * @param userId
     * @param topicId
     */
      @PostMapping("/likeCount/{userId}/{topicId}")
    public void addLikeCount(@PathVariable("userId")Long userId,@PathVariable("topicId") Long topicId){
                 topicService.likeClick(userId,topicId);
    }

    /**
     * 判断用户是否已经阅读了话题
     * @param userId
     * @param topicId
     */
    @PostMapping("/isJoinTopic/{userId}/{topicId}")
    public void isJoinTopic(@PathVariable("userId") Long userId,@PathVariable("topicId") Long topicId){
    topicService.isJoinTopic(userId,topicId);
    }

    /**
     * 获取课程的所有章节
     * @param courseId
     * @return
     */
    @GetMapping("/getAllChapter/{courseId}")
    public AjaxResult getAllChapterByCourseId(@PathVariable Long courseId){

           return  success(topicService.processChapters(courseId));
    }

    /**
     * 查找一个字典类型的所有字典信息
     * @return
     */@GetMapping("/getDictionaryByType/{type}")

    public AjaxResult dictName(@PathVariable  String type){

            return success(sysDictDataService.allDictName(type));

    }

    /**
     * 获取发表该话题的用户信息
     * @param topicId
     * @return
     */
    @GetMapping("/findUserByTopicId/{topicId}")
    public AjaxResult findUserByTopicId(@PathVariable Long topicId){
          return  success(topicService.findUserByTopicId(topicId));
    }

    @PostMapping("/deleteComment")
    public AjaxResult deleteComment(@RequestBody CommentVo commentVo){

        return success( topicService.deleteComment(commentVo)) ;
    }
    @GetMapping("/commentLikes/{userId}/{commentId}")
    public void CommentLikes(@PathVariable Long userId, @PathVariable Long commentId){

        topicService.CommentLikes(userId,commentId);
    }
    @GetMapping("/likeState/{userId}/{commentId}")
    public AjaxResult likeState(@PathVariable Long userId,@PathVariable Long commentId){
        return success(topicService.likeState(userId,commentId));
    }

    /**
     * 获取所有章节信息
     * @param courseId
     * @return
     */
    @GetMapping("/byCourseId/{courseId}")
    public AjaxResult getAllChapterById(@PathVariable Long courseId){
                return  success(topicService.getChapterByCourseId(courseId));
    }
}
