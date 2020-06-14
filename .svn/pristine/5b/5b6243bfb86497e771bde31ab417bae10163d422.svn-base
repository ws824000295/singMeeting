package com.boot.common.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 一卡易 工具类
 * @author dgq
 *
 */
public class EcardUtil {

	 private volatile static EcardUtil uniqueInstance;
	 
     private EcardUtil() {}
	  
     public static EcardUtil getInstance() {    
	    if(uniqueInstance == null) { 
	       synchronized(EcardUtil.class) {   
	          if(uniqueInstance == null){
	        	  uniqueInstance = new EcardUtil();   
	          }   
	       }   
	    }   
         return uniqueInstance;   
    }   
	    
	
     /**
 	 * 
 	* @Description:获取卡券list
 	* @author WangShuo
 	* @date 2018年4月16日 下午1:25:06 
 	* @return 0G33DY  E547D6DC3BA911E8BEA2001018640E28
 	* @throws Exception
 	 */
 	public Map<String, Object> getCardList(String OpenId,String Secret,String userAccount) throws Exception {
 		Map<String, Object> returnMap = new HashMap<>();
 		Map<String, Object> objmap = new HashMap<>();
 		List<Map> end = new ArrayList<>();
 		int pageIndex = 0;
 		int pageSize = 20;
 		Map<String,Object> paramMap = new HashMap<String,Object>();
 		paramMap.put("userAccount", userAccount);
 		paramMap.put("pageIndex",pageIndex);
 		paramMap.put("pageSize", pageSize);
 		paramMap.put("where", "1=1");
 		String sendParam = new Gson().toJson(paramMap);
 		long TimeStamp = System.currentTimeMillis();
 		String Signature = MD5Utils.StringToMD5(OpenId+Secret+TimeStamp+sendParam).toUpperCase();
 		String url = "https://openapi.1card1.cn/OpenApi/Get_CouponPagedV2?openId="+OpenId+"&signature="+Signature+"&timestamp="+TimeStamp;
 		Map<String, String> textMap = new HashMap<String,String>();
 		textMap.put("data", sendParam);
 		String back = send(url,textMap);
 		System.out.println("一卡易返回back="+back);
 		Map<String, Object> resultMap = new Gson().fromJson(back, Map.class);
 	
 		JSONArray jsonArray = JSONArray.fromObject(resultMap.get("data"));
 		JSONObject jsonObjecty ;
 		int count;
 		for(int i=0;i<jsonArray.size();i++){
 			 objmap = new HashMap<>();
 			 jsonObjecty =  jsonArray.getJSONObject(i);
 			 objmap.put("Guid", jsonObjecty.getString("Guid"));
 			 objmap.put("Title",  jsonObjecty.getString("Title"));
 				 count = jsonObjecty.getInt("TotalCount")  - jsonObjecty.getInt("SendCount");
 				 objmap.put("count",  count);
 			 end.add(objmap);
 		}
 		
 		Integer total = new Double(resultMap.get("total").toString()).intValue();
 		Integer list = total % pageSize;
 		if(list == 0){
 			list = total / pageSize;
 		}else{
 			list = total / pageSize + 1;
 		}
 		
 		for( int i= 0; i < list-1 ; i++){
 			pageIndex = pageIndex + 1;
 			paramMap.put("userAccount", "10000");
 			paramMap.put("pageIndex",pageIndex);
 			paramMap.put("pageSize", pageSize);
 			paramMap.put("where", "1=1");
 			paramMap.put("orderBy", "CreateTime desc");
 			String nextsendParam = new Gson().toJson(paramMap);
 			long nextTimeStamp = System.currentTimeMillis();
 			String nexturl = "https://openapi.1card1.cn/OpenApi/Get_CouponPagedV2?openId="+OpenId+"&signature="+MD5Utils.StringToMD5(OpenId+Secret+nextTimeStamp+nextsendParam).toUpperCase()+"&timestamp="+nextTimeStamp;
 			Map<String, String> nextmap = new HashMap<String,String>();
 			nextmap.put("data", nextsendParam);
 			String nextback = send(nexturl,nextmap);
 			System.out.println(nextback);
 			Map<String, Object> nextresultMap = new Gson().fromJson(nextback, Map.class);
 			JSONArray nextJsonArray = JSONArray.fromObject(nextresultMap.get("data"));
 			for(int y =0; y <nextJsonArray.size(); y++){
 				 objmap = new HashMap<>();
 				 jsonObjecty =  nextJsonArray.getJSONObject(y);
 				 objmap.put("Guid", jsonObjecty.getString("Guid"));
 				 objmap.put("Title",  jsonObjecty.getString("Title"));
 				 count = jsonObjecty.getInt("TotalCount")  - jsonObjecty.getInt("SendCount");
 	 			 objmap.put("count",  count);
 				 end.add(objmap);
 			}
 			
 		}
 		returnMap.put("cardList", end);
 		return returnMap ;
 	}

 	
	/**
	 * 一卡易会员注册
	 * @param openId
	 * @param secret
	 * @param cardId		卡号
	 * @param phone			手机号
	 * @return
	 */
	public String registerMember(String openId, String secret, String cardId, String phone){
		
		Map<String,Object> map = new HashMap<String,Object>();
		Gson gson = new Gson();
		LogUtil.info("**********执行注册*******注册手机号*******"+phone);
		String memberGuid = "fail";
		
		map.put("cardId", cardId);
		//map.put("mobile", phone);
		String c = gson.toJson(map);

		long TimeStamp = System.currentTimeMillis();
		String Signature = MD5Utils.StringToMD5(openId+secret+TimeStamp+c).toUpperCase();
		
		Map<String, String> textMap = new HashMap<String,String>();
		textMap.put("data", c);
		
		String url = "https://openapi.1card1.cn/OpenApi/Add_Member?openId="+openId+"&signature="+Signature+"&timestamp="+TimeStamp;
		String back = send(url,textMap);
		LogUtil.info("*************一卡易会员注册返回信息*************************"+back);
		//{"status":0,"message":"注册成功","cardId":"1523844607638","memberGuid":"48988459-411b-11e8-86e0-0010185de866"}
		
		Map<String, Object> returnValue = gson.fromJson(back, Map.class);
		
		Integer status = ((Double) returnValue.get("status")).intValue();
		if(status == 0){
			memberGuid = returnValue.get("memberGuid").toString();
		}
		
		return memberGuid;
	}
	
