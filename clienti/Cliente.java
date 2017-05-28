package clienti;

import java.util.HashSet;
import eccezioni.CodiceFiscalePresenteEccezione;

/**
 * 
 * Cliente dell'agenzia
 * @author Gabriele Fortunato
 * versione 1.0
 * 
 */
public class Cliente implements Comparable<Cliente>{

	private static HashSet<String> codiciFiscali = new HashSet<>();
	private String codiceFiscale;
	private String nome;
	private String cognome;
	private String dataNascita;
	private String indirizzo;
	
	/**
	 * Metodo costruttore
	 * @param codicefiscale
	 * @param nome
	 * @param cognome
	 * @param dataNascita
	 * @param indirizzo
	 * @throws CodiceFiscalePresenteEccezione
	 */
	public Cliente(String codicefiscale, String nome, String cognome, String dataNascita, 
			String indirizzo) throws CodiceFiscalePresenteEccezione {
		this.setCodiceFiscale(codicefiscale);
		this.setNome(nome);
		this.setCognome(cognome);
		this.setIndirizzo(indirizzo);
		this.setDataNascita(dataNascita);
	}
	
	/**
	 * Metodo costruttore
	 * @param codicefiscale
	 * @param nome
	 * @param cognome
	 * @throws CodiceFiscalePresenteEccezione
	 */
	public Cliente(String codicefiscale, String nome, String cognome) throws CodiceFiscalePresenteEccezione {
		this.setCodiceFiscale(codicefiscale);
		this.setNome(nome);
		this.setCognome(cognome);
	}
	
	/**
	 * Imposta il codice fiscale del cliente
	 * @param codiceFiscale
	 * @throws CodiceFiscalePresenteEccezione
	 */
	private void setCodiceFiscale(String codiceFiscale) 
			throws CodiceFiscalePresenteEccezione {
		if (!codiciFiscali.contains(codiceFiscale)){
			this.codiceFiscale = codiceFiscale;
			codiciFiscali.add(codiceFiscale);
		} else {
			throw new CodiceFiscalePresenteEccezione();
		}
	}
	
	/**
	 * Imposta il nome del cliente
	 * @param nome
	 */
	private void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Imposta il cognome del cliente
	 * @param cognome
	 */
	private void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	/**
	 * Imposta la data di nascita del cliente
	 * @param dataNascita
	 */
	private void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}
	
	/**
	 * Imposta l'indirizzo del cliente
	 * @param indirizzo
	 */
	private void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	/**
	 * Restituisce il nome del cliente
	 * @return
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Imposta il cognome del cliente
	 * @return
	 */
	public String getCognome() {
		return cognome;
	}

	@Override
	/**
	 * Restituisce le informazioni sotto forma di stringa
	 */
	public String toString() {
		return 
				"\nCodice fiscale: "+codiceFiscale+" Nome: "+nome+" Cognome: "+cognome
				+" Data di nascita: "+dataNascita+" Indirizzo: "+indirizzo;
	}

	@Override
	/**
	 * Confronta con un altro cliente in base al nome e al cognome
	 */
	public int compareTo(Cliente cliente) {
		if (this.cognome.compareTo(cliente.getCognome())==0){
			return this.nome.compareTo(cliente.getNome());
		} else {
			return this.cognome.compareTo(cliente.getCognome());
		}
	}
	
	/**
	 * Verifica l'uguaglianza con un altro cliente in base al codice fiscale 
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (codiceFiscale == null) {
			if (other.codiceFiscale != null)
				return false;
		} else if (!codiceFiscale.equals(other.codiceFiscale))
			return false;
		return true;
	}
}