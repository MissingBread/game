package com.cbs.look;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 用于保存游戏排行榜信息
 * 
 * @author CBS
 */
public class GameSave {

	public boolean save(User user) {

		String path = "src/com/cbs/look/save3.txt";

		try {
			List<User> array = opean();
			if(array!=null){
				array.add(user);
				for (int i=0;i<array.size()-1;i++) {
					int flag=i;
					for(int j=i+1;j<array.size();j++){
						if(array.get(i).getTime()>array.get(j).getTime())
							flag=j;
					}
					if(flag!=i){
						User u1=array.get(i);
						User u2=array.get(flag);
						array.set(i, u2);
						array.set(flag, u1);
					}
				}
			}else{
				array=new ArrayList<User>(); 
				array.add(user);
			}
				
			OutputStream os = new FileOutputStream(path);
			DataOutputStream dos=new DataOutputStream(os);
			//先写入有几条信息
			os.write(array.size());
			for (int i = 0; i < array.size(); i++) {
				User u =  array.get(i);
				dos.writeByte(u.getName().getBytes().length);
				dos.write(u.getName().getBytes());
				dos.writeInt(u.getTime());
			}
			os.close();
			dos.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	public List<User> opean() {
		String path = "src/com/cbs/look/save3.txt";
		try {
			InputStream is = new FileInputStream(path);
			DataInputStream dis = new DataInputStream(is); 
			
			//读取有几条信息
			int size=is.read();
			List<User> list=new ArrayList<User>();
			
			if(size!=-1){
				for(int i=0;i<size;i++){
					byte bsize=dis.readByte();
					byte[] b=new byte[bsize];
					is.read(b);
					int time=dis.readInt();
					User u=new User(new String(b),time);
					list.add(u);
				}
			}
			dis.close();
			is.close();
			return list;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
