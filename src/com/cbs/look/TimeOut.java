package com.cbs.look;
/**
 * 线程类，控制时间
 */
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class TimeOut extends Thread {
	private int seconds=90;//游戏时间
	private JFrame frame;//主窗体对象
	private JLabel jl;//倒计时标签
	private boolean flag = true;// 控制线程结束
	private GameListener gl;//事件处理类

	// 在别的类中控制线程的关闭
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
				JOptionPane.showMessageDialog(frame, "不好意思，时间用光了，请开始新游戏");
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
