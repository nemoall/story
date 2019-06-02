package com.story.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.NullNode;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Json util class based on fastjson
 */
public class FastJsonUtil {
    private static Object NONE = new Object();
    private static final SerializeConfig config;

    static {
        config = new SerializeConfig();
        config.put(java.util.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
        config.put(java.sql.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
    }

    private static final SerializerFeature[] features = {SerializerFeature.WriteMapNullValue, // 输出空置字段
            SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为null，输出为""，而不是null
    };

    public static String toJSONStringWithoutNull(Object object) {
        return JSON.toJSONString(object, config);
    }

    public static String toJSONString(Object object) {
        return JSON.toJSONString(object, config, features);
    }

    public static String toJSONNoFeatures(Object object) {
        return JSON.toJSONString(object, config);
    }

    public static Object toBean(String text) {
        if (StringUtils.isEmpty(text)) {
            return NONE;
        }
        return JSON.parse(text);
    }

    public static <T> T toBean(String text, Class<T> clazz) {
        if (StringUtils.isEmpty(text)) {
            return null;
        }
        return JSON.parseObject(text, clazz);
    }

    // 转换为数组  
    public static <T> Object[] toArray(String text) {
        return toArray(text, null);
    }

    // 转换为数组  
    public static <T> Object[] toArray(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz).toArray();
    }

    // 转换为List  
    public static <T> List<T> toList(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz);
    }

    /**
     * json字符串转化为map
     *
     * @param s
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Map stringToCollect(String s) {
        Map m = JSONObject.parseObject(s);
        return m;
    }


    /**
     * jsonArray转换成List
     *
     * @param jsonArray
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonArrayToList(JSONArray jsonArray, Class<T> clazz) {
        String json = JSON.toJSONString(jsonArray);
        return JSON.parseArray(json, clazz);
    }


    /***
     * 根据节点来查找
     *
     * @param json
     * @param fieldName
     * @return
     */
    public static JsonNode findValue(String json, String fieldName) {
        if (StringUtils.isEmpty(json)) {
            return NullNode.instance;
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode rootNode = mapper.readTree(json);
            rootNode = rootNode.findValue(fieldName);
            if (null == rootNode) {
                return NullNode.instance;
            }
            return rootNode;
        } catch (Exception e) {
            return NullNode.instance;
        }
    }

    public static String findStringValue(String json, String fieldName) {
        JsonNode rootNode = findValue(json, fieldName);
        return rootNode.isNull() ? "" : rootNode.asText();
    }

    /**
     * 直接获取第一个key相同的string
     *
     * @param payload
     * @param key
     * @return
     */

    public static String getString(String payload, String key) {
        JSONObject payloadJson = JSON.parseObject(payload);
        return getString(payloadJson, key);
    }

    public static String getString(JSONObject payloadJson, String key) {
        Set entrySet = payloadJson.entrySet();

        String result;
        for (Object entry : entrySet) {
            HashMap.Entry entryNode = (HashMap.Entry) entry;

            String innerKey = String.valueOf(entryNode.getKey());

            if (innerKey.equals(key) && !(entryNode.getValue() instanceof JSONObject)) {
                return String.valueOf(entryNode.getValue());
            } else {
                if (entryNode.getValue() instanceof JSONObject) {
                    result = getString(String.valueOf(payloadJson.getString(innerKey)), key);
                    if (StringUtils.isNotBlank(result)) {
                        return result;
                    }
                } else if (entryNode.getValue() instanceof JSONArray) {
                    JSONArray jarray = (JSONArray) entryNode.getValue();
                    for (Object jo : jarray) {
                        result = getString(JSON.toJSONString(jo), key);
                        if (StringUtils.isNotBlank(result)) {
                            return result;
                        }
                    }
                } else {
                    continue;
                }
            }
        }
        return null;
    }


    /**
     * 通过父、子节点键值对获取值
     *
     * @param payload
     * @param fatherKey
     * @param key
     * @return
     */
    public static String getStringWithFatherKey(String payload, String fatherKey, String key) {
        JSONObject payloadJson = JSON.parseObject(payload);
        return getStringWithFatherKey(payloadJson, fatherKey, key, fatherKey);
    }

    public static String getStringWithFatherKey(JSONObject payloadJson, String fatherKey, String key, String originFatherKey) {
        Set entrySet = payloadJson.entrySet();

        String result;
        for (Object entry : entrySet) {
            HashMap.Entry entryNode = (HashMap.Entry) entry;

            String innerKey = String.valueOf(entryNode.getKey());

            if (innerKey.equals(key) && fatherKey.equals(originFatherKey)) {
                return String.valueOf(entryNode.getValue());
            } else {
                if (entryNode.getValue() instanceof JSONObject) {
                    result = getStringWithFatherKey((JSONObject) entryNode.getValue(), innerKey, key, originFatherKey);
                    if (StringUtils.isNotBlank(result)) {
                        return result;
                    }
                } else if (entryNode.getValue() instanceof JSONArray) {
                    JSONArray jarray = (JSONArray) entryNode.getValue();
                    for (Object jo : jarray) {
                        result = getStringWithFatherKey((JSONObject) jo, innerKey, key, originFatherKey);
                        if (StringUtils.isNotBlank(result)) {
                            return result;
                        }
                    }
                } else {
                    continue;
                }
            }
        }
        return "查询父、子节点关系键值对不存在。请重新输入。";
    }


    /**
     * 直接获取第一个key相同, 类相同的值
     *
     * @param payload
     * @param key
     * @return
     */
    public static <T> T getValue(String payload, String key, Class<T> clazz) {
        JSONObject payloadJson = JSON.parseObject(payload);
        return getValue(payloadJson, key, clazz);
    }


    public static <T> T getValue(JSONObject payloadJson, String key, Class<T> clazz) {
        Set entrySet = payloadJson.entrySet();

        T result;
        for (Object entry : entrySet) {
            HashMap.Entry entryNode = (HashMap.Entry) entry;

            String innerKey = String.valueOf(entryNode.getKey());

            if (innerKey.equals(key) && clazz.isInstance(entryNode.getValue())) {
                return (T) entryNode.getValue();
            } else {
                if (entryNode.getValue() instanceof JSONObject) {
                    result = getValue(payloadJson.getJSONObject(innerKey), key, clazz);
                    if (result != null) {
                        return result;
                    }
                } else if (entryNode.getValue() instanceof JSONArray) {
                    JSONArray jarray = (JSONArray) entryNode.getValue();
                    for (Object jo : jarray) {
                        result = getValue((JSONObject) jo, key, clazz);
                        if (result != null) {
                            return result;
                        }
                    }
                } else {
                    continue;
                }
            }
        }
        return null;
    }

}
