package com.cbs;

import javax.swing.JFrame;

import static java.lang.System.out;
  

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		   String s="abc"; 
//		   		System.out.println(s); 
//		    s=null; 
//		    	System.out.println(s);
//		    s="null"; 
//		    	System.out.println(s);
//		    s=""; 
//		    	System.out.println(s);
//		    s="  "; 
//			     System.out.println(s);
		    
		    
		    JFrame jf=new JFrame();
		    jf.setTitle("’‚ ±±ÍÊ√ ");
		    
		    System.out.println(jf.toString()); 
		    
		
//		    Student saa=null;//sts[i];
//	       out.println(saa.toString());
		
		Student a=new Student();
	         
	         out.println(a);
	          
	        
		   
		 
		   
		    

	}

}


class Student  extends Object{
	public int age;
	public String name;
	
	//over write
//	  public String toString() {
//		   JFrame jf=new JFrame();
//		   jf.setSize(300, 500);
//		   jf.setVisible(true);
//		  return "";
//	  }
	
}


