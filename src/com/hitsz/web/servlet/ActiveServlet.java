package com.hitsz.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hitsz.domain.User;
import com.hitsz.service.UserService;
import com.hitsz.service.impl.UserServiceImpl;

/**
 * ���ڼ���Servlet
 * Servlet implementation class ActiveServlet
 */
public class ActiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			//���ܼ�����
			String code = request.getParameter("code");
			//���ݼ������ѯ�û�
			UserService userService= new UserServiceImpl();
			User user = userService.findByCode();
			//�Ѿ���ѯ�����޸��û���״̬
			if(user != null){
				//�Ѿ���ѯ�����޸��û���״̬
				user.setState(1);//1��ʾ�Ѿ�����
				user.setCode(null);
				userService.update(user);
				request.setAttribute("msg", "���ļ�����ɹ�����ȥ��½��");
			}else{
				//���ݼ�����û�в�ѯ�����û�
				request.setAttribute("msg", "���ļ��������������¼��");
			}
			//ҳ����ת
			request.getRequestDispatcher("/msg.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
