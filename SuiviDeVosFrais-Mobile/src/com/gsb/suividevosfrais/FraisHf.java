package com.gsb.suividevosfrais;

import java.io.Serializable;

/**
 * Classe métier contenant la description d'un frais hors forfait
 *
 */
public class FraisHf  implements Serializable {

	private Integer montant ;
	private String motif ;
	private Integer jour ;
	
	/**
	 * Constructeur de la classe
	 * 
	 * Valorisation des propriétés privées
	 * @param montant
	 * @param motif
	 * @param jour
	 */
	public FraisHf(Integer montant, String motif, Integer jour) {
		this.montant = montant ;
		this.motif = motif ;
		this.jour = jour ;
	}

	/**
	 * Getter montan
	 * 
	 * Permet de visualiser le montant
	 * @return Integer montant
	 */
	public Integer getMontant() {
		return montant;
	}

	/**
	 * Getter motif
	 * 
	 * Permet de visualiser le motif
	 * @return String motif
	 */
	public String getMotif() {
		return motif;
	}

	/**
	 * Getter jour
	 * 
	 * Permet de visualiser le nombre de jour
	 * @return Integer jours
	 */
	public Integer getJour() {
		return jour;
	}

}
