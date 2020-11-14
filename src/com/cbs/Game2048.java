package com.cbs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Game2048 extends JFrame implements Draw2048 {

	private int arry[][] = new int[Draw2048.r][Draw2048.c];
	private JLabel  jl2;
	private JButton startJB,backJB;

	public static void main(String[] args) {
		Game2048 game = new Game2048();
		game.initUI();
	}

	private void initUI() {
		setTitle("2048");
		setDefaultCloseOperation(3);
		setSize(600, 700);
		setLocationRelativeTo(null);
		this.setLayout(null);
		//添加分数
		jl2 = new JLabel("分数：0");
		jl2.setFont(new Font("黑体", Font.BOLD, 30));
		jl2.setBounds(20, 30, 200, 50);
		this.add(jl2);
		//添加开始按钮
		ImageIcon start=new ImageIcon("res/start.png");
		startJB=new JButton(start);
		startJB.setBounds(280, 40, 120, 30);
		startJB.setFocusable(false);
		startJB.setBorderPainted(false);//设置按钮的边框为空
		startJB.setFocusPainted(false);
		startJB.setContentAreaFilled(false);//设置按钮的边框内填充颜色
		
		//添加退一步按钮
		ImageIcon back=new ImageIcon("res/backicon.png");
		backJB=new JButton(back);
		backJB.setBounds(430, 40, 120, 30);
		backJB.setFocusable(false);
		backJB.setBorderPainted(false);
		backJB.setFocusPainted(false);
		backJB.setContentAreaFilled(false);
		
		this.add(startJB);
		this.add(backJB);
		setVisible(true);
		GameListener gl = new GameListener(this, arry, jl2,startJB,backJB);
		JFrame jf=new JFrame();
       jf.addKeyListener(gl);
//		ｓｙｓｔｅｍ．ｏｕｔ．ｐｒｉｎｔｌｎ（ａａａ＂）；
		
		startJB.addActionListener(gl);
		backJB.addActionListener(gl);
	}

	// 生成初始随机数
	public void rand() {
		int r1, c1, r2, c2;
		Random random = new Random();
		r1 = random.nextInt(arry.length);
		c1 = random.nextInt(arry[r1].length);
		do {
			r2 = random.nextInt(arry.length);
			c2 = random.nextInt(arry[r2].length);
		} while (c1 == c2 && r1 == r2);

		arry[r1][c1] = random.nextInt(2) == 0 ? 2 : 4;
		arry[r2][c2] = random.nextInt(2) == 0 ? 2 : 4;
	}

	// paint方法在窗体的大小改变或者最小化时会调用，如果不改变窗体的状态仍想要变化则调用repaint方法
	public void paint(Graphics g) {
		super.paint(g);
		buffPaint(g);
	}

	public void buffPaint(Graphics g) {
		Image image = createImage(600, 600);
		Graphics g2 = image.getGraphics();
		g2.setColor(Color.LIGHT_GRAY);
		Graphics2D g2d = (Graphics2D) g2;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.fillRoundRect(0, 0, Draw2048.RD, Draw2048.RD, 20, 20);
		g2.setColor(Color.GRAY);
		for (int r = 0; r < arry.length; r++) {
			for (int c = 0; c < arry[r].length; c++) {
				g2.fillRect(20 + c * (Draw2048.D + Draw2048.space), 20 + r
						* (Draw2048.D + Draw2048.space), Draw2048.D, Draw2048.D);
			}
		}
		for (int r = 0; r < arry.length; r++) {
			for (int c = 0; c < arry[r].length; c++) {
				if (arry[r][c] != 0) {
					if (arry[r][c] == 2)
						g2.setColor(new Color(255, 250, 240));
					if (arry[r][c] == 4)
						g2.setColor(new Color(253, 245, 230));
					if (arry[r][c] == 8)
						g2.setColor(new Color(250, 240, 230));
					if (arry[r][c] == 16)
						g2.setColor(new Color(250, 235, 215));
					if (arry[r][c] == 32)
						g2.setColor(new Color(255, 239, 213));
					if (arry[r][c] == 64)
						g2.setColor(new Color(255, 235, 205));
					if (arry[r][c] == 128)
						g2.setColor(new Color(255, 228, 196));
					if (arry[r][c] == 256)
						g2.setColor(new Color(255, 218, 185));
					if (arry[r][c] == 512)
						g2.setColor(new Color(255, 215, 0));
					if (arry[r][c] == 1024)
						g2.setColor(new Color(218, 165, 32));
					if (arry[r][c] == 2048)
						g2.setColor(new Color(205, 92, 92));
					g2.fillRect(20 + c * (Draw2048.D + Draw2048.space), 20 + r
							* (Draw2048.D + Draw2048.space), Draw2048.D,
							Draw2048.D);
					g2.setColor(new Color(0, 191, 255));
					g2.setFont(new Font("楷体", Font.BOLD, Draw2048.FSIZE));
					g2.drawString(arry[r][c] + "", 50 + c
							* (Draw2048.D + Draw2048.space), 90 + r
							* (Draw2048.D + Draw2048.space));
				}
			}
		}
		g.drawImage(image, 50, 150, this);
	}

}
