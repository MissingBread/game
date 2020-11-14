package com.cbs.kuai;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

public class DrawListener extends MouseAdapter implements Runnable{
	
	private Graphics g;//ª≠± ∂‘œÛ
	int x,y;
	private JFrame jf;
	private ArrayList<Block> list;
	
	public DrawListener(){
		
	}
	public DrawListener(JFrame jf,ArrayList<Block> list){
		this.jf=jf;
		this.list=list;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		x=e.getX();
		y=e.getY();
		for(int i=0;i<list.size();i++){
			Block b=list.get(i);
			if(x<=100 && x>=0 && y>=b.getY() && y<=b.getY()+b.getHeight()){
				list.remove(i);
			}
			else if(x<=210 && x>=110 && y>=b.getY() && y<=b.getY()+b.getHeight())
				list.remove(i);
			else if(x<=320 && x>=220 && y>=b.getY() && y<=b.getY()+b.getHeight())
				list.remove(i);
			else if(x<=430 && x>=330 && y>=b.getY() && y<=b.getY()+b.getHeight())
				list.remove(i);
		}
		
		
		
	}
	//’≈Œ¨”≠£¨—¶’◊∑·
	public void run() {
		while(true){
			jf.repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	

}
