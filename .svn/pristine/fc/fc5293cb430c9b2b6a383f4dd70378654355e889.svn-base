package com.boot.common.web;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.common.util.ImageCodeBuilder;

@Controller
@RequestMapping("/verificationCode")
public class VerificationCodeController {

	@RequestMapping("/getCode")
	public void getCode(HttpServletRequest request,HttpServletResponse response) throws IOException{
		OutputStream os = response.getOutputStream();
		Map<String,Object> map = ImageCodeBuilder.create(60, 20);
		request.getSession().setAttribute("benchmarkCaptcha", map.get("strEnsure").toString().toLowerCase());
		request.getSession().setAttribute("codeTime", new Date().getTime());
		try {
			ImageIO.write((BufferedImage) map.get("image"), "JPEG", os);
		} catch (IOException e) {
		
		}
		
	}

}
