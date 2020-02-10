package net.devatom.cargest;

import net.devatom.classes.parcAutomobile;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.util.Objects;

public class VuePrincipale extends Activity implements OnClickListener {
	private Button btQuit, btShowVehicules;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vue_principale_layout);
		
//		Objects.requireNonNull(getActionBar()).hide();
		
		//Initialisation du parc auto
		parcAutomobile.initVehicules(getApplicationContext());
		
		btShowVehicules = (Button) findViewById(R.id.bt_listevehicules);
		btQuit = (Button) findViewById(R.id.bt_quit);
		
		btShowVehicules.setOnClickListener(this);
		btQuit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.bt_quit:{
				finish();
				break;
			}
			case R.id.bt_listevehicules:{
				Intent it = new Intent(getBaseContext(), ListeVehicules.class);
				startActivity(it);
			}
		}
	}
	
	@Override
	protected void onDestroy() {
		try{
			parcAutomobile.maDatabase.closeDb();
			super.onDestroy();
		}catch (Exception e){
			Log.e("End Application", e.getLocalizedMessage());
		}
	}
}
