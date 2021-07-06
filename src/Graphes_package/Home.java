package Graphes_package;
import java.util.Arrays;
import java.util.Scanner;



public class Home {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int nbLigneOrigin=7, nbColOrigin=8;		// Nombre de ligne et de colonnes du tableau des demandes et Stock
		
		
		int calculCout =0;						// Variable qui stocke le calcul de coût
		
				// initialisation du tableau à 2 dimension
				// on utilise las lignes pour les destinations et le colonnes pour les Stocks
		int [][] tableauOrigine = new int [nbLigneOrigin][nbColOrigin];		
				// tableau qui sert à stocker les coordonnée d'une case
				// qui permettra de chercher la ligne ou la colonne avec le coût le plus faible
		String [] coordonnee =new String[2];
		
				// tableau qui sert à stocker les coordonnée du resultat final de la methode Ballas hammer
		int [][] tableauResultat = new int [nbLigneOrigin-2][nbColOrigin-2];	
		
		
		int OriginX=1,OriginY=3;    // coordonnées des origines (Stocks)
		int DestX=3,DestY=1;   		 // coordonnées des destinations (Magasins)
		
			// remplissage des demandes
		tableauOrigine[1][3]=10;
		tableauOrigine[1][4]=11;
		tableauOrigine[1][5]=15;
		tableauOrigine[1][6]=5;
		tableauOrigine[1][7]=4;
		// remplissage des Stocks
		tableauOrigine[3][1]=12;
		tableauOrigine[4][1]=11;
		tableauOrigine[5][1]=14;
		tableauOrigine[6][1]=8;
		//tableauOrigine[6][1]=4;
		
			// remplissage des coûts
		tableauOrigine[3][3]=7 ;
		tableauOrigine[3][4]=12;
		tableauOrigine[3][5]=1;
		tableauOrigine[3][6]=5;
		tableauOrigine[3][7]=9;
		
		tableauOrigine[4][3]=15;
		tableauOrigine[4][4]=3;
		tableauOrigine[4][5]=12;
		tableauOrigine[4][6]=6;
		tableauOrigine[4][7]=14;
		
		tableauOrigine[5][3]=8;
		tableauOrigine[5][4]=16;
		tableauOrigine[5][5]=10;
		tableauOrigine[5][6]=12;
		tableauOrigine[5][7]=7;
		
		tableauOrigine[6][3]=18;
		tableauOrigine[6][4]=8;
		tableauOrigine[6][5]=17;
		tableauOrigine[6][6]=11;
		tableauOrigine[6][7]=16;
		
		
		
for (int o=0;o<10;o++) {
		
		System.out.println("ITERATION N° "+o+"\n");
	
		// Boucle qui va recupérer pour chaque ligne et fait la diffférence entre les 2 valeurs les plus petites
		for (int i=3; i<nbColOrigin-1;i++) {
			int differenceLigne = calculMiniLigne(tableauOrigine, nbColOrigin-3, i); 		// appel de la fonction qui trouve la différence entre les 2 plus petite valeurs
			tableauOrigine[i][2]=differenceLigne;											// on stocke dans le tableau cette valeur
			
		}
		
		// Boucle qui va recupérer pour chaque colonne et fait la diffférence entre les 2 valeurs les plus petites
		for (int p=3; p<nbLigneOrigin+1;p++) {
			int differenceCol = calculMiniColonne(tableauOrigine,p,nbLigneOrigin-3);		// appel de la fonction qui trouve la différence entre les 2 plus petite valeurs
			tableauOrigine[2][p]=differenceCol;											// on stocke dans le tableau cette valeur
		
		}
		
		
		// affichage du tableau principale
		System.out.println("***************      TABLEAU PRINCIPALE     ***************************************");
		for (int a=0;a<7;a++){
				for (int z=0;z<8;z++){
					if (tableauOrigine[a][z]!=0) {
								if (z==2) {
									System.out.printf("-"+"%3s",tableauOrigine[a][z]+"-   ");
								}
								else if (a==2) {
									System.out.printf("-"+"%3s",tableauOrigine[a][z]+"  -  ");
								}
								else {
									System.out.printf("%7s",tableauOrigine[a][z]+"  ,  ");
								}
					}
					else {
						System.out.printf("%7s","  ,  ");
					}
				}
				System.out.println();
		}
		
		
			
		// appel de la fonction qui va retourner la valeur la plus grande 
		coordonnee = valeurPlusGrande(tableauOrigine,nbColOrigin-3,nbLigneOrigin-3 );
				
		// appel de la fonction qui va retourner les coordonnees de la valeur la + petite
		// soit sur la ligne soit sur la colonne
		int coord = Integer.parseInt(coordonnee[0]);			// Valeur la plus grande
		coordonnee= pointCoutPlusFaible(tableauOrigine, coordonnee,coord, nbColOrigin-3,nbLigneOrigin-3);
		
		// On stocke la quantite soit de la destination soit de l'origine dans le tableau 'tableauResultat'
		affectation(tableauOrigine, coordonnee,tableauResultat, nbColOrigin-3,nbLigneOrigin-3);
		

		// affichage du tableau des résultat
		System.out.println("\n***************      TABLEAU RESULTAT     ***************************************\n");
		for (int a=0;a<nbLigneOrigin-3;a++){
				for (int z=0;z<nbColOrigin-3;z++){
					System.out.printf("%7s",tableauResultat[a][z]+"  ,  ");
		}
				System.out.println();
		}
		
		

		
		 Scanner sc = new Scanner(System.in);
		 System.out.println("\nAppuyer sur une touche pour la suite de l'itération   ***********************************\n"); 

		 


		 String str = sc.nextLine();
}	
		
		

	}
	
	
	
	
	
