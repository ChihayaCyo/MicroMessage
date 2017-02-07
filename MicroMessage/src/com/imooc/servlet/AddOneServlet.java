package com.imooc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imooc.service.MaintainService;

@SuppressWarnings("serial")
public class AddOneServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//设置编码
		req.setCharacterEncoding("UTF-8");
		//接受页面的值
		String name=req.getParameter("command");
		String description=req.getParameter("description");
		String content=req.getParameter("content");
		//向页面传值
/*		req.setAttribute("command", name);
		req.setAttribute("description", description);*/
		//修改数据库（业务需要去调用service）
		MaintainService maintainService=new MaintainService();
		maintainService.addOne(name, description,content);
		//向页面跳转
		req.getRequestDispatcher("/WEB-INF/jsp/back/addToList.jsp").forward(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}
}
