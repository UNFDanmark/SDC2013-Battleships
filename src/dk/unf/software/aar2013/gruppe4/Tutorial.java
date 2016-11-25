package dk.unf.software.aar2013.gruppe4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Tutorial extends Activity {

	Button nextpage;
	Button nextpage2;
	Button play;
	Button mainmenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tutorial1);
		setOnClickListeners();
	}

	public void setOnClickListeners() {
		nextpage = (Button) findViewById(R.id.nextpage);
		nextpage2 = (Button) findViewById(R.id.nextpage2);
		play = (Button) findViewById(R.id.play);
		mainmenu = (Button) findViewById(R.id.mainmenu);

		if (nextpage != null) {
			nextpage.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Tutorial.this.setContentView(R.layout.activity_tutorial2);
					Tutorial.this.setOnClickListeners();
				}
			});
		}
		if (nextpage2 != null) {
			nextpage2.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Tutorial.this.setContentView(R.layout.activity_tutorial3);
					Tutorial.this.setOnClickListeners();
				}
			});
		}
		if (play != null) {
			play.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent startplay = new Intent(Tutorial.this, SetUp.class);
					startActivity(startplay);

				}
			});
		}
		if (mainmenu != null) {
			mainmenu.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent startmainmenu = new Intent(Tutorial.this, Menu.class);
					startActivity(startmainmenu);

				}
			});
		}
	}

}
