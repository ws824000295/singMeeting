package com.boot.common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 * @author  WangShuo 
 * @date 创建时间：2018年8月4日 下午1:31:39 
 * @version 1.0 
 * @Description: key 按照 字母排序
 * @since 1.8
 * @return  
 */
public class MapSort {

	
	  /**
     * 
     * @Title: sortMap
     * @Description: 对集合内的数据按key的字母顺序做排序
     */
    public List<Map.Entry<String, String>> sortMap(final Map<String, String> map) {
        final List<Map.Entry<String, String>> infos = new ArrayList<Map.Entry<String, String>>(map.entrySet());

        // 重写集合的排序方法：按字母顺序
        Collections.sort(infos, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(final Entry<String, String> o1, final Entry<String, String> o2) {
                return (o1.getKey().toString().compareTo(o2.getKey()));
            }
        });

        return infos;
    }
    
    public static String getSign(Map<String, String> map, String key){
         String sign = "";
         final List<Map.Entry<String, String>> list = new MapSort().sortMap(map);
         for (final Map.Entry<String, String> m : list) {
         	sign += m.getKey() + "=" + m.getValue();
         }
         System.out.println("小程序获取签名加密前===================="+sign+"key="+key);
         sign  = MD5Utils.StringToMD5(sign+"key="+key);
		return sign;
    	
    }
    
}
