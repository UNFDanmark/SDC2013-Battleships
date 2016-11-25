package dk.unf.software.aar2013.gruppe4;

import android.R.menu;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class End extends Activity {
	int winner;
	Intent gameend;
	Intent returnToMenu;
	Intent rematch;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_end);
		Button mmbutton = (Button) findViewById(R.id.mmbutton);
		Button b_rematch = (Button) findViewById(R.id.rematch);
		TextView wintext = (TextView) findViewById(R.id.wintext);
		
		winner = getIntent().getIntExtra("winner",0);
		returnToMenu = new Intent(End.this,Menu.class);
		rematch = new Intent(End.this,SetUp.class);
		
		if (winner == 1) {
			wintext.setText("Player 1 Wins!");
		} else {
			wintext.setText("Player 2 Wins!");
		}
		
		mmbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(returnToMenu);
			}
		});
		
		b_rematch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(rematch);
			}
		});
		
	}
}
