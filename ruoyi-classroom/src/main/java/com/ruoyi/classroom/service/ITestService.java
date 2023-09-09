package com.ruoyi.classroom.service;

import java.util.List;
import com.ruoyi.classroom.domain.Test;

/**
 * 测试管理Service接口
 * 
 * @author Yuan
 * @date 2023-09-08
 */
public interface ITestService 
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
     * 批量删除测试管理
     * 
     * @param testIds 需要删除的测试管理主键集合
     * @return 结果
     */
    public int deleteTestByTestIds(Long[] testIds);

    /**
     * 删除测试管理信息
     * 
     * @param testId 测试管理主键
     * @return 结果
     */
    public int deleteTestByTestId(Long testId);
}
