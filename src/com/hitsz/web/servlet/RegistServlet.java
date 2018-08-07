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
 * 用户注册的Servlet
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
		//接收数据
		//处理中文的乱码
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		//封装数据
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setNickname(nickname);
		user.setEmail(email);
		user.setState(0);//0：未激活 1：已经激活
		String code = UUIDUtils.getUUID() + UUIDUtils.getUUID();//产生一个64bit随机字符串
		user.setCode(code);
		
		//调用业务层处理数据
		UserService userService = new UserServiceImpl();//面向接口编程
		userService.regist(user);
		//页面跳转
		request.setAttribute("msg", "您已经注册成功，请去邮箱激活");
		request.getRequestDispatcher("/msg.jsp").forward(request, response);;
		
		//页面跳转
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
