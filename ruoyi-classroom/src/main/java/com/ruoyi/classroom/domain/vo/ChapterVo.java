package com.ruoyi.classroom.domain.vo;

import com.ruoyi.classroom.domain.Chapter;
import lombok.Data;

@Data
public class ChapterVo extends Chapter {
    private Integer activeNumber;

    private Object content;
}
