package com.ruoyi.classroom.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.classroom.mapper.TestMapper;
import com.ruoyi.classroom.domain.Test;
import com.ruoyi.classroom.service.ITestService;

/**
 * 测试管理Service业务层处理
 *
 * @author Yuan
 * @date 2023-09-08
 */
@Service
public class TestServiceImpl implements ITestService
{
    @Autowired
    private TestMapper testMapper;

    /**
     * 查询测试管理
     *
     * @param testId 测试管理主键
     * @return 测试管理
     */
    @Override
    public Test selectTestByTestId(Long testId)
    {
        return testMapper.selectTestByTestId(testId);
    }

    /**
     * 查询测试管理列表
     *
     * @param test 测试管理
     * @return 测试管理
     */
    @Override
    public List<Test> selectTestList(Test test)
    {
        return testMapper.selectTestList(test);
    }

    /**
     * 新增测试管理
     *
     * @param test 测试管理
     * @return 结果
     */
    @Override
    public int insertTest(Test test)
    {
        test.setCreateTime(DateUtils.getNowDate());
        return testMapper.insertTest(test);
    }

    /**
     * 修改测试管理
     *
     * @param test 测试管理
     * @return 结果
     */
    @Override
    public int updateTest(Test test)
    {
        test.setUpdateTime(DateUtils.getNowDate());
        return testMapper.updateTest(test);
    }

    /**
     * 批量删除测试管理
     *
     * @param testIds 需要删除的测试管理主键
     * @return 结果
     */
    @Override
    public int deleteTestByTestIds(Long[] testIds)
    {
        return testMapper.deleteTestByTestIds(testIds);
    }

    /**
     * 删除测试管理信息
     *
     * @param testId 测试管理主键
     * @return 结果
     */
    @Override
    public int deleteTestByTestId(Long testId)
    {
        return testMapper.deleteTestByTestId(testId);
    }

    /**
     * 计算该测试已批的人数并放入测试表
     *
     * @return 结果
     */
    @Override
    public void CalculateCorrrcted(Long testId) {
        int number = testMapper.CalculateBatchQuantity(testId);
        testMapper.InputCorrrcted(testId,number);
    }


    /**
     * 获得该测试已批的人数
     *
     * @return 结果
     */
    @Override
    public int GetCorrrcted(Long testId) {
        return testMapper.GetCorrrcted(testId);
    }

    /**
     * 计算该测试已批的人数并放入测试表
     *
     * @return 结果
     */
    @Override
    public void CalculateUnpaid(Long testId) {
        int number = testMapper.CalculateUnpaid(testId);
        testMapper.InputUnpaid(testId,number);
    }

    /**
     * 获得未交人数
     *
     * @return 结果
     */
    @Override
    public int GetUnpaid(Long testId) {
        return testMapper.GetUnpaid(testId);
    }

    @Override
    public int GetNotCorrected(Long testId) {
        return testMapper.GetPaid(testId)-testMapper.GetCorrrcted(testId);
    }

    @Override
    public Test SelectTest(Long testId) {
        return testMapper.SelectTest(testId);
    }

    @Override
    public int CheckRole(Integer UserId) {
        return testMapper.CheckRole(UserId);
    }
}
