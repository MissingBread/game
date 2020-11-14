package com.cbs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GameListener extends KeyAdapter implements ActionListener,Draw2048 {

	private Game2048 game2048;
	private int[][] arry,copyArry=new int[Draw2048.r][Draw2048.c];
	private int count = 0, max = 0, score = 0,tempScore=0;
	private JLabel jl;
	private JButton startJB, backJB;

	public GameListener(Game2048 game2048, int[][] arry, JLabel jl,
			JButton startJB, JButton backJB) {
		this.game2048 = game2048;
		this.arry = arry;
		this.jl = jl;
		this.startJB = startJB;
		this.backJB = backJB;
	}

	@Override
	public void keyReleased(KeyEvent e) {
//		�ӣ�����壮����������������
		
		
		//��¼�ƶ�ǰ����
		tempScore=score;
		//��¼�ƶ�ǰ����
		for (int r = 0; r < arry.length; r++)
			for (int c = 0; c < arry[r].length; c++)
				copyArry[r][c]=arry[r][c];
		int key = e.getKeyCode();
		switch (key) {
		case 37:// ����
			for (int r = 0; r < arry.length; r++)
				for (int c = 0; c < arry[r].length; c++)
					if (arry[r][c] != 0)
						for (int c1 = c + 1; c1 < arry[r].length; c1++)
							if (arry[r][c] == arry[r][c1]) {
								arry[r][c] *= 2;
								score += arry[r][c];
								arry[r][c1] = 0;
								count++;
								break;
							} else if (arry[r][c1] != 0) {
								break;
							}
			for (int r = 0; r < arry.length; r++) 
				for (int c = 0; c < arry[r].length; c++) {
					if (arry[r][c] > max)
						max = arry[r][c];
					if (arry[r][c] == 0) {
						for (int c1 = c + 1; c1 < arry[r].length; c1++) {
							if (arry[r][c1] != 0) {
								arry[r][c] = arry[r][c1];
								arry[r][c1] = 0;
								count++;
								break;
							}
						}
					}
				}
			break;
		case 38:// ����
			//���ϵ�����㷨
			for (int c = 0; c < arry[0].length; c++) 
				for (int r = 0; r < arry.length; r++) 
					if (arry[r][c] != 0)
						for (int r1 = r + 1; r1 < arry[c].length; r1++) {
							if (arry[r][c] == arry[r1][c]) {
								arry[r][c] *= 2;
								score += arry[r][c];
								arry[r1][c] = 0;
								count++;
								break;
							} else if (arry[r1][c] != 0)
								break;
							}
			for (int c = 0; c < arry[0].length; c++) 
				for (int r = 0; r < arry.length; r++){
					if (arry[r][c] > max)//�ҳ��������ֵ�������ж���ҵķ����Ƿ�ﵽ2048
						max = arry[r][c];
					if (arry[r][c] == 0) {
						for (int r1 = r + 1; r1 < arry[c].length; r1++) 
							if (arry[r1][c] != 0) {
								arry[r][c] = arry[r1][c];
								arry[r1][c] = 0;
								count++;
								break;
							}
					}
				}
			break;
		case 39:// ����
			for (int r = 0; r < arry.length; r++) {
				for (int c = arry[r].length - 1; c >= 0; c--) {
					if (arry[r][c] != 0)
						for (int c1 = c - 1; c1 >= 0; c1--) {
							if (arry[r][c] == arry[r][c1]) {
								arry[r][c] *= 2;
								score += arry[r][c];
								arry[r][c1] = 0;
								count++;
								break;
							} else if (arry[r][c1] != 0)
								break;
						}
				}
			}
			for (int r = 0; r < arry.length; r++) {
				for (int c = arry[r].length - 1; c >= 0; c--) {
					if (arry[r][c] > max)
						max = arry[r][c];
					if (arry[r][c] == 0) {
						for (int c1 = c - 1; c1 >= 0; c1--) {
							if (arry[r][c1] != 0) {
								arry[r][c] = arry[r][c1];
								arry[r][c1] = 0;
								count++;
								break;
							}
						}
					}
				}
			}
			break;

		case 40:// ����
			for (int c = 0; c < arry[0].length; c++) {// ע������һ��ѭ�����Ⱥ����
				for (int r = arry.length - 1; r >= 0; r--) {
					if (arry[r][c] > max)
						max = arry[r][c];
					if (arry[r][c] != 0)
						for (int r1 = r - 1; r1 >= 0; r1--) {
							if (arry[r][c] == arry[r1][c]) {
								arry[r][c] *= 2;
								score += arry[r][c];
								arry[r1][c] = 0;
								count++;
								break;// ע�����break����һ��break�Ŀ���ѭ��Ч��
							} else if (arry[r1][c] != 0)
								break;
						}
				}
			}
			for (int c = 0; c < arry[0].length; c++) 
				for (int r = arry.length - 1; r >= 0; r--) 
					if (arry[r][c] == 0) 
						for (int r1 = r - 1; r1 >= 0; r1--) 
							if (arry[r1][c] != 0) {
								arry[r][c] = arry[r1][c];
								arry[r1][c] = 0;
								count++;
								System.out.println("count:��"+count);
								break;
							}
			break;
		}
		if (count == 0 && isOut(arry)) {
			game2048.paint(game2048.getGraphics());
			JOptionPane.showMessageDialog(game2048, "������˼������!\n�������յ÷�Ϊ��"
					+ score);
		} else if (count > 0) {
			Rand();
			jl.setText("������" + score);
			game2048.repaint();
			count = 0;
			if (max >= 2048) {
				game2048.paint(game2048.getGraphics());
				JOptionPane.showMessageDialog(game2048, "��ϲ��Ӯ��!\n�������յ÷�Ϊ��"
						+ score);
			}
		}
	}

	// ������������������
	public void Rand() {
		Random random = new Random();
		int r, c;
		do {
			r = random.nextInt(arry.length);
			c = random.nextInt(arry[r].length);
		} while (arry[r][c] != 0);
		arry[r][c] = random.nextInt(2) == 0 ? 2 : 4;
	}

	// �ж������Ƿ��Ѿ���
	public boolean isOut(int[][] arry) {
		int sign = 0;
		for (int i = 0; i < arry.length; i++)
			for (int j = 0; j < arry[i].length; j++) {
				if (arry[i][j] == 0)
					sign++;
			}
		if (sign == 0)
			return true;
		else
			return false;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startJB) {
			game2048.rand();
			game2048.repaint();
		}
		if (e.getSource() == backJB) {
			//����ƶ�ǰ����
			score=tempScore;
			jl.setText("������" + score);
			//����ƶ�ǰ����
			for (int r = 0; r < arry.length; r++)
				for (int c = 0; c < arry[r].length; c++)
					arry[r][c]=copyArry[r][c];
			game2048.repaint();
		}
	}

}
