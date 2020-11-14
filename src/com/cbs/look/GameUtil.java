package com.cbs.look;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

/**
 * �����㷨
 * �ж����������Ƿ���ͨ
 * @author CBS
 * 
 */
public class GameUtil implements LookConfig {
	//path��Ҫ�Ǽ�¼�������������λ�ã�Ϊ�˷���ʵ�����ߵĹ���
	private List<Point> path=new ArrayList<Point>();
	

	public List<Point> getPath() {
		return path;
	}

	public GameUtil(JFrame frame) {
	}

	/**
	 * �����㷨,���۵������Ҫôͬ�У��ж����Ƿ���ͨ��Ҫôͬ���ж����Ƿ���ͨ
	 * 
	 * @param r1��һ���������±�
	 * @param c1��һ���������±�
	 * @param r2�ڶ����������±�
	 * @param c2�ڶ����������±�
	 * @param array���ڱ����������Ϣ
	 * @return ����ܹ���ͨ����TRUE��or����FALSE
	 */
	public boolean wuZhe(int r1, int c1, int r2, int c2, int[][] array) {

		if (r1 != r2 && c1 != c2)
			return false;
		// ��������x������ȣ�����ˮƽ������ɨ��
		if (r1 == r2) {
			if (c1 == c2 - 1 || c2 == c1 - 1)// ������
				return true;
			for (int i = Math.min(c1, c2) + 1; i < Math.max(c2, c1); i++)
				if (array[r1][i] != 0)
					return false;
		}
		// ��������y������ȣ�������ֱ������ɨ��
		else if (c1 == c2) {
			if (r1 == r2 - 1 || r2 == r1 - 1)// ������
				return true;
			for (int i = Math.min(r1, r2) + 1; i < Math.max(r2, r1); i++)
				if (array[i][c1] != 0)
					return false;
		}
		return true;
	}

	/**
	 * һ���㷨����������������£���ֻ��Ҫ�ж϶Խǵ�r1,c2��r2,c1�������Ƿ���ͨ
	 * 
	 * @param r1��һ���������±�
	 * @param c1��һ���������±�
	 * @param r2�ڶ����������±�
	 * @param c2�ڶ����������±�
	 * @param array ���ڱ����������Ϣ
	 * @return ����ܹ���ͨ����TRUE��or����FALSE
	 */
	public boolean yiZhe(int r1, int c1, int r2, int c2, int[][] array) {

		// �������0�۵������ֱ�ӷ���FALSE
		if (r1 == r2 || c1 == c2)
			return false;
		// ���ԶԽǵ�1
		if (array[r1][c2] == 0) {
			boolean test1 = wuZhe(r1, c1, r1, c2, array);
			boolean test2 = test1 ? wuZhe(r1, c2, r2, c2, array) : test1;
			if (test1 && test2){
				path.add(new Point(r1,c2));
				return true;
			}
		}
		// ���ԶԽǵ�2
		if (array[r2][c1] == 0) {
			boolean test1 = wuZhe(r1, c1, r2, c1, array);
			boolean test2 = test1 ? wuZhe(r2, c1, r2, c2, array) : test1;
			if (test1 && test2){
				path.add(new Point(r2,c1));
				return true;
			}
		}
		return false;
	}

	/**
	 * �����㷨
	 * 
	 * @param r1��һ���������±�
	 * @param c1��һ���������±�
	 * @param r2�ڶ����������±�
	 * @param c2�ڶ����������±�
	 * @param array���ڱ����������Ϣ
	 * @return ����ܹ���ͨ����TRUE��or����FALSE
	 */
	public boolean erZhe(int r1, int c1, int r2, int c2, int[][] array) {
		//��ԭ������Ļ���������һȦ�������жϱ߽�ķ���
		int[][] newArray = new int[array.length + 2][array[0].length + 2];
		for (int r = 0; r < array.length; r++) {
			for (int c = 0; c < array[r].length; c++) {
				newArray[r + 1][c + 1] = array[r][c];
			}
		}
		
		//�ж��Ƿ��������
		
		// ���´�ֱ����
		for (int i = r1 + 2; i < newArray.length; i++) {
			if (newArray[i][c1+1] == 0 ) {
				if(yiZhe(r2+1, c2+1, i, c1+1, newArray)){
					path.add(new Point(i-1, c1));
					return true;
				}
			}else
				break;
		}
		// ���ϴ�ֱ����
		for (int i = r1 ; i > -1; i--) {
			if (newArray[i][c1+1] == 0  ){
				if(yiZhe(i, c1+1, r2+1, c2+1, newArray)){
					path.add(new Point(i-1, c1));
					return true;
				}
			}else
				break;
			
		}
		// ����ˮƽ����
		for (int i = c1 + 2; i < newArray[0].length; i++) {
			if (newArray[r1+1][i] == 0 ){
				if( yiZhe(r2+1, c2+1, r1+1, i, newArray)){
					path.add(new Point(r1,i-1));
					return true;
				}
			}else
				break;
			
		}
		// ����ˮƽ����
		for (int i = c1 ; i > -1; i--) {
			if (newArray[r1+1][i] == 0  ) {
				if(yiZhe(r1+1, i, r2+1, c2+1, newArray)){
					path.add(new Point(r1,i-1));
					return true;
				}
			}else 
				break;
		}
		return false;
		
	}

}
