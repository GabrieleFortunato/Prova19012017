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
import viaggi.Citt�;
import viaggi.Viaggio;

public class Agenzia {

	/**
	 * Metodo main
	 * @param args
	 */
	public static void main(String[] args) {		
		Cliente[] clienti = clienti();
		Citt�[] citt� = citt�();
		Viaggio[] viaggi = viaggi(citt�,clienti);
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
					e.printMessage("Codice fiscale gi� presente");
				}
 			} else {
 				int numeroTessera = random.nextInt(2000);
 				int numeroViaggi = random.nextInt(2000);
 				try {
					cliente[i] = new ClientePremium(codiceFiscale+i,nome+i,cognome+i,numeroTessera,numeroViaggi);
				} catch (CodiceFiscalePresenteEccezione e) {
					e.printMessage("Codice fiscale gi� presente");
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
	 * Crea 100 citt�
	 * @return
	 */
	private static Citt�[] citt�(){
		Citt�[] citt� = new Citt�[100];
		for (int i=0;i<citt�.length;i++){
			citt�[i] = new Citt�("nome"+i, "citt�"+i);
		}
		return citt�;
	}
	
	/**
	 * Crea 5 viaggi
	 * @param citt�
	 * @param cliente
	 * @return
	 */
	private static Viaggio[] viaggi(Citt�[] citt�, Cliente[] cliente){
		Random random = new Random();
		Viaggio[] viaggio = new Viaggio[5];
		for (int i=0;i<viaggio.length;i++){
			viaggio[i] = new Viaggio("nome"+i,"data di inizio"+i,"data di fine"+i);
			for (int j=0;j<8;j++){
				
				int ind = random.nextInt(citt�.length);
				Citt� c = citt�[ind];
				viaggio[i].aggiungiCitt�(c);
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