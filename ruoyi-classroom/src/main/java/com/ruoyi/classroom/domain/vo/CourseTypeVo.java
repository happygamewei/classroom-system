package com.ruoyi.classroom.domain.vo;

import lombok.Data;

import java.util.List;

@Data
public class CourseTypeVo {

    private String schoolYear;

    private String term;

    private List<?> courses;
}
