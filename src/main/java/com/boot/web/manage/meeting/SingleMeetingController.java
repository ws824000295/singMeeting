package com.boot.web.manage.meeting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.boot.common.web.AbstractController;
import com.boot.config.Constants;
import com.boot.domain.AdminUser;
import com.boot.domain.Device;
import com.boot.domain.Meeting;
import com.boot.service.SingleMeetingService;

/**
 * @ClassName FuliController
 * @Description: 会议
 * @author: WS
 * @since: 2017-11-22 下午11:50:38
 */
@Controller
@RequestMapping("/single/meeting")
public class SingleMeetingController extends AbstractController {

	@Autowired
	private SingleMeetingService meetingService;

	/**
	* @Title: pingStart 
	* @Description:启动完成 开启socket 8002
	* @param 设定文件 
	* @author 王朔
	* @return void返回类型 
	* @throws
	 */
	@PostConstruct
	public void socketStart() {
		try {
			InitSocketControl s = new InitSocketControl();
			Thread th = new Thread(s);
			th.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: queryMeeting
	 * @Description 查询未使用的设备列表
	 * @param @param request
	 * @param @param resp
	 * @param @throws Exception设定文件
	 * @author 王朔
	 * @return ModelAndView返回类型
	 * @throws
	 */
	@RequestMapping(value = "/queryMeeting")
	@ResponseBody
	public ModelAndView queryMeeting(HttpServletRequest request, HttpServletResponse resp) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();

		AdminUser adminuser = (AdminUser) request.getSession().getAttribute(Constants.ADMIN_USER_KEY);
		if(null == adminuser){
			return new ModelAndView("index", model); 
		}
		
		List<Device> li = meetingService.queryDevice();
		model.put("list", li);

		List<Meeting> listMeet = meetingService.queryListMeeting();
		model.put("listMeet", listMeet);
		return new ModelAndView("manage/meeting/PC", model);
	}

	/**
	* @Title: queryMeetingByParam 
	* @Description: 条件查询
	* @param @param param
	* @param @return设定文件 
	* @author 王朔
	* @return Map<String,Object>返回类型 
	* @throws
	 */
	@RequestMapping(value = "/queryMeetingByParam")
	@ResponseBody
	public Map<String,Object> queryMeetingByParam(String param){
		Map<String, Object> model = new HashMap<String, Object>();
		List<Meeting> listMeet = meetingService.queryMeetingByParam(param);
		model.put("success", true);
		model.put("listMeet", listMeet);
		return model;
	}
	
	
	/**
	 * @Title: createMeeting
	 * @Description: 创建会议
	 * @param @param ids
	 * @param @return
	 * @author 王朔
	 * @return ModelAndView返回类型
	 * @throws
	 */
	@RequestMapping(value = "/createMeeting")
	@ResponseBody
	public Map<String,Object> createMeeting(@RequestParam(value = "ids", required = false) List<String> ids,String meetName) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		model = meetingService.createMeeting(ids,meetName);
		return model;
	}

	/**
	 * @Title: closeMeeting
	 * @Description:关闭会议
	 * @param @param id
	 * @param @return
	 * @param @throws Exception设定文件
	 * @author 王朔
	 * @return Map<String,Object>返回类型
	 * @throws
	 */
	@RequestMapping(value = "/closeMeeting")
	@ResponseBody
	public Map<String, Object> closeMeeting(String id) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		model = meetingService.closeMeetingById(id);
		return model;
	}

	/**
	 * @throws Exception
	 * @Title: downLoadMeet
	 * @Description:下载会议
	 * @param 设定文件
	 * @author 王朔
	 * @return void返回类型
	 * @throws
	 */
	@RequestMapping(value = "/downLoadMeet")
	public void downLoadMeet(String meetId, HttpServletRequest request, HttpServletResponse resp) throws Exception {
		meetingService.downLoadMeetByCode(meetId, request, resp);
	}
	
//	@RequestMapping(value = "/creteUser")
//	public void creteUser() throws Exception {
//		meetingService.creteUser();
//	}
//	

}
