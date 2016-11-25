package dk.unf.software.aar2013.gruppe4;

//import
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	Grid p1;
	Grid p2;
	BattleView BV;
	boolean p1Turn;
	boolean victory;
	int winner;
	TextView turnindicator;
	sound snd;
	int hit;
	int splash; 

	// create
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// find layout inputs
		final EditText xcoordinate = (EditText) findViewById(R.id.xcoordinate);
		final EditText ycoordinate = (EditText) findViewById(R.id.ycoordinate);
		Button b_shoot = (Button) findViewById(R.id.b_shoot);
		turnindicator = (TextView) findViewById(R.id.turnindicator);
		
		//afspilling af lyd
		snd = new sound(getApplicationContext());
		this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		
		hit = snd.load(R.raw.shit);
		splash = snd.load(R.raw.ssplash);

		// TextView textturnp1 = (TextView) findViewById(R.id.t_pturn);
		// link til Grid
		Intent intent = getIntent();
		ArrayList<Ship> shipList = ShipsListIntent.intentToShipList(intent);
		p1 = new Grid(this);
		p2 = new Grid(this);
		for (int i = 0; i < 5; i++) {
			p1.placeShip(shipList.get(i));
		}
		for (int i = 5; i <= 9; i++) {
			p2.placeShip(shipList.get(i));
		}

		BV = (BattleView) findViewById(R.id.battleView1);

		BV.setGrid(p2);
		p1Turn = true;
		victory = false;
		turnindicator.setText("Player 1's turn");

		// textturnp1.setText("Player 1's turn");
		// kør hvis "skyd" trykkes
		b_shoot.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String testx = xcoordinate.getText().toString();
				String testy = ycoordinate.getText().toString();
				
				if (testx.matches("") || testy.matches("")) {
					Toast.makeText(getApplicationContext(),
							"Type a coordinate", Toast.LENGTH_SHORT).show();
				} else {
					int x = Integer.parseInt(xcoordinate.getText() + "") - 1;
					int y = Integer.parseInt(ycoordinate.getText() + "") - 1;

					if (x < 10 && y < 10 && x >= 0 && y >= 0) {
						if (p1Turn) {
							if (p2.shotAt(x, y)) {
								Toast.makeText(getApplicationContext(),
										"Already shot there!",
										Toast.LENGTH_SHORT).show();
							} else {
								xcoordinate.setText("");
								ycoordinate.setText("");
								resolveRound(x, y);
							}
						} else {
							if (p1.shotAt(x, y)) {
								Toast.makeText(getApplicationContext(),
										"Already shot there!",
										Toast.LENGTH_SHORT).show();
							} else {
								xcoordinate.setText("");
								ycoordinate.setText("");
								resolveRound(x, y);
							}
						}
					} else {
						Toast.makeText(getApplicationContext(),
								"No such coordinate", Toast.LENGTH_SHORT)
								.show();
					}
				}

			}
		});
	}
	
	public void resolveRound(int x, int y) {
		Grid grid = (p1Turn ? p2 : p1);
		grid.hits[x][y] = true;
		if (grid.shipAt(x, y)) {
			Toast.makeText(getApplicationContext(), "Hit!", Toast.LENGTH_SHORT)
					.show();
			
			snd.play(hit,(float) 0.6);
			
		} else {
			Toast.makeText(getApplicationContext(), "Splash!",
					Toast.LENGTH_SHORT).show();
			
			snd.play(splash, (float) 0.2);
		}
		BV.setGrid(grid);
		victory = true;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (grid.shipAt(i, j) && !grid.shotAt(i, j)) {
					victory = false;
				}
			}
		}
		if (victory) {
			if (p1Turn) {
				winner = 1;
			} else {
				winner = 2;
			}
			Intent gameend = new Intent(MainActivity.this, End.class);
			gameend.putExtra("winner", winner);
			startActivity(gameend);
		}

		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		builder.setMessage("It's player " + (p1Turn ? "2" : "1") + "'s turn")
				.setTitle("Next turn");
		builder.setPositiveButton(R.string.knapTextNextTurn,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						p1Turn = !p1Turn;
						MainActivity.this.BV.setGrid((p1Turn ? p2 : p1));
					}
				});
		builder.create().show();
		turnindicator.setText("Player " + (p1Turn ? "2" : "1") + "'s turn");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
