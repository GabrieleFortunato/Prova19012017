package agenzia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Random;
import clienti.Cliente;
import clienti.ClientePremium;
import eccezioni.CodiceFiscalePresenteEccezione;
import eccezioni.NumeroTesseraEccezione;
import eccezioni.NumeroViaggiEccezione;
import viaggi.Città;
import viaggi.Viaggio;

public class Agenzia {

	/**
	 * Metodo main
	 * @param args
	 */
	public static void main(String[] args) {		
		Cliente[] clienti = clienti();
		Città[] città = città();
		Viaggio[] viaggi = viaggi(città,clienti);
		stampaNumeroMedioClientiPremium(viaggi);
		stampaSuFile(viaggi);
	}
	
	/**
	 * Crea 300 clienti
	 * @return
	 */
	private static Cliente[] clienti(){
		Random random = new Random();
		Cliente[] cliente = new Cliente[300];
		for (int i=0;i<cliente.length;i++){
			double prob = random.nextDouble();
			String codiceFiscale = "codicefiscale";
			String nome = "nome";
			String cognome = "cognome";
			if (prob>80/300.0){
				try {
					cliente[i] = new Cliente(codiceFiscale+i,nome+i,cognome+i);
				} catch (CodiceFiscalePresenteEccezione e) {
					e.printMessage("Codice fiscale già presente");
				}
 			} else {
 				int numeroTessera = random.nextInt(2000);
 				int numeroViaggi = random.nextInt(2000);
 				try {
					cliente[i] = new ClientePremium(codiceFiscale+i,nome+i,cognome+i,numeroTessera,numeroViaggi);
				} catch (CodiceFiscalePresenteEccezione e) {
					e.printMessage("Codice fiscale già presente");
				} catch (NumeroTesseraEccezione e) {
					e.printMessage("Numero di tessera non valido");
				} catch (NumeroViaggiEccezione e) {
					e.printMessage("Numero di viaggi non valido");
				}
 			}
		}
		return cliente;
	}
	
	/**
	 * Crea 100 città
	 * @return
	 */
	private static Città[] città(){
		Città[] città = new Città[100];
		for (int i=0;i<città.length;i++){
			città[i] = new Città("nome"+i, "città"+i);
		}
		return città;
	}
	
	/**
	 * Crea 5 viaggi
	 * @param città
	 * @param cliente
	 * @return
	 */
	private static Viaggio[] viaggi(Città[] città, Cliente[] cliente){
		Random random = new Random();
		Viaggio[] viaggio = new Viaggio[5];
		for (int i=0;i<viaggio.length;i++){
			viaggio[i] = new Viaggio("nome"+i,"data di inizio"+i,"data di fine"+i);
			for (int j=0;j<8;j++){
				
				int ind = random.nextInt(città.length);
				Città c = città[ind];
				viaggio[i].aggiungiCittà(c);
			}
			for (int j=0;j<50;j++){
				int ind = random.nextInt(cliente.length);
				Cliente c = cliente[ind];
				viaggio[i].aggiungiCliente(c);
			}
		}
		return viaggio;
	}
	
	/**
	 * Stampa a video il numero medio dei clienti
	 * @param viaggi
	 */
	private static void stampaNumeroMedioClientiPremium(Viaggio[] viaggi){
		int numero = 0;
		for (Viaggio v:viaggi){
			numero += v.numeroClientiPremium();
		}
		System.out.println("Numero medio di clienti premium: "+numero/viaggi.length);
	}
	
	/**
	 * Stampa i dati du file
	 * @param viaggi
	 */
	private static void stampaSuFile(Viaggio[] viaggi){
		try {
			PrintStream output = new PrintStream(new File("agenzia.csv"));
			for (Viaggio v: viaggi){
				output.println(v);
			}
			output.flush();
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}