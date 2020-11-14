package com.cbs.kuai;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

public class NewBlock extends Thread{
	
	private Block b;
	private JFrame jf;
	private ArrayList<Block> list;
	private int speed=10;
	private int count;
	
	
	public NewBlock() {
	}


	public NewBlock(JFrame jf, ArrayList<Block> list) {
		this.jf = jf;
		this.list = list;
	}



	public void run(){
		while(true){
			speed++;
			Random r=new Random();
			int key=r.nextInt(4);
			switch (key) {
			case 0:
				b=new Block(jf, 100, 100, 0, 0, speed);
				list.add(b);
				count++;
				break;
			case 1:
				b=new Block(jf, 100, 100, 110, 0, speed);
				list.add(b);
				count++;
				break;
			case 2:
				b=new Block(jf, 100, 100, 220, 0, speed);
				list.add(b);
				count++;
				break;
			case 3:
				b=new Block(jf, 100, 100, 330, 0, speed);
				list.add(b);
				count++;
				break;
		
			}
			try {
				this.sleep(1100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
