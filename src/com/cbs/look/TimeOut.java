package com.cbs.look;
/**
 * �߳��࣬����ʱ��
 */
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class TimeOut extends Thread {
	private int seconds=90;//��Ϸʱ��
	private JFrame frame;//���������
	private JLabel jl;//����ʱ��ǩ
	private boolean flag = true;// �����߳̽���
	private GameListener gl;//�¼�������

	// �ڱ�����п����̵߳Ĺر�
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public boolean isFlag() {
		return flag;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public TimeOut(JLabel jl, JFrame frame, GameListener gl) {
		this.jl = jl;
		this.frame = frame;
		this.gl = gl;
	}

	@Override
	public void run() {
//		seconds = 90;
		jl.setText(seconds + "");
		while (seconds-- > 0 && flag) {
			jl.setText(seconds + "");
			if (seconds == 0) {
				JOptionPane.showMessageDialog(frame, "������˼��ʱ���ù��ˣ��뿪ʼ����Ϸ");
				frame.removeMouseListener(gl);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
