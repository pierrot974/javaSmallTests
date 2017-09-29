/* ce programme demande à l'utilisateur d'entrer un nombre entier et le convertit en lettre
	par exemple, l'utilisateur entre 586,01
	et le programme répond : cinq cents quatre-vingt six euros et un centime
*/

import java.util.Scanner ;

class nombreToLettre {
	
	public static void main(String[]args) {
		// Déclaration et initialisation des variables
		float saisi = 0 ;		//saisi de l'utilisateur
		String phrase = "" ;	//réponse du programme
		// Traitement
		saisi = saisiNombre() ;
		phrase = conversionTexte(saisi) ;
		afficherTexte(phrase) ;
	}
	
	public static float saisiNombre() {
		Scanner lireclav = new Scanner(System.in) ;
		System.out.print("Entrez un nombre positif") ;
		float saisi = lireclav.nextInt() ;
		return saisi ;
	}
	
	public static String conversionTexte(float saisi) {
		
		
		String ordreGrandeur[] = {"milliard","million","mille",""} ;
		float  nombreTraite = saisi / 1000000000 ;
		float valeur = 0 ;
		String motCDU = "" ;
		String phraseCDU = "" ;
		
		for(int compteur = 0;compteur<=3;compteur++) {
			valeur = (int)(nombreTraite) ;
			if (valeur != 0) {
				motCDU = conversionCentaine(valeur) ;
				nombreTraite = cduSuivant(nombreTraite,valeur) ;
				phraseCDU = phraseCDU + " " + motCDU + " " + ordreGrandeur[compteur] ;
				if (valeur > 1 && compteur <= 1) {
					phraseCDU = phraseCDU + " " ;
				}
				phraseCDU = phraseCDU + " " ;
			}
		}
		
		// Gestion des décimales
		valeur = (saisi*100) % 100 ;
		motCDU = "euros et " + conversionCentaine(valeur) + " centime" ;
		if (valeur > 1) {
			phraseCDU = phraseCDU + "s" ;
		}
		return phraseCDU ;
	}
	
	public static String conversionCentaine(float valeur) {
		String tabNbTexte[][] = { {"zero","un","deux","trois","quatre","cinq","six","sept","huit","neuf","dix","onze","douze","treize","quatorze","quinze","seize"} , {"","","vingt","trente","quarante","cinquante","soixante","soixante-dix","quatre-vingt","quatre-vingt-dix"} } ;
		int centaine = (int)(valeur/100) ;
		int dizaine = 0 ;
		int unite = 0 ;
		String motCDU = "" ;
		if (centaine == 1) {
			motCDU = "cent" ;
		}
		else if (centaine > 0) {
			motCDU = motCDU + " " + tabNbTexte[0][centaine] + "cents" ;
		}
		dizaine = (int)(valeur/10) % 10 ;
		unite = (int)(valeur % 10) ;
		if (dizaine <= 1 & unite < 7 & unite > 0) {
			motCDU = motCDU + " " + tabNbTexte[0][dizaine*10+unite] ;
		}
		if (dizaine == 1 & unite >= 7) {
			motCDU = motCDU + " " + tabNbTexte[0][10] + "-" + tabNbTexte[0][unite] ;
		}
		if (dizaine > 1) {
			if (dizaine == 7) {
				if (unite == 1) {
					motCDU = motCDU + tabNbTexte[1][dizaine-1] + "-et-" + tabNbTexte[0][unite+10] ;
				}
				else if (unite <=6) {
					motCDU = motCDU + " " + tabNbTexte[1][dizaine-1] + "-" + tabNbTexte[0][unite+10] ;
				}
				else {
					motCDU = motCDU + " " + tabNbTexte[1][dizaine] + " " + tabNbTexte[0][unite] ;
				}
			}
			else if (dizaine == 9) {
				if (unite <= 6) {
					motCDU = motCDU + " " +tabNbTexte[1][dizaine-1] + "-" + tabNbTexte[0][unite+10] ;
				}
				else {
					motCDU = motCDU + " " + tabNbTexte[1][dizaine] + tabNbTexte[0][unite] ;
				}
			}
			else {
				if (unite == 1) {
					motCDU = motCDU + " " + tabNbTexte[1][dizaine-2] + "-et-" + tabNbTexte[0][unite] ;
				}
				else {
					motCDU = motCDU + " " + tabNbTexte[1][dizaine-2] + "-" + tabNbTexte[0][unite] ;
				}
			}
				
		}
		return motCDU ;
	}
	public static float cduSuivant(float nombreTraite, float valeur){
		float nouveauCDU = (nombreTraite - valeur) * 100 ;
		return nouveauCDU ;
	}
	public static void afficherTexte(String phrase) {
		System.out.print(phrase) ;
	}
}
