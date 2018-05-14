package com.ctbu.javateach666.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

@Component
public class PageUtil {
	
	/**
	 * 转换为easyui能够处理的json字符串
	 * @param collection
	 * @param totalCount
	 * @return
	 */
	public static String creatDataGritJson(Collection<?> collection, Integer totalCount) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", collection);
        map.put("total", totalCount);
        return JSON.toJSONString(map);
    }
	
	/**
	 * 根据内容集合与页码、行数进行分页
	 * @param page 页数
	 * @param rows 每页显示数
	 * @param lists 分页集合
	 * @return json 字符串
	 */
	public static String findPage(Integer page,Integer rows,List<?> lists) {
		int begin = (page - 1) * rows;
        int end = page * rows;
        int size = lists.size();
        lists = lists.subList(begin, end >= size ? size : end);
        String json = creatDataGritJson(lists, size);
        System.out.println("分类内容:"+json);
		return json;
	}
}
