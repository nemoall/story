package com.story.common.utils;

import com.alibaba.fastjson.JSON;
import com.story.common.Constants;


import java.util.HashMap;
import java.util.Map;

public class ResultBuilderUtil {
	
	public static Object buildSuccess(String message) {
		Map<String, Object> data = new HashMap<>();
		data.put(Constants.MESSAGE_FLG, message);
		return buildSuccess(data);
	}

	public static Object buildSuccess(Object data) {
		BaseResponseDTO result = new BaseResponseDTO(data);
		return JSON.parseObject(FastJsonUtil.toJSONString(result));
	}

	public static Object buildError(String message) {
		BaseResponseDTO result = new BaseResponseDTO(message);
		return JSON.parseObject(FastJsonUtil.toJSONString(result));
	}
}
