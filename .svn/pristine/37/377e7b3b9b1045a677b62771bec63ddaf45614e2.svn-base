package com.boot.common.util;

import java.io.File;
import java.util.Date;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
public class EmailUtil {

	private static int port = 25;

	/**
	 * 发送文本邮件
	 * 
	 * @throws Exception
	 */
	public static void sendTextMail(String topic, String content, String host, String userName, String password, String tos) throws Exception {
		SimpleEmail mail = new SimpleEmail();
		// 设置邮箱服务器信息
		mail.setSmtpPort(port);
		mail.setHostName(host);
		// 设置密码验证器
		mail.setAuthentication(userName, password);
		// 设置邮件发送者
		mail.setFrom(userName);
		// 设置邮件接收者
		mail.addTo(tos.split(","));
		// 设置邮件编码
		mail.setCharset("UTF-8");
		// 设置邮件主题
		mail.setSubject(topic);
		// 设置邮件内容
		mail.setMsg(content);
		// 设置邮件发送时间
		mail.setSentDate(new Date());
		// 发送邮件
		mail.send();
	}

	/**
	 * 发送Html邮件
	 * 
	 * @throws Exception
	 */
	public static void sendHtmlMail(String topic, String content, String host, String userName, String password, String to) throws Exception {
		HtmlEmail mail = new HtmlEmail();
		// 设置邮箱服务器信息
		mail.setSmtpPort(port);
		mail.setHostName(host);
		// 设置密码验证器
		mail.setAuthentication(userName, password);
		// 设置邮件发送者
		mail.setFrom(userName);
		// 设置邮件接收者
		mail.addTo(to);
		// 设置邮件编码
		mail.setCharset("UTF-8");
		// 设置邮件主题
		mail.setSubject("Test Email");
		// 设置邮件内容
		mail.setHtmlMsg("<html><body><img src='http://avatar.csdn.net/A/C/A/1_jianggujin.jpg'/><div>this is a HTML email.</div></body></html>");
		// 设置邮件发送时间
		mail.setSentDate(new Date());
		// 发送邮件
		mail.send();
	}

	/**
	 * 发送内嵌图片邮件
	 * 
	 * @throws Exception
	 */
	public static void sendImageMail(String topic, String content, String host, String userName, String password, String to) throws Exception {
		HtmlEmail mail = new HtmlEmail();
		// 设置邮箱服务器信息
		mail.setSmtpPort(port);
		mail.setHostName(host);
		// 设置密码验证器
		mail.setAuthentication(userName, password);
		// 设置邮件发送者
		mail.setFrom(userName);
		// 设置邮件接收者
		mail.addTo(to);
		// 设置邮件编码
		mail.setCharset("UTF-8");
		// 设置邮件主题
		mail.setSubject("Test Email");
		mail.embed(new File("1_jianggujin.jpg"), "image");
		// 设置邮件内容
		String htmlText = "<html><body><img src='cid:image'/><div>this is a HTML email.</div></body></html>";
		mail.setHtmlMsg(htmlText);
		// 设置邮件发送时间
		mail.setSentDate(new Date());
		// 发送邮件
		mail.send();
	}

	/**
	 * 发送附件邮件
	 * 
	 * @throws Exception
	 */
	public static void sendAttachmentMail(String topic, String content, String host, String userName, String password, String to) throws Exception {
		MultiPartEmail mail = new MultiPartEmail();
		// 设置邮箱服务器信息
		mail.setSmtpPort(port);
		mail.setHostName(host);
		// 设置密码验证器
		mail.setAuthentication(userName, password);
		// 设置邮件发送者
		mail.setFrom(userName);
		// 设置邮件接收者
		mail.addTo(to);
		// 设置邮件编码
		mail.setCharset("UTF-8");
		// 设置邮件主题
		mail.setSubject("Test Email");

		mail.setMsg("this is a Attachment email.this email has a attachment!");
		// 创建附件
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath("1_jianggujin.jpg");
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setName("1_jianggujin.jpg");
		mail.attach(attachment);

		// 设置邮件发送时间
		mail.setSentDate(new Date());
		// 发送邮件
		mail.send();
	}
}
