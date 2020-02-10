package net.devatom.cargest;

import net.devatom.classes.Entretien;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class VueEntretien extends Activity{
	
	private Entretien monEntretien;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vue_entretien_layout);
		
		monEntretien = (Entretien) getIntent().getSerializableExtra("dateEntretien");
		
		TextView infos = (TextView) findViewById(R.id.ttInfos);
		infos.setText(monEntretien.toString());
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.vue_entretien_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		boolean retVal = false;
		switch (item.getItemId()){
			case R.id.mnu_edit_entretien:{
				Toast.makeText(getApplicationContext(), "Entretien modifé", Toast.LENGTH_SHORT).show();
				retVal = true;
				break;
			}
			case R.id.mnu_delete_entretien:{
				Toast.makeText(getApplicationContext(), "Entretien supprimé", Toast.LENGTH_SHORT).show();
				retVal = true;
				break;
			}
			case R.id.mnu_close:{
				finish();
				break;
			}
			default:{
				return super.onOptionsItemSelected(item);
			}
		}
		return retVal;
	}
}
