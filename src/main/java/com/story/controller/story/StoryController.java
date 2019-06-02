package com.story.controller.story;

import com.story.pojo.GridPage;
import com.story.pojo.LayGridPage;
import com.story.pojo.po.StoryPO;
import com.story.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StoryController {

    @Autowired
    private StoryService storyService;

    /**
     *  查询学科信息
     * @return
     */
    @RequestMapping(value = "/pageStory", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object pageStory() {
        GridPage<StoryPO> page = storyService.pageStory();
        return new LayGridPage<StoryPO>(page);
    }
}
