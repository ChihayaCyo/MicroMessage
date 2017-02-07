package com.imooc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.imooc.bean.Command;
import com.imooc.bean.CommandContent;
import com.imooc.dao.CommandDao;
import com.imooc.entity.Page;
import com.imooc.util.Iconst;


public class QueryService {	
	/**
	 * 列表相关的业务功能
	 */
	public List<Command> queryCommandList(String id,String name,String description,Page page){
		//组织指令对象
		Command command=new Command();
		if(id!=null&&!"".equals(id.trim())) 
			command.setId((Integer.valueOf(id)));
		command.setName(name);
		command.setDescription(description);
		CommandDao commandDao=new CommandDao();
		//根据条件查询条数
		int totalNumber = commandDao.count(command);
		//组织分页查询参数
		page.setTotalNumber(totalNumber);
		Map<String,Object> parameter=new HashMap<String,Object>();
		parameter.put("command",command);
		parameter.put("page", page);
		//分页查询返回结果
		return commandDao.queryCommandList(parameter);
	}
	
	/**
	 * 通过指令查询自动回复的内容
	 * @param command 指令
	 * @return 自动回复的内容
	 */
	public String queryByCommand(String name) {
		Command command=new Command();
		command.setName(name);
		Map<String,Object> parameter=new HashMap<String,Object>();
		parameter.put("command",command);
		CommandDao commandDao=new CommandDao();
		List<Command> commandList;
		if(Iconst.HELP_COMMAND.equals(name)) {
			commandList = commandDao.queryCommandList(parameter);//null,null, null
			StringBuilder result = new StringBuilder();
			for(int i = 0; i < commandList.size(); i++) {
				if(i != 0) {
					result.append("<br/>");
				}
				result.append("回复[" + commandList.get(i).getName() + "]可以查看" + commandList.get(i).getDescription());
			}
			return result.toString();
		}
		commandList = commandDao.queryCommandList(parameter);//null,command, null
		if(commandList.size() > 0) {
			List<CommandContent> contentList = commandList.get(0).getContentList();
			//取 [ 0 , contentList.size ) 的整型随机数
			int i=new Random().nextInt(contentList.size());
			return contentList.get(i).getContent();
		}
		return Iconst.NO_MATCHING_CONTENT;
	}


}
