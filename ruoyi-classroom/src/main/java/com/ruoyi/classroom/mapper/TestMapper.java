package com.ruoyi.classroom.mapper;

import java.util.List;
import com.ruoyi.classroom.domain.Test;

/**
 * 测试管理Mapper接口
 *
 * @author Yuan
 * @date 2023-09-08
 */
public interface TestMapper
{
    /**
     * 查询测试管理
     *
     * @param testId 测试管理主键
     * @return 测试管理
     */
    public Test selectTestByTestId(Long testId);

    /**
     * 查询测试管理列表
     *
     * @param test 测试管理
     * @return 测试管理集合
     */
    public List<Test> selectTestList(Test test);

    /**
     * 新增测试管理
     *
     * @param test 测试管理
     * @return 结果
     */
    public int insertTest(Test test);

    /**
     * 修改测试管理
     *
     * @param test 测试管理
     * @return 结果
     */
    public int updateTest(Test test);

    /**
     * 删除测试管理
     *
     * @param testId 测试管理主键
     * @return 结果
     */
    public int deleteTestByTestId(Long testId);

    /**
     * 批量删除测试管理
     *
     * @param testIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTestByTestIds(Long[] testIds);

    /**
     * 根据test_id选择测试
     *
     * @return 结果
     */
    public Test SelectTest(Long testId);

    /**
     * 计算该测试已批的人数
     *
     * @return 结果
     */
    public int CalculateBatchQuantity(Long testId);

    /**
     * 该测试已批的人数放入测试表
     *
     * @return 结果
     */
    public void InputCorrrcted(Long testId,int corrrcted);

    /**
     * 获得已批数量
     *
     * @return 结果
     */
    public int GetCorrrcted(Long testId);


    /**
     * 计算该测试已批的人数
     *
     * @return 结果
     */
    public int CalculateUnpaid(Long testId);

    /**
     * 该测试未交的人数放入测试表
     *
     * @return 结果
     */
    public void InputUnpaid(Long testId,int corrrcted);

    /**
     * 获得未交数量
     *
     * @return 结果
     */
    public int GetUnpaid(Long testId);


    /**
     * 获得已交数量
     *
     * @return 结果
     */
    public int GetPaid(Long testId);

    public int CheckRole(Integer user_id);


}
