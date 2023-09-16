package com.ruoyi.classroom.domain.dto;

import com.ruoyi.classroom.domain.Chapter;
import lombok.Data;

@Data
public class ChapterDto extends Chapter {

    private Long courseId;
}
