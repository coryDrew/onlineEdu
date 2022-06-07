package com.atguigu.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;
@Data
public class ChapterVo {
    private String id;
    private String title;
    //一个章节里有多个小节
    private List<VideoVo> children = new ArrayList<>();
}
