package com.ruoyi.classroom.service;

import java.util.List;
import com.ruoyi.classroom.domain.Resource;

/**
 * 资料管理Service接口
 * 
 * @author Yuan
 * @date 2023-09-09
 */
public interface IResourceService 
{
    /**
     * 查询资料管理
     * 
     * @param resourceId 资料管理主键
     * @return 资料管理
     */
    public Resource selectResourceByResourceId(Long resourceId);

    /**
     * 查询资料管理列表
     * 
     * @param resource 资料管理
     * @return 资料管理集合
     */
    public List<Resource> selectResourceList(Resource resource);

    /**
     * 新增资料管理
     * 
     * @param resource 资料管理
     * @return 结果
     */
    public int insertResource(Resource resource);

    /**
     * 修改资料管理
     * 
     * @param resource 资料管理
     * @return 结果
     */
    public int updateResource(Resource resource);

    /**
     * 批量删除资料管理
     * 
     * @param resourceIds 需要删除的资料管理主键集合
     * @return 结果
     */
    public int deleteResourceByResourceIds(Long[] resourceIds);

    /**
     * 删除资料管理信息
     * 
     * @param resourceId 资料管理主键
     * @return 结果
     */
    public int deleteResourceByResourceId(Long resourceId);

    /**
     * 获取资料通过courseId
     * @param courseId
     * @return
     */

    public List<Resource> getResourceByCourseId(Long courseId);
}
