package ka2.pr;


public class Cantr{
	public final int maxx=10;
	public final int maxy=10;
	private int[][] r = new int[maxx][maxy];
	private int[][] g = new int[maxx][maxy];
	private int[][] b = new int[maxx][maxy];
	
	public int getR(int x,int y) {
		return r[x][y];
	}
	
	public void setR(int x, int y, int n) {
		this.r[x][y] = n;
	}
	
	public int getG(int x,int y) {
		return g[x][y];
	}
	
	public void setG(int x, int y, int n) {
		this.g[x][y] = n;
	}
	
	public int getB(int x,int y) {
		return b[x][y];
	}
	
	public void setB(int x, int y, int n) {
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
	/**
	 * 
	 */

	
}
