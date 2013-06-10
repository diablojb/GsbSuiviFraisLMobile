package com.gsb.suividevosfrais;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.BaseAdapter;
import android.widget.Toast;

public class FraisHfAdapter extends BaseAdapter {

	ArrayList<FraisHf> lesFrais ; // liste des frais du mois
	LayoutInflater inflater ;
	Integer key ;  // annee et mois (clé dans la liste)
	Context context ; // contexte pour gérer la sérialisation
	
	/**
	 * Constructeur de l'adapter pour valoriser les propriétés
	 * @param Context le context de la list
	 * @param ArrayList lesFrais la list des frais hors forfait
	 * @param Integer key clé dans la list
	 */
	public FraisHfAdapter(Context context, ArrayList<FraisHf> lesFrais, Integer key) {
		inflater = LayoutInflater.from(context) ;
		this.lesFrais = lesFrais ;
		this.key = key ;
		this.context = context ;
	}
	
	/**
	 * retourne le nombre d'�l�ments de la listview
	 * 
	 * @return Int count 
	 */
	@Override
	public int getCount() {
		return lesFrais.size() ;
	}

	/**
	 * retourne l'item de la listview à un index précis
	 * @param int index la position de l'élément
	 * @return Object object a la position
	 */
	@Override
	public Object getItem(int index) {
		return lesFrais.get(index) ;
	}

	/**
	 * retourne l'index de l'élément actuel
	 * @param int index
	 * @return long itemId
	 */
	@Override
	public long getItemId(int index) {
		return index;
	}

	/**
	 * structure contenant les éléments d'une ligne
	 * 
	 */
	private class ViewHolder {
		TextView txtListJour ;
		TextView txtListMontant ;
		TextView txtListMotif ;
		ImageButton cmdListSuppr ;
	}
	
	/**
	 * Affichage dans la liste
	 * @param int index
	 * @param View convertView
	 * @param ViewGroup parent
	 * @return View
	 */
	@Override
	public View getView(int index, View convertView, ViewGroup parent) {
		ViewHolder holder ;
		if (convertView == null) {
			holder = new ViewHolder() ;
			convertView = inflater.inflate(R.layout.layout_liste, null) ;
			holder.txtListJour = (TextView)convertView.findViewById(R.id.txtListJour) ;
			holder.txtListMontant = (TextView)convertView.findViewById(R.id.txtListMontant) ;
			holder.txtListMotif = (TextView)convertView.findViewById(R.id.txtListMotif) ;
			holder.cmdListSuppr = (ImageButton)convertView.findViewById(R.id.cmdListSuppr);
			convertView.setTag(holder) ;
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		holder.txtListJour.setText(lesFrais.get(index).getJour().toString()) ;
		holder.txtListMontant.setText(lesFrais.get(index).getMontant().toString()) ;
		holder.txtListMotif.setText(lesFrais.get(index).getMotif());
		holder.cmdListSuppr.setTag(index);
		holder.cmdListSuppr.setOnClickListener(supprClick);
		return convertView ;
	}
	/**
	 * méthode pour gérer l'action sur le clique du bouton supprimer un hors forfait
	 * Lors de la suppression le tag du bouton image contient le numéro dans la liste
	 * des frais hors forfaits
	 * 
	 */
	public OnClickListener supprClick = new OnClickListener(){
		@Override
		public void onClick(View v){
			int numBouton = (Integer)v.getTag();//stock le tag qui a la position dans la liste.
			Global.listFraisMois.get(key).getLesFraisHf().remove(numBouton);//supprime l'élément dans la liste
			Serializer.serialize(Global.filename,Global.listFraisMois, context);//enregistrement des modifications dans la liste
			notifyDataSetChanged();//actualise la listView
			//fait apparaitre une notification de message pour l'utilisateur
			Toast.makeText(context, "Le hors forfait vient d'être supprimer!",Toast.LENGTH_SHORT).show();
		};
	};


	
}
