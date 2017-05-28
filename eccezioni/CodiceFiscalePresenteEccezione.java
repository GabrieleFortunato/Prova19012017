package eccezioni;

import java.util.logging.Logger;

/**
 * 
 * Eccezione sollevata nel caso in cui il codice fiscale è gia presente
 * @author Gabriele Fortunato
 * versione 1.0
 * 
 */
public class CodiceFiscalePresenteEccezione extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Stampa a video un messaggio quando viene lanciata l'eccezione
	 * @param message
	 */
	public void printMessage(String message){
		Logger.getLogger(message);
	}
	
}
