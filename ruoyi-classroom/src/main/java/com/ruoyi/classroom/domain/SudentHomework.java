package com.ruoyi.classroom.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学生作业对象 tb_sudent_homework
 * 
 * @author rain
 * @date 2023-09-08
 */
public class SudentHomework extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学生作业表id */
    private Long shId;

    /** 作业id */
    @Excel(name = "作业id")
    private Long homeworkId;

    /** 学生id */
    @Excel(name = "学生id")
    private Long studentId;

    /** 上传文件路径 */
    @Excel(name = "上传文件路径")
    private String filePath;

    /** 提交状态，0未提交，1提交 */
    @Excel(name = "提交状态，0未提交，1提交")
    private String submitStatus;

    /** 提交时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "提交时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitTime;

    /** 字数 */
    @Excel(name = "字数")
    private Long wordNumber;

    /** 批阅次数 */
    @Excel(name = "批阅次数")
    private Long checkNumber;

    /** 催交(次数) */
    @Excel(name = "催交(次数)")
    private Long urgeSubmit;

    /** 成绩 */
    @Excel(name = "成绩")
    private Long grade;

    /** 作业状态，1批改，2打回 */
    @Excel(name = "作业状态，1批改，2打回")
    private String workStatus;

    /** 状态，0禁用，1激活 */
    @Excel(name = "状态，0禁用，1激活")
    private String status;

    public void setShId(Long shId) 
    {
        this.shId = shId;
    }

    public Long getShId() 
    {
        return shId;
    }
    public void setHomeworkId(Long homeworkId) 
    {
        this.homeworkId = homeworkId;
    }

    public Long getHomeworkId() 
    {
        return homeworkId;
    }
    public void setStudentId(Long studentId) 
    {
        this.studentId = studentId;
    }

    public Long getStudentId() 
    {
        return studentId;
    }
    public void setFilePath(String filePath) 
    {
        this.filePath = filePath;
    }

    public String getFilePath() 
    {
        return filePath;
    }
    public void setSubmitStatus(String submitStatus) 
    {
        this.submitStatus = submitStatus;
    }

    public String getSubmitStatus() 
    {
        return submitStatus;
    }
    public void setSubmitTime(Date submitTime) 
    {
        this.submitTime = submitTime;
    }

    public Date getSubmitTime() 
    {
        return submitTime;
    }
    public void setWordNumber(Long wordNumber) 
    {
        this.wordNumber = wordNumber;
    }

    public Long getWordNumber() 
    {
        return wordNumber;
    }
    public void setCheckNumber(Long checkNumber) 
    {
        this.checkNumber = checkNumber;
    }

    public Long getCheckNumber() 
    {
        return checkNumber;
    }
    public void setUrgeSubmit(Long urgeSubmit) 
    {
        this.urgeSubmit = urgeSubmit;
    }

    public Long getUrgeSubmit() 
    {
        return urgeSubmit;
    }
    public void setGrade(Long grade) 
    {
        this.grade = grade;
    }

    public Long getGrade() 
    {
        return grade;
    }
    public void setWorkStatus(String workStatus) 
    {
        this.workStatus = workStatus;
    }

    public String getWorkStatus() 
    {
        return workStatus;
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
            .append("shId", getShId())
            .append("homeworkId", getHomeworkId())
            .append("studentId", getStudentId())
            .append("filePath", getFilePath())
            .append("submitStatus", getSubmitStatus())
            .append("submitTime", getSubmitTime())
            .append("wordNumber", getWordNumber())
            .append("checkNumber", getCheckNumber())
            .append("urgeSubmit", getUrgeSubmit())
            .append("grade", getGrade())
            .append("workStatus", getWorkStatus())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
