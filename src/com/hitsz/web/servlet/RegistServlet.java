package com.hitsz.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hitsz.domain.User;
import com.hitsz.service.UserService;
import com.hitsz.service.impl.UserServiceImpl;
import com.hitsz.utils.UUIDUtils;


/**
 * �û�ע���Servlet
 * Servlet implementation class RegistServlet
 */
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    //public RegistServlet() {
  //      super();
        // TODO Auto-generated constructor stub
  //  }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
		//��������
		//�������ĵ�����
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		//��װ����
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setNickname(nickname);
		user.setEmail(email);
		user.setState(0);//0��δ���� 1���Ѿ�����
		String code = UUIDUtils.getUUID() + UUIDUtils.getUUID();//����һ��64bit����ַ���
		user.setCode(code);
		
		//����ҵ��㴦������
		UserService userService = new UserServiceImpl();//����ӿڱ��
		userService.regist(user);
		//ҳ����ת
		request.setAttribute("msg", "���Ѿ�ע��ɹ�����ȥ���伤��");
		request.getRequestDispatcher("/msg.jsp").forward(request, response);;
		
		//ҳ����ת
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