/*
 * 	Méthode calculMiniLigne
 * On créer un tableau temporaire 'plusPetit' qui permet de récupérer une ligne complete
 * du tableau principal 'tableauOrigine'. Ensuite on fait un trie du tableau 'plusPetit'
 * et on soustrait les 2 plus petites valeurs.
 */
public static  int  calculMiniLigne(int [][]tableauOrigine , int colonne, int ligne) {
	
	int [] plusPetit = new int [colonne];				// initialisation du tableau provisoire
	for (int i=0; i<colonne;i++) {
		if (tableauOrigine[ligne][i+3]!=0) {
			plusPetit[i]=tableauOrigine[ligne][i+3];	// on ajoute les valeurs d'une ligne du tableau dans 'plusPetit'
		}
	}
	Arrays.sort(plusPetit);								// on trie le tableau 'plusPetit'
	
	/*
	 * Ici on fait une boucle while pour trouver la premiere valeur non nul du tableau 'plusPetit' qui a été trier
	 * donc on recupère les 2 valeur les plus petites. Il suffit ensuite de les soustraires.
	 * dans le cas où il ne reste plus qu'une seule valeur on gardera uniquement cette valeur.
	 */
	int indice=0;										// variable qui permet de se positionner dans la recherche des valeurs les + faible dans 
														// le tableau 'plusPetit'
	
	/*
	 *   Le but est de trouver la ou les 2 valeurs les plus petites.
	 *   Soit il n'y a qu'une seule valeur on la retourne tel quel, soit il y a 2 valeurs
	 *   et dans ce cas on les soustraits avant de les retournées
	 */
	while (plusPetit[indice]==0 && indice<plusPetit.length-1){		
		indice++;
	}
	if (indice==(plusPetit.length-1)){
		return plusPetit[indice];
	}
	else {
		return plusPetit[indice+1]- plusPetit[indice];					// on retourne la différence entre les 2 plus petites valeurs
	}

}



/*
 * 	Méthode calculMiniLigne
 * On créer un tableau temporaire 'plusPetit' qui permet de récupérer une colonne complete
 * du tableau principal 'tableauOrigine'. Ensuite on fait un trie du tableau 'plusPetit'
 * et on soustrait les 2 plus petites valeurs.
 */
public static  int  calculMiniColonne(int [][]tableauOrigine , int colonne, int ligne) {

	int [] plusPetit = new int [ligne];					// initialisation du tableau provisoire
	for (int i=0; i<ligne;i++) {
		if (tableauOrigine[i+3][colonne]!=0) {
			plusPetit[i]=tableauOrigine[i+3][colonne];		// on ajoute les valeurs d'une colonne du tableau dans 'plusPetit'
		}
	}
	Arrays.sort(plusPetit);	
	
	/*
	 * Ici on fait une boucle while pour trouver la premiere valeur non nul du tableau 'plusPetit' qui a été trier
	 * donc on recupère les 2 valeur les plus petites. Il suffit ensuite de les soustraires.
	 * dans le cas où il ne reste plus qu'une seule valeur on gardera uniquement cette valeur.
	 */
	int indice=0;
	while (plusPetit[indice]==0 && indice<plusPetit.length-1){
		indice++;
	}
	if (indice==(plusPetit.length-1)){
		return plusPetit[indice];
	}
	else {
		return plusPetit[indice+1]- plusPetit[indice];					// on retourne la différence entre les 2 plus petites valeurs
	}

}


/*
 * Ici on recherche la valeur la plus grande parmis les valeurs calculer dans 'calculMiniColonne' et 'calculMiniLigne'
 */
