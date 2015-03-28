package ka2.pr;

import java.io.Serializable;


public class Cantr implements Serializable{
	public final int maxx=20;
	public final int maxy=20;
	private int[][] r = new int[maxx][maxy];
	private int[][] g = new int[maxx][maxy];
	private int[][] b = new int[maxx][maxy];
	
	public int getR(int x,int y) {
		return r[x][y];
	}
	
	public void setR(int x, int y, int n) {
		if(n>=0&&n<256)
		this.r[x][y] = n;
	
	}
	
	public int getG(int x,int y) {
		return g[x][y];
	}
	
	public void setG(int x, int y, int n) {
		if(n>=0&&n<256)
		this.g[x][y] = n;
	}
	
	public int getB(int x,int y) {
		return b[x][y];
	}
	
	public void setB(int x, int y, int n) {
		if(n>=0&&n<256)
		this.b[x][y] = n;
	}
	
	
	
	
	public void drr(){
		for(int i=0;i<maxx;i+=1){
			for(int j=0;j<maxy;j+=1){
				System.out.print(" "+getR(i,j));
			}
			System.out.println("");
		}
		
	}
	
	public void printCurrent(int x, int y){
		System.out.println("R:"+getR(x, y));
		System.out.println("G:"+getG(x, y));
		System.out.println("B:"+getB(x, y));
		
	}
	/**
	 * 
	 */

	
}
