package com.cbs.kuai;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

public class DrawMain extends JFrame {
	
	private Random r;
	private Block b;
	private ArrayList<Block> list;
	
	public DrawMain(){
		r=new Random();
		list=new ArrayList<Block>();
	}
	
	public static void main(String[] args) {
		DrawMain d=new DrawMain();
		d.showUI();
	}
	
	public void showUI(){
		setTitle("Black And White");
		setSize(430,700);
		setDefaultCloseOperation(3);
		setLocationRelativeTo(null);
		getContentPane().setBackground(Color.WHITE);
		setResizable(false);
		setVisible(true);
		
		DrawListener dl=new DrawListener(this,list);
		this.addMouseListener(dl);
		NewBlock nb=new NewBlock(this, list);
		nb.start();
		Thread t=new Thread(dl);
		t.start();
		
	}
	public void paint(Graphics g){
		super.paint(g);
		g.setColor(Color.GREEN);
		g.fillRect(0, 680, 100, 20);
		g.setColor(Color.GREEN);
		g.fillRect(110, 680, 100, 20);
		g.setColor(Color.GREEN);
		g.fillRect(220, 680, 100, 20);
		g.setColor(Color.GREEN);
		g.fillRect(330, 680, 100, 20);
		
		for(int i=0;i<list.size();i++){
			list.get(i).draw();
		}
		
		
		
	}
	
	public void random(){
		
	}

}
