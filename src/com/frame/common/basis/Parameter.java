package com.frame.common.basis;

import java.util.HashMap;
import java.util.Map;

import com.frame.common.utils.StringUtils;

/**
 * 查询参数类
 */
public class Parameter {

    private Map<String, Object> map;

    public Parameter() {
        map = new HashMap<String, Object>();
    }

    public static Parameter create() {
        return new Parameter();
    }

    public static Parameter create(String key, Object value) {
        return new Parameter(key, value);
    }

    public Parameter insert(String key, Object value) {
        map.put(key, value);
        return this;
    }

    public Parameter insertFilterNullValue(String key, String value) {
        if (StringUtils.isBlank(value)) {
            return this;
        }
        map.put(key, value);
        return this;
    }

    public Parameter(String key, Object value) {
        map = new HashMap<String, Object>();
        map.put(key, value);
    }


    public Map<String, Object> getMap() {
        return map;
    }


}
