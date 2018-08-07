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
 * �ʼ����͵Ĺ�����
 * @param to: ��˭���ʼ�
 * @param code:�ʼ��ļ�����
 * @author Dell
 *
 */
public class MailUtils {
	public static void sendMail(String to, String code) throws Exception{
		//1.�������Ӷ������ӵ����������
		Properties props = new Properties();
		
		Session session = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("service@store.com", "1122");
			}
			
		});
		
		//2.�����ʼ�����
		Message message = new MimeMessage(session);
		//2.1�����ʼ�����
		message.setFrom(new InternetAddress("service@store.com"));
		//�����ռ���
		message.setRecipient(RecipientType.TO, new InternetAddress(to));
		//2.3�����ʼ�������
		message.setSubject("����XXX��վ�ļ����ʼ�");
		//2.4�����ʼ�������
		message.setContent("<h1>����XXX��վ�ļ����ʼ������������������ӣ�</h1><h3><a href = 'http://localhost:8080/regist_web/ActiveServlet?code="+code+"'>http://localhost:8080/regist_web/ActiveServlet?code="+code+"</a></h3>", "text/html;charset=UTF-8");
		//3.����һ�⼤���ʼ�
		Transport.send(message);
		
		//3.����һ�⼤���ʼ�
	}
}
