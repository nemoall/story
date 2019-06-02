package com.story.service.impl;

import com.story.mapper.story.StoryMapper;
import com.story.pojo.GridPage;
import com.story.pojo.po.StoryPO;
import com.story.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoryServiceImpl implements StoryService {

    @Autowired
    private StoryMapper storyMapper;

    @Override
    public GridPage<StoryPO> pageStory() {
        int total = storyMapper.countStory();
        if (0 == total) {
            return new GridPage<>();
        }
        List<StoryPO> rows = storyMapper.listStory();
        return new GridPage<>(rows, total);
    }
}
