import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SudokuExtension4 {

    // Variables globlaes
    private static Scanner input = new Scanner(System.in);
    private static String [] possibleAnsYes = {"Y", "y", "yes", "Yes", "YES"};
    private static String [] possibleAnsNo = {"N", "n", "no", "No", "NO"};
    private static int tempsPause = 2000; //en millisec
    private static String fichierGrille = "grille2.txt";

    // .........................................................................
    // Fonctions utiles
    // .........................................................................

    /**
     * pré-requis : min <= max
     * résultat : un entier saisi compris entre min et max, avec re-saisie
     * éventuelle jusqu'à ce qu'il le soit
     */
    public static int saisirEntierMinMax(int min, int max) {
        System.out.print("Veuillez saisir un nombre entre " + min + " et " + max + " : ");
        int nb = input.nextInt();
        while (nb < min || nb > max) {
            System.out.print("Veuillez saisir un nombre entre " + min + " et " + max + " : ");
            nb = input.nextInt();
        }
        return nb;
    } // fin saisirEntierMinMax
      // .........................................................................

    /**
     * MODIFICI
     * pré-requis : mat1 et mat2 ont les mêmes dimensions
     * action : copie toutes les valeurs de mat1 dans mat2 de sorte que mat1 et mat2
     * soient identiques
     */
    public static void copieMatrice(int[][] mat1, int[][] mat2) {
        // ________________________________________________________
        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat1[i].length; j++) {
                mat2[i][j] = mat1[i][j];
            }
        }
    } // fin copieMatrice

    /**
     * prerequis : mat est une matrice d'entier de taille n x m
     * action : afficher la matrice (fonction de test)
     * 
     * @param mat
     */
    public static void afficherMat(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + "\t");
            }
            System.out.print("\n");
        }
        System.out.println();
    }

    // .........................................................................

    /**
     * pré-requis : n >= 0
     * résultat : un tableau de booléens représentant le sous-ensemble de l'ensemble
     * des entiers de 1 à n égal à lui-même
     */
    public static boolean[] ensPlein(int n) {
        // _____________________________________
        boolean[] tab_bool = new boolean[n + 1]; 
        // de taille n + 1 car l'indice commence à 0, on négligera tab_bool[0] pour la suite.
        for (int i = 1; i < tab_bool.length; i++) {
            tab_bool[i] = true;
        }
        return tab_bool;
    } // fin ensPlein

    // .........................................................................

    /**
     * pré-requis : 1 <= val < ens.length
     * action : supprime la valeur val de l'ensemble représenté par ens, s'il y est
     * résultat : vrai ssi val était dans cet ensemble
     */
    public static boolean supprime(boolean[] ens, int val) {
        // ______________________________________________________
        if (ens[val]) {
            ens[val] = false;
            return true;
        } else {
            return false;
        }
    } // fin supprime

    // .........................................................................

    /**
     * pré-requis : l'ensemble représenté par ens n'est pas vide
     * résultat : un élément de cet ensemble
     */
    public static int uneValeur(boolean[] ens) {
        // _____________________________________________
        // élément choisi aléatoirement :
        // stocker les éléments de l'ensemble dans un tableau d'entier
        // compter le nombre d'élement
        // choisir un indice entre 0 et compteur-1
        // retourner la valeur du tableau située à cette indice
    
        int[] tabIntTrue = new int[9];
        int compteur = 0;
        Random r = new Random();
        for (int i = 1; i < ens.length; i++) {
            if (ens[i] == true) {
                tabIntTrue[compteur] = i;
                compteur++;
            }
        }
        int choice = r.nextInt(compteur);
        return tabIntTrue[choice];
    } // fin


    /**
     * FONCTION AJOUTEE
     * action : clear le contenu affichée sur la console
     */
    public static void clearConsole() {
        System.out.flush();  // reposition cursor on console
        System.out.print("\033[H\033[2J"); //clear console
    }


    /**
     * FONCTION AJOUTEE
     * pré-requis : n est un entier naturel
     * action : renvoie l'entier le plus grand qui est égal ou inférieur à la racine carré de n
     */
    public static int racineParfaite(int n) {

        int i = 1, res = 1;
        // Cas triviaux
        if (n == 0 || n == 1) {
            return n;
        }
        else {
            //tester les entiers i en commencant par 1
            //s'areter quand le carre est > à n
            //renvoyer i - 1
            while (res <= n) {
                i++;
                res = i * i;
            }
        }
        return i - 1;
    }


    /**
     * FONCTION AJOUTEE
     * action : verifier si un string est contenu dans un tab de String
     * retourne boolean true si oui false sinon
     */
    public static boolean verifyStringFromTabString(String in, String[] tabString) {
        for (String elem : tabString) {
            if (in.equals(elem)) {
                return true;
            }
        }
        return false;
    }


    /**
     * FONCTION AJOUTEE
     * action : pause le process en cours d'une valeur entiere en milisex entrée en paramètres
     * affiche une barre de progression (pour indiquer que l'IA est en train de jouer...)
     */
    public static void barDeProgression (int milisec) {
        try {
            int n = 0;
            while (n<21) {
                clearConsole();
                System.out.println("L'IA est en train de jouer, veuillez patienter...");
                System.out.print("[");
                for (int i = 0; i < n; i++) {
                    System.out.print("#");
                }
                for (int i = 0; i < 20 - n; i++) {
                    System.out.print(" ");
                }
                System.out.print("] - " + n*5 + "%");
                n++;
                Thread.sleep(milisec/20);
            }
            System.out.println();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // .........................................................................

    /**
     * 
     * 1 2 3 4 5 6 7 8 9
     * -------------------
     * 1 |6 2 9|7 8 1|3 4 5|
     * 2 |4 7 3|9 6 5|8 1 2|
     * 3 |8 1 5|2 4 3|6 9 7|
     * -------------------
     * 4 |9 5 8|3 1 2|4 7 6|
     * 5 |7 3 2|4 5 6|1 8 9|
     * 6| 1 6 4|8 7 9|2 5 3|
     * -------------------
     * 7 3 8 1|5 2 7|9 6 4
     * 8 |5 9 6|1 3 4|7 2 8|
     * 9 |2 4 7|6 9 8|5 3 1|
     * -------------------
     * 
     * 
     * 
     * 
     * 1 2 3 4 5 6 7 8 9
     * -------------------
     * 1 |6 0 0|0 0 1|0 4 0|
     * 2 |0 0 0|9 6 5|0 1 2|
     * 3 |8 1 0|0 4 0|0 0 0|
     * -------------------
     * 4 |0 5 0|3 0 2|0 7 0|
     * 5 |7 0 0|0 0 0|1 8 9|
     * 6||0 0 0|0 7 0|0 0 3|
     * -------------------
     * 7 |3 0 0|0 2 0|9 0 4|
     * 8 |0 9 0|0 0 0|7 2 0|
     * 9 |2 4 0|6 9 0|0 0 0|
     * -------------------
     * 
     * 
     * pré-requis : 0<=k<=3 et g est une grille k^2xk^2 dont les valeurs sont
     * comprises
     * entre 0 et k^2 et qui est partitionnée en k sous-carrés kxk
     * action : affiche la grille g avec ses sous-carrés et avec les numéros des
     * lignes
     * et des colonnes de 1 à k^2.
     * Par exemple, pour k = 3, on obtient le dessin d'une grille de Sudoku
     * 
     */
    public static void afficheGrille(int k, int[][] g) {
        // __________________________________________________

        System.out.print("   ");
        for (int c = 1; c <= g.length; c++) {
            System.out.print(c + " ");
            if (c % k == 0) {
            }
        }
        System.out.print("\n");
        for (int h = 0; h < 2 * k * k + 3; h++) {
            System.out.print("-"); // k*k (chiffre) + k*k + 1 (char) + 2 (index + increment)
        }
        System.out.print("\n");
        for (int i = 0; i < g.length; i++) {

            System.out.print(i + 1 + " |");
            for (int j = 0; j < g.length; j++) {
                if (g[i][j] == -1) { //POUR VOIR LES TROUS EVIDENTS DE LA PILE (EXTENSION3)
                    System.out.print("x");
                }
                // pour remplacer 0 par un point
                else if (g[i][j] == 0) {
                    System.out.print(".");
                } else {
                    System.out.print(g[i][j]);
                }

                // aesthetics
                if ((j + 1) % k == 0) {
                    System.out.print("|");
                } else {
                    System.out.print(" ");
                }
            }
            if ((i + 1) % k == 0) {
                System.out.print("\n");
                for (int h = 0; h < 2 * k * k + 3; h++) {
                    System.out.print("-"); // k*k (chiffre) + k*k + 1 (char) + 2 (index + increment)
                }

            }

            System.out.print("\n");
        }
    } // fin afficheGrille
      // .........................................................................



    /**
     * pré-requis : k > 0, 0 <= i< k^2 et 0 <= j < k^2
     * résultat : (i,j) étant les coordonnées d'une case d'une grille k^2xk^2
     * partitionnée
     * en k sous-carrés kxk, retourne les coordonnées de la case du haut à gauche
     * du sous-carré de la grille contenant cette case.
     * Par exemple, si k=3, i=5 et j=7, la fonction retourne (3,6).
     */
    public static int[] debCarre(int k, int i, int j) {
        // __________________________________________________
        int debX = (i / k) * k; // abscisse indice de 0 a 8
        int debY = (j / k) * k; // ordonnée indice de 0 a 8
        int[] debCoord = {debX, debY};

        // Version alternative pour indice de 1 a 9
        // int[] debCoord = { ((i - 1) / k) * k + 1, 
        //                    ((j - 1) / k) * k + 1 };

        return debCoord;
    } // fin debCarre



    // .........................................................................
    // Initialisation
    // .........................................................................

    /**
     * MODIFICI
     * pré-requis : gComplete est une matrice 9X9
     * action : remplit gComplete pour que la grille de Sudoku correspondante soit
     * complète
     * stratégie : les valeurs sont données directement dans le code et on peut
     * utiliser copieMatrice pour mettre à jour gComplete
     */
    public static void initGrilleComplete(int[][] gComplete) {
        // _________________________________________________
        int[][] g = {
                { 6, 2, 9, 7, 8, 1, 3, 4, 5 },
                { 4, 7, 3, 9, 6, 5, 8, 1, 2 },
                { 8, 1, 5, 2, 4, 3, 6, 9, 7 },

                { 9, 5, 8, 3, 1, 2, 4, 7, 6 },
                { 7, 3, 2, 4, 5, 6, 1, 8, 9 },
                { 1, 6, 4, 8, 7, 9, 2, 5, 3 },

                { 3, 8, 1, 5, 2, 7, 9, 6, 4 },
                { 5, 9, 6, 1, 3, 4, 7, 2, 8 },
                { 2, 4, 7, 6, 9, 8, 5, 3, 1 }
        };
        copieMatrice(g, gComplete);
    } // fin initGrilleComplete


    /**
     * EXTENSION4
     * action : même que l'orginal avec une succession de 100 transformations 
     * choisis aléatoirement parmi 4 possibles.
     */
    public static void initGrilleCompleteV4(int[][] gComplete) {
        // _________________________________________________
        int[][] g = {
                { 6, 2, 9, 7, 8, 1, 3, 4, 5 },
                { 4, 7, 3, 9, 6, 5, 8, 1, 2 },
                { 8, 1, 5, 2, 4, 3, 6, 9, 7 },

                { 9, 5, 8, 3, 1, 2, 4, 7, 6 },
                { 7, 3, 2, 4, 5, 6, 1, 8, 9 },
                { 1, 6, 4, 8, 7, 9, 2, 5, 3 },

                { 3, 8, 1, 5, 2, 7, 9, 6, 4 },
                { 5, 9, 6, 1, 3, 4, 7, 2, 8 },
                { 2, 4, 7, 6, 9, 8, 5, 3, 1 }
        };
        copieMatrice(g, gComplete);

        Random r = new Random();
        for (int m = 0; m < 100; m++) {   // nombre de transfo
            int pick = r.nextInt(4);
            if (pick == 0) {
                rotation90deg(gComplete);
            } else if (pick == 1) {
                symetrieHorizontal(gComplete);
            } else if (pick == 2) {
                symetrieDiagPrin(gComplete);
            } else {
                echangeLignes(0, 1, gComplete);
            }
        }
    } // fin initGrilleComplete

    
    /**
     * EXTENSION4
     * FONCTION AJOUTEE
     * pre-requis: gComplete une grille carré
     * action : une rotation de 90 degré de cette grille     * 
     */
    public static void rotation90deg(int[][] gComplete) {

        int[][] grilleRota = new int[gComplete.length][gComplete.length]; 

        for (int i = 0; i < gComplete.length; i++) {
            for (int j = 0; j < gComplete.length; j++) {
                grilleRota[j][gComplete.length - i - 1] = gComplete[i][j];
            }
        }
        copieMatrice(grilleRota, gComplete);
    }


    /**
     * EXTENSION4
     * FONCTION AJOUTEE
     * pre-requis: gComplete une grille carré
     * action : une symetrie horizontale de cette grille 
     */
    public static void symetrieHorizontal(int[][] gComplete) {

        int[][] grilleSym = new int[gComplete.length][gComplete.length];

        for (int i = 0; i < gComplete.length; i++) {
            for (int j = 0; j < gComplete.length; j++)
                grilleSym[gComplete.length - i - 1][j] = gComplete[i][j];
        }  
        copieMatrice(grilleSym, gComplete);
    }


    /**
     * EXTENSION4
     * FONCTION AJOUTEE
     * pre-requis: gComplete une grille carré
     * action : une symetrie diagonale de cette grille
     * par rapport à la diagonale principale 
     */   
    public static void symetrieDiagPrin(int[][] gComplete) {

        int[][] grilleSymD = new int[gComplete.length][gComplete.length];

        for (int i = 0; i < gComplete.length; i++) {
            for (int j = 0; j < gComplete.length; j++) {
                grilleSymD[j][i] = gComplete[i][j];
            }
        }
        copieMatrice(grilleSymD, gComplete);
    }


    /**
     * EXTENSION4
     * FONCTION AJOUTEE
     * pre-requis: gComplete une grille carré
     * action : echange les deux lignes entré en parametres ssi
     * ils passent par les mêmes sous-carrés
     */ 
    public static void echangeLignes(int i1, int i2, int[][] gComplete) {
        int k = racineParfaite(gComplete.length);
        int[] coord1 = debCarre(k, i2, k); //le dernier parametre j ici n'importe pas
        int[] coord2 = debCarre(k, i1, k); //mais doit etre egal pour les deux coord

        if (coord1[0] == coord2[0] && coord1[1] == coord2[1]) {
            int temp;
            for (int j=0;j<gComplete.length;j++){
                temp = gComplete[i1][j];
                gComplete[i1][j] = gComplete[i2][j];
                gComplete[i2][j] = temp;
            }
        }
    }


    // .........................................................................

    /**
     * MODIFICI
     * pré-requis : gSecret est une grille de Sudoku complète de mêmes dimensions
     * que gIncomplete et 0 <= nbTrous <= 81
     * action : modifie gIncomplete pour qu'elle corresponde à une version
     * incomplète de la grille de Sudoku gSecret (gIncomplete peut être complétée en
     * gSecret), avec nbTrous trous à des positions aléatoires
     * 
     * STRAT : parmi les coordonnees de cases remplies stocker dans une pile tabCoord
     * Piocher aléatoirment dans tabCoord et mettre la case asociée à ces coordonées à
     * 0 (et donc devient un trou).
     * Actualiser la pile et reiterer pour nbTrous.
     */
    public static void initGrilleIncomplete(int nbTrous, int[][] gSecret, int[][] gIncomplete) {
        // ___________________________________________________________________________
        copieMatrice(gSecret, gIncomplete);
        int[][] tabCoord = new int[82][2]; // Pile de coordonnées des cases remplies
        initTabCoordSaufTrou(gIncomplete, tabCoord); //Initialiser tabCoord
        Random r = new Random();
        int choice;
        for (int k = 0; k < nbTrous; k++) {
            choice = r.nextInt(tabCoord[0][0] - 1) + 1; // entre 1 et cardinal-1
            gIncomplete[tabCoord[choice][0]][tabCoord[choice][1]] = 0;
            //retirer cette coordonnée à tabCoord :
                //mettre la derniere coord de l'ensemble à la place de celui utilisé pour
                //fabriquer le trou, et decrémenter le cardinal stocké dans tabCoord[0][0]
            tabCoord[choice][0] = tabCoord[tabCoord[0][0]][0];
            tabCoord[choice][1] = tabCoord[tabCoord[0][0]][1];
            tabCoord[0][0] = tabCoord[0][0] - 1; 
        }


    } // fin initGrilleIncomplete

 
    /**
     * FONCTION AJOUTEE
     * pré-requis : f est un grille de Sudoku, et tabCoord une pile de Coordonées de taile 82 x 2
     * On stockera le cardinal de cet ensemble de coordonée dans l'indice [0][0].
     * action : initialise la pile, comme un tableau de coordonées des cases qui ne sont
     * pas des trous.
     * @param g
     * @param tabCoord
     */
    public static void initTabCoordSaufTrou(int[][] g, int[][] tabCoord) {
        tabCoord[0][0] = 0; //cette valeur est le cardinal du nombre de trou

        //modifier tabCoord pour creet une pile contenant 
        //tout les coords des non trou de la grille g
        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g.length; j++) {
                if (g[i][j] != 0) {
                    tabCoord[0][0] = tabCoord[0][0] + 1;
                    tabCoord[tabCoord[0][0]][0] = i;
                    tabCoord[tabCoord[0][0]][1] = j;
                }   
            }   
        }
    }

    // .........................................................................


