package com.ruoyi.classroom.domain;

import com.ruoyi.classroom.domain.vo.CourseSmallVo;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 课程管理对象 tb_course
 * 
 * @author Yuan
 * @date 2023-09-08
 */
public class Course extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 课程id，主键 */
    private Long courseId;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String name;

    /** 课程码 */
    @Excel(name = "课程码")
    private String code;

    /** 教学班级 */
    @Excel(name = "教学班级")
    private String teachClass;

    /** 学年 */
    @Excel(name = "学年")
    private String schoolYear;

    /** 学期 */
    @Excel(name = "学期")
    private String term;

    /** 授课模式 */
    @Excel(name = "授课模式")
    private String teachMode;

    /** 学时数 */
    @Excel(name = "学时数")
    private Long creditHours;

    /** 课程介绍 */
    @Excel(name = "课程介绍")
    private String introduce;

    /** 授课地点 */
    @Excel(name = "授课地点")
    private String place;

    /** 机构相关 */
    @Excel(name = "机构相关")
    private String institutional;

    /** 参加人数 */
    @Excel(name = "参加人数")
    private Long joinNumber;

    /** 状态，0禁用，1激活 */
    @Excel(name = "状态，0禁用，1激活")
    private String status;

    public void setCourseId(Long courseId) 
    {
        this.courseId = courseId;
    }

    public Long getCourseId() 
    {
        return courseId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setTeachClass(String teachClass) 
    {
        this.teachClass = teachClass;
    }

    public String getTeachClass() 
    {
        return teachClass;
    }
    public void setSchoolYear(String schoolYear) 
    {
        this.schoolYear = schoolYear;
    }

    public String getSchoolYear() 
    {
        return schoolYear;
    }
    public void setTerm(String term) 
    {
        this.term = term;
    }

    public String getTerm() 
    {
        return term;
    }
    public void setTeachMode(String teachMode) 
    {
        this.teachMode = teachMode;
    }

    public String getTeachMode() 
    {
        return teachMode;
    }
    public void setCreditHours(Long creditHours) 
    {
        this.creditHours = creditHours;
    }

    public Long getCreditHours() 
    {
        return creditHours;
    }
    public void setIntroduce(String introduce) 
    {
        this.introduce = introduce;
    }

    public String getIntroduce() 
    {
        return introduce;
    }
    public void setPlace(String place) 
    {
        this.place = place;
    }

    public String getPlace() 
    {
        return place;
    }
    public void setInstitutional(String institutional) 
    {
        this.institutional = institutional;
    }

    public String getInstitutional() 
    {
        return institutional;
    }
    public void setJoinNumber(Long joinNumber) 
    {
        this.joinNumber = joinNumber;
    }

    public Long getJoinNumber() 
    {
        return joinNumber;
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
            .append("courseId", getCourseId())
            .append("name", getName())
            .append("code", getCode())
            .append("teachClass", getTeachClass())
            .append("schoolYear", getSchoolYear())
            .append("term", getTerm())
            .append("teachMode", getTeachMode())
            .append("creditHours", getCreditHours())
            .append("introduce", getIntroduce())
            .append("place", getPlace())
            .append("institutional", getInstitutional())
            .append("joinNumber", getJoinNumber())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
