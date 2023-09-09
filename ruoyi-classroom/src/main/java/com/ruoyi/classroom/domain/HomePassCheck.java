package com.ruoyi.classroom.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 测试上交批改对象 tb_home_pass_check
 * 
 * @author rain
 * @date 2023-09-08
 */
public class HomePassCheck extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 作业反馈id */
    private Long homeId;

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

    /** 状态，0禁用，1激活 */
    @Excel(name = "状态，0禁用，1激活")
    private String status;

    public void setHomeId(Long homeId) 
    {
        this.homeId = homeId;
    }

    public Long getHomeId() 
    {
        return homeId;
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
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("homeId", getHomeId())
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
