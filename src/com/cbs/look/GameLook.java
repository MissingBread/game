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
 * ����������������
 * 
 * @author CBS
 * 
 */
@SuppressWarnings("serial")
public class GameLook extends JFrame implements LookConfig {

	private int[][] array = new int[8][8];// �������ڱ���������Ϣ

	JLabel timeJl;// ������ʾʣ��ʱ��

	public static void main(String[] args) throws InterruptedException {
		GameLook g = new GameLook();
		g.showUI();

	}

	/**
	 * ��ʼ������
	 * 
	 * @throws InterruptedException
	 */
	public void showUI() throws InterruptedException {
		setTitle("������");
		setSize(700, 800);
		setDefaultCloseOperation(3);
		setLocationRelativeTo(null);
		setResizable(true);
		setLayout(null);

		// �������Ϸ��ť
//		ImageIcon start = new ImageIcon("res/start.png");
		JButton startJB = new JButton("����Ϸ");
		startJB.setBounds(30, 700, 100, 40);
//		startJB.setBorderPainted(false);// ���ñ߿�Ϊ��
		startJB.setFocusable(false);
//		startJB.setContentAreaFilled(false);// �������ݿ�
		this.add(startJB);
		// ������а�ť
		JButton save = new JButton("���а�");
		save.setFocusable(false);
		save.setBounds(190, 700, 100, 40);
		this.add(save);

		// ��Ӵ浵��ť
		JButton saveGame = new JButton("�浵");
		saveGame.setFocusable(false);
		saveGame.setBounds(320, 700, 100, 40);
		this.add(saveGame);


		// ���ʣ��ʱ��
		JLabel jl = new JLabel("Time:");
		jl.setFont(new Font("", Font.BOLD, 20));
		jl.setBounds(440, 700, 80, 50);
		this.add(jl);

		// ��ʾʣ��ʱ��
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
		
		int i=JOptionPane.showConfirmDialog(this, "�Ƿ��ȡ�ϴεĴ浵", "����",
				JOptionPane.YES_NO_OPTION);
		if(i==1){
			JOptionPane.showMessageDialog(this, "�밴����Ϸ��ʼ��Ϸ�ɣ�");
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
				JOptionPane.showMessageDialog(this, "��ȡʧ�ܣ�");
			}
		}
		

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		buffPaint(g);
	}

	/**
	 * ʹ��˫���弼�������������
	 * 
	 * @param g����Ļ��ʶ���
	 */
	public void buffPaint(Graphics g) {
		Image i = createImage(space + (size + space) * array[0].length, space
				+ (size + space) * array.length);
		Graphics2D g2d = (Graphics2D) i.getGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		// ���Ʊ�������
		g2d.setColor(new Color(210, 180, 140));
		g2d.fillRoundRect(0, 0, space + (size + space) * array[0].length, space
				+ (size + space) * array.length, arc, arc);
		// ���Ʊ�������
		g2d.setColor(new Color(245, 245, 220));
		for (int r = 0; r < array.length; r++) {
			for (int c = 0; c < array[r].length; c++) {
				g2d.fillRect(space + (size + space) * c, space + (size + space)
						* r, size, size);
			}
		}
		// ����ͼƬ
		g2d.setColor(Color.BLUE);
		g2d.setFont(new Font("����", Font.BOLD, 30));
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
