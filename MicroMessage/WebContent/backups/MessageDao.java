/**
 * dao层：完成与数据库的交互，执行相应的sql语句
 */
package com.imooc.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.imooc.bean.Message;
import com.imooc.db.DBAccess;

/**
 * 和message表相关的数据库操作
 */
public class MessageDao {
	
	/**
	 * 根据查询条件查询消息列表
	 */
	public List<Message> queryMessageList(String command,String description){
		DBAccess dbAccess=new DBAccess();
		List<Message> messageList=new ArrayList<Message>();
		SqlSession sqlSession = null;
		try {
			sqlSession=dbAccess.getSqlSession();
			Message message=new Message();
			message.setCommand(command);
			message.setDescription(description);
			//通过sqlSession执行SQL语句
			messageList=sqlSession.selectList("Message.queryMessageList",message);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(sqlSession!=null){
				sqlSession.close();	
			}
		}
		return messageList;
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
			sqlSession.delete("Message.deleteOne",id);
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
			sqlSession.delete("Message.deleteBatch",ids);
			sqlSession.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(sqlSession!=null){
				sqlSession.close();	
			}
		}
	}
	
	public static void main(String[] args) {
		MessageDao messageDao=new MessageDao();
		messageDao.queryMessageList("", "");
	}
	

	/*public List<Message> queryMessageList(String command,String description){
		List<Message> messageList=new ArrayList<Message>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/micro_message","root","");
			StringBuilder sql=new StringBuilder("select id,command,description,content from message where 1=1");
			List<String> paramList=new ArrayList<String>();
			if(command!=null && !"".equals(command.trim())){
				sql.append(" and command=?");
				paramList.add(command);
			}
			if(description!=null && !"".equals(description.trim())){
				sql.append(" and description like '%' ? '%'");
				paramList.add(description);
			}
			PreparedStatement statement=conn.prepareStatement(sql.toString());
			//?
			for(int i=0;i<paramList.size();i++){
				statement.setString(i+1,paramList.get(i));
			}
			ResultSet rs=statement.executeQuery();
			while(rs.next()){
				Message message=new Message();
				messageList.add(message);
				message.setId(rs.getString("id"));
				message.setCommand(rs.getString("command"));
				message.setDescription(rs.getString("description"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		return messageList;
	}*/
}
