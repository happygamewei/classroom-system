package com.ruoyi.classroom.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.classroom.domain.Resource;
import com.ruoyi.classroom.service.IResourceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 资料管理Controller
 * 
 * @author Yuan
 * @date 2023-09-09
 */
@RestController
@RequestMapping("/classroom/resource")
public class ResourceController extends BaseController
{
    @Autowired
    private IResourceService resourceService;


//    @GetMapping("/byCourseId/{courseId}")
//    public AjaxResult getChapterByCourseId(@PathVariable("courseId") Long courseId){
//        return success(resourceService.getResourceByCourseId(courseId));
//    }

    /**
     * 查询资料管理列表
     */
    @PreAuthorize("@ss.hasPermi('classroom:resource:list')")
    @GetMapping("/list")
    public TableDataInfo list(Resource resource)
    {
        startPage();
        List<Resource> list = resourceService.selectResourceList(resource);
        return getDataTable(list);
    }

    /**
     * 导出资料管理列表
     */
    @PreAuthorize("@ss.hasPermi('classroom:resource:export')")
    @Log(title = "资料管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Resource resource)
    {
        List<Resource> list = resourceService.selectResourceList(resource);
        ExcelUtil<Resource> util = new ExcelUtil<Resource>(Resource.class);
        util.exportExcel(response, list, "资料管理数据");
    }

    /**
     * 获取资料管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('classroom:resource:query')")
    @GetMapping(value = "/{resourceId}")
    public AjaxResult getInfo(@PathVariable("resourceId") Long resourceId)
    {
        return success(resourceService.selectResourceByResourceId(resourceId));
    }

    /**
     * 新增资料管理
     */
    @PreAuthorize("@ss.hasPermi('classroom:resource:add')")
    @Log(title = "资料管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Resource resource)
    {
        return toAjax(resourceService.insertResource(resource));
    }

    /**
     * 修改资料管理
     */
    @PreAuthorize("@ss.hasPermi('classroom:resource:edit')")
    @Log(title = "资料管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Resource resource)
    {
        return toAjax(resourceService.updateResource(resource));
    }

    /**
     * 删除资料管理
     */
    @PreAuthorize("@ss.hasPermi('classroom:resource:remove')")
    @Log(title = "资料管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/delete/{resourceIds}")
    public AjaxResult remove(@PathVariable Long[] resourceIds)
    {
        return toAjax(resourceService.deleteResourceByResourceIds(resourceIds));
    }

    /**
     * 通过课程ID获取资料列表
     */
    @GetMapping("/byCourseId/{courseId}")
    public AjaxResult getResourceByCourseId(@PathVariable("courseId") long courseId){
        return success(resourceService.getResourceByCourseId(courseId));
    }

    /**
     * 删除资料
     */
    @DeleteMapping("/{resourceId}")
    public AjaxResult deleteResourceByResourceId(@PathVariable("resourceId") long resourceId){
        return success(resourceService.deleteResourceByResourceId(resourceId));
    }
}