	/**
	 * 一卡易优惠券发送
	 * @param openId
	 * @param secret
	 * @param userAccount		工号
	 * @param memberCard
	 * @param couponGuid		优惠券唯一标识
	 * @param sendCount			发送数量
	 * @return
	 */
	public Map<String, Object> sendCoupon(String openId, String secret, String userAccount, String memberCard, String couponGuid, Integer sendCount){
		
		Map<String,Object> map = new HashMap<String,Object>();
		Gson gson = new Gson();
		
		LogUtil.info("**********发送卡卷*******卡号*******"+memberCard+"*******couponGuid*******"+couponGuid);
		map.put("userAccount", userAccount);
		map.put("cardIds", memberCard);
		map.put("couponGuid", couponGuid);
		map.put("sendCount", sendCount);
		String c = gson.toJson(map);

		long TimeStamp = System.currentTimeMillis();
		String Signature = MD5Utils.StringToMD5(openId+secret+TimeStamp+c).toUpperCase();
		
		Map<String, String> textMap = new HashMap<String,String>();
		textMap.put("data", c);
		
		String url = "https://openapi.1card1.cn/OpenApi/SendCoupon?openId="+openId+"&signature="+Signature+"&timestamp="+TimeStamp;
		String back = send(url,textMap);
		LogUtil.info("**********发送卡卷返回信息*********"+back);
		Map<String, Object> returnValue = gson.fromJson(back, Map.class);
		
		Integer status = ((Double) returnValue.get("status")).intValue();
		String message = (String) returnValue.get("failMessages");
		String couponSendGuid = (String) returnValue.get("couponSendGuid");
		
		map.put("status", status+"");		//0 成功    -1失败
		map.put("message", message);
		map.put("couponSendGuid", couponSendGuid);
		LogUtil.info("**************返回信息*******************************"+map.toString());
		return map;
	}
	
	
	/**
	 * 查询已发送优惠券、未使用优惠券、已核销的优惠券、已过期的优惠券
	 * @param openId
	 * @param secret
	 * @param userAccount
	 * @param memberCardId		一卡易会员卡卡号
	 * @param flag
	 * 		send---- 查询已发送优惠券(对应页面的全部)
	 * 		unused-- 未使用优惠券(不包含已过期的)
	 * 		use------已核销的优惠券(对应页面的已使用)
	 * 		expire---已过期的优惠券
	 * @return
	 */
	public String queryEcard(Integer pageIndex,Integer pageSize,String openId, String secret, String userAccount,String memberCardId){
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("userAccount", userAccount);
		map.put("pageIndex",pageIndex);
		map.put("pageSize", pageSize);
	    map.put("where", " CardId='"+ memberCardId + "' ");
		map.put("orderBy", "OperateTime desc");
		
		String param = JSONObject.fromObject(map).toString();

		LogUtil.info("*********queryEcard请求参数**************"+param);
		long TimeStamp = System.currentTimeMillis();
		String Signature = MD5Utils.StringToMD5(openId+secret+TimeStamp+param).toUpperCase();
		
		Map<String, String> textMap = new HashMap<String,String>();
		textMap.put("data", param);
		
		String url = "https://openapi.1card1.cn/OpenApi/Get_CoupnSendPagedV2?openId="+openId+"&signature="+Signature+"&timestamp="+TimeStamp;
		String back = send(url,textMap);
		return back;
	}
	
   
	public static String send(String urlStr, Map<String, String> textMap) {
		String res = "";
		HttpURLConnection conn = null;
		String BOUNDARY = "---------------------------123821742118716"; 
		try {
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

			OutputStream out = new DataOutputStream(conn.getOutputStream());
			if (textMap != null) {
				StringBuffer strBuf = new StringBuffer();
				Iterator iter = textMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"\r\n\r\n");
					strBuf.append(inputValue);
				}
				out.write(strBuf.toString().getBytes());
			}

			byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
			out.write(endData);
			out.flush();
			out.close();

			// 读取返回数据
			StringBuffer strBuf = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				strBuf.append(line).append("\n");
			}
			res = strBuf.toString();
			reader.close();
			reader = null;
		} catch (Exception e) {
			System.out.println("发送POST请求出错。" + urlStr);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		return res;
	}
}