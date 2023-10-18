package com.ruoyi.classroom.domain.vo;

import com.ruoyi.classroom.domain.Comment;
import lombok.Data;

import java.util.Collection;
import java.util.List;

/**
 * @Description
 * @Author: Qiaoshicong
 * @Date: 2023/10/3 16:54
 */
@Data
public class CommentVo extends Comment {
    private int isDelete;
    private Long rootParentId;
    private List<CommentVo> child;
}
