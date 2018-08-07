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
 * 用于激活Servlet
 * Servlet implementation class ActiveServlet
 */
public class ActiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			//接受激活码
			String code = request.getParameter("code");
			//根据激活码查询用户
			UserService userService= new UserServiceImpl();
			User user = userService.findByCode();
			//已经查询到，修改用户的状态
			if(user != null){
				//已经查询到，修改用户的状态
				user.setState(1);//1表示已经激活
				user.setCode(null);
				userService.update(user);
				request.setAttribute("msg", "您的激活码成功，请去登陆！");
			}else{
				//根据激活码没有查询到该用户
				request.setAttribute("msg", "您的激活码有误，请重新激活！");
			}
			//页面跳转
			request.getRequestDispatcher("/msg.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
