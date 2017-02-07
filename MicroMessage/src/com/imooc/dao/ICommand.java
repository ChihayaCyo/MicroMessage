package com.imooc.dao;

import java.util.List;

import com.imooc.bean.Command;

/**
 * 与Command配置文件相对应的接口
 */
public interface ICommand {
	/**
	 * 根据查询条件查询指令列表
	 */
	public List<Command> queryCommandList(Command command);
	
	/**
	 * 根据查询条件查询指令列表的条数
	 */
	public int count(Command command);
}
