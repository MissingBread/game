package com.cbs.look;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 用于保存游戏存档的信息
 * 
 * @author CBS
 */
public class GameSave2 {

	public boolean save(CunD d) {

		String path = "src/com/cbs/look/info.txt";

		try {

			OutputStream os = new FileOutputStream(path);
			os.write(d.getTime());
			for (int r = 0; r < d.getArray().length; r++) {
				for (int c = 0; c < d.getArray()[0].length; c++) {
					os.write(d.getArray()[r][c]);
				}
			}
			os.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	public CunD opean() {
		String path = "src/com/cbs/look/info.txt";
		try {
			InputStream is = new FileInputStream(path);
			int time = is.read();
			int array[][]=new int[8][8];

			for (int i = 0; i < array.length; i++)
				for (int j = 0; j < array[0].length; j++)
					array[i][j] = is.read();
			CunD c=new CunD(array,time);
			is.close();
			return c;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
