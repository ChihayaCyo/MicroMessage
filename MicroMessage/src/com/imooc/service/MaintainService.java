/**
 * service层：接受servlet传过来的值，对这个值进行处理，做业务的操作、算法等，如果有需要则调用相应的dao层
 */
package com.imooc.service;

import java.util.ArrayList;
import java.util.List;

import com.imooc.bean.Command;
import com.imooc.bean.CommandContent;
import com.imooc.dao.CommandDao;

/**
 * 维护相关的功能
 */
public class MaintainService {
	/**
	 * 单条删除
	 */
	public void deleteOne(String id){
		if(id!=null&&!"".equals(id.trim())){
			CommandDao commandDao=new CommandDao();
			commandDao.deleteOne(Integer.valueOf(id));
		}
	}
	
	/**
	 * 批量删除
	 */
	public void deleteBatch(String[] ids){
		CommandDao commandDao=new CommandDao();
		List<Integer> idList=new ArrayList<Integer>();
		for(String id:ids){
			idList.add(Integer.valueOf(id));
		}
		commandDao.deleteBatch(idList);
	}
	
	/**
	 * 单条新增
	 */
	public void addOne(String name,String description,String[] contents){
		
		if((name!=null&&!"".equals(name.trim()))||(description!=null&&!"".equals(description.trim()))||(contents!=null)){
			CommandDao commandDao=new CommandDao();
			Command icommand=new Command();
			icommand.setName(name);
			icommand.setDescription(description);
				List<CommandContent> contentList=new ArrayList<CommandContent>();	
				int i=0;
				for(String content:contents){
					CommandContent commandContent=new CommandContent();
					commandContent.setContent(content);
					contentList.add(i, commandContent);
					i++;
				}
			icommand.setContentList(contentList);
			commandDao.addOne(icommand);
		}
	}
	
	/**
	 * 单条修改
	 */
	public void alterList(String id,String name,String description,String[] contents){
			CommandDao commandDao=new CommandDao();
			Command icommand = new Command();
			icommand.setId(Integer.valueOf(id));
			icommand.setName(name);
			icommand.setDescription(description);
				List<CommandContent> contentList=new ArrayList<CommandContent>();	
				int i=0;
				for(String content:contents){
					CommandContent commandContent=new CommandContent();
					commandContent.setContent(content);
					contentList.add(i, commandContent);
					i++;
				}
			icommand.setContentList(contentList);
			commandDao.alterList(icommand);

	}
	
	
}
