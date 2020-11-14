package com.cbs.look;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

/**
 * 核心算法
 * 判断两个方块是否联通
 * @author CBS
 * 
 */
public class GameUtil implements LookConfig {
	//path主要是记录下相连的数组的位置，为了方便实现连线的功能
	private List<Point> path=new ArrayList<Point>();
	

	public List<Point> getPath() {
		return path;
	}

	public GameUtil(JFrame frame) {
	}

	/**
	 * 无折算法,无折的情况，要么同行，判断列是否连通；要么同列判断行是否连通
	 * 
	 * @param r1第一个方块行下标
	 * @param c1第一个方块列下标
	 * @param r2第二个方块行下标
	 * @param c2第二个方块列下标
	 * @param array用于保存数组的信息
	 * @return 如果能够连通返回TRUE，or返回FALSE
	 */
	public boolean wuZhe(int r1, int c1, int r2, int c2, int[][] array) {

		if (r1 != r2 && c1 != c2)
			return false;
		// 如果两点的x坐标相等，则在水平方向上扫描
		if (r1 == r2) {
			if (c1 == c2 - 1 || c2 == c1 - 1)// 列相邻
				return true;
			for (int i = Math.min(c1, c2) + 1; i < Math.max(c2, c1); i++)
				if (array[r1][i] != 0)
					return false;
		}
		// 如果两点的y坐标相等，则在竖直方向上扫描
		else if (c1 == c2) {
			if (r1 == r2 - 1 || r2 == r1 - 1)// 行相邻
				return true;
			for (int i = Math.min(r1, r2) + 1; i < Math.max(r2, r1); i++)
				if (array[i][c1] != 0)
					return false;
		}
		return true;
	}

	/**
	 * 一折算法，无论是哪种情况下，都只需要判断对角的r1,c2和r2,c1和两点是否连通
	 * 
	 * @param r1第一个方块行下标
	 * @param c1第一个方块列下标
	 * @param r2第二个方块行下标
	 * @param c2第二个方块列下标
	 * @param array 用于保存数组的信息
	 * @return 如果能够连通返回TRUE，or返回FALSE
	 */
	public boolean yiZhe(int r1, int c1, int r2, int c2, int[][] array) {

		// 如果属于0折的情况，直接返回FALSE
		if (r1 == r2 || c1 == c2)
			return false;
		// 测试对角点1
		if (array[r1][c2] == 0) {
			boolean test1 = wuZhe(r1, c1, r1, c2, array);
			boolean test2 = test1 ? wuZhe(r1, c2, r2, c2, array) : test1;
			if (test1 && test2){
				path.add(new Point(r1,c2));
				return true;
			}
		}
		// 测试对角点2
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
	 * 二折算法
	 * 
	 * @param r1第一个方块行下标
	 * @param c1第一个方块列下标
	 * @param r2第二个方块行下标
	 * @param c2第二个方块列下标
	 * @param array用于保存数组的信息
	 * @return 如果能够连通返回TRUE，or返回FALSE
	 */
	public boolean erZhe(int r1, int c1, int r2, int c2, int[][] array) {
		//在原来数组的基础上扩大一圈，用于判断边界的方格
		int[][] newArray = new int[array.length + 2][array[0].length + 2];
		for (int r = 0; r < array.length; r++) {
			for (int c = 0; c < array[r].length; c++) {
				newArray[r + 1][c + 1] = array[r][c];
			}
		}
		
		//判断是否二折连接
		
		// 向下垂直遍历
		for (int i = r1 + 2; i < newArray.length; i++) {
			if (newArray[i][c1+1] == 0 ) {
				if(yiZhe(r2+1, c2+1, i, c1+1, newArray)){
					path.add(new Point(i-1, c1));
					return true;
				}
			}else
				break;
		}
		// 向上垂直遍历
		for (int i = r1 ; i > -1; i--) {
			if (newArray[i][c1+1] == 0  ){
				if(yiZhe(i, c1+1, r2+1, c2+1, newArray)){
					path.add(new Point(i-1, c1));
					return true;
				}
			}else
				break;
			
		}
		// 向右水平遍历
		for (int i = c1 + 2; i < newArray[0].length; i++) {
			if (newArray[r1+1][i] == 0 ){
				if( yiZhe(r2+1, c2+1, r1+1, i, newArray)){
					path.add(new Point(r1,i-1));
					return true;
				}
			}else
				break;
			
		}
		// 向左水平遍历
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
