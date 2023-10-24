package com.ruoyi.classroom.domain.vo;

import com.ruoyi.classroom.domain.Topic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicChapterNameVo extends Topic {
     private String chapterName;
}
