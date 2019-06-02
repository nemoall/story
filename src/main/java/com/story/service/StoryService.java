package com.story.service;

import com.story.pojo.GridPage;
import com.story.pojo.po.StoryPO;

public interface StoryService {
    GridPage<StoryPO> pageStory();

}
