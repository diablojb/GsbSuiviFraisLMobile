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

public class EtapeActivity extends Activity {
private int annee;
private int mois;
private Integer qte;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_etape);
		//change le datePicker
		Global.changeAfficheDate((DatePicker) findViewById(R.id.datEtapes)) ;
		//valorisation des propriétés
		valoriseProprietes();
		cmdValider_clic();
		cmdMoins_clic();
		cmdPlus_clic();
		dat_clic();
		enregNewQte();
		imgReturn_clic();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.etape, menu);
		return true;
	}
	
	/**
	 * méthode pour la valorisation des propriétés
	 * @return void
	 */
	private void valoriseProprietes() {
		annee = ((DatePicker)findViewById(R.id.datEtapes)).getYear() ;
		mois = ((DatePicker)findViewById(R.id.datEtapes)).getMonth() + 1 ;
		// récupération de la qte correspondant au mois actuel
		qte = 0 ;
		Integer key = annee*100+mois ;
		if (Global.listFraisMois.containsKey(key)) {
			qte = Global.listFraisMois.get(key).getEtape() ;
		}
		((EditText)findViewById(R.id.txtEtapes)).setText(qte.toString()) ;
		((EditText)findViewById(R.id.txtEtapes)).setKeyListener(null);
		((EditText)findViewById(R.id.txtEtapes)).setFocusable(false);
	}
	
	/**
	 * méthode appelé lors de l'évennement valider
	 * permet la  sérialisation des données
	 * @return void
	 */
    private void cmdValider_clic() {
    	((Button)findViewById(R.id.cmdEtapesValider)).setOnClickListener(new Button.OnClickListener() {
    		public void onClick(View v) {
    			Serializer.serialize(Global.filename, Global.listFraisMois, EtapeActivity.this) ;
    			retourActivityPrincipale() ;    		
    		}
    	}) ;    	
    }
    
  /**
   * méthode sur le bouton "+"
   * Permet d'ajouter de 1 dans la quantité
   * @return void
   */
    private void cmdPlus_clic() {
    	((Button)findViewById(R.id.cmdEtapesPlus)).setOnClickListener(new Button.OnClickListener() {
    		public void onClick(View v) {
    			qte+=1 ;
    			enregNewQte() ;
    		}
    	}) ;    	
    }
    
    /**
     * méthode sur le bouton "-"
     * Permet d'enléver 1 dans la quantité si c'est possible
     * @return void
     */
    private void cmdMoins_clic() {
    	((Button)findViewById(R.id.cmdEtapesMoins)).setOnClickListener(new Button.OnClickListener() {
    		public void onClick(View v) {
   				qte = Math.max(0, qte-1) ; // suppression de 10 si possible
    			enregNewQte() ;
     		}
    	}) ;    	
    }
    
   /**
    * méthode sur le bouton du calendrier
    * Sur le changement de date : mise à jour de l'affichage de la qte
    * @return void
    */
    private void dat_clic() {   	
    	final DatePicker uneDate = (DatePicker)findViewById(R.id.datEtapes) ;
    	uneDate.init(uneDate.getYear(), uneDate.getMonth(), uneDate.getDayOfMonth(), new OnDateChangedListener(){
			@Override
			public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				valoriseProprietes() ;				
			}
    	});       	
    }

	/**
	 *
	 *  Enregistrement dans la zone de texte et dans la liste de la nouvelle qte, à la date choisie
	 *  @return void
	 */
	private void enregNewQte() {
		// enregistrement dans la zone de texte
		((EditText)findViewById(R.id.txtEtapes)).setText(qte.toString()) ;
		// enregistrement dans la liste
		Integer key = annee*100+mois ;
		if (!Global.listFraisMois.containsKey(key)) {
			// creation du mois et de l'annee s'ils n'existent pas déjà
			Global.listFraisMois.put(key, new FraisMois(annee, mois)) ;
		}
		Global.listFraisMois.get(key).setEtape(qte) ;		
	}

	/**
	 * Sur la selection de l'image : retour au menu principal
	 * @return void
	 */
    private void imgReturn_clic() {
    	((ImageView)findViewById(R.id.imgEtapesReturn)).setOnClickListener(new ImageView.OnClickListener() {
    		public void onClick(View v) {
    			retourActivityPrincipale() ;    		
    		}
    	}) ;
    }	
		/**
		 * Retour à l'activité principale (le menu)
		 * @return void
		 */
		private void retourActivityPrincipale() {
			Intent intent = new Intent(EtapeActivity.this, MainActivity.class) ;
			startActivity(intent) ;   					
		}

}
