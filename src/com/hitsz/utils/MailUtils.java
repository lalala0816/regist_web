package com.hitsz.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 邮件发送的工具类
 * @param to: 给谁发邮件
 * @param code:邮件的激活码
 * @author Dell
 *
 */
public class MailUtils {
	public static void sendMail(String to, String code) throws Exception{
		//1.创建连接对象，连接到邮箱服务器
		Properties props = new Properties();
		
		Session session = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("service@store.com", "1122");
			}
			
		});
		
		//2.创建邮件对象
		Message message = new MimeMessage(session);
		//2.1设置邮件对象
		message.setFrom(new InternetAddress("service@store.com"));
		//设置收件人
		message.setRecipient(RecipientType.TO, new InternetAddress(to));
		//2.3设置邮件的主题
		message.setSubject("来自XXX网站的激活邮件");
		//2.4设置邮件的正文
		message.setContent("<h1>来自XXX网站的激活邮件，激活请点击以下链接：</h1><h3><a href = 'http://localhost:8080/regist_web/ActiveServlet?code="+code+"'>http://localhost:8080/regist_web/ActiveServlet?code="+code+"</a></h3>", "text/html;charset=UTF-8");
		//3.发送一封激活邮件
		Transport.send(message);
		
		//3.发送一封激活邮件
	}
}