//MODIFICI =================================================================================

  //.........................................................................

    /** MODIFICI
     *  pré-requis : 0 <= nbTrous <= 81 ; g est une grille 9x9 (vide a priori) ; 
     *               fic est un nom de fichier de ce répertoire contenant des valeurs de Sudoku
     *  action :   remplit g avec les valeurs lues dans fic. Si la grille ne contient pas des valeurs 
     *             entre 0 et 9 ou n'a pas exactement nbTrous valeurs nulles, la méthode doit signaler l'erreur,
     *             et l'utilisateur doit corriger le fichier jusqu'à ce que ces conditions soient vérifiées.
     *             On suppose dans la version de base que la grille saisie est bien une grille de Sudoku incomplète.
     * 
     *  AJOUT : return true si Grille valide et false sinon
     */
    public static boolean saisirGrilleIncompleteFichier(int nbTrous, int [][] g, String fic){
	//_________________________________________________
    // Des tests d'erreur sont à ajouter quelque part !
    // Vérification du nombre de trous et valeurs entre 0 et 9
	try (BufferedReader lecteur = new BufferedReader(new FileReader(fic))) {  
        int countNbTrou = 0; // compteur du nombre de trous
	    for (int i = 0 ; i < 9 ; i++){
            String ligne = lecteur.readLine();
            String [] valeurs = ligne.split("\\s+");
                for (int j = 0 ; j < 9 ; j++) {
                    g[i][j] = Integer.parseInt(valeurs[j]);
                    if (g[i][j] == 0) {
                        countNbTrou = countNbTrou + 1;
                    }
                    if (g[i][j] < 0 || g[i][j] > 9) { // check de valeurs interdites
                        g[i][j] = 0; //reset valeur de la case à 0 pour eviter probleme dans initPartie, vu 
                        // qu'on ne releve pas l'erreur mais qu'on l'affiche seulement
                        System.out.println("La grille du fichier \'" 
                        + fic + "\' contient des valeurs non autorisés.");
                        return false;
                    }
                    //verifications de doublons
                    if (g[i][j]!= 0 && (!verifierLignes(g[i][j], i, j, g) || 
                                                !verifierColonnes(g[i][j], i, j, g) || 
                                                !verifierCarre(g[i][j], i, j, g))){
                        System.out.println("La grille du fichier \'" 
                        + fic + "\' contient des doublons.");
                        return false;
                    }
                }
	        }
            // Check si le nombre de trous dans la trille coincide avec nbTrous
            if (countNbTrou != nbTrous) {
                System.out.println("La grille du ficher \'"
                + fic + "\' ne contient pas " + nbTrous + " trous.");
                return false;
            }
            System.out.println("Votre grille a bien été enregistrée, l'IA est prêt à jouer contre vous :");
            return true;
	    }

    catch (IOException e) {
	    e.printStackTrace();
        return false;
	}
    } // fin saisirGrilleIncompleteFichier



