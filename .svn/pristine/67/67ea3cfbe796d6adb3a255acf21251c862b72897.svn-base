package com.boot.common.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/common/map")
public class MapController {

	@RequestMapping("/showMap")
	public ModelAndView showMap(String address,String size) throws UnsupportedEncodingException{
		Map<String,Object> model = new HashMap<String,Object>();
		if(StringUtils.isBlank(address)){
			model.put("address", "北京");
		}else{
			model.put("address", URLDecoder.decode(address, "utf-8"));
		}
		model.put("width", size.split("\\*")[0]);
		model.put("height", size.split("\\*")[1]);
		return new ModelAndView("common/map_show",model);
	}
	
}
