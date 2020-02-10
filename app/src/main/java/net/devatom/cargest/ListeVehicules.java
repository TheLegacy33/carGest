package net.devatom.cargest;

import net.devatom.classes.Vehicule;
import net.devatom.classes.parcAutomobile;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListeVehicules extends ListActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.liste_vehicules_layout);

		ArrayAdapter<Vehicule> adapter = new ArrayAdapter<Vehicule>(this, android.R.layout.simple_list_item_1, parcAutomobile.getVehicules());
		setListAdapter(adapter); 	
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Vehicule unVeh = (Vehicule) l.getItemAtPosition(position);
		Intent it = new Intent(getBaseContext(), VueVehicule.class);
		it.putExtra("vehicule", unVeh);
		startActivity(it);
	}
}
