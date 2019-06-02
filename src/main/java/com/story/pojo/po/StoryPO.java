package com.story.pojo.po;

import com.alibaba.fastjson.annotation.JSONField;

public class StoryPO {
    @JSONField(name = "story_uid")
    private String storyUid;
    @JSONField(name = "story_name")
    private String storyName;

    public String getStoryUid() {
        return storyUid;
    }

    public void setStoryUid(String storyUid) {
        this.storyUid = storyUid;
    }

    public String getStoryName() {
        return storyName;
    }

    public void setStoryName(String storyName) {
        this.storyName = storyName;
    }
}
