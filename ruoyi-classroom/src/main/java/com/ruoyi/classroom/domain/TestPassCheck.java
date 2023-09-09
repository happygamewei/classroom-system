package com.ruoyi.classroom.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 测试上交批改对象 tb_test_pass_check
 * 
 * @author Yuan
 * @date 2023-09-09
 */
public class TestPassCheck extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 测试id */
    private Long testId;

    /** 学生id */
    private Long studentId;

    /** 老师id */
    private Long teacherId;

    /** 批改状态，0未批，1已批 */
    @Excel(name = "批改状态，0未批，1已批")
    private String ifPass;

    /** 上交状态，0未交，1已交 */
    @Excel(name = "上交状态，0未交，1已交")
    private String ifCheck;

    /** int	状态，0禁用，1激活 */
    @Excel(name = "int	状态，0禁用，1激活")
    private Long status;

    public void setTestId(Long testId) 
    {
        this.testId = testId;
    }

    public Long getTestId() 
    {
        return testId;
    }
    public void setStudentId(Long studentId) 
    {
        this.studentId = studentId;
    }

    public Long getStudentId() 
    {
        return studentId;
    }
    public void setTeacherId(Long teacherId) 
    {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() 
    {
        return teacherId;
    }
    public void setIfPass(String ifPass) 
    {
        this.ifPass = ifPass;
    }

    public String getIfPass() 
    {
        return ifPass;
    }
    public void setIfCheck(String ifCheck) 
    {
        this.ifCheck = ifCheck;
    }

    public String getIfCheck() 
    {
        return ifCheck;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("testId", getTestId())
            .append("studentId", getStudentId())
            .append("teacherId", getTeacherId())
            .append("ifPass", getIfPass())
            .append("ifCheck", getIfCheck())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
