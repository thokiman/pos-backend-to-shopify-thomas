/*
 * © Copyright. LeapAd Network - All Rights Reserved
 * 准动网络科技-银豹收银系统
 */
package cn.leadpad.pospal.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * @Type: cn.leadpad.pospal.common.utils.JsonConvertor
 * @ClassName: JsonConvertor
 * @Author: ianylb@sina.com,673874424@qq.com
 * @Version: 1.0 
 * @Date: 2015年12月18日 下午4:39:41
 * @Description: <br/>
 */
public class JsonConvertor {
	private static final Gson gson = 
			new GsonBuilder()
				//.registerTypeAdapter(Time.class, timeAdapter)
				//.registerTypeAdapter(Date.class, dateAdapter)
				.create();
	
    public static String toJson(Object src) {
        return gson.toJson(src);
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        return gson.fromJson(json, typeOfT);
    }
}
