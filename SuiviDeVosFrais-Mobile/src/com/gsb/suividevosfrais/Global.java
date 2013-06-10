package com.gsb.suividevosfrais;

import java.lang.reflect.Field;
import java.util.Hashtable;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;


/**
 * Classe abstraite pour la gestion de l'application
 *
 * @author Dev-jb
 */
public abstract class Global {

	// tableau d'informations mémorisées
	public static Hashtable<Integer, FraisMois> listFraisMois = new Hashtable<Integer, FraisMois>() ;

	// fichier contenant les informations sérialisées
	public static final String filename = new String("save.fic") ;

/**
 * Modification de l'affichage de la date (juste le mois et l'ann"e, sans le jour)
 * @param datePicker re"oie l'objet datePicker
 * @return void
 */
	public static void changeAfficheDate(DatePicker datePicker) {
		try {
		    Field f[] = datePicker.getClass().getDeclaredFields();
		    for (Field field : f) {
		        if (field.getName().equals("mDaySpinner")) {
		            field.setAccessible(true);
		            Object dayPicker = new Object();
		            dayPicker = field.get(datePicker);
		            ((View) dayPicker).setVisibility(View.GONE);
		        }
		    }
		} catch (SecurityException e) {
		    Log.d("ERROR", e.getMessage());
		} catch (IllegalArgumentException e) {
		    Log.d("ERROR", e.getMessage());
		} catch (IllegalAccessException e) {
		    Log.d("ERROR", e.getMessage());
		}	
	}
	
}
