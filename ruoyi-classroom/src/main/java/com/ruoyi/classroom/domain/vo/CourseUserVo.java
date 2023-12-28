package com.ruoyi.classroom.domain.vo;

import lombok.Data;

import java.util.List;

@Data
public class CourseUserVo {
    private List<CourseTeacherVo> courseTeacherVos;
    private List<CourseStudentVo> courseStudentVos;
}
