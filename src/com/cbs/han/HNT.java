package com.cbs.han;
/**
 * ��ŵ��ģ��
 * @author CBS
 *
 */
public class HNT {
	private int n;//���ӵ���Ŀ
	private String or;//ԭʼ��
	private String as;//������
	private String des;//Ŀ����
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
		System.out.println("������"+or+"------>"+des);
		count++;
	}
	
	
}
