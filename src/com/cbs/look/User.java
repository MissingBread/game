package com.cbs.look;

/**
 * 记录排行榜的用户信息的类
 * 
 * @author CBS
 * 
 */
public class User {
	
	private String name;//用户名
	
	private int time;//记录用户通过所用时间

	public User() {
	}

	public User(String name, int time) {
		this.name = name;
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	@Override
	public String toString() {
		String str=name+"用时为："+time;
		return str;
	}
	
	
}
