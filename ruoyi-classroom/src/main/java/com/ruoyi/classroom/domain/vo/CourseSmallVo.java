package com.ruoyi.classroom.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CourseSmallVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long courseId;

    private String name;
}
