package com.ruoyi.classroom.mapper;

import java.util.List;
import com.ruoyi.classroom.domain.TestPassCheck;

/**
 * 测试上交批改Mapper接口
 *
 * @author Yuan
 * @date 2023-09-09
 */
public interface TestPassCheckMapper
{
    /**
     * 查询测试上交批改
     *
     * @param testId 测试上交批改主键
     * @return 测试上交批改
     */
    public TestPassCheck selectTestPassCheckByTestId(Long testId);

    /**
     * 查询测试上交批改列表
     *
     * @param testPassCheck 测试上交批改
     * @return 测试上交批改集合
     */
    public List<TestPassCheck> selectTestPassCheckList(TestPassCheck testPassCheck);

    /**
     * 新增测试上交批改
     *
     * @param testPassCheck 测试上交批改
     * @return 结果
     */
    public int insertTestPassCheck(TestPassCheck testPassCheck);

    /**
     * 修改测试上交批改
     *
     * @param testPassCheck 测试上交批改
     * @return 结果
     */
    public int updateTestPassCheck(TestPassCheck testPassCheck);

    /**
     * 删除测试上交批改
     *
     * @param testId 测试上交批改主键
     * @return 结果
     */
    public int deleteTestPassCheckByTestId(Long testId);

    /**
     * 批量删除测试上交批改
     *
     * @param testIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTestPassCheckByTestIds(Long[] testIds);
}
