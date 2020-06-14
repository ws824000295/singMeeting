package com.boot.common.util;

import java.math.BigDecimal;

import com.boot.config.Constants;

public class DemicalUtil {

	
    public static  BigDecimal nullToZero(BigDecimal bigDecimal){
    	return bigDecimal==null?new BigDecimal(0.00):bigDecimal;
    }
    //保留n位小数 进行四舍五入
    //figure  位数
    public static  BigDecimal roundUpOrDown(BigDecimal bigDecimal1,BigDecimal bigDecimal2,Integer figure){
    	BigDecimal tempValue=bigDecimal1.multiply(bigDecimal2).setScale(figure,BigDecimal.ROUND_HALF_UP);    
    	return tempValue;
    }
    
    public static  BigDecimal del(BigDecimal bigDecimal1,BigDecimal bigDecimal2){
    	BigDecimal tempValue=bigDecimal1.subtract(bigDecimal2).setScale(2);  
    	return tempValue;
    }
    public static void main(String[] args) {
    	BigDecimal bigDecimal1 = new BigDecimal("100");
    	BigDecimal bigDecimal2 = new BigDecimal("20");
    	System.out.println(bigDecimal2.compareTo(bigDecimal1));
    	
	}
}
