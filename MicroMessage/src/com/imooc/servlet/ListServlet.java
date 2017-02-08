package com.imooc.servlet;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imooc.entity.Page;
import com.imooc.service.QueryService;

@SuppressWarnings("serial")
public class ListServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//设置编码
		req.setCharacterEncoding("UTF-8");
		//接受页面的值
		String id=req.getParameter("idn");
		String name=req.getParameter("command");
		String description=req.getParameter("description");
		String currentPage=req.getParameter("currentPage");
		//创建分页对象
		Page page=new Page();
		Pattern pattern=Pattern.compile("[0-9]{1,9}");
		if(currentPage==null||!pattern.matcher(currentPage).matches()){
			page.setCurrentPage(1);
		}else{
			page.setCurrentPage(Integer.valueOf(currentPage));
		}
		//查询消息列表并传给页面（业务需要去调用service）
		QueryService listService=new QueryService();
		req.setAttribute("commandList",listService.queryCommandList(id,name, description,page));//里面count了page
		//向页面传值
		req.setAttribute("id", id);
		req.setAttribute("name", name);
		req.setAttribute("description", description);
		req.setAttribute("page", page);
		//向页面跳转
		req.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}
}
