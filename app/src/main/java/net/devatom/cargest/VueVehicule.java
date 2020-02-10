package net.devatom.cargest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import net.devatom.classes.Entretien;
import net.devatom.classes.Vehicule;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class VueVehicule extends Activity implements OnItemClickListener, OnClickListener {

	/*
	 * Déclaration des variables pour les lier aux champs du formulaire d'affichage
	 */
	private TextView ttImmat, ttDateImmat, ttKilometrageVeh;
	private ListView lstvEntretiens;
	private Vehicule monVehicule = null;
	private Button btSend;
	private boolean alreadyStarted = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vue_vehicule_layout);
		
		ttImmat = (TextView) findViewById(R.id.immat);
		ttDateImmat = (TextView) findViewById(R.id.dateimmat);
		ttKilometrageVeh = (TextView) findViewById(R.id.kilometrage);
		
		lstvEntretiens = (ListView) findViewById(R.id.lstentretiens);
		btSend = (Button) findViewById(R.id.bt_send);
		btSend.setOnClickListener(this);
		
		if (!alreadyStarted){
			initDatas();
		}else{
			reloadDatas();
		}
	}
	
	private void initDatas(){
		//String repXml = getResources().getString(R.string.xmldatafile);
		Intent it = getIntent();
		if (it.getExtras() == null){
			Toast.makeText(getBaseContext(), "Véhicule non désigné !", Toast.LENGTH_SHORT).show();
			finish();
		}else{
			monVehicule = (Vehicule) getIntent().getSerializableExtra("vehicule");
			if (monVehicule != null){
				ttImmat.setText(monVehicule.getImmat());
				ttDateImmat.setText(monVehicule.getDateImmatToStr());
				ttKilometrageVeh.setText(String.valueOf(monVehicule.getKilometrage()));
			
				SimpleAdapter lstAdapter = new SimpleAdapter(getBaseContext(), monVehicule.getEntretiens(), R.layout.item_entretiens_layout, 
						new String[] {"entretien"}, new int[] {R.id.itementretien});
				
				lstvEntretiens.setAdapter(lstAdapter);
				lstvEntretiens.setOnItemClickListener(this);
			}
		}
	}
    
	private void reloadDatas(){
		
	}
	
    public static void sendMessage(String pNumero, String pMessage){
        SmsManager smsManager = SmsManager.getDefault();
        int length = pMessage.length();
        if(length > 160) {
            ArrayList<String> messagelist = smsManager.divideMessage(pMessage);
            smsManager.sendMultipartTextMessage(pNumero, null, messagelist, null, null);
        }else{
            smsManager.sendTextMessage(pNumero, null, pMessage, null, null);
        }
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		@SuppressWarnings("unchecked")
		HashMap<String, Entretien> item = (HashMap<String, Entretien>) parent.getItemAtPosition(position);

		Entretien selectedEntretien = item.get("entretien");

		Intent it = new Intent(getBaseContext(), VueEntretien.class);
		it.putExtra("dateEntretien", selectedEntretien);
		startActivity(it);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.bt_send){
			Iterator<HashMap<String, Entretien>> it = monVehicule.getEntretiens().iterator();
			Entretien unEntretien = null;
			String msg = "";
			String msg2 = "";

			if (it.hasNext()){
				msg = "Voici la liste de vos entretiens : \n";
				while (it.hasNext()){
					unEntretien = it.next().get("entretien");
					msg += unEntretien + "\n";
				}
				int nextEntretien = (unEntretien.getKilometrage() + 15000);
				msg += "Votre prochain entretien est prévu à " + String.format("%1$,3d km", nextEntretien);
				msg += " soit dans " + String.format("%1$,3d km", (nextEntretien - monVehicule.getKilometrage()));

				if (monVehicule.getTelProprio().equals("") || monVehicule.getTelProprio() == null){
					msg2 = "Le propriétaire de ce véhicule n'a pas de numéro de téléphone !";
				}else{
					msg2 = "Envoi à " + monVehicule.getTelProprio();
					sendMessage(monVehicule.getTelProprio(), msg);
				}
			}else{
				msg2 = "Ce vehicule n'a aucun entretien enregistré !";
			}
			
			Toast.makeText(getBaseContext(), msg2, Toast.LENGTH_LONG).show();
		}
	}
}
