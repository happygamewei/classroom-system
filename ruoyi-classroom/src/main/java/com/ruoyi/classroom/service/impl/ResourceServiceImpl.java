package com.ruoyi.classroom.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.classroom.mapper.ResourceMapper;
import com.ruoyi.classroom.domain.Resource;
import com.ruoyi.classroom.service.IResourceService;

/**
 * 资料管理Service业务层处理
 * 
 * @author Yuan
 * @date 2023-09-09
 */
@Service
public class ResourceServiceImpl implements IResourceService 
{
    @Autowired
    private ResourceMapper resourceMapper;

    /**
     * 查询资料管理
     * 
     * @param resourceId 资料管理主键
     * @return 资料管理
     */
    @Override
    public Resource selectResourceByResourceId(Long resourceId)
    {
        return resourceMapper.selectResourceByResourceId(resourceId);
    }

    /**
     * 查询资料管理列表
     * 
     * @param resource 资料管理
     * @return 资料管理
     */
    @Override
    public List<Resource> selectResourceList(Resource resource)
    {
        return resourceMapper.selectResourceList(resource);
    }

    /**
     * 新增资料管理
     * 
     * @param resource 资料管理
     * @return 结果
     */
    @Override
    public int insertResource(Resource resource)
    {
        resource.setCreateTime(DateUtils.getNowDate());
        return resourceMapper.insertResource(resource);
    }

    /**
     * 修改资料管理
     * 
     * @param resource 资料管理
     * @return 结果
     */
    @Override
    public int updateResource(Resource resource)
    {
        resource.setUpdateTime(DateUtils.getNowDate());
        return resourceMapper.updateResource(resource);
    }

    /**
     * 批量删除资料管理
     * 
     * @param resourceIds 需要删除的资料管理主键
     * @return 结果
     */
    @Override
    public int deleteResourceByResourceIds(Long[] resourceIds)
    {
        return resourceMapper.deleteResourceByResourceIds(resourceIds);
    }

    /**
     * 删除资料管理信息
     * 
     * @param resourceId 资料管理主键
     * @return 结果
     */
    @Override
    public int deleteResourceByResourceId(Long resourceId)
    {
        return resourceMapper.deleteResourceByResourceId(resourceId);
    }
}
