package com.cbs.look;

/**
 * ��¼���а���û���Ϣ����
 * 
 * @author CBS
 * 
 */
public class User {
	
	private String name;//�û���
	
	private int time;//��¼�û�ͨ������ʱ��

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
		String str=name+"��ʱΪ��"+time;
		return str;
	}
	
	
}
