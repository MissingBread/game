package com.cbs.han;
/**
 * 汉诺塔模拟
 * @author CBS
 *
 */
public class HNT {
	private int n;//盘子的数目
	private String or;//原始组
	private String as;//辅助组
	private String des;//目的组
	static int count;
	
	public static void main(String args[]) {
		// TODO Auto-generated method stub
		HNT h=new HNT();
		h.hanoi(3,"a","b","c");
		System.out.println(count);

	}
	
	public void hanoi(int n,String or,String as,String des){
		
		if(n==1){
			move( or, des);
		}
		else{
			hanoi(n-1,or,des,as);
			move( or, des);
			hanoi(n-1,as,or,des);
			
		}
	}
	
	public void move(String or,String des){
		System.out.println("描述："+or+"------>"+des);
		count++;
	}
	
	
}