//============================================================================================


    /**
     * MODIFICI
     * pré-requis : 0 <= nbTrous <= 81 ; g est une grille 9x9 (vide a priori)
     * action : remplit g avec des valeurs saisies au clavier comprises entre 0 et 9
     * avec exactement nbTrous valeurs nulles
     * et avec re-saisie jusqu'à ce que ces conditions soient vérifiées.
     * On suppose dans la version de base que la grille saisie est bien une grille
     * de Sudoku incomplète.
     * stratégie : utilise la fonction saisirEntierMinMax
     */

    public static void saisirGrilleIncomplete(int nbTrous, int[][] grille) {
        // _________________________________________________
        int k = 3;
        int nbTrousCount = 0; //compteur de trous crées 
        int[][] gNul = new int[k * k][k * k];
        while (nbTrousCount != nbTrous) { //Reitérer tant que la grille ne contient pas nbTrous trous
            nbTrousCount = 0;
            copieMatrice(gNul, grille);
            clearConsole();
            afficheGrille(k, grille); //affichage pré-remplissage
            //Parcourir la grille
            for (int i = 0; i < grille.length; i++) {
                for (int j = 0; j < grille.length; j++) {
                    System.out.println("Veuillez remplir la grille ci-dessus (saisir 0 pour un Trou), " + 
                    "vous pouvez remplir plusieurs cases en séparant la saisie par un ou des espaces, " + 
                    "le remplissage se fait de gauche à droite puis de haut en bas." + 
                    "\nVous êtes aux coordonées ("+ (i + 1) + ", " + (j + 1) + ") : ");
                    System.out.println("Nombre de Trous requis : " + nbTrous);
                    System.out.println("Nombre de Trous saisis : " + nbTrousCount);
                    if (nbTrousCount < nbTrous) {
                        grille[i][j] = saisirEntierMinMax(0, 9);
                    } else {
                        System.out.println("Vous ne pouvez plus saisir de trous!");
                        grille[i][j] = saisirEntierMinMax(1, 9);
                    }
                    clearConsole();
                    afficheGrille(k, grille); //affichage post-remplissage
                    if (grille[i][j] == 0) {
                        nbTrousCount++; //incrementer le compteur de trous crées 
                    }
                }
            }
        }
        clearConsole();
        System.out.println("Votre grille a bien été enregistrée, l'IA est prêt à jouer contre vous :");
    } // fin saisirGrilleIncomplete

    /**
     * MODIFICI
     * pré-requis : 0 <= nbTrous <= 81 ; g est une grille 9x9 (vide a priori)
     * action : même que l'original, avec des verifications sur les valeurs saisies 
     * (pas de doublons)
     */
    public static void saisirGrilleIncompleteV2(int nbTrous, int[][] grille) {
        // _________________________________________________
        int k = 3; int saisie;
        int nbTrousCount = 0; //compteur de trous crées 
        int[][] gNul = new int[k * k][k * k];
        while (nbTrousCount != nbTrous) { //Reitérer tant que la grille ne contient pas nbTrous trous
            nbTrousCount = 0;
            copieMatrice(gNul, grille);
            clearConsole();
            afficheGrille(k, grille); //affichage pré-remplissage
            //Parcourir la grille
            for (int i = 0; i < grille.length; i++) {
                for (int j = 0; j < grille.length; j++) {
                    System.out.println("Veuillez remplir la grille ci-dessus (saisir 0 pour un Trou), " + 
                    "vous pouvez remplir plusieurs cases en séparant la saisie par un ou des espaces, " + 
                    "le remplissage se fait de gauche à droite puis de haut en bas." + 
                    "\nVous êtes aux coordonées ("+ (i + 1) + ", " + (j + 1) + ") : ");
                    System.out.println("Nombre de Trous requis : " + nbTrous);
                    System.out.println("Nombre de Trous saisis : " + nbTrousCount);
                    if (nbTrousCount < nbTrous) {
                        saisie = saisirEntierMinMax(0, 9); //EXTENSION2
                        while (saisie!= 0 && (!verifierLignes(saisie, i, j, grille) || 
                                                !verifierColonnes(saisie, i, j, grille) || 
                                                !verifierCarre(saisie, i, j, grille))){
                            System.out.println("Attention aux doublons !");
                            saisie = saisirEntierMinMax(0, 9);
                        }
                        grille[i][j] = saisie;
                    } else {
                        System.out.println("Vous ne pouvez plus saisir de trous!");
                        saisie = saisirEntierMinMax(1, 9); //EXTENSION2
                        while (!verifierLignes(saisie, i, j, grille) || 
                                !verifierColonnes(saisie, i, j, grille) || 
                                !verifierCarre(saisie, i, j, grille)){
                            System.out.println("Attention aux doublons !");
                            saisie = saisirEntierMinMax(1, 9);
                        }
                        grille[i][j] = saisie;
                    }
                    clearConsole();
                    afficheGrille(k, grille); //affichage post-remplissage
                    if (grille[i][j] == 0) {
                        nbTrousCount++; //incrementer le compteur de trous crées 
                    }
                }
            }
        }
        clearConsole();
        System.out.println("Votre grille a bien été enregistrée, l'IA est prêt à jouer contre vous :");
    } // fin saisirGrilleIncomplete

    // .........................................................................

    /**
     * pré-requis : gOrdi est une grille de Sudoku incomplète,
     * valPossibles est une matrice 9x9 de tableaux de 10 booléens
     * et nbValPoss est une matrice 9x9 d'entiers
     * action : met dans valPossibles l'ensemble des entiers de 1 à 9 pour chaque
     * trou de gOrdi et leur nombre dans nbValPoss
     */
    public static void initPleines(int[][] gOrdi, boolean[][][] valPossibles, int[][] nbValPoss) {
        // ________________________________________________________________________________________________
        //Parcourir gOrdi 
        for (int i = 0; i < gOrdi.length; i++) {
            for (int j = 0; j < gOrdi[0].length; j++) {
                if (gOrdi[i][j] == 0) { //detection d'un trou
                    //associe l'adresse du tableau de boolean valPoss[i][j] à l'adresse 
                    //d'un tableau de boolean "plein" crée par ensPlein.
                    //L'adresse du tableau créé par ensPlein differe à chaque itération
                    valPossibles[i][j] = ensPlein(gOrdi.length); 
                    nbValPoss[i][j] = gOrdi.length;
                } else {
                    nbValPoss[i][j] = 0;
                }
            }
        }
    } // fin



    // .........................................................................

    /**
     * pré-requis : gOrdi est une grille de Sudoku incomplète,
     * 0<=i<9, 0<=j<9,g[i][j]>0,
     * valPossibles est une matrice 9x9 de tableaux de 10 booléens
     * et nbValPoss est une matrice 9x9 d'entiers
     * action : supprime dans les matrices valPossibles et nbValPoss la valeur
     * gOrdi[i][j] pour chaque case de la ligne, de la colonne et du carré contenant
     * la case (i,j) correspondant à un trou de gOrdi.
     */
    public static void suppValPoss(int[][] gOrdi, int i, int j, boolean[][][] valPossibles, int[][] nbValPoss) {
        // _____________________________________________________________________________________________________________
        // STRAT : Pour une case (remplie) donnée en params,
        // parcourir les trous de la ligne/colonne/carre
        // et supprimer du valPoss associée la valeur de la case.
        // décrementer nbValPoss associée au trou pour chaque supression.

        // LIGNES
        for (int l = 0; l < gOrdi.length; l++) {
            if (gOrdi[i][l] == 0) { //check Trou
                boolean check = supprime(valPossibles[i][l], gOrdi[i][j]); 
                if (check) {
                    nbValPoss[i][l] = nbValPoss[i][l] - 1;
                }
            }
        }

        // COLONNES
        for (int k = 0; k < gOrdi.length; k++) {
            if (gOrdi[k][j] == 0) {
                boolean check = supprime(valPossibles[k][j], gOrdi[i][j]);
                if (check) {
                    nbValPoss[k][j] = nbValPoss[k][j] - 1;
                }
            }
        }

        // CARRE
        int k = racineParfaite(gOrdi.length);
        int xDebCarre, yDebCarre; //coordonnées du début du carré de la case correspondante
        xDebCarre = debCarre(k, i, j)[0]; 
        yDebCarre = debCarre(k, i, j)[1];
        //parcourir le carré
        for (int ligne = xDebCarre; ligne < xDebCarre + k; ligne++) {
            for (int colonne = yDebCarre; colonne < yDebCarre + k; colonne++) {
                if (gOrdi[ligne][colonne] == 0) {
                    boolean check = supprime(valPossibles[ligne][colonne], gOrdi[i][j]);
                    if (check) {
                        nbValPoss[ligne][colonne] = nbValPoss[ligne][colonne] - 1;
                    }
                }
            }
        }
    } // fin suppValPoss



    /**
     * FONCTION AJOUTEE (fonction test)
     * pré-requis : gOrdi est une grille de Sudoku incomplète,
     * 0<=i<9, 0<=j<9,g[i][j]>0,
     * valPossibles est une matrice 9x9 de tableaux de 10 booléens
     * et nbValPoss est une matrice 9x9 d'entiers.
     * 
     * action : (lors d'un remplissage) 
     * supprime de valPossibles[i][j] toutes les valeurs possibles (car n'est plus un trou) 
     * et mets nbValPoss à 0.
     * 
     * but : en soi inutile dans l'algorithme mais meilleur visualisation lors des tests.
     * 
     */
    public static void  updateCaseRempli(int[][] gOrdi, int i, int j, boolean[][][] valPossibles, int[][] nbValPoss) {
        //verifie bien que gordi n'est PLUS un trou
        if (gOrdi[i][j] != 0) {
            for (int k = 1; k < valPossibles[i][j].length; k++) {
                boolean check = supprime(valPossibles[i][j], k);
                if (check && nbValPoss[i][j] != -1) {
                    nbValPoss[i][j] = nbValPoss[i][j] - 1;
                }
                else if (check && nbValPoss[i][j] == -1) {
                    nbValPoss[i][j] = nbValPoss[i][j] + 1;
                }
            }
        }
    }



    // .........................................................................

    /**
     * pré-requis : gOrdi est une grille de Sudoju incomplète,
     * valPossibles est une matrice 9x9 de tableaux de 10 booléens
     * et nbValPoss est une matrice 9x9 d'entiers
     * action : met dans valPossibles l'ensemble des valeurs possibles de chaque
     * trou de gOrdi
     * et leur nombre dans nbValPoss
     */
    public static void initPossibles(int[][] gOrdi, boolean[][][] valPossibles, int[][] nbValPoss) {
        // ________________________________________________________________________________________________
        initPleines(gOrdi, valPossibles, nbValPoss);
        for (int i = 0; i < gOrdi.length; i++) {
            for (int j = 0; j < gOrdi.length; j++) {
                if (gOrdi[i][j] != 0) {
                    suppValPoss(gOrdi, i, j, valPossibles, nbValPoss);
                }
            }
        }
    } // fin initPossibles



    // .........................................................................

    /**
     * pré-requis : gSecret, gHumain et gOrdi sont des grilles 9x9
     * action : demande au joueur humain de saisir le nombre nbTrous compris entre 0
     * et 81,
     * met dans gSecret une grille de Sudoku complète,
     * met dans gHumain une grille de Sudoku incomplète, pouvant être complétée en
     * gSecret et ayant exactement nbTrous trous de positions aléatoires,
     * met dans gOrdi une grille de Sudoku incomplète saisie par le joueur humain
     * ayant nbTrous trous,
     * met dans valPossibles l'ensemble des valeurs possibles de chaque trou de
     * gOrdi et leur nombre dans nbValPoss.
     * retour : la valeur de nbTrous
     * 
     * MODIF : propose au joueur humain de saisir sa grille manuellement ou bien avec un fichier txt
     */
    public static int initPartie(int[][] gSecret, int[][] gHumain, int[][] gOrdi, boolean[][][] valPossibles,
            int[][] nbValPoss, int[][] tabTrouEvident) {
        // ______________________________________________________________________________________________
        clearConsole();
        int nb = 82;
        while (nb < 0 || nb > 81) {
            System.out.println(
                    "Entre un nombre entre 0 et 81. Il s'agit du nombre de trous à compléter.");
            nb = input.nextInt();
        }
        initGrilleCompleteV4(gSecret);
        initGrilleIncomplete(nb, gSecret, gHumain);
        
        //MODIFICI
        System.out.println("Voulez-vous saisir manuellement votre grille (Y/yes)" +
                        " ou bien utiliser le fichier \'grille1.txt\' (N/no) ?");

        String reponse = input.nextLine();

        while (!verifyStringFromTabString(reponse, possibleAnsYes) && 
                !verifyStringFromTabString(reponse, possibleAnsNo)) {
                reponse = input.nextLine();
            }

        if (verifyStringFromTabString(reponse, possibleAnsYes)) {
            saisirGrilleIncompleteV2(nb, gOrdi); 
        }
        else {
            if (!saisirGrilleIncompleteFichier(nb, gOrdi, fichierGrille)) {
                nb = 0; //ne lance pas les tours!
            }
        }

        initPossibles(gOrdi, valPossibles, nbValPoss);
        initChercheTrouEvident(gOrdi, nbValPoss, tabTrouEvident);

        return nb;
    } // fin initPartie





    // ...........................................................
    // Tour du joueur humain
    // ...........................................................

    /**
     * pré-requis : gHumain est une grille de Sudoku incomplète pouvant se compléter
     * en la grille de Sudoku complète gSecret
     *
     * résultat : le nombre de points de pénalité pris par le joueur humain pendant
     * le tour de jeu
     *
     * action : effectue un tour du joueur humain
     */
    public static int tourHumain(int[][] gSecret, int[][] gHumain) {
        // DEROULEMENT :
        // Afficher gHumain
        // Demander à l'entité humaine de choisir un trou (coord)
        // Verifier quil sagit bien dun trou, sinon repeter le demande (while)
        // Demander si il veut utiliser un joker
            // Si OUI remplir le trou avec bonne val et +1 pen
            // SINON
        //      Remplir le trou avec une valeur de 1 à 9 saisi
        //      Si valeur FAUSSE, point de pénalité = + 1 et recommencer depuis joker
        //      Sinon au tour de l'ordinateur
        // 
        // ___________________________________________________________________
        int pen = 0;
        boolean boolTrou = false; //indicateur si trou a été rempli ou non

        while (!boolTrou) {
            System.out.println("C'est votre tour, voici votre grille à résoudre : ");
            afficheGrille(3, gHumain);
            System.out.println("Veuillez choisir les coordonnées d'un trou à remplir (ligne i, colonne j) : ");
            System.out.print("i = ");
            int i = input.nextInt() - 1;
            System.out.print("j = ");
            int j = input.nextInt() - 1;

            while (gHumain[i][j] != 0) {
                System.out.println("Veuillez choisir les coordonnées d'un trou remplissable à remplir (ligne i, colonne j) : ");
                System.out.print("i = ");
                i = input.nextInt() - 1;
                System.out.print("j = ");
                j = input.nextInt() - 1;  
            }

            System.out.println("Voulez-vous un joker ? Faîtes votre choix et saisissez Y/N ou yes/no");

            String ans = input.nextLine();

            while (!verifyStringFromTabString(ans, possibleAnsYes) && 
                    !verifyStringFromTabString(ans, possibleAnsNo)) {
                ans = input.nextLine();
            }

            if (verifyStringFromTabString(ans, possibleAnsYes)) {
                    pen = pen + 1; //penalité pour le joker
                    gHumain[i][j] = gSecret[i][j]; //remplissage du trou automatique
                    boolTrou = true; //le trou a été rempli
            }
            else { //sans joker
                System.out.println("Choisissez le chiffre pour remplir cette case :");
                int ansTrou = saisirEntierMinMax(1, gSecret.length);
                if (ansTrou != gSecret[i][j]){
                    pen = pen + 1; //pénalité car valeur saisie fausse
                    System.out.println("Dommage, réessayez");
                }
                else { //bonne valeur saisie
                    gHumain[i][j] = gSecret[i][j];
                    boolTrou = true;
                }
            }
        }
        return pen;
    } // fin tourHumain


    // .........................................................................

    // Tour de l'ordinateur
    // .........................................................................

    /**
     * pré-requis : gOrdi et nbValPoss sont des matrices 9x9
     * résultat : le premier trou (i,j) de gOrdi (c'est-à-dire tel que
     * gOrdi[i][j]==0)
     * évident (c'est-à-dire tel que nbValPoss[i][j]==1) dans l'ordre des lignes,
     * s'il y en a, sinon le premier trou de gOrdi dans l'ordre des lignes
     * 
     */
    public static int[] chercheTrou(int[][] gOrdi, int[][] nbValPoss) {
        // ___________________________________________________________________
        int k=0; //variable pour conditioner le stockage du premier trou (eviter overwrite par les autres trous)
        int [] tab = {-1, -1}; 
        //initialisation du tableau devant contenir les coordonnées du trou s'il y'en a

        for (int i = 0; i < gOrdi.length; i++) {
            for (int j = 0; j < gOrdi[i].length; j++) {
                if (gOrdi[i][j]==0 && k==0) { //stocker le premier trou
                    tab[0] = i;
                    tab[1] = j;
                    k = 1;
                }
        
                if (nbValPoss[i][j]==1) { //si trou evident overwrite la valeur stocker et fin de la boucle.
                    tab[0] = i;
                    tab[1] = j;
                    return tab;
                }
            }
        }
        
        return tab;
        
    } // fin chercheTrou



    /**
     * EXTENSION1
     * action : même principe que la fonction originale mais
     * qui stock, si pas de trous evidents, les coords du premier trou avec 2 valeurs possibles en 
     * priorités.
     * 
     */
    public static int[] chercheTrouV1(int[][] gOrdi, int[][] nbValPoss) {
        // ___________________________________________________________________
        int k = 0, m = 0;
        int[] tab = { -1, -1 };

        for (int i = 0; i < gOrdi.length; i++) {
            for (int j = 0; j < gOrdi[i].length; j++) {

                if (nbValPoss[i][j] == 2 && m == 0) { //stocker le premier trou à 2 vals possibles
                    tab[0] = i;
                    tab[1] = j;
                    k = 1; //pour empecher le overwrite d'un trou non evident quelconque
                    m = 1;
                }

                if (gOrdi[i][j] == 0 && k == 0) {
                    tab[0] = i;
                    tab[1] = j;
                    k = 1;
                }

                if (nbValPoss[i][j] == 1) { //overwrite si trou evident
                    tab[0] = i;
                    tab[1] = j;
                    return tab;
                }
            }
        }

        return tab;

    } // fin chercheTrouV1



    // .........................................................................


    /**
     * pré-requis : gOrdi est une grille de Sudoju incomplète,
     * valPossibles est une matrice 9x9 de tableaux de 10 booléens
     * et nbValPoss est une matrice 9x9 d'entiers
     * action : effectue un tour de l'ordinateur
     */
    public static int tourOrdinateur(int[][] gOrdi, boolean[][][] valPossibles, int[][] nbValPoss) {
        // ________________________________________________________________________________________________
        // DEROULEMENT :
        // vérifier si trou évident, si oui le remplir. 
        // Si pas de trous évidents choisir le premier trou et Ordi prend un joker. 
        // L'humain saisi la bonne valeur pour ce trou (il est honnête).
        int [] tab = chercheTrou(gOrdi, nbValPoss); //coord du premier trou à remplir par Ordi

            barDeProgression(tempsPause);
            int ans, pen = 0;
            if (nbValPoss[tab[0]][tab[1]] == 1){ //check trou evident
                gOrdi[tab[0]][tab[1]] = uneValeur(valPossibles[tab[0]][tab[1]]);
                suppValPoss(gOrdi, tab[0], tab[1], valPossibles, nbValPoss); //Actualiser la table apres remplissage
                updateCaseRempli(gOrdi, tab[0], tab[1], valPossibles, nbValPoss); //actualiser la case qui n'est plus un trou
                //pas de pénalité
            }
            else {
                afficheGrille(3, gOrdi);
                System.out.println("IA : Je n'arrive pas à trouver la bonne réponse. Pourriez-vous combler le trou dans la ligne " 
                + (tab[0]+1) + " et la colonne " + (tab[1]+1) + " pour moi s'il-te-plait?");
                ans = input.nextInt();
                gOrdi[tab[0]][tab[1]] = ans;
                suppValPoss(gOrdi, tab[0], tab[1], valPossibles, nbValPoss); //actualiser la table apres remplissage
                updateCaseRempli(gOrdi, tab[0], tab[1], valPossibles, nbValPoss);
                pen = pen + 1; //pénalité car joker
            
            }
            
            clearConsole();
            System.out.println("L'IA a fini de jouer, ci-dessous sa grille :");
            afficheGrille(3, gOrdi);
            System.out.println();
            return pen;
        
    
    } // fin tourOrdinateur



    /**
     * EXTENSION2
     * pré-requis : gOrdi est une grille de Sudoju incomplète,
     * valPossibles est une matrice 9x9 de tableaux de 10 booléens
     * et nbValPoss est une matrice 9x9 d'entiers
     * action : effectue un tour de l'ordinateur
     */
    public static int tourOrdinateurV3(int[][] gOrdi, boolean[][][] valPossibles, int[][] nbValPoss,
                                        int[][] tabTrouEvident) {
        // ________________________________________________________________________________________________
        // return -1 => triche
        int [] tab = chercheTrouV1(gOrdi, nbValPoss); //coord du premier trou à remplir par Ordi

        barDeProgression(tempsPause);
        clearConsole();
        int ans, pen = 0;
        String reponse;
        afficheGrille(3, gOrdi);

        //EXTENSION2 =======================================================================
        if (nbValPoss[tab[0]][tab[1]] == 0) { //cas ou trou sans valeurs possibles
            System.out.println("TRICHE DETECTEE : Trou sans valeur possible à la position" + 
            "(" + (tab[0]+1) + ", " + (tab[1]+1) + ")");
            return -1; //Fin de partie, voir fonction partie
        }
        //===================================================================================

        //EXTENSION3 ==========================================================================

        if (tabTrouEvident[0][0] != 0) { // trous evidents de la pile
            //prendre le derniers trous de la pile pour le remplir
            int i = tabTrouEvident[tabTrouEvident[0][0]][0]; 
            int j = tabTrouEvident[tabTrouEvident[0][0]][1];
            gOrdi[i][j] = uneValeur(valPossibles[i][j]);
            // RETIRER CE QUI A ETE AJOUTE 
            suppValPoss(gOrdi, i, j, valPossibles, nbValPoss);
            updateCaseRempli(gOrdi, i, j, valPossibles, nbValPoss);
            synchroTrouEvident(gOrdi, nbValPoss, i, j, tabTrouEvident, true);
            //pas de pénalité
        }

        //======================================================================================

        //EXTENSION1 =====================================================================================================
        else if (nbValPoss[tab[0]][tab[1]] == 2) {
            int valChoix = uneValeur(valPossibles[tab[0]][tab[1]]);

            // Demande de confirmation
            reponse = askHumanToConfirm(tab[0], tab[1], valChoix);
            // l'humain peut mentir ici, mais la triche ne sera que detectee au moment ou l'Ordi 
            // réalise que la grille est désormais impossible (trou sans val poss EXTENSION2)

            if (verifyStringFromTabString(reponse, possibleAnsYes)) { //Choix Juste
                gOrdi[tab[0]][tab[1]] = valChoix;
                suppValPoss(gOrdi, tab[0], tab[1], valPossibles, nbValPoss);
                updateCaseRempli(gOrdi, tab[0], tab[1], valPossibles, nbValPoss);
                synchroTrouEvident(gOrdi, nbValPoss, tab[0], tab[1], tabTrouEvident, false);
                //pas de pénalité

            } else { //Choix Faux
                valPossibles[tab[0]][tab[1]][valChoix] = false; //enlever la valeur choisie qui est fausse d'apres l'humain
                nbValPoss[tab[0]][tab[1]] = nbValPoss[tab[0]][tab[1]] - 1;
                //demander confirmation meme si evident
                gOrdi[tab[0]][tab[1]] = uneValeur(valPossibles[tab[0]][tab[1]]);
                reponse = askHumanToConfirm(tab[0], tab[1], gOrdi[tab[0]][tab[1]]);
                if (verifyStringFromTabString(reponse, possibleAnsNo)) { //l'humain MENT, la reponse est evidente (EXTENSION2)
                    System.out.println("TRICHE DETECTEE : mensonge.");
                    return -1;
                }                    
                suppValPoss(gOrdi, tab[0], tab[1], valPossibles, nbValPoss);
                updateCaseRempli(gOrdi, tab[0], tab[1], valPossibles, nbValPoss);
                synchroTrouEvident(gOrdi, nbValPoss, tab[0], tab[1], tabTrouEvident, false);
                pen = pen + 1;
            }
        }
        //==================================================================================================================

        else {
            System.out.println("IA : Je n'arrive pas à trouver la bonne réponse. Pourriez-vous combler le trou dans la ligne " 
            + (tab[0]+1) + " et la colonne " + (tab[1]+1) + " pour moi s'il-te-plait?");
            ans = input.nextInt();
            if (!valPossibles[tab[0]][tab[1]][ans]) { //EXTENSION2
                return -1;
            }
            gOrdi[tab[0]][tab[1]] = ans;
            suppValPoss(gOrdi, tab[0], tab[1], valPossibles, nbValPoss); //actualiser la table apres remplissage
            updateCaseRempli(gOrdi, tab[0], tab[1], valPossibles, nbValPoss);
            synchroTrouEvident(gOrdi, nbValPoss, tab[0], tab[1], tabTrouEvident, false);
            pen = pen + 1; //pénalité car joker
        }
        
        clearConsole();
        System.out.println("L'IA a fini de jouer, ci-dessous sa grille :");
        afficheGrille(3, gOrdi);
        System.out.println();
        return pen;
    

    } // fin tourOrdinateur


    // .........................................................................

    // Partie
    // .........................................................................

    /**
     * pré-requis : aucun
     * action : effectue une partie de Sudoku entre le joueur humain et l'ordinateur
     * résultat : 0 s'il y a match nul, 1 si c'est le joueur humain qui gagne et 2
     * sinon
     */
    public static int partie() {
        
        //Init tableaux et variables
        int[][] gSecret = new int[9][9];
        int[][] gHumain = new int[9][9];
        int [][] gOrdi = new int [9][9];
        int [][] nbValPoss = new int[9][9];
        boolean [][][] valPossibles = new boolean[9][9][10];
        int[][] tabTrouEvident = new int[82][2]; //(EXTENSION3)
        int penHum = 0, penOrdi = 0;
        int nbTrou = initPartie(gSecret, gHumain, gOrdi, valPossibles, nbValPoss, tabTrouEvident);
        boolean nopartie = false; //le cas ou la partie ne se lance pas du au fichier grille1.txt non conforme
    
        if (nbTrou == 0) { //MODIFICI
            nopartie = true;
        }

        //La partie
        for (int i = 0; i<nbTrou;i++){
            penHum = penHum + tourHumain(gSecret, gHumain);
            // penOrdi = penOrdi + tourOrdinateurV2(gOrdi, valPossibles, nbValPoss); //EXTENSION1
            int check = tourOrdinateurV3(gOrdi, valPossibles, nbValPoss, tabTrouEvident); //EXTENSION3
            if (check == -1) {
                System.out.println("=== FIN DE LA PARTIE POUR CAUSE DE TRICHERIE ===");
                return 2;
            }
            penOrdi = penOrdi + check;
        }

        //Resultats de la partie
        if (penHum == penOrdi && !nopartie) { //matchnul
            return 0;
        }
        else if (penHum<penOrdi){ //homme gagne
            return 1;
        }
        else if (penHum>penOrdi) { //ordi gagnr
            return 2;
        }
        else { // pas de partie //MODIFICI
            return -1;
        }
        // _____________________________
        
    } // fin partie

    // .........................................................................



    /**
     * EXTENSION1
     * FONCTION AJOUTEE
     * pre-requis : i,j entre 0 et 8 des coords d'une case d'une grille de Sudoku
     * val une valeur entre 1 et 9 de cette case
     * action : demande à l'humain de confirmer la valeur entrée par l'Ordi
     * return : String parmi Y/N ou yes/no
     */
    public static String askHumanToConfirm (int i, int j, int val) {
        System.out.println("Ordi entre " + val + " aux coordonées (" + (i+1) + ", " + (j+1) + ").");
        System.out.println("Est-ce juste ? (Yes/No ou Y/N) : ");
        String reponse = input.nextLine();
        while (!verifyStringFromTabString(reponse, possibleAnsYes) && !verifyStringFromTabString(reponse, possibleAnsNo)) {
            reponse = input.nextLine();
        }
        return reponse;
    }



    /**
     * EXTENSION2
     * FONCTION AJOUTEE
     * pre-requis : input est un entier entre 1 et 9
     * int i entre 0 et 8 correspond à la ligne de la case à verifier
     * grille est une grille de sudoku Incomplete
     * action : vérifie si la valeur input est deja présent dans la ligne i
     * return : true si oui, false sinon
     */
    public static boolean verifierLignes(int input, int i, int j, int[][] grille){
        for (int k = 0; k < grille.length; k++) {
            if (k!=j && grille[i][k] == input){
               return false;
            }
        }
        return true;
    }

    /**
     * EXTENSION2
     * FONCTION AJOUTEE
     * pre-requis : input est un entier entre 1 et 9
     * int j entre 0 et 8 correspond à la colonne de la case à verifier
     * grille est une grille de sudoku Incomplete
     * action : vérifie si la valeur input est deja présent dans la colonne j
     * return : true si oui, false sinon
     */
    public static boolean verifierColonnes(int input, int i, int j, int[][] grille) {
        for (int k = 0; k < grille.length; k++) {
            if (k!=i && grille[k][j] == input) {
                return false;
            }
        }
        return true;
    }



    /**
     * EXTENSION2
     * FONCTION AJOUTEE
     * pre-requis : input est un entier entre 1 et 9
     * int i et j entre 0 et 8 correspond aux coords de la case à verifier
     * grille est une grille de sudoku Incomplete
     * action : vérifie si la valeur input est deja présent dans le carre contenant la case (i,j)
     * return : true si oui, false sinon
     */
    public static boolean verifierCarre(int input, int i, int j, int[][] grille) {
        int k = racineParfaite(grille.length);
        int xDebCarre, yDebCarre;
        xDebCarre = debCarre(k, i, j)[0];
        yDebCarre = debCarre(k, i, j)[1];

        for (int ligne = xDebCarre; ligne < xDebCarre + k; ligne++) {
            for (int colonne = yDebCarre; colonne < yDebCarre + k; colonne++) {
                if (ligne!=i && colonne!=j && grille[ligne][colonne] == input) {
                    return false;
                }
            }
        }
        return true;
    }



    /**
     * EXTENSION3
     * FONCTION AJOUTEE
     * action : initialise la pile de trous évidents
     */
    public static void initChercheTrouEvident(int[][] gOrdi, int[][] nbValPoss, int[][] tabTrouEvident) {
        int count = 0;
        tabTrouEvident[0][0] = count;
        for (int i = 0; i < gOrdi.length; i++) {
            for (int j = 0; j < gOrdi[i].length; j++) {
                if (nbValPoss[i][j] == 1) {
                    count = count + 1;
                    tabTrouEvident[0][0] = count;
                    tabTrouEvident[count][0] = i;
                    tabTrouEvident[count][1] = j;
                    nbValPoss[i][j] = -1;
                }
            }
        }
    }

    /**
     * EXTENSION3
     * FONCTION AJOUTEE
     * action : synchronise la pile lors d'un remplissage de trou (i,j) par l'Ordi
     */
    public static void synchroTrouEvident(int[][] gOrdi, int[][] nbValPoss, int i, int j, int[][] tabTrouEvident,
            boolean checkTrouEvident) {

        if (checkTrouEvident) { //si trou evident rempli, decremente le cardinal
            tabTrouEvident[0][0] = tabTrouEvident[0][0] - 1;
        }

        // LIGNES verifier si nouveaux trous evidents
        for (int l = 0; l < gOrdi.length; l++) {
            if (gOrdi[i][l] == 0) {
                if (nbValPoss[i][l] == 1) {
                    tabTrouEvident[0][0] = tabTrouEvident[0][0] + 1;
                    tabTrouEvident[tabTrouEvident[0][0]][0] = i;
                    tabTrouEvident[tabTrouEvident[0][0]][1] = l;
                    nbValPoss[i][l] = -1; // eviter les doublons dans la pile
                }

            }
        }

        // COLONNES verifier si nouveaux trous evidents
        for (int k = 0; k < gOrdi.length; k++) {
            if (gOrdi[k][j] == 0) {
                if (nbValPoss[k][j] == 1) {
                    tabTrouEvident[0][0] = tabTrouEvident[0][0] + 1;
                    tabTrouEvident[tabTrouEvident[0][0]][0] = k;
                    tabTrouEvident[tabTrouEvident[0][0]][1] = j;
                    nbValPoss[k][j] = -1; // eviter les doublons
                }
            }
        }

        // CARRE verifier si nouveaux trous evidents
        int k = racineParfaite(gOrdi.length);
        int xDebCarre, yDebCarre;
        xDebCarre = debCarre(k, i, j)[0];
        yDebCarre = debCarre(k, i, j)[1];

        for (int ligne = xDebCarre; ligne < xDebCarre + k; ligne++) {
            for (int colonne = yDebCarre; colonne < yDebCarre + k; colonne++) {
                if (gOrdi[ligne][colonne] == 0) {
                    if (nbValPoss[ligne][colonne] == 1) {
                        tabTrouEvident[0][0] = tabTrouEvident[0][0] + 1;
                        tabTrouEvident[tabTrouEvident[0][0]][0] = ligne;
                        tabTrouEvident[tabTrouEvident[0][0]][1] = colonne;
                        nbValPoss[ligne][colonne] = -1; // eviter doublon
                    }
                }
            }
        }
    }







    /**
     * pré-requis : aucun
     * action : effectue une partie de Sudoku entre le joueur humain et l'ordinateur
     * et affiche qui a gagné
     */
    public static void main(String[] args) {
        // ________________________________________
        //MODIFICI
        int res = partie();
        if (res == 0) {
            System.out.println("PARTIE TERMINE : Match nul");
        }
        else if (res == 1) {
            System.out.println("PARTIE TERMINE : Vous avez gagné. Félcitations!");
        }
        else if (res == 2) {
            System.out.println("PARTIE TERMINE : L'ordinateur a gagné. Dommage!");
        }
        else {
            System.out.println("ERREUR : VEUILLEZ RESSAISIR UNE GRILLE CORRECTE SUR LE FICHIER grille1.txt");
        }
    } // fin main

} // fin SudokuBase
