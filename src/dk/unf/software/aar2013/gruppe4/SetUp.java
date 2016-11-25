package dk.unf.software.aar2013.gruppe4;

import java.io.ObjectOutputStream.PutField;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SetUp extends Activity {

	int shipsPlaced;
	List<Ship> shipList;
	List<Integer> shipSizeList;
	TextView playertext;
	Grid grid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup);

		shipsPlaced = 0;
		grid = new Grid(this);
		shipList = new ArrayList<Ship>();

		shipSizeList = new ArrayList<Integer>();
		shipSizeList.add(2);
		shipSizeList.add(3);
		shipSizeList.add(3);
		shipSizeList.add(4);
		shipSizeList.add(5);

		final Button bvertical = (Button) findViewById(R.id.bvertical);
		final Button bhorisontal = (Button) findViewById(R.id.bhorisontal);
		final EditText xvalue = (EditText) findViewById(R.id.xvalue);
		final EditText yvalue = (EditText) findViewById(R.id.yvalue);
		final TextView placement = (TextView) findViewById(R.id.b_s_placement);

		playertext = (TextView) findViewById(R.id.welcometext_setup);
		playertext.setText("Place your ships, Player 1!");

		bvertical.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String testx = xvalue.getText().toString();
				String testy = yvalue.getText().toString();
				if (testx.matches("") || testy.matches("")) {
					Toast.makeText(getApplicationContext(),
							"Type a coordinate", Toast.LENGTH_SHORT).show();
				} else {

					int x = Integer.parseInt(xvalue.getText() + "") - 1;
					int y = Integer.parseInt(yvalue.getText() + "") - 1;
					int shipSize = shipSizeList.get(shipsPlaced % 5);

					if (x < 10 && y < (11 - shipSize) && x >= 0 && y >= 0) {
						boolean shipintheway = grid.shipInTheWay(x, y,
								shipSize, true);
						if (!shipintheway) {
							Ship ship = new Ship(x, x, y, y + shipSize - 1);
							shipList.add(ship);
							for (int i = y; i < y + shipSize - 1; i++) {
								grid.place1Ship(x, i);
							}

							Toast.makeText(
									getApplicationContext(),
									"Ship " + (shipsPlaced % 5 + 1) + " placed",
									Toast.LENGTH_SHORT).show();

							shipsPlaced++;

							if (shipsPlaced == 1 || shipsPlaced == 6) {
								placement.setText("Place the Submarine");
							} else if (shipsPlaced == 2 || shipsPlaced == 7) {
								placement.setText("Place the Destroyer");
							} else if (shipsPlaced == 3 || shipsPlaced == 8) {
								placement.setText("Place the Battleship");
							} else if (shipsPlaced == 4 || shipsPlaced == 9) {
								placement.setText("Place the Aircraft Carrier");
							} else if (shipsPlaced == 5) {
								placement.setText("Place the Patrol Boat");
							}

							xvalue.setText("");
							yvalue.setText("");
						} else {
							Toast.makeText(getApplicationContext(),
									"Ship in the way", Toast.LENGTH_SHORT)
									.show();
						}
					} else {
						Toast.makeText(getApplicationContext(),
								"Invalid Placement", Toast.LENGTH_SHORT).show();
					}
				}
				if (shipsPlaced == 5) {
					for (int i = 0; i < 10; i++) {
						for (int j = 0; j < 10; j++) {
							grid.remove1Ship(i, j);
						}
					}
					playertext.setText("Place your ships, Player 2!");
					AlertDialog.Builder builder = new AlertDialog.Builder(
							SetUp.this);
					builder.setTitle("Next player");
					builder.setPositiveButton(R.string.knapTextNextTurn,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
								}
							});
					builder.create().show();
				}
				if (shipsPlaced == 10) {
					Intent begingame = new Intent(SetUp.this,
							MainActivity.class);
					begingame = ShipsListIntent.listToIntent(shipList,
							begingame);

					// begingame.putExtras(shipList);
					startActivity(begingame);
				}

			}
		});

		bhorisontal.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String testx = xvalue.getText().toString();
				String testy = yvalue.getText().toString();
				if (testx.matches("") || testy.matches("")) {
					Toast.makeText(getApplicationContext(),
							"Type a coordinate", Toast.LENGTH_SHORT).show();
				} else {

					int x = Integer.parseInt(xvalue.getText() + "") - 1;
					int y = Integer.parseInt(yvalue.getText() + "") - 1;
					int shipSize = shipSizeList.get(shipsPlaced % 5);
					if (x < 11 - shipSize && y < 10 && x >= 0 && y >= 0) {
						boolean shipintheway = grid.shipInTheWay(x, y,
								shipSize, false);
						if (!shipintheway) {
							Ship ship = new Ship(x, x + shipSize - 1, y, y);
							shipList.add(ship);
							for (int i = x; i < x + shipSize - 1; i++) {
								grid.place1Ship(i, y);
							}
							Toast.makeText(
									getApplicationContext(),
									"Ship " + (shipsPlaced % 5 + 1) + " placed",
									Toast.LENGTH_SHORT).show();

							shipsPlaced++;

							if (shipsPlaced == 1 || shipsPlaced == 6) {
								placement.setText("Place the Submarine");
							} else if (shipsPlaced == 2 || shipsPlaced == 7) {
								placement.setText("Place the Destroyer");
							} else if (shipsPlaced == 3 || shipsPlaced == 8) {
								placement.setText("Place the Battleship");
							} else if (shipsPlaced == 4 || shipsPlaced == 9) {
								placement.setText("Place the Aircraft Carrier");
							} else if (shipsPlaced == 5) {
								placement.setText("Place the Patrol Boat");
							}

							xvalue.setText("");
							yvalue.setText("");
						} else {
							Toast.makeText(getApplicationContext(),
									"Ship in the way", Toast.LENGTH_SHORT)
									.show();
						}
					} else {
						Toast.makeText(getApplicationContext(),
								"Invalid Placement", Toast.LENGTH_SHORT).show();
					}
				}
				if (shipsPlaced == 5) {
					for (int i = 0; i < 10; i++) {
						for (int j = 0; j < 10; j++) {
							grid.remove1Ship(i, j);
						}
					}
					playertext.setText("Place your ships, Player 2!");
					AlertDialog.Builder builder = new AlertDialog.Builder(
							SetUp.this);
					builder.setTitle("Next player");
					builder.setPositiveButton(R.string.knapTextNextTurn,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
								}
							});
					builder.create().show();
				}
				if (shipsPlaced == 10) {
					Intent begingame = new Intent(SetUp.this,
							MainActivity.class);
					begingame = ShipsListIntent.listToIntent(shipList,
							begingame);

					// begingame.putExtras(shipList);
					startActivity(begingame);
				}

			}
		});

	}
}
