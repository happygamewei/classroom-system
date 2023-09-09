package com.ruoyi.classroom.mapper;

import java.util.List;
import com.ruoyi.classroom.domain.HomePassCheck;

/**
 * 测试上交批改Mapper接口
 * 
 * @author rain
 * @date 2023-09-08
 */
public interface HomePassCheckMapper 
{
    /**
     * 查询测试上交批改
     * 
     * @param homeId 测试上交批改主键
     * @return 测试上交批改
     */
    public HomePassCheck selectHomePassCheckByHomeId(Long homeId);

    /**
     * 查询测试上交批改列表
     * 
     * @param homePassCheck 测试上交批改
     * @return 测试上交批改集合
     */
    public List<HomePassCheck> selectHomePassCheckList(HomePassCheck homePassCheck);

    /**
     * 新增测试上交批改
     * 
     * @param homePassCheck 测试上交批改
     * @return 结果
     */
    public int insertHomePassCheck(HomePassCheck homePassCheck);

    /**
     * 修改测试上交批改
     * 
     * @param homePassCheck 测试上交批改
     * @return 结果
     */
    public int updateHomePassCheck(HomePassCheck homePassCheck);

    /**
     * 删除测试上交批改
     * 
     * @param homeId 测试上交批改主键
     * @return 结果
     */
    public int deleteHomePassCheckByHomeId(Long homeId);

    /**
     * 批量删除测试上交批改
     * 
     * @param homeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHomePassCheckByHomeIds(Long[] homeIds);
}
