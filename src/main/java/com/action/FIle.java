package com.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.service.Find;

/**
 * Servlet implementation class FIle
 */
@WebServlet("/FIle")
public class FIle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FIle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		
		
		
		ServletInputStream is=request.getInputStream();
		String path=request.getParameter("file");
		
		System.out.println(path);
		
		FileOutputStream fos=new FileOutputStream("D:\\testmaven\\test.jpg");
		
		int len=0;
		byte[] buf=new byte[1024];
		
		while((len=is.read(buf))!=-1) {
			
			fos.write(buf,0,len);
			
		}
		
		
		fos.close();
		is.close();
		System.out.println("读取完成");

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		DiskFileItemFactory dff=new DiskFileItemFactory();
		
		File temp=new File("D:\\temps");
		
		if(!temp.exists()) {
			
			temp.mkdirs();
		}
		
		dff.setRepository(temp);

		ServletFileUpload fileUpload=new ServletFileUpload(dff);
		
		fileUpload.setHeaderEncoding("utf-8");
		
		try {
			List<FileItem> fileitems=fileUpload.parseRequest(request);
			
			for(FileItem item:fileitems) {
				
				if(item.isFormField()) {
				}else {
					
					
						String filename=item.getName();
						
						System.out.println(filename);
						
						java.io.InputStream is=item.getInputStream();
						
						FileOutputStream fos=new FileOutputStream("D:\\testmaven\\Retrieval_system\\src\\main\\webapp\\test.jpg");
						
						int len=0;
						byte[] buf=new byte[1024];
						
						while((len=is.read(buf))!=-1) {
							
							fos.write(buf,0,len);
							System.out.println(buf);
						}
						
						
						fos.close();
						is.close();
						System.out.println("读取完成");
						
						Find.select("D:\\testmaven\\Retrieval_system\\src\\main\\webapp\\test.jpg");
						
						
						List<String> set=Find.set;
						
						Map<String,String> map=Find.map;
						
						
						String HTML="";
						
						Iterator<String> it=set.iterator();
						int num=0;
						while(it.hasNext()) {
							
							
							
							String pdf=it.next();
							
							String pic=map.get(pdf);
							
							String[] array=pic.split("#");
							if(Pattern.matches(".*第.*页.*",pdf)) {
								pdf=(String) pdf.substring(0, pdf.lastIndexOf("第"));
							}
							
							for(int i=0;i<array.length;i++) {
								for(int j=i+1;j<array.length;j++) {
									String[] sort=array[i].split("&");
									Double score=Double.parseDouble(sort[1]);
									String[] sort1=array[j].split("&");
									Double score1=Double.parseDouble(sort1[1]);	
									if(score1>score) {	
										String temps=array[i];
										array[i]=array[j];
										array[j]=temps;	
									}	
								}
							}
							String[] Img_msg=array[0].split("&");
							String score=Img_msg[1];
							String img=Img_msg[0].substring(Img_msg[0].lastIndexOf("\\")+1);
							//String pageNum=img.substring(img.lastIndexOf("第"), img.lastIndexOf("页"));
							HTML=HTML+"<div style='float:left;margin-left:10%;'>"
									+ "<ul style='list-style:none;' class='pricing-table'>"
									+ "<li class='title' style='text-algin:center; margin-left:4em;'"
									+ ">匹配率："+(int)(Float.parseFloat(score)*100)+"%&nbsp;&nbsp;&nbsp;"
											+ "&nbsp;<img src='test.jpg' alt='img'>---<img src='"
									+img
									+"' alt='img'> 第    "+" 页 </li>"
									+"<embed src='"+pdf+"' width=\"1200\" height=\"500\">"
									+"</li></ul></div>";
							
							
							
							num++;
							
														
							
							
						}
						
						
						response.getWriter().write(HTML);
						
						
						
						
						
						
					
				}
			}
			
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
