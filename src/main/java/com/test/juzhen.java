package com.test;

public class juzhen {
	private static float[][] n;
	private static int height;
	private static int weight;
	public static void main(String[]a) {
		
		float[] m= {4,2,3};
		float[][] n= {{1,2,1},{4,3,2},{2,5,3}};
		float[][] _n=new float[3][3];
		float value=(n[0][0]*n[1][1]*n[2][2]+n[0][1]*n[1][2]*n[2][0]+n[0][2]*n[1][0]*n[2][1])
				-(n[0][2]*n[1][1]*n[2][0]+n[0][1]*n[1][0]*n[2][2]+n[0][0]*n[1][2]*n[2][1]);
		_n[0][0]=n[1][1]*n[2][2]-n[1][2]*n[2][1];
		_n[0][1]=-(n[0][1]*n[2][2]-n[0][2]*n[2][1]);
		_n[0][2]=n[0][1]*n[1][2]-n[0][2]*n[1][1];
		_n[1][0]=-(n[1][0]*n[2][2]-n[1][2]*n[2][0]);
		_n[1][1]=n[0][0]*n[2][2]-n[0][2]*n[2][0];
		_n[1][2]=-(n[0][0]*n[1][2]-n[0][2]*n[1][1]);
		_n[2][0]=n[1][0]*n[2][1]-n[1][1]*n[2][0];
		_n[2][1]=-(n[0][0]*n[2][1]-n[0][1]*n[2][0]);
		_n[2][2]=n[0][0]*n[1][1]-n[0][1]*n[1][0];
		
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				n[i][j]=value*_n[i][j];
				System.out.print(n[i][j]+"  ");
			}
			System.out.println();
		}
		
		for(int i=0;i<3;i++) {
			
			System.out.println(m[0]*_n[0][0+i]+m[1]*_n[1][0+i]+m[2]*_n[2][0+i]);
			
			
			
		}
		
		
		for (int y = 0; y < 2; ++y) {

			int yMaxIndex = y;
			float yMaxValue = Math.abs(n[y][y]);
			 
			for (int py = y; py < 3; ++py) {
				if (Math.abs(n[py][y]) > yMaxValue) {
					yMaxValue = Math.abs(n[py][y]);
					yMaxIndex = py;
				}
			}
			for (int x = 0; x < 3; ++x) {
				float temp = n[y][x];
				n[y][x] = n[yMaxIndex][x];
				n[yMaxIndex][x] = temp;
				float temp2 = m[y];
				m[y] = m[yMaxIndex];
				m[yMaxIndex] = temp2;
			}
			
		 //矩阵*矩阵的逆
			for (int py = y + 1; py < 3; ++py) {
				float elimMul = n[py][y]
						/ n[y][y];
				for (int x = 0; x < 3; ++x)
					n[py][x] -= elimMul
							* n[y][x];
				m[py] -= elimMul * m[y];
			}
		}

	 
		for (int y = 2; y >= 0; --y) {
			float solY = m[y];
			for (int x = 2; x > y; --x)
				solY -= n[y][x]
						* m[x];
			m[y] = solY / n[y][y];
			System.out.println(m[y]);
		}
		
	}
	
	
}
