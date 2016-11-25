package dk.unf.software.aar2013.gruppe4;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;



public class ShipsListIntent {
	
	public static Intent listToIntent(List<Ship> shipList, Intent intent){
		//Intent intent = new Intent();
		
		for (int i = 0; i<shipList.size(); i++){
			intent.putExtra(i+".startx", shipList.get(i).startx);
			intent.putExtra(i+".endx", shipList.get(i).endx);
			intent.putExtra(i+".starty", shipList.get(i).starty);
			intent.putExtra(i+".endy", shipList.get(i).endy);
		}
		
		return intent;
	}
	
	public static ArrayList<Ship> intentToShipList(Intent intent){
		
		ArrayList<Ship> shipList = new ArrayList<Ship>();
		boolean exit = false;
		for (int i = 0; !exit; i++){
			if (intent.hasExtra(i+".startx")){
				int startx = intent.getIntExtra(i+".startx", -1);
				int endx = intent.getIntExtra(i+".endx", -1);
				int starty = intent.getIntExtra(i+".starty", -1);
				int endy = intent.getIntExtra(i+".endy", -1);
				shipList.add(new Ship(startx, endx, starty, endy));
			}
			else exit = true;
			//Ship ship = new Ship(intent.getIntExtra(name, defaultValue), endx, starty, endy)
		}
		
		return shipList;
		
	}

}
