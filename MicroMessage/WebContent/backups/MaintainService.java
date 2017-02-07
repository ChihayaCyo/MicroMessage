/**
 * service层：接受servlet传过来的值，对这个值进行处理，做业务的操作、算法等，如果有需要则调用相应的dao层
 */
package com.imooc.service;

import java.util.ArrayList;
import java.util.List;

import com.imooc.dao.MessageDao;

/**
 * 维护相关的功能
 */
public class MaintainService {
	/**
	 * 单条删除
	 */
	public void deleteOne(String id){
		if(id!=null&&!"".equals(id.trim())){
			MessageDao messageDao=new MessageDao();
			messageDao.deleteOne(Integer.valueOf(id));
		}
	}
	
	/**
	 * 批量删除
	 */
	public void deleteBatch(String[] ids){
		MessageDao messageDao=new MessageDao();
		List<Integer> idList=new ArrayList<Integer>();
		for(String id:ids){
			idList.add(Integer.valueOf(id));
		}
		messageDao.deleteBatch(idList);
	}
}
