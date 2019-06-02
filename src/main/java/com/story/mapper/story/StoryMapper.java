package com.story.mapper.story;

import com.story.pojo.po.StoryPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoryMapper {
    int countStory();

    List<StoryPO>  listStory();
}
