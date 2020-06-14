//package com.boot.aop;
//
//import java.util.Date;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.core.JmsMessagingTemplate;
//import org.springframework.stereotype.Component;
//
//import com.boot.common.cache.AbstractCache;
//import com.boot.common.exception.WorkException;
//import com.boot.common.util.AppPath;
//import com.boot.common.util.LogUtil;
//import com.boot.config.QueueKey;
//import com.boot.domain.VisitAnalysis;
//
//@Aspect
//@Component
//public class CommonAspect extends AbstractCache {
//
//	@Autowired 
//	private JmsMessagingTemplate jmsTemplate;
//	
//	/**
//	 * @Description: 转币监控
//	 * @param jp
//	 * @return
//	 * @throws Throwable
//	 * @return Object
//	 * @throws 
//	 * @Create Date 2018年8月17日 下午1:58:25 By gaoy
//	 */
//	@Around("execution(* com.boot.service.QukuailianUtilService.transferAccounts(..))")
//	public Object monitoringAccessRecord(ProceedingJoinPoint jp) throws Throwable {
//		HttpServletRequest request = AppPath.getRequest();
//		String path = request.getContextPath();
//		String port = "";
//		if(80!=request.getServerPort()){
//			port = ":"+request.getServerPort();
//		}
//		String basePath = request.getServerName()+port+path+request.getRequestURI();
//		LogUtil.info("=====访问的路径为："+basePath+"==============");
//		if (basePath.contains("/terminal/wxCenter") || basePath.contains("/terminal/toykyPayReturn") || basePath.contains("/terminal/updateYkyOrder") || basePath.contains("/yeepay/paySuccess")) {
//			LogUtil.info("=====走微信中心回调修改订单状态的方法，不拦截==============");
//		}else {
//			try {
//				String ip = AppPath.getIpAddress();
//				LogUtil.info("=====访问的ip为："+ip+"==============");
//				//redis中查看是否ip为黑名单
//				Object ipValue = getCache("warning"+ip);
//				if(ipValue != null){
//					throw new WorkException("黑名单："+ip);
//				}
//				Object[] param = jp.getArgs();
//				String userId = (String)param[1];
//				LogUtil.info("=====访问的用户id为："+userId+"==============");
//				Date date = new Date();
//				VisitAnalysis vad = new VisitAnalysis();
//				vad.setUserId(userId);
//				vad.setUserIp(ip);
//				vad.setVisitPath(basePath);
//				vad.setCreateTime(date);
//				//把监控数据放到队列
//				jmsTemplate.convertAndSend(QueueKey.VISIT_ANALYSIS_QUEUE,vad);
//			} catch (WorkException e){
//				throw new Exception(e.getMessage());
//			} catch (Exception e) {
//				LogUtil.info("*****%%%%%%%****获取访问信息是出现异常******%%%%%%%********");
//				e.printStackTrace();
//			}
//		}
//		return jp.proceed();
//	}
//	
//}
