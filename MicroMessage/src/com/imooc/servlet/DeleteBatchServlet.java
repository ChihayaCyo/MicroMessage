package com.imooc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imooc.service.MaintainService;

@SuppressWarnings("serial")
public class DeleteBatchServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//设置编码
		req.setCharacterEncoding("UTF-8");
		//接受页面的值
		String[] ids=req.getParameterValues("id");
		//批量删除（业务需要去调用service）
		if(ids!=null){
			MaintainService maintainService=new MaintainService();
			maintainService.deleteBatch(ids);
		}
		//向页面跳转
		req.getRequestDispatcher("/List.action").forward(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}
}
