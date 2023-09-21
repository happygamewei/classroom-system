package com.ruoyi.classroom.domain.vo;

import com.ruoyi.classroom.domain.Chapter;
import lombok.Data;

import java.util.List;

@Data
public class ChapterVo extends Chapter {

    private Integer activeNumber = 0;

    private List<?> content;
}
