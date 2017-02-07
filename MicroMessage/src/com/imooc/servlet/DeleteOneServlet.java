/**
 * servlet层：负责接受页面的值、向页面传值、如果有业务需要处理则调用相应service
 */
package com.imooc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imooc.service.MaintainService;

/**
 * 单条删除控制层
 */
@SuppressWarnings("serial")
public class DeleteOneServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//设置编码
		req.setCharacterEncoding("UTF-8");
		//接受页面的值
		String id=req.getParameter("id");
		//单条删除（业务需要去调用service）
		MaintainService maintainService=new MaintainService();
		maintainService.deleteOne(id);
		//向页面跳转
		req.getRequestDispatcher("/List.action").forward(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}
}
