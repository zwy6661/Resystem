package com.fontanly;


import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

/**
* ͼƬת��base64����UrlEncode���
*/

public class BaseImg64 {
   /**
    * ��һ�ű���ͼƬת����Base64�ַ���
    */
   public static String getImageStrFromPath(String imgPath) {
       InputStream in;
       byte[] data = null;
       // ��ȡͼƬ�ֽ�����
       try {
           in = new FileInputStream(imgPath);
           data = new byte[in.available()];
           in.read(data);
           in.close();
       } catch (IOException e) {
           e.printStackTrace();
       }
       // ���ֽ�����Base64����
       BASE64Encoder encoder = new BASE64Encoder();
       // ����Base64�������URLEncode���ֽ������ַ���
       return URLEncoder.encode(encoder.encode(data));
   }
}

