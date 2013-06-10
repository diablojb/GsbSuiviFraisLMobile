package com.gsb.suividevosfrais;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe métier contenant les informations des frais d'un mois
 *
 */
public class FraisMois implements Serializable {

	private Integer mois ; // mois concerné
	private Integer annee ; // année concernée
	private Integer etape ; // nombre d'étapes du mois
	private Integer km ; // nombre de km du mois
	private Integer nuitee ; // nombre de nuitées du mois
	private Integer repas ; // nombre de repas du mois
	private ArrayList<FraisHf> lesFraisHf ; // liste des frais hors forfait du mois
	
	/**
	 * Constructeur de la classe FraisMois
	 * 
	 * Permet de valoriser les propriétes privée
	 * @param Integer annee 
	 * @param Integer mois
	 */
	public FraisMois(Integer annee, Integer mois) {
		this.annee = annee ;
		this.mois = mois ;
		this.etape = 0 ;
		this.km = 0 ;
		this.nuitee = 0 ;
		this.repas = 0 ;
		lesFraisHf = new ArrayList<FraisHf>() ;
	}

	/**
	 * Ajout d'un frais hors forfait
	 * @param Integer montant le montant du hors forfait
	 * @param String motif le motif du hors forfait
	 * @param Integer jour le jour d'aujourd'hui
	 */
	public void addFraisHf(Integer montant, String motif, Integer jour) {
		lesFraisHf.add(new FraisHf(montant, motif, jour)) ;
	}
	
	/**
	 * Suppression d'un frais hors forfait
	 * @param Integer index position dans la liste
	 * @return void
	 */
	public void supprFraisHf(Integer index) {
		lesFraisHf.remove(index) ;
	}
	
	/**
	 * Getter Mois
	 * Permet de retourner la propriété du mois
	 * @return Integer mois
	 */
	public Integer getMois() {
		return mois;
	}

	/**
	 * Setter mois
	 * Permet de valoriser la propriété privée du mois
	 * @param Integer mois
	 * @return void
	 */
	public void setMois(Integer mois) {
		this.mois = mois;
	}

	/**
	 * Getter annee
	 * Permet de retourner la propriété privée annee
	 * @return Integer annee
	 */
	public Integer getAnnee() {
		return annee;
	}
	/**
	 * Setter annee
	 * Permet de valoriser la propriété privée annee
	 * @param Integer annee
	 * @return void
	 */
	public void setAnnee(Integer annee) {
		this.annee = annee;
	}

	/**
	 * Getter etape
	 * Permet de retourner la propriété privée etape
	 * @return Integer etape le nbr d'étapes
	 */
	public Integer getEtape() {
		return etape;
	}

	/**
	 * Setter etape
	 * Permet de valoriser le la propriété privée etape
	 * @param Integer etape le nombre d'étapes
	 * @return void
	 */
	public void setEtape(Integer etape) {
		this.etape = etape;
	}

	/**
	 * Getter km
	 * Permet de retourner les KM
	 * @return Integer km le nbr des km
	 */
	public Integer getKm() {
		return km;
	}

	/**
	 * Setter km
	 * Permet de valoriser la propriétée privée km
	 * @param Integer km le nbr km
	 * @return void
	 */
	public void setKm(Integer km) {
		this.km = km;
	}

	/**
	 * Getter nuitee
	 * Permet de retoruner le nombre de nuits
	 * @return Integer nuitee le nbr de nuit�e
	 */
	public Integer getNuitee() {
		return nuitee;
	}

	/**
	 * Setter nuitee
	 * Permet de valoriser la propriété privée nuitee
	 * @param Integer nuitee le nbr nuits
	 * @return void
	 */
	public void setNuitee(Integer nuitee) {
		this.nuitee = nuitee;
	}

	/**
	 * Getter repas
	 * Permet de retoruner le nbr de repas
	 * @return Integer repas le nbr de reas
	 */
	public Integer getRepas() {
		return repas;
	}

	/**
	 * Setter repas
	 * Permet de valoriser la propriété privée repas
	 * @param Integer repas le nbr repas
	 * @return void
	 */
	public void setRepas(Integer repas) {
		this.repas = repas;
	}	
	
/**
 * Getter pour les frais hors forfaits
 * Permet de retourner la list des hros forfait
 * @return ArrayList FraisHf 
 */
	public ArrayList<FraisHf> getLesFraisHf() {
		return lesFraisHf ;
	}
	
}
