package com.cbs.kuai;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class Block {
	
	private Graphics g2d;//画笔坐标
	private int width;//方块的宽
	private int height;//方块的宽
	private JFrame frame;//窗体对象
	private int x,y,movey;
	
	public Block(){
		
	}
	public Block(JFrame frame,int width,int height,int x,int y,int movey){
		this.frame=frame;
		this.width=width;
		this.height=height;
		this.g2d=frame.getGraphics();
		this.x=x;
		this.y=y;
		this.movey =movey;
	}
	
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void draw(){
		
		
		
		g2d.setColor(frame.getBackground());
		g2d.fillRect(x, y, width, height);
		y += movey;
		g2d.setColor(Color.BLACK);
		g2d.fillRect(x, y, width, height);
	}

}
