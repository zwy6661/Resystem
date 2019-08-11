package com.fontanly;


import static java.lang.System.*;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSObject;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripper;
public class dingImg {
	static {
		try {
			File file=new File("C:\\Users\\22682\\Desktop\\���ڻ�\\���ڻ���������ؼ׹��о� ��.pdf");
			InputStream is=new FileInputStream(file);
			PDDocument document=PDDocument.load(is);
			StringWriter writer=new StringWriter();
			PDFTextStripper stripper=new PDFTextStripper();
			//stripper.writeText(document, writer);
			//String contents=writer.getBuffer().toString();
			PDDocumentInformation difm=document.getDocumentInformation();
			out.println("���⣺"+difm.getTitle());
			PDDocumentInformation info=document.getDocumentInformation();
			/*out.println("���⣺"+difm.getTitle());
			out.println("���ߣ�"+(difm.getCreationDate()).getTime());
			System.out.println("����:" + info.getTitle());
			System.out.println("����:" + info.getSubject());
			System.out.println("����:" + info.getAuthor());
			System.out.println("�ؼ���:" + info.getKeywords());
			System.out.println("Ӧ�ó���:" + info.getCreator());
			System.out.println("pdf ��������:" + info.getProducer());
			System.out.println("����:" + info.getTrapped());
			System.out.println("����ʱ��:" + (info.getCreationDate()).getTime());
			System.out.println("�޸�ʱ��:" + (info.getModificationDate()).getTime());*/
			PDDocumentCatalog cata=document.getDocumentCatalog();
			out.println(cata.getPages().getCount());
			int pagecount=document.getNumberOfPages();
			int last_name=0;
			int count=1;
			for(int i=176;i<pagecount;i++) {
				PDPage page=cata.getPages().get(i);
				if(page!=null) {
					PDFTextStripper pst=new PDFTextStripper();
					pst.setSortByPosition(true);
					pst.setStartPage(i+1);
					pst.setEndPage(i+1);
					
					File f3=new File("C:\\Users\\22682\\Desktop\\���ڻ�����\\text.txt");
					FileOutputStream fos1=new FileOutputStream(f3);
					Writer wr=new OutputStreamWriter(fos1,"unicode");
					pst.writeText(document, wr);
					wr.close();
					fos1.close();
					PDResources rso=page.getResources();
					COSDictionary coso=rso.getCOSObject();
					out.println(" : "+coso);
					Iterable<COSName> xobjects=rso.getXObjectNames();
					
					if(xobjects!=null) {
						
						Iterator<COSName> imageIt=xobjects.iterator();
						while(imageIt.hasNext()) {
							
							COSName key=imageIt.next();
							//COSName cn=key.getKey();
							/*boolean cb=coso.containsKey(cn);
							
							out.println("���ԣ�"+cn.getName());
							out.println("ֵ��"+cb);
							COSName conn = null;
							System.out.println(coso.containsKey(conn));*/
							
							if(rso.isImageXObject(key)) {
								out.print("����");
								
								PDImageXObject image=(PDImageXObject)rso.getXObject(key);
								File f2=new File("C:\\Users\\22682\\Desktop\\���ڻ�����\\����_"+last_name+".jpg");
								FileOutputStream fos=new FileOutputStream(f2);
								BufferedImage bi=image.getImage();
								/*BufferedImage bis=new BufferedImage(bi.getWidth(),bi.getHeight(),4);
								for(int x=bi.getMinX();x<bi.getWidth();x++) {
									for(int y=bi.getMinY();y<bi.getHeight();y++) {
										int pixel=bi.getRGB(x, y);
										bis.setRGB(x,y,pixel);
									}
								}*/
								ImageIO.write(bi,"jpg",fos);
								last_name++;
							}
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
		
		
	}
	public static void main(String[]a) {
		
	}

}
