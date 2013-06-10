package com.gsb.suividevosfrais;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.DatePicker.OnDateChangedListener;

public class RepasActivity extends Activity {

	private int mois;
	private int annee;
	private Integer qte;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_repas);
		// change l'affichage du DatePicker
		Global.changeAfficheDate((DatePicker) findViewById(R.id.datRepas));
		this.valoriseProprietes();
		this.cmdPlus_clic();
		this.cmdMoins_clic();
		this.cmdValider_clic();
		imgReturn_clic();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.repas, menu);
		return true;
	}

	/**
	 * lors du clic sur l'image menu retour au menu.
	 * 
	 * @return void
	 */
	private void imgReturn_clic() {
		((ImageView) findViewById(R.id.imgRepasReturn))
				.setOnClickListener(new ImageView.OnClickListener() {
					public void onClick(View v) {
						retourActivityPrincipal();
					}
				});
	}

	/**
	 * Valorisation des propriétés avec les informations affichées
	 */
	private void valoriseProprietes() {
		annee = ((DatePicker) findViewById(R.id.datRepas)).getYear();
		mois = ((DatePicker) findViewById(R.id.datRepas)).getMonth() + 1;
		// r�cup�ration de la qte correspondant au mois actuel
		qte = 0;
		Integer key = annee * 100 + mois;
		if (Global.listFraisMois.containsKey(key)) {
			qte = Global.listFraisMois.get(key).getRepas();
		}
		((EditText) findViewById(R.id.txtRepas)).setText(qte.toString());
		((EditText) findViewById(R.id.txtRepas)).setKeyListener(null);
		((EditText) findViewById(R.id.txtRepas)).setFocusable(false);
	}

	/**
	 * Sur le clic du bouton valider : sérialisation
	 */
	private void cmdValider_clic() {
		((Button) findViewById(R.id.cmdRepasValider))
				.setOnClickListener(new Button.OnClickListener() {
					public void onClick(View v) {
						Serializer.serialize(Global.filename,
								Global.listFraisMois, RepasActivity.this);
						retourActivityPrincipal();
					}
				});
	}

	/**
	 * Sur le clic du bouton plus : ajout de 1 dans la quantité
	 * 
	 * @return void
	 */
	private void cmdPlus_clic() {
		((Button) findViewById(R.id.cmdRepasPlus))
				.setOnClickListener(new Button.OnClickListener() {
					public void onClick(View v) {
						qte += 1;
						enregNewQte();
					}
				});
	}

	/**
	 * Sur le clic du bouton moins : enléve 1 dans la quantité si c'est
	 * possible
	 * 
	 * @return void
	 */
	private void cmdMoins_clic() {
		((Button) findViewById(R.id.cmdRepasMoins))
				.setOnClickListener(new Button.OnClickListener() {
					public void onClick(View v) {
						qte = Math.max(0, qte - 1); // suppression de 10 si
													// possible
						enregNewQte();
					}
				});
	}

	/**
	 * Sur le changement de date : mise à jour de l'affichage de la qte
	 * 
	 * @return void
	 */
	private void dat_clic() {
		final DatePicker uneDate = (DatePicker) findViewById(R.id.datRepas);
		uneDate.init(uneDate.getYear(), uneDate.getMonth(),
				uneDate.getDayOfMonth(), new OnDateChangedListener() {
					@Override
					public void onDateChanged(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						valoriseProprietes();
					}
				});
	}

	/**
	 * Enregistrement dans la zone de texte et dans la liste de la nouvelle qte,
	 * � la date choisie
	 * 
	 * @return void
	 */
	private void enregNewQte() {
		// enregistrement dans la zone de texte
		((EditText) findViewById(R.id.txtRepas)).setText(qte.toString());
		// enregistrement dans la liste
		Integer key = annee * 100 + mois;
		if (!Global.listFraisMois.containsKey(key)) {
			// creation du mois et de l'annee s'ils n'existent pas déjà
			Global.listFraisMois.put(key, new FraisMois(annee, mois));
		}
		Global.listFraisMois.get(key).setRepas(qte);
	}

	/**
	 * permet de redonner le contrôle à l'activité principal
	 * 
	 * @return void
	 */
	protected void retourActivityPrincipal() {
		Intent intent = new Intent(RepasActivity.this, MainActivity.class);
		startActivity(intent);
	}

}
