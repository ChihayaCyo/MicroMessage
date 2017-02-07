package com.imooc.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.imooc.bean.Command;
import com.imooc.db.DBAccess;

public class CommandDao {
	/**
	 * 根据查询条件查询指令列表
	 */
	public List<Command> queryCommandList(String id,String name,String description){
		DBAccess dbAccess=new DBAccess();
		List<Command> commandList=new ArrayList<Command>();
		SqlSession sqlSession = null;
		try {
			sqlSession=dbAccess.getSqlSession();
			Command command=new Command();
			if(id!=null&&!"".equals(id.trim())) 
				command.setId((Integer.valueOf(id)));
			command.setName(name);
			command.setDescription(description);
			//通过sqlSession执行SQL语句
			commandList=sqlSession.selectList("Command.queryCommandList",command);
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
			sqlSession.delete("Command.deleteOne",id);
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
			sqlSession.delete("Command.deleteBatch",ids);
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
	 * 单条新增
	 */
	public void addOne(Command icommand){
		DBAccess dbAccess=new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession=dbAccess.getSqlSession();
			//通过sqlSession执行SQL语句
			sqlSession.insert("Command.addOne",icommand);
			sqlSession.commit();
/*				sqlSession.selectList("Command.selectLastId",icommand);*/
				int commandId=icommand.getId();
				icommand.getContentList().get(0).setCommandId(commandId);
				sqlSession.insert("CommandContent.addOne",icommand.getContentList().get(0));
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
	 * 单条修改
	 */
	public void alterList(Command icommand){
		DBAccess dbAccess=new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession=dbAccess.getSqlSession();
			//通过sqlSession执行SQL语句
			sqlSession.update("Command.alterList",icommand);
			sqlSession.commit();
			/*sqlSession.selectList("Command.selectLastId",icommand);*/
				int commandId=icommand.getId();
				icommand.getContentList().get(0).setCommandId(commandId);
				sqlSession.update("CommandContent.alterList",icommand.getContentList().get(0));
				sqlSession.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(sqlSession!=null){
				sqlSession.close();	
			}
		}
	}
	
	
}