public static String[] valeurPlusGrande (int [][]tableauOrigine, int colonne, int ligne) {
	
	String [] point = new String [2];						// initialisation du tableau des coordonnées
	int [] plusGrandCol = new int [colonne];				// initialisation du tableau provisoire
	for (int i=0; i<colonne;i++) {
		plusGrandCol[i]=tableauOrigine[2][i+3];				// on ajoute les valeurs d'une colonne du tableau dans 'plusGrandCol'
	
	}
	Arrays.sort(plusGrandCol);								// on trie le tableau 'plusGrandCol'

	
	int [] plusGrandLigne = new int [ligne];				// initialisation du tableau provisoire
	for (int z=0; z<ligne;z++) {
		plusGrandLigne[z]=tableauOrigine[z+3][2];			// on ajoute les valeurs d'une colonne du tableau dans 'plusGrand'
		
	}
	Arrays.sort(plusGrandLigne);							// on trie le tableau 'plusGrandLigne'
	
	
	/*
	 * On compare la valeur la plus grande de la ligne et de la colonne
	 * et on renvoi la valeur la plus grande des 2 plus l'indication "C" pour colonne et "l" pour ligne
	 */
	if (plusGrandLigne[ligne-1]< plusGrandCol[colonne-1]) {
		point[0]=String.valueOf(plusGrandCol[ligne]);
		point[1]="C";
		return point;					
	}
	else {
		point[0]=String.valueOf(plusGrandLigne[colonne-2]);
		point[1]="L";
		return point;	
		
	}
	
}


/*
 * pointCoutPlusFaible : Cette méthode recherche le coût le plus faible 
 * soit sur la ligne soit sur la colonne
 */

public static String[] pointCoutPlusFaible(int [][]tableauOrigine, String []coordonnee, int plusGrand,  int nbColonne, int nbLigne) {
	String [] point = new String [2];	
	int ligneTemp=9999999;							// Valeur temporaire de la position de la ligne. 999999 pour etre sûr que cette valeur soit la plus grande
		int posLigne=0;								// Stocke la positiopn de la ligne de la valeur trouvée
		int posCol=0;								// Stocke la positiopn de la colonne de la valeur trouvée
		if (coordonnee[1] =="C") {					//	Si la valeur est trouvée dans une colonne 
			for (int i=3;i<nbColonne+3;i++) {					// boucle de parcours de la colonne	
				if (tableauOrigine[2][i]==plusGrand) {			// si on trouve la leur la plus grande
					posCol=i;									// on stocke la valeur trouvée dans posCol
					for (int t=3;t<nbLigne+3;t++) {				// boucle pour poarcourir les lignes de la colonne
						
						if (tableauOrigine[t][i] <ligneTemp && tableauOrigine[t][i]!=0) {	 // verifie si on trouve une valeur plus petite mais differente de 0
							posLigne=t;														 // si trouvé on stocke la valeur dans posLigne
							ligneTemp =tableauOrigine[t][i];								// et on affecte la valeur trouvée dans ligneTemp
						}
					}
					point[0]=String.valueOf(posLigne);					// on stocke le resultat de la ligne dans point[0]
					point[1]=String.valueOf(posCol);					// on stocke le resultat de la colonne dans point[0]
					
				}
			}
		}
		else {
			for (int i=3;i<nbLigne+3;i++) {
				if (tableauOrigine[i][2]==plusGrand) {
					posLigne=i;
					for (int t=3;t<nbColonne+3;t++) {
						if (tableauOrigine[i][t]<ligneTemp && tableauOrigine[i][t]!=0) {
							posCol=t;
							ligneTemp =tableauOrigine[i][t];
						}
					}
					point[0]=String.valueOf(posLigne);
					point[1]=String.valueOf(posCol);
				
				}
			}
		}
		
		
	return point;
}


/*
 * Méthode qui permet de stocker les résultats trouvés dans un nouveau tableau  'tableauResultat'
 */
public static void affectation(int [][]tableauOrigine, String []coordonnee, int [][]tableauResultat, int nbColonne, int nbLigne) {
	
	int ligne = Integer.parseInt(coordonnee[0]);				// on recupere la coordonnée ligne
	int colonne = Integer.parseInt(coordonnee[1]);				// // on recupere la coordonnée colonne
	if (tableauOrigine[ligne][1]<tableauOrigine[1][colonne]) {								// si l'offre est inférieure à la demande 
		tableauResultat[ligne-3][colonne-3]=tableauOrigine[ligne][1];						// on stocke 
		tableauOrigine[1][colonne]=tableauOrigine[1][colonne]-tableauOrigine[ligne][1];		// on soustrait l'offre à la demande
		for (int x=0;x<nbColonne+3;x++) {													
			tableauOrigine[ligne][x]=0;														// l'offre de la ligne etant à 0 on supprime la ligne en mettant tout à 0
		}
	}
	else {
		tableauResultat[ligne-3][colonne-3]=tableauOrigine[ligne][1];
		tableauOrigine[ligne][1]=tableauOrigine[ligne][1]-tableauOrigine[1][colonne];
		for (int x=0;x<nbLigne+3;x++) {
			tableauOrigine[x][colonne]=0;
		}
		
	}

}




}
