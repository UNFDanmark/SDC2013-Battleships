package dk.unf.software.aar2013.gruppe4;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class Grid {

	boolean[][] grid = new boolean[10][10];
	Context context;
	boolean[][] hits = new boolean[10][10];
	Context hcontext;

	public Grid(Context context) {
		this.context = context;
	}

	public void placeShip(Ship ship){
		int startx = ship.startx;
		int endx=ship.endx;
		int starty=ship.starty;
		int endy=ship.endy;
		
		for (int i = startx; i<=endx; i++){
			for (int j = starty; j<=endy; j++){
				grid[i][j]=true;
			}
		}
	}

	public boolean shipAt(int x, int y) {
		return (grid[x][y]);
	}
	
	public boolean shotAt(int hitx, int hity) {
		return (hits[hitx][hity]);
	}
	
	public void place1Ship(int x, int y) {
		grid[x][y]=true;
	}
	
	public void remove1Ship(int x, int y) {
		grid[x][y]=false;
	}
	
	public boolean shipInTheWay(int x, int y, int shipSize, boolean vertical ){
		if (vertical){
			for (int i = y; i < y + shipSize - 1; i++) {
				if (shipAt(x, i)){
					Log.d("derp", i+"");
					return true;
				}
			}
		} else {
			for (int i = x; i < x + shipSize - 1; i++) {
				if (shipAt(i, y)){
					return true;
				}
			}
		}
		return false;
	}
}
