package com.fontanly;

import java.io.*;

import org.json.JSONArray;
import org.json.JSONObject;

import static java.lang.System.*;
public class JSONanly {
	public static  void test(String path) throws Exception {
		FileInputStream fis=new FileInputStream(path);
		BufferedReader br=new BufferedReader(new InputStreamReader(fis,"utf-8"));
		String str="";
		String len;
		byte[] buf=new byte[1024];
		while((len=br.readLine())!=null) {
			str=str+len;
		}
		fis.close();
		br.close();
		out.println(str);
		JSONObject obj=new JSONObject(str);
		JSONArray array=obj.getJSONArray("words_result");
		FileOutputStream fos=new FileOutputStream(path);
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(fos,"utf-8"));
		for(int i=0;i<array.length();i++) {
			JSONObject jso=array.getJSONObject(i);
			out.println(jso.get("words"));
			bw.write(jso.getString("words"));
			bw.write("\n");
		}
		bw.close();
		fos.close();
		
	}
	public static void main(String[]a) {
		File file=new File("C:\\Users\\22682\\Desktop\\PDF_txt");
		String[] files=file.list();
		//for(String filename:files) {
			//out.println(filename);
			try {
				JSONanly.test("C:\\Users\\22682\\Desktop\\PDF_txt[全职all叶]潮涌(ABO).pdf第99页_91.txt");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//}
	}
}
