package com.kaikai.doubanspider.output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/** 
* @author 作者 kaikai: 
* @version 创建时间：2020年8月24日 下午5:00:37 
* @Description 类说明 文件输出的方式
*/
public class FileOutput {
	//输出流
	private static PrintStream ps;
	//输出到相对位置
	static {
		String path="../config/outputT1.md";
		String confPath = System.getProperty("user.dir");
		confPath = confPath + File.separator + path;
		File filemd = new File(confPath);
		try {			
			ps = new PrintStream(filemd);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 该方法用于写出数据
	 * @param s
	 */
	public static void write(String s) {
		ps.append(s);
	}
	
	
}
