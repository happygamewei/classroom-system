package com.ruoyi.classroom.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CourseContentVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer chapterNumber;

    private Integer activeNumber;

    private Integer creditHours;

    private Integer allocatedHours;
}
