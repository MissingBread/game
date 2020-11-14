package com.cbs.han;

public class ChengF {
	public static void main(String[] args) {
		ChengF f=new ChengF();
		f.print(9);
		f.FBLQ(1,1);
	}
	
	public void print(int i){
		if(i>0){
			for(int j=1;j<=i;j++){
				System.out.print(j+"*"+i+"="+i*j+"\t");
			}
			System.out.println();
			print(i-1);
		}
	}
	public  void FBLQ(int i,int j){
		
		if((i+j)<100){
			int k=i+j;
			System.out.println(k);
			i=j;
			j=k;
		FBLQ(i,j);
		}
	}
}
