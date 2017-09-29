import java.util.Scanner;

public class Conversion{

public static void main (String[] args){

   regroupeFonctions();

}




public static void regroupeFonctions(){
	
	String saisieUser = " " ;
	   int longueurSaisie = 0 ;
	   int comptageCDU = 0 ;
	   String resultatFinal =  " ";


	   saisieUser = recupSaisie(); 												//cette fonction recupere la saisie utilisateur
	   longueurSaisie = chercheVirgule(saisieUser);								//celle ci determine si le nombre saisi est a decimale
	   
	   if (longueurSaisie == 1 || longueurSaisie ==4 ||longueurSaisie==7 ||longueurSaisie == 10){
		   saisieUser = "00" + saisieUser;										//ici on gere le cas d'une saisie inferieure a 100 ou 10
		   longueurSaisie += 2;
	   }
	   else if (longueurSaisie == 2 || longueurSaisie == 5 || longueurSaisie== 8 || longueurSaisie == 11){
		   saisieUser = "0" + saisieUser;
		   longueurSaisie ++ ;
	   }
	   
	   comptageCDU = comptage(longueurSaisie);									//Cette fonction determine le nombre de CDU(centazine dizaine unite) presents dans la saisie

	   resultatFinal = ensembleNombre(comptageCDU,saisieUser,longueurSaisie);   //La fonction ensembleNomùbre regroupe la partie du programme chargee d effectuer la conversion
	   
	   
	   System.out.println(resultatFinal);										//ici on affiche le resultat final
}


public static String recupSaisie(){    //ici on regroupe donc l 'affichage de la consigne et la recuperation grace a Scanner

   String saisieUser = " ";
   Scanner sc = new Scanner(System.in);
   System.out.println("Veuillez saisir un nombre entre 999 milliards et 0 compris afin de le convertir en lettres : ");
   saisieUser = sc.nextLine();
   
   return saisieUser;

}


public static int comptage(int longueurSaisie){
   int verif2 = 0;							//ici, on decoupe la saisie(sans la decimale) pour verifier le nombre de CDU
   double verif3 = 0;						//Pour se faire, on recupere la longueur de la saisie sans decimale et on divise par 3
   double verif = 0 ;						//car on veut savoir combien de membrtes de trois il y a
   verif = longueurSaisie ;
   verif = verif/3;							
   verif3 = Math.floor(verif);				//ensuite on recupere la partie entiere du double et on compare a la partie integrale
   if (verif3 != verif){                    //si c'est identique, on retounre la valeur qui represente alors le nombre de CDU
   
       verif2 = (int)verif + 1 ;			//sinon, cela veut dire que l'on a un CDU incomplet et on rajoute donc 1 pour le prendre en compte
      } 
   else
      {
       verif2=(int)verif;     
      }   
      
     
   return verif2 ;   

}


public static int chercheVirgule(String saisieUser){ //cette fonction nous permet de determiner si la saisie est a decimale ou non

   String virgule =",";
   int longueurTT = 0 ;
   String lettre = " ";
   int longueurSaisie = 0 ;
   int cpt = 0;
   
   longueurTT = saisieUser.length();
   while (cpt < longueurTT ) {
      lettre = saisieUser.substring(cpt,cpt+ 1);   //on parcourt donc la saisie jusqu'a trouver ou non une virgule
      
      if (lettre.equals(virgule)){				  //si on la trouve, on conserve la position de la partie gauche qui est definit par le compteur de boucle
         longueurSaisie = cpt;
      }
      cpt++;
   }
   if (longueurSaisie == 0){
         longueurSaisie = longueurTT;      		 //sinon, on garde la longueur totale du mot!
   }
      
   return longueurSaisie;
}
   
   
public static String Centaine(String saisieUser,int longueurSaisie){   //Cette fonction permet de convertir en lettre la partie Centaine du CDU

   String tabUnite[][] = {{"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19"}
   ,{"zero","un","deux","trois","quatre","cinq","six","sept","huit","neuf","dix","onze","douze","treize","quatorze","quinze","seize","dix-sept","dix-huit","dix-neuf"}};
    
   //Pour se faire, on utilise donc un tableau a deux dimensions qui contient d'une part le chiffre et de l'autre son equyivalent en lettres
   
   String c = saisieUser.substring(longueurSaisie-3,longueurSaisie-2);  //ici on recupere la partie C du CDU

   if(c.equals("0")){													//ensuite on lance les comparaisons afin de savoir quel est le chiffre
	   c = " ";
   }
   if(c != "0"){
      if(c.equals("1")){                     							//on prend en compte le cas particulier du un !
         c = "cent";	
	 
      }
      else{
         for(int posC = 2; posC < 10; posC++){						    //ici on parcourt les autres chiffres	
	    if(c.equals(tabUnite[0][posC])){
	       c = tabUnite[1][posC] +" " + "cent";

	    }
	 }
      }
   }
   
   return c;
}



public static String ensembleNombre(int comptageCDU,String saisieUser,int longueurSaisie){
   String CDU = " ";
   String resultatFinal = " ";
   String resultatDecim = " ";
   int longueurSaisieTransit = 0;
   longueurSaisieTransit = longueurSaisie ;
   if(longueurSaisie != saisieUser.length())
   {
    resultatFinal = decimal(saisieUser);
    resultatDecim = resultatFinal;
	resultatFinal = "euros et "+resultatFinal + " centimes.";
   }
   else
   {
      resultatFinal = "euros et zero centimes.";
   }
   
   for (int cpt = 0 ; cpt < comptageCDU ; cpt++){ 
	   
	   CDU = trioNb(saisieUser,longueurSaisieTransit);	   	
	    
	    if     (cpt==1  ){
	    	if(CDU.equals("   un ")){
	    		CDU = "";
	    	}
	    	if(CDU.equals("   ")){
			 
	    	}
	    	else{
	    		resultatFinal= " mille " + resultatFinal;	
	    	}
		  }
		else if (cpt==2){
			if(CDU.equals("   ")){
				 
	    	}
	    	else{
	    		resultatFinal= " million " + resultatFinal;	
	    	}
		} 
		else if (cpt==3){
			if(CDU.equals("   ")){
				 
	    	}
	    	else{
	    		resultatFinal= " milliard " + resultatFinal;	
	    	}
		}	
	   
	    //if (cpt != 1 & CDU !="1"){ 
	   resultatFinal = CDU + resultatFinal;
		//}
	   longueurSaisieTransit = longueurSaisieTransit - 3 ;

   }
   if(resultatFinal.equals("   euros et zero centimes.")){
	   resultatFinal = "zero euros et zero centimes.";
   }
   if(resultatFinal.equals("   "+"euros et "+ resultatDecim + " centimes.")){
	   resultatFinal = "zero"+ resultatFinal;
   }
   
   return resultatFinal;
   
}


public static String trioNb ( String saisieUser, int longueurSaisieTransit){	//cette fonction regroupe la calcul de la centaine et celui du DU
	String du = " ";
	String c  = " ";
	String CDU = " ";
	
	du = DU(saisieUser,longueurSaisieTransit);					//on fait donc juste un appel a fonctions pour recuperer les valeurs cherchees
	c  = Centaine(saisieUser,longueurSaisieTransit);
	CDU = c +" "+ du ;
	return CDU;
	
	
}



public static String DU (String saisieUser, int longueurSaisieTransit){  		//Cette fonction convertit la partie dizaine et unite en lettre
	
	String tabUnite[][] = {{"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19"}
   ,{"zero","un","deux","trois","quatre","cinq","six","sept","huit","neuf","dix","onze","douze","treize","quatorze","quinze","seize","dix-sept","dix-huit","dix-neuf"}};
    
	String tabDiz[][]  ={{" "," ","20","30","40","50","60"},{" "," ","vingt","trente","quarante","cinquante","soixante"}};
	
	String d =" ";					//la difficulte etant de prendre en comtpe toutes les particularites de la langue francaise!
	String u =" ";
	String DUtransit = " ";			//on utilise donc deux tableaux a deux dimensions, l'un comprenant les dizaines "simples" et l'autres les unites de un a dix neuf
	Boolean unadixneuf= false;
	
	DUtransit = saisieUser.substring(longueurSaisieTransit-2,longueurSaisieTransit);//on recupoere ensuite la partie concernee
	d = DUtransit.substring(0,1);
	u = DUtransit.substring(1,2);
		
	for (int indTab=0; indTab <20;indTab++){					//et on lance toutes les comparaisons pour trouver d'abord la dizaine non composee
		if(DUtransit.equals(tabUnite[0][indTab])){				//puis assigner l'unite en fonction de celle ci
			DUtransit = tabUnite[1][indTab] + " ";				//en commencant par le plus simple : 1 a 19
			unadixneuf = true;
			
		}
	}
	if (DUtransit.equals("00")){								//ensuite on gere le cas du 00 et on amorce un booleen si on le trouve
		unadixneuf = true;										//ce qui nous evitera de lancer les comapraisons suivantes au cas ou tel est son composant
		DUtransit = " ";
	}
	if (unadixneuf==false){
		for(int cpt =2 ; cpt < 7 ; cpt ++){
			if(d.equals(tabUnite[0][cpt])){						//ensuite on gere tous les autres cas de figures!! 
			d = tabDiz[1][cpt] + " ";
			
				for(int cpt2 =1; cpt2 < 10;cpt2++){
					if (u.equals(tabUnite[0][cpt2])){
						u = tabUnite[1][cpt2] + " ";
					}
					if (u.equals("0")){
						u = "";
					}
				}
			}
		}

		switch (d){
			
		    case "0" :
		       d= " ";
		       for(int pos = 1 ; pos < 20 ; pos++){
		    	   if(u.equals(tabUnite[0][pos])){
		    		   u = tabUnite[1][pos] + " ";
		    		   
		    	   }
		       }
		       break;
		
		
			case "7" :
			   d= tabDiz[1][6];
			   for(int pos = 0 ; pos < 10 ;pos++){  					//et ici on gere les dizaines composées !! de 70 a 90
				   if(u.equals(tabUnite[0][pos])){
					   u =" "+ tabUnite[1][pos+10] + " ";
				   }
			   }
			   break;
			
			case "8" : 
			   d= tabUnite[1][4] + "-" + tabDiz[1][2];
			   for(int pos = 1; pos < 10; pos++){
				   if(u.equals(tabUnite[0][pos])){
					   u=" " + tabUnite[1][pos]+ " ";
				   }
			   }
			   break;
			
			case "9" :
			   	d= tabUnite[1][4] + "-" + tabDiz[1][2];
				for(int pos = 0 ;pos < 10;pos++){
				   if(u.equals(tabUnite[0][pos])){
					   u =" "+ tabUnite[1][pos+10]+" ";
				   }
			   }
			   break;
		}
		
		DUtransit= d+u;
		
	}
	
	return DUtransit;
	
	
	
}



public static String decimal(String saisieUser){
	
	int longueurTT = saisieUser.length();
	
	String decimSaisie = DU(saisieUser,longueurTT);

	return decimSaisie;
	
}



}