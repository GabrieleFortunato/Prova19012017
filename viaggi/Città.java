package viaggi;

public class Citt� {

	private String nome;
	private String nazione;
		
	/**
	 * Metodo costruttore
	 * @param nome
	 * @param nazione
	 */
	public Citt�(String nome, String nazione) {
		super();
		this.setNome(nome);
		this.setNazione(nazione);
	}

	/**
	 * Imposta il nome della citt�
	 * @param nome
	 */
	private void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Imposta la nazione della citt�
	 * @param nazione
	 */
	private void setNazione(String nazione) {
		this.nazione = nazione;
	}
	
	
	@Override
	/**
	 * Genera l'hashCode in base al nome e alla nazione
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nazione == null) ? 0 : nazione.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}
	
}
