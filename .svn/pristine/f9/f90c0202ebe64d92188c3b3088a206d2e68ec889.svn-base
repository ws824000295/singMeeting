package com.boot.common.util;
/**
 * @author  WangShuo 
 * @date 创建时间：2017年12月18日 上午10:24:54 
 * @version 1.0 
 * @Description: TODO
 * @since 1.8
 * @return 根据2地经纬度 算出相差距离
 */
public class LocationUtils {

	  private static double EARTH_RADIUS = 6378.137;    
	    
	    private static double rad(double d) {    
	        return d * Math.PI / 180.0;    
	    }    
	    
	    /**   
	     * 通过经纬度获取距离(单位：千米)   
	     * @param lat1   
	     * @param lng1   
	     * @param lat2   
	     * @param lng2   
	     * @return   
	     */    
	    public static double getDistance(double lat1, double lng1, double lat2, double lng2) {    
	        double radLat1 = rad(lat1);    
	        double radLat2 = rad(lat2);    
	        double a = radLat1 - radLat2;    
	        double b = rad(lng1) - rad(lng2);    
	        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)    
	                + Math.cos(radLat1) * Math.cos(radLat2)    
	                * Math.pow(Math.sin(b / 2), 2)));    
	        s = s * EARTH_RADIUS;    
	        s = Math.round(s * 10000d) / 10000d;    
//	        s = s*1000;     *1000返回的是以米为单位
	        return s;    
	    }    
}
