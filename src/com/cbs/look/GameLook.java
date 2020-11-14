package com.cbs.look;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * 连连看的主界面类
 * 
 * @author CBS
 * 
 */
@SuppressWarnings("serial")
public class GameLook extends JFrame implements LookConfig {

	private int[][] array = new int[8][8];// 数组用于保存界面的信息

	JLabel timeJl;// 用于显示剩余时间

	public static void main(String[] args) throws InterruptedException {
		GameLook g = new GameLook();
		g.showUI();

	}

	/**
	 * 初始化界面
	 * 
	 * @throws InterruptedException
	 */
	public void showUI() throws InterruptedException {
		setTitle("连连看");
		setSize(700, 800);
		setDefaultCloseOperation(3);
		setLocationRelativeTo(null);
		setResizable(true);
		setLayout(null);

		// 添加新游戏按钮
//		ImageIcon start = new ImageIcon("res/start.png");
		JButton startJB = new JButton("新游戏");
		startJB.setBounds(30, 700, 100, 40);
//		startJB.setBorderPainted(false);// 设置边框为空
		startJB.setFocusable(false);
//		startJB.setContentAreaFilled(false);// 设置内容空
		this.add(startJB);
		// 添加排行榜按钮
		JButton save = new JButton("排行榜");
		save.setFocusable(false);
		save.setBounds(190, 700, 100, 40);
		this.add(save);

		// 添加存档按钮
		JButton saveGame = new JButton("存档");
		saveGame.setFocusable(false);
		saveGame.setBounds(320, 700, 100, 40);
		this.add(saveGame);


		// 添加剩余时间
		JLabel jl = new JLabel("Time:");
		jl.setFont(new Font("", Font.BOLD, 20));
		jl.setBounds(440, 700, 80, 50);
		this.add(jl);

		// 显示剩余时间
		timeJl = new JLabel("90");
		timeJl.setFont(new Font("", Font.BOLD, 20));
		timeJl.setBounds(520, 700, 80, 50);
		this.add(timeJl);

		setVisible(true);

		GameListener gl = new GameListener();
		gl.setFrame(this);
		gl.setTimeJl(timeJl);
		gl.setArray(array);
		saveGame.addActionListener(gl);
		startJB.addActionListener(gl);
		save.addActionListener(gl);
		
		int i=JOptionPane.showConfirmDialog(this, "是否读取上次的存档", "读档",
				JOptionPane.YES_NO_OPTION);
		if(i==1){
			JOptionPane.showMessageDialog(this, "请按新游戏开始游戏吧！");
		}else{
			GameSave2 gs2=new GameSave2();
			CunD c=gs2.opean();
			if(c!=null){
				array=c.getArray();
				gl.setArray(array);
				this.addMouseListener(gl);
				this.repaint();
				TimeOut tt =new TimeOut(timeJl, this, gl);
				gl.setTt(tt);
				tt.setSeconds(c.getTime());
				tt.start();
			}else{
				JOptionPane.showMessageDialog(this, "读取失败！");
			}
		}
		

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		buffPaint(g);
	}

	/**
	 * 使用双缓冲技术解决闪屏问题
	 * 
	 * @param g传入的画笔对象
	 */
	public void buffPaint(Graphics g) {
		Image i = createImage(space + (size + space) * array[0].length, space
				+ (size + space) * array.length);
		Graphics2D g2d = (Graphics2D) i.getGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		// 绘制背景矩形
		g2d.setColor(new Color(210, 180, 140));
		g2d.fillRoundRect(0, 0, space + (size + space) * array[0].length, space
				+ (size + space) * array.length, arc, arc);
		// 绘制背景方格
		g2d.setColor(new Color(245, 245, 220));
		for (int r = 0; r < array.length; r++) {
			for (int c = 0; c < array[r].length; c++) {
				g2d.fillRect(space + (size + space) * c, space + (size + space)
						* r, size, size);
			}
		}
		// 绘制图片
		g2d.setColor(Color.BLUE);
		g2d.setFont(new Font("宋体", Font.BOLD, 30));
		for (int r = 0; r < array.length; r++) {
			for (int c = 0; c < array[r].length; c++) {
				if (array[r][c] != 0) {
					ImageIcon icon = new ImageIcon("res/" + array[r][c]
							+ ".jpg");
					Image image = icon.getImage();
					g2d.drawImage(image, space + (size + space) * c, space
							+ (size + space) * r, size, size, null);
				}
			}
		}
		g.drawImage(i, x, y, this);
	}
}
