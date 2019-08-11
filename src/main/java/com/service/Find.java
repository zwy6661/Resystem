package com.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

import com.baidu.aip.imagesearch.AipImageSearch;

public class Find {
	
	public static List<String> set=new ArrayList<String>();
	
	public static Map<String,String> map=new HashMap<String,String>();
	
	public static void select(String path) throws IOException {
		AipImageSearch client=new Sample().getAuth();
	
		String imgPath=path;
		
		//String iamge=BaseImg.getPath(imgPath);
		
		JSONObject res=client.similarSearch(imgPath, new HashMap<String,String>());
		
		System.out.println(res.toString());
		
		JSONArray array=res.getJSONArray("result");
		Double maxScore = null;
		
		for(int i=0;i<array.length();i++) {
			
			JSONObject obj=array.getJSONObject(i);
			
			String url=obj.getString("brief");
			
			
			Object score=obj.get("score");
			
			Double scor=Double.parseDouble(score.toString());
			if(i==0) {
				maxScore=scor;
			}
			if(scor>(maxScore-0.2)&&!Pattern.matches(".*name.*", url)) {
				
				System.out.println(url);
				System.out.println(scor);
				System.out.println();
				
				String pdf=url.substring(0, url.lastIndexOf("_"));
				
				System.out.println(pdf);
				
				
				
				url="D:\\testmaven\\照片\\"+url;
				
				if(!set.contains(pdf)){
					set.add(pdf);
					String u=url+"&"+score;
					map.put(pdf, u);
					
					
				}else {
					
					String u=map.get(pdf);
					
					u=u+"#"+url+"&"+score;
					map.put(pdf, u);
					
				}
				
				
				
			}
			
		}
		
		System.out.println(set.toString());
		
		System.out.println(map.toString());
		
	}
	
	
}
