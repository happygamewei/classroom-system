package com.ruoyi.classroom.domain.vo;

import lombok.Data;

@Data
public class ContentVo{

    private Object contentInfo;

    private String typeContent;

    public ContentVo(Object contentInfo) {
        this.contentInfo = contentInfo;
    }
}
