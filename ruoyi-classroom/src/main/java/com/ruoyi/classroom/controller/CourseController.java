package com.ruoyi.classroom.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.classroom.domain.vo.CourseVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.classroom.domain.Course;
import com.ruoyi.classroom.service.ICourseService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 课程管理Controller
 * 
 * @author Yuan
 * @date 2023-09-08
 */
@RestController
@RequestMapping("/classroom/course")
public class CourseController extends BaseController
{
    @Autowired
    private ICourseService courseService;

    /**
     * 结课
     * @param courseId
     * @return
     */
    @PutMapping("/overCourse/{courseId}")
    public AjaxResult overCourse(@PathVariable("courseId") Long courseId){
        return toAjax(courseService.overCourse(courseId));
    }

    /**
     * 取消结课
     * @param courseId
     * @return
     */
    @PutMapping("/openCourse/{courseId}")
    public AjaxResult openCourse(@PathVariable("courseId") Long courseId){
        return toAjax(courseService.openCourse(courseId));
    }

    /**
     * 重置课程码
     * @param courseId
     * @return
     */
    @PutMapping("/renew/{courseId}")
    public AjaxResult renewCourseCode(@PathVariable("courseId") Long courseId){
        return toAjax(courseService.renewCourseCode(courseId));
    }

    /**
     * 加入课程
     * @param code
     * @return
     */
    @GetMapping("/code/{code}")
    public AjaxResult joinCourse(@PathVariable("code") String code){
        int joinCourse = courseService.joinCourse(code);
        if(joinCourse == -1){
            return error("没有该课程");
        }
        if(joinCourse == -2){
            return error("该课程已经结课");
        }
        return success();
    }

    /**
     * 查询用户教的还是学的
     * @param userId
     * @param type
     * @return
     */
    @GetMapping("/byUserId")
    public AjaxResult getUserTeachCourse(@RequestParam("userId") Long userId, @RequestParam("type") String type){
        return success(courseService.getUserTeachCourse(userId, type));
    }

    /**
     * 得到课程的相关信息
     * @param courseId
     * @return
     */
    @GetMapping(value = "/courseContentById/{courseId}")
    public AjaxResult getCourseContentInfo(@PathVariable("courseId") Long courseId){
        return success(courseService.getCourseContentInfo(courseId));
    }

    /**
     * 根据用户查询课程管理列表
     */
    @PreAuthorize("@ss.hasPermi('classroom:course:list')")
    @GetMapping("/list/{userId}")
    public TableDataInfo listByUserId(@PathVariable("userId") Long userId)
    {
        List<CourseVo> list = courseService.listByUserId(userId);
        return getDataTable(list);
    }

    /**
     * 查询课程管理列表
     */
//    @PreAuthorize("@ss.hasPermi('classroom:course:list')")
    @GetMapping("/list")
    public TableDataInfo list(Course course)
    {
        startPage();
        List<Course> list = courseService.selectCourseList(course);
        return getDataTable(list);
    }

    /**
     * 导出课程管理列表
     */
    @PreAuthorize("@ss.hasPermi('classroom:course:export')")
    @Log(title = "课程管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Course course)
    {
        List<Course> list = courseService.selectCourseList(course);
        ExcelUtil<Course> util = new ExcelUtil<Course>(Course.class);
        util.exportExcel(response, list, "课程管理数据");
    }

    /**
     * 得到一个课程
     * @param courseId
     * @return
     */
    @GetMapping(value = "/byId/{courseId}")
    public AjaxResult getInfoById(@PathVariable("courseId") Long courseId){
        return success(courseService.selectCourseByCourseId(courseId));
    }

    /**
     * 获取课程管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('classroom:course:query')")
    @GetMapping(value = "/{courseId}")
    public AjaxResult getInfo(@PathVariable("courseId") Long courseId)
    {
        return success(courseService.selectCourseByCourseId(courseId));
    }

    /**
     * 新增课程管理
     */
    @PreAuthorize("@ss.hasPermi('classroom:course:add')")
    @Log(title = "课程管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Course course)
    {
        return toAjax(courseService.insertCourse(course));
    }

    /**
     * 修改课程管理
     */
    @PreAuthorize("@ss.hasPermi('classroom:course:edit')")
    @Log(title = "课程管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Course course)
    {
        return toAjax(courseService.updateCourse(course));
    }

    /**
     * 删除课程管理
     */
    @PreAuthorize("@ss.hasPermi('classroom:course:remove')")
    @Log(title = "课程管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{courseIds}")
    public AjaxResult remove(@PathVariable Long[] courseIds)
    {
        return toAjax(courseService.deleteCourseByCourseIds(courseIds));
    }
}
