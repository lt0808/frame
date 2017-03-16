package com.frame.common.utils;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * 全局配置类

 */
public class Global {
	
	/**
	 * 属性文件加载对象
	 */
	private static PropertiesLoader propertiesLoader;
	
	/**
	 * 获取配置
	 */
	public static String getConfig(String key) {
		if (propertiesLoader == null){
			propertiesLoader = new PropertiesLoader("config.properties");
		}
		return propertiesLoader.getProperty(key);
	}

	/////////////////////////////////////////////////////////
	
	public static String getAdminPath() {
		return getConfig("adminPath");
	}
	public static String getFrontPath() {
		return getConfig("frontPath");
	}
	public static String getUrlSuffix() {
		return getConfig("urlSuffix");
	}
	/////////////////////////////////////////////////////////
    /**
     * 获取单页分页条数
     */
    public static int getPageSize() {
        return NumberUtils.toInt(getConfig("page.pageSize"), 10);
    }

}
