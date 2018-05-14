package com.ctbu.javateach666.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ctbu.javateach666.pojo.bo.QuestionPaper;



/**
 * 集合操作工具类
 *
 * @author Direct
 */
public class CollectionUtils {
    /**
     * 判断一个Collection集合不能为空
     *
     * @param collection 传人collection类型参数
     * @return boolean 返回判断结果
     */
    public static boolean isNotBlank(Collection<?> collection) {
        return collection != null && collection.size() > 0;
    }
    
    
    public static Map<Integer,Integer> parseQuestionPaper(List<QuestionPaper> questionPapers){
        Map<Integer,Integer> map = new HashMap<>();
        if (isNotBlank(questionPapers)){
            for (QuestionPaper questionPaper : questionPapers){
                map.put(questionPaper.getId(),questionPaper.getScore());
            }
        }
        return map;
    }

}
