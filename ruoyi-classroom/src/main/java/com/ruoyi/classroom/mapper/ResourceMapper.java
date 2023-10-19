package com.ruoyi.classroom.mapper;

import java.util.List;
import com.ruoyi.classroom.domain.Resource;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资料管理Mapper接口
 * 
 * @author Yuan
 * @date 2023-09-09
 */
public interface ResourceMapper 
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
     * 删除资料管理
     * 
     * @param resourceId 资料管理主键
     * @return 结果
     */
    public int deleteResourceByResourceId(Long resourceId);

    /**
     * 批量删除资料管理
     * 
     * @param resourceIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteResourceByResourceIds(Long[] resourceIds);

    /**
     * 通过课程ID查询到章节ID，再通过章节ID查询到资料
     * @param chapterIds
     * @return
     */
    List<Resource> getResourceByChapterId(Long[] chapterIds);
}
