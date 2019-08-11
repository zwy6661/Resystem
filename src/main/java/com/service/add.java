package com.service;

import java.util.HashMap;
import java.util.regex.Pattern;

import org.json.JSONObject;

import com.baidu.aip.imagesearch.AipImageSearch;

import java.io.*;
public class add {
	
	public static void add() {
		AipImageSearch client=new Sample().getAuth();
		
		int n=0;
		
		String imgPath="C:\\Users\\22682\\Desktop\\PDF";
		
		File file=new File(imgPath);
		
		File[] files=file.listFiles();
		
		for(File f:files) {
			
			
			
			String path=imgPath+"\\"+f.getName();
			
			String brief=f.getName();
			
			HashMap<String,String> options=new HashMap<String,String>();
			
			options.put("brief", brief);
			
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//String tags=(int)((Math.random()*10))+""+ n+"";
			
			//options.put("tags", tags);
			
			JSONObject res=client.similarAdd(path, options);
			
			System.out.println(res.toString(2));
			
			if(Pattern.matches(".*log.*", res.toString(2))) {
				n++;
			}
			
			
		}
		System.out.println("上传成功数 ："+n);
		
		
		
		
		
	}
	
	public static void main(String[]a) {
		add();
	}
}
