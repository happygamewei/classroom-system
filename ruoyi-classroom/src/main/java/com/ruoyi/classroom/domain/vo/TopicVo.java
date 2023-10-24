package com.ruoyi.classroom.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicVo implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long topicId;
    private String title;
    private Date deadline;
    private Long joinNumber;
    private Long noJoinNumber;
    private int commentCount;
    private Date publishDate;
    private  String content;
    private Long likeCount;
    private Long totalScore;
    private Long leastReplyNumber;
    private  String chapterName;

}
