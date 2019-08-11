package com.test;

import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class test {
	
	private String name="zwy";
	protected String qs="zwy";
	Iterator it;
	
	
	public static void main(String[]a) throws NoSuchFieldException {
		/*new Thread(()->{
			int i=0;
			for(i=0;i<10;i++)
			System.out.println("你好！");
			
			} ).start();*/
		//System.out.println((int)(Math.random()*10));
		int j=1;
		int q=j++;
		System.out.println(j++);
		System.out.println(q);
		System.out.println(Math.toDegrees((2.0f * Math.PI) / 360)+" : "+Float.POSITIVE_INFINITY);
		int dim=7;
		dim|=1;
		//System.out.println(1.6*(float) Math.sqrt(Math.pow(Math.pow(2.0, 1.0 / 3),
			//	2.0) - 1.0));
		for(int y=0;y<5;++y) {
			System.out.println("y:"+y);
		}
		//System.out.println();
		
		
		
		ExecutorService pool = Executors.newCachedThreadPool();
		
		pool.submit(new Thread(new test().new TestThread(),"线程一"));
		pool.submit(new Thread(new test().new TestThread(),"线程二"));
		pool.shutdown();
		try {
			Class<?> cla=Class.forName("com.test.test");
		//	System.out.println(cla.getDeclaredFields().length);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int n=0;
		new test().num(n);
		System.out.println("n : "+n);
		int[] num=new int[10];
		for(int i=0;i<num.length;i++) {
			//System.out.println(num[i]);
		}
		double nums=255;
		System.out.println(nums==255);
		
	}
	public boolean num(int n) {
		n=n+1;
		return true;
	}
	class TestThread implements Runnable{
		
		
		public synchronized void run() {
			int i=0;
			do {
				//System.out.println(Thread.currentThread().getThreadGroup()+":"+i);
			}while(i++<10);
			
		}
		
	}
	
}

