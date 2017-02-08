package com.imooc.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.imooc.bean.Command;
import com.imooc.bean.CommandContent;
import com.imooc.db.DBAccess;

public class CommandDao {
	/**
	 * 根据查询条件查询指令列表
	 */
	public List<Command> queryCommandList(Map<String,Object> parameter){
		DBAccess dbAccess=new DBAccess();
		List<Command> commandList=new ArrayList<Command>();
		SqlSession sqlSession = null;
		try {
			sqlSession=dbAccess.getSqlSession();
			//通过sqlSession执行SQL语句
			commandList=sqlSession.selectList("com.imooc.dao.ICommand.queryCommandList",parameter);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(sqlSession!=null){
				sqlSession.close();	
			}
		}
		return commandList;
	}
	
	public List<Command> queryCommandAll(Command command){
		DBAccess dbAccess=new DBAccess();
		List<Command> commandList=new ArrayList<Command>();
		SqlSession sqlSession = null;
		try {
			sqlSession=dbAccess.getSqlSession();
			//通过sqlSession执行SQL语句
			commandList=sqlSession.selectList("com.imooc.dao.ICommand.queryCommandAll",command);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(sqlSession!=null){
				sqlSession.close();	
			}
		}
		return commandList;
	}
	
	/**
	 * 单条删除
	 */
	public void deleteOne(int id){
		DBAccess dbAccess=new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession=dbAccess.getSqlSession();
			//通过sqlSession执行SQL语句
			sqlSession.delete("com.imooc.dao.ICommand.deleteOne",id);
			sqlSession.commit();
			sqlSession.delete("CommandContent.deleteOne",id);
			sqlSession.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(sqlSession!=null){
				sqlSession.close();	
			}
		}
	}
	
	/**
	 * 批量删除
	 */
	public void deleteBatch(List<Integer> ids){
		DBAccess dbAccess=new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession=dbAccess.getSqlSession();
			//通过sqlSession执行SQL语句
			sqlSession.delete("com.imooc.dao.ICommand.deleteBatch",ids);
			sqlSession.commit();
			sqlSession.delete("CommandContent.deleteBatch",ids);
			sqlSession.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(sqlSession!=null){
				sqlSession.close();	
			}
		}
	}
	
	/**
	 * 新增
	 */
	public void addOne(Command icommand){
		DBAccess dbAccess=new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession=dbAccess.getSqlSession();
			//通过sqlSession执行SQL语句
			sqlSession.insert("com.imooc.dao.ICommand.addOne",icommand);
			sqlSession.commit();		
				int commandId=icommand.getId();		
				int i=0;
				for(CommandContent content:icommand.getContentList()){
					icommand.getContentList().get(i).setCommandId(commandId);
					sqlSession.insert("CommandContent.addOne",content);
					sqlSession.commit();
					i++;
				}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(sqlSession!=null){
				sqlSession.close();	
			}
		}
	}
	
	/**
	 * 单条修改
	 */
	public void alterList(Command icommand){
		DBAccess dbAccess=new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession=dbAccess.getSqlSession();
			//通过sqlSession执行SQL语句
			sqlSession.update("com.imooc.dao.ICommand.alterList",icommand);
			sqlSession.commit();
				int commandId=icommand.getId();			
				//得到contents所对应的command_content表里的id值
				List<CommandContent> contentListId=new ArrayList<CommandContent>();
				contentListId=sqlSession.selectList("CommandContent.queryCommandContentList",commandId);
				int i=0;
				for(CommandContent content:icommand.getContentList()){
					content.setCommandId(commandId);	
					content.setId(contentListId.get(i).getId());
					sqlSession.update("CommandContent.alterList",content);
					sqlSession.commit();
					i++;
				}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(sqlSession!=null){
				sqlSession.close();	
			}
		}
	}
	
	
	/**
	 * 根据条件查询条数
	 */
	public int count(Command command){
		DBAccess dbAccess=new DBAccess();
		SqlSession sqlSession=null;
		int result=0;
		try {
			sqlSession = dbAccess.getSqlSession();
			//通过sqlSession执行SQL语句
			ICommand icommand=sqlSession.getMapper(ICommand.class);
			result=icommand.count(command);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
		return result;
	}
	
}
