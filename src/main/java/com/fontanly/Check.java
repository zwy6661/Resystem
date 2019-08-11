package com.fontanly;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Pattern;

/**
* ͼ������ʶ��
*/

public class Check {
   private static final String POST_URL = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic?access_token=" + AuthService.getAuth();

   /**
    * ʶ�𱾵�ͼƬ������
    */
   public static String checkFile(String path,String savapath) throws URISyntaxException, IOException {
       File file = new File(path);
       if (!file.exists()) {
           throw new NullPointerException("ͼƬ������");
       }
       String image = BaseImg64.getImageStrFromPath(path);
       String param = "image=" + image;
       return post(param,savapath);
   }

   /**
    * ͼƬurl
    * ʶ������Ϊjson��ʽ
    */
   public static String checkUrl(String url,String savepath) throws IOException, URISyntaxException {
       String param = "url=" + url;
       return post(param,savepath);
   }

   /**
    * ͨ�����ݲ�����url��image��������ʶ��
    */
   private static String post(String param,String savepath) throws URISyntaxException, IOException {
       //��ʼ�post����
       HttpClient httpClient = new DefaultHttpClient();
       HttpPost post = new HttpPost();
       URI url = new URI(POST_URL);
       post.setURI(url);
       //��������ͷ������ͷ����Ϊapplication/x-www-form-urlencoded����Ϊ�Ǵ���һ���ܳ����ַ��������ֶܷη���
        post.setHeader("Content-Type", "application/x-www-form-urlencoded;text/html;utf-8");
       StringEntity entity = new StringEntity(param);
       post.setEntity(entity);
       HttpResponse response = httpClient.execute(post);
       response.setHeader("Content-Type","charset=utf-8");
       System.out.println(response.toString());
       if (response.getStatusLine().getStatusCode() == 200) {
           String str;
           HttpEntity entits=response.getEntity();
           System.out.println(entits.getContent());
           FileOutputStream fos=new FileOutputStream(savepath);
           InputStream is=entits.getContent();
           byte[] buf=new byte[1024];
           int len;
           while((len=is.read(buf))!=-1) {
        	   String sts=new String(buf,0,len);
        	   sts=new String(sts.getBytes());
        	   System.out.println(sts.toString());
        	   fos.write(buf,0,len);
           }
       }
       return null;
   }

   public static void main(String[] args) throws InterruptedException {
	   File files=new File("C:\\Users\\22682\\Desktop\\PDF");
	   String[] fls=files.list();
	   for(String filsname:fls) {
		   System.out.println(filsname);
	   }
	  // for(int j=0;;j++) {
	   for(int i=0;i<fls.length;i++) {
		   if(Pattern.matches(".*.jpg.*", fls[i])) {
		   String path="C:\\Users\\22682\\Desktop\\PDF\\"+fls[i];
		   try {
	           long now = System.currentTimeMillis();
	           String savepath="C:\\Users\\22682\\Desktop\\PDF_txt"+fls[i].substring(0, fls[i].lastIndexOf("."))+".txt";
	           checkFile(path,savepath);
	           //Thread.sleep(300);
	           // System.out.println("��ʱ��" + (System.currentTimeMillis() - now) / 1000 + "s");
	        } catch (URISyntaxException | IOException e) {
	           e.printStackTrace();
	       }
		   }
	   }
	   //}
   }
}