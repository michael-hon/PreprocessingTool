package com.scut.PreprocessingTool.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class FileUtil {
	
	//读取文件内容
	public static String readToString(String filepath){
		String encoding = "UTF-8";
		File file = new File(filepath);
		Long fileLength = file.length();
		byte[]filecontent = new byte[fileLength.intValue()];
		try{
			FileInputStream in = new FileInputStream(file);
			in.read(filecontent);
			in.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		try{
			return new String(filecontent, encoding);
		}
		catch(UnsupportedEncodingException e){
			System.out.println("The OS does not support " + encoding);
			e.printStackTrace();
		}
		return "";
	}
	
	//替换文件内容
	public static void replaceTxt(String filepath, String newStr){
		File file = new File(filepath);
		//Long fileLength = file.length();
		//byte[]fileContext = new byte[fileLength.intValue()];
		//FileInputStream in = null;
		PrintWriter out = null;
		try{
			//in = new FileInputStream(file);
			//in.read(fileContext);
			out = new PrintWriter(filepath);
			out.write(newStr);
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally {
				out.flush();
				out.close();
				//out.close();
				//in.close();
		}
	}
}
