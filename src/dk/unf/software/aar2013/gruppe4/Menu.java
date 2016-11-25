package dk.unf.software.aar2013.gruppe4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Menu extends Activity {

	Intent startgame;
	Intent tutorial;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		Button b_startGame = (Button) findViewById(R.id.startGame);
		Button b_tutorial = (Button) findViewById(R.id.tutorial);
		Button b_exit = (Button) findViewById(R.id.b_exit);
		
		startgame = new Intent(Menu.this,SetUp.class);
		tutorial =  new Intent(Menu.this,Tutorial.class);
		
		b_startGame.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(startgame);
			}
		});
		
		b_exit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				//startActivity(new Intent(Intent.ACTION_MAIN));
			}
		});
		
		b_tutorial.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent tutorial = new Intent(Menu.this, Tutorial.class);
				startActivity(tutorial);
			}
		});
		
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.menu, menu);
//		return true;
//	}

}
