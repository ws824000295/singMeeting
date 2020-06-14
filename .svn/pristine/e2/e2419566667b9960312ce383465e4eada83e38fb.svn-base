package com.boot.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author  顶级+超级物联网工程师-WangShuo 
 * @date 创建时间：2018年8月16日 下午3:31:42 
 * @version 1.0 
 * @Description:  百度接口 根据IP 获取地理位置
 * @since 1.8
 * @return  
 */
public class BaiDuUtil {

	private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public static String  getCityNameByIp(String ip) throws Exception{
        JSONObject json = readJsonFromUrl("http://api.map.baidu.com/location/ip?ak=LEhGKDNBygX6Uw0g0TcxBaZuihImvcZc&ip="+ip);
        JSONObject cityobj =  (JSONObject) ((JSONObject) json.get("content")).get("address_detail");
        System.out.println("百度获取城市名称==============="+cityobj.get("city"));
		return cityobj.get("city").toString();
    }
    
    
    public static Map<String,Object>  getCityAndProvinceByIp(String ip) throws JSONException, IOException{
    	Map<String,Object> result =  new HashMap<>();
        JSONObject json = readJsonFromUrl("http://api.map.baidu.com/location/ip?ak=LEhGKDNBygX6Uw0g0TcxBaZuihImvcZc&ip="+ip);
        JSONObject obj =  (JSONObject) ((JSONObject) json.get("content")).get("address_detail");
        result.put("city", obj.get("city").toString());
        result.put("province", obj.get("province").toString());
		return result;
    }
    
//    public static void main(String[] args) throws Exception {
//        //这里调用百度的ip定位api服务 详见 http://api.map.baidu.com/lbsapi/cloud/ip-location-api.htm
//        JSONObject json = readJsonFromUrl("http://api.map.baidu.com/location/ip?ak=F454f8a5efe5e577997931cc01de3974&ip=120.134.33.9");
//        System.out.println(json.toString());
//        System.out.println(((JSONObject) json.get("content")).get("address_detail"));
//        JSONObject cityobj =  (JSONObject) ((JSONObject) json.get("content")).get("address_detail");
//        System.out.println(cityobj.get("city"));
//    }


}
