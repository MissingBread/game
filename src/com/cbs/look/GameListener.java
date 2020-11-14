package com.cbs.look;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.plaf.FontUIResource;
/**
 * �¼�������
 * @author CBS
 */
public class GameListener extends MouseAdapter implements LookConfig,
		ActionListener {
	// ���ڿ�������Ļ�ȡ
	private boolean flag = true;
	private int r1, c1, r2, c2;// ��Ӧ������±�λ��
	private int x1, y1, x2, y2;// �����������
	private int array[][];// ��������
	private JFrame frame;// ���ڻ�ȡ������󣬵���Repaint����
	private Graphics2D g;// ���ʶ���
	JLabel timeJl;// ������ʾʣ��ʱ��
	TimeOut tt ;// ����ʱ�߳���

	private int x;// ���滭��Ķ���x����
	private int y;// ���滭��Ķ���y����

	public TimeOut getTt() {
		return tt;
	}

	public void setTt(TimeOut tt) {
		this.tt = tt;
	}

	public void setTimeJl(JLabel timeJl) {
		this.timeJl = timeJl;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
		g = (Graphics2D) frame.getGraphics();
	}

	public void setArray(int[][] array) {
		this.array = array;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// ��ȡ����
		if (flag) {
			x1 = e.getX() - 40;
			y1 = e.getY() - 50;
			flag = false;
			if (y1 / (size + space) - 1 >= array.length)
				r1 = array.length - 1;
			else if (y1 / (size + space) - 1 < 0)
				r1 = 0;
			else
				r1 = y1 / (size + space) - 1;
			if (x1 / (size + space) >= array[0].length)
				c1 = array[0].length - 1;
			else
				c1 = x1 / (size + space);
			g.setColor(Color.RED);
			g.setStroke(new BasicStroke(5));
			x = space + space + c1 * (size + space) + 40;
			y = size + r1 * (size + space) + 50;
			g.drawRect(x, y, size, size);
		} else {
			x2 = e.getX() - 40;
			y2 = e.getY() - 50;
			flag = true;
			if (y2 / (size + space) - 1 >= array.length)
				r2 = array.length - 1;
			else if (y1 / (size + space) - 1 < 0)
				r1 = 0;
			else
				r2 = y2 / (size + space) - 1;
			if (x2 / (size + space) >= array[0].length)
				c2 = array[0].length - 1;
			else
				c2 = x2 / (size + space);
			g.setColor(Color.RED);
			g.setStroke(new BasicStroke(4));
			x = space + space + c2 * (size + space) + 40;
			y = size + r2 * (size + space) + 50;
			g.drawRect(x, y, size, size);
		}
		GameUtil gu = new GameUtil(this.frame);
		if (array[r1][c1] == array[r2][c2] && flag && !(r1 == r2 && c2 == c1)
				&& (array[r1][c1] != 0 || array[r2][c2] != 0)) {
			if (gu.wuZhe(r1, c1, r2, c2, array)) {
				array[r1][c1] = 0;
				array[r2][c2] = 0;
				g.setColor(Color.PINK);
				g.drawLine(2 * space + size / 2 + c2 * (size + space) + 40,
						size + size / 2 + r2 * (size + space) + 50, 2 * space
								+ size / 2 + c1 * (size + space) + 40, size
								+ size / 2 + r1 * (size + space) + 50);

			} else if (gu.yiZhe(r1, c1, r2, c2, array)) {
				array[r1][c1] = 0;
				array[r2][c2] = 0;
				g.setColor(Color.PINK);
				g.drawLine(2 * space + size / 2 + gu.getPath().get(0).y
						* (size + space) + 40, size + size / 2
						+ gu.getPath().get(0).x * (size + space) + 50, 2
						* space + size / 2 + c1 * (size + space) + 40, size
						+ size / 2 + r1 * (size + space) + 50);
				g.drawLine(2 * space + size / 2 + gu.getPath().get(0).y
						* (size + space) + 40, size + size / 2
						+ gu.getPath().get(0).x * (size + space) + 50, 2
						* space + size / 2 + c2 * (size + space) + 40, size
						+ size / 2 + r2 * (size + space) + 50);

			} else if (gu.erZhe(r1, c1, r2, c2, array)) {
				array[r1][c1] = 0;
				array[r2][c2] = 0;
				g.setColor(Color.PINK);
				g.drawLine(2 * space + size / 2 + gu.getPath().get(1).y
						* (size + space) + 40, size + size / 2
						+ gu.getPath().get(1).x * (size + space) + 50, 2
						* space + size / 2 + c1 * (size + space) + 40, size
						+ size / 2 + r1 * (size + space) + 50);
				// path���±�Ϊһ��λ��Ҫ��һ����Ϊ����������
				g.drawLine(2 * space + size / 2 + (gu.getPath().get(0).y - 1)
						* (size + space) + 40, size + size / 2
						+ (gu.getPath().get(0).x - 1) * (size + space) + 50, 2
						* space + size / 2 + gu.getPath().get(1).y
						* (size + space) + 40, size + size / 2
						+ gu.getPath().get(1).x * (size + space) + 50);
				g.drawLine(2 * space + size / 2 + (gu.getPath().get(0).y - 1)
						* (size + space) + 40, size + size / 2
						+ (gu.getPath().get(0).x - 1) * (size + space) + 50, 2
						* space + size / 2 + c2 * (size + space) + 40, size
						+ size / 2 + r2 * (size + space) + 50);
			}

			//ʵ�������������ػ��ˢ��ʱ��
			Thread t=new Thread();
			try {
				t.sleep(100);
				frame.repaint();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			if (isWin(array)) {
				tt.setFlag(false);
				frame.removeMouseListener(this);
				JOptionPane.showMessageDialog(frame, "��ϲ�㣬"
						+ "��Ӯ�ˣ�����������Ϸ��ʼ��һ��");
				int i = JOptionPane.showConfirmDialog(frame, "�Ƿ��¼�������Ϣ�������а�",
						"���а�", JOptionPane.YES_NO_OPTION);
				if (i == 0) {
					String str = JOptionPane.showInputDialog(frame, "�������������",
							"���а�", JOptionPane.YES_NO_OPTION);
					int time=90-tt.getSeconds();
					User u = new User(str, time);
					GameSave gs = new GameSave();
					gs.save(u);
				}
			}
		}
		//δʵ���������ػ�ȥ������
		if (flag) {
			Thread t=new Thread();
			try {
				t.sleep(100);
				frame.repaint();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	// ��ť��������
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		
		if ("����Ϸ".equals(str)) {
			for (int r = 0; r < array.length; r++)
				for (int c = 0; c < array[r].length; c++)
					if (array[r][c] != 0) {
						array[r][c] = 0;
					}
			if(tt!=null){
				if(tt.isFlag()){
					frame.removeMouseListener(this);
					tt.setFlag(false);
				}
			}
			randomData();
			frame.repaint();
			frame.addMouseListener(this);
			// �����߳�
			tt = new TimeOut(timeJl, frame, this);
			if(!tt.isFlag())
				tt.setFlag(false);
			tt.start();
		}
		if ("���а�".equals(str)) {
			GameSave gs = new GameSave();
			List<User> list = gs.opean();
			for (int i = 0; i < list.size(); i++) {
				int flag = i;
				for (int j = i + 1; j < list.size(); j++) {
					if (list.get(i).getTime() > list.get(j).getTime())
						flag = j;
				}
				if (flag != i) {
					User u1 = list.get(i);
					User u2 = list.get(flag);
					list.set(i, u2);
					list.set(flag, u1);
				}
			}
			JFrame jf = new JFrame();
			jf.setTitle("���а�");
			jf.setDefaultCloseOperation(2);
			jf.setSize(300, 500);
			FlowLayout fl = new FlowLayout(FlowLayout.LEFT);
			jf.setLayout(fl);
			jf.setLocationRelativeTo(null);
			for (int i = 0; i < list.size(); i++) {
				JLabel jl = new JLabel(list.get(i).toString());
				jl.setFont(new FontUIResource("����", Font.BOLD, 20));
				jf.add(jl);
			}
			jf.setVisible(true);
		}
		if("�浵".equals(str)){
			System.out.println(23333);
			GameSave2 gs2=new GameSave2();
			int time=tt.getSeconds();
			CunD c=new CunD(array, time);
			boolean is=gs2.save(c);
			if(is)
				JOptionPane.showMessageDialog(frame, "�浵�ɹ���");
			else
				JOptionPane.showMessageDialog(frame, "�浵ʧ�ܣ�");
		}
	}

	/**
	 * �����������
	 */
	public void randomData() {
		Random random = new Random();
		int r1, r2, c1, c2;
		for (int i = 0; i < array.length * array[0].length / 2; i++) {
			do {
				r1 = random.nextInt(array.length);
				c1 = random.nextInt(array[r1].length);
			} while (array[r1][c1] != 0);
			array[r1][c1] = random.nextInt(num) + 1;
			do {
				r2 = random.nextInt(array.length);
				c2 = random.nextInt(array[r2].length);
			} while (array[r2][c2] != 0);
			array[r2][c2] = array[r1][c1];
		}
	}
	//�������飬�ж���Ӯ
	public boolean isWin(int[][] array) {
		for (int r = 0; r < array.length; r++)
			for (int c = 0; c < array[r].length; c++)
				if (array[r][c] != 0)
					return false;
		return true;
	}
}
