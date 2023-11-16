import java.util.*;

public class SudokuBase {

    // .........................................................................
    // Fonctions utiles
    // .........................................................................

    /**
     * pré-requis : min <= max
     * résultat : un entier saisi compris entre min et max, avec re-saisie
     * éventuelle jusqu'à ce qu'il le soit
     */
    private static Scanner input = new Scanner(System.in);

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


 /** MODIFICI
     *  pré-requis : mat1 et mat2 ont les mêmes dimensions
     *  action : copie toutes les valeurs de mat1 dans mat2 de sorte que mat1 et mat2 soient identiques
     */
    public static void copieMatrice(int[][] mat1, int[][] mat2){
        //________________________________________________________
        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat1[0].length; j++) {
                mat2[i][j] = mat1[i][j];
            }
        }
        }  // fin copieMatrice

        


    /**
     * pre requis : mat de taille n x m, element de taille m
     * action :
     * 
     * @param mat
     * @param elem
     * @return : mat de taille (n+1) x m
     */
    public static int[][] ajouterElemMat(int[][] mat, int[] elem, int m) {
        int[][] nouvMat = new int[mat.length + 1][m];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < m; j++) {
                nouvMat[i][j] = mat[i][j];
            }
        }
        nouvMat[mat.length] = elem;
        return nouvMat;
    }

    /**
     * prerequis : mat de taille n x m, ; 0 <= indice < mat.length
     * action : supprime un element d'un tableau d'entier sans conserver l'ordre des
     * elems.
     * 
     * @param mat
     * @param indice
     * @return mat de taille (n - 1) x m
     */

    public static int[][] supprimerElemMat(int[][] mat, int indice) {
        int[][] nouvMat = new int[mat.length - 1][mat[0].length];
        for (int j = 0; j < mat[0].length; j++) {
            mat[indice][j] = mat[mat.length - 1][j];
        }
        for (int i = 0; i < nouvMat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                nouvMat[i][j] = mat[i][j];
            }
        }
        return nouvMat;
    }

    /**
     * prerequis : mat entier e taille nx m
     * action : affiche la matrice
     * 
     * @param mat
     */
    public static void afficherMat(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
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
     * des entiers
     * de 1 à n égal à lui-même
     */
    public static boolean[] ensPlein(int n) {
        // _____________________________________
        boolean[] tab_bool = new boolean[n+1];
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
        if (ens[val]==true){
            ens[val] = false;
            return true;
        }
        else {
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
        int [] tabIntTrue = new int[0];
        for (int i=1;i<ens.length;i++){
            if (ens[i]==true){
                tabIntTrue = ajouterElem(tabIntTrue,i);
            }
        }
        Random ran = new Random();
        int selec = ran.nextInt(tabIntTrue.length);
        return tabIntTrue[selec];
    } // fin 
    
    public static int [] ajouterElem(int [] tab, int el){
        int [] anothertab = new int [tab.length+1];
        for (int i=0;i<tab.length;i++){
            anothertab[i] = tab[i];
        }
        anothertab[tab.length] = el;
        return anothertab;
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
        // prerequis non necessaire ?!
        System.err.print("   ");
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
                // pour remplacer 0 par rien :d
                if (g[i][j] == 0) {
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
        // verifier intervalle prerequis ?
        // int debX = (i/k)*k ; //abscisse indice de 0 a 8
        // int debY = (j/k)*k; //ordo

        int[] debCoord = { ((i - 1) / k) * k + 1, ((j - 1) / k) * k + 1 }; // indice de 1 a 9

        return debCoord;

    } // fin debCarre

    // .........................................................................

    // Initialisation
    // .........................................................................


    /** MODIFICI
     *  pré-requis : gComplete est une matrice 9X9
     *  action   :   remplit gComplete pour que la grille de Sudoku correspondante soit complète
     *  stratégie :  les valeurs sont données directement dans le code et on peut utiliser copieMatrice pour mettre à jour gComplete
     */
    public static void initGrilleComplete(int [][] gComplete){
        //_________________________________________________
        int [][] g = {
            {6, 2, 9,  7, 8, 1,  3, 4, 5},
            {4, 7, 3,  9, 6, 5,  8, 1, 2},
            {8, 1, 5,  2, 4, 3,  6, 9, 7},

            {9, 5, 8,  3, 1, 2,  4, 7, 6},
            {7, 3, 2,  4, 5, 6,  1, 8, 9},
            {1, 6, 4,  8, 7, 9,  2, 5, 3},

            {3, 8, 1,  5, 2, 7,  9, 6, 4},
            {5, 9, 6,  1, 3, 4,  7, 2, 8},
            {2, 4, 7,  6, 9, 8,  5, 3, 1}
        };
        copieMatrice(g, gComplete);
        } // fin initGrilleComplete




    /** pré-requis : aucun
     *  résultat :   une grille de Sudoku complète
     *  stratégie :  les valeurs sont données dans le code
     */
    public static int [][] genereGrilleComplete(){
    //       1 2 3 4 5 6 7 8 9
    //    ------------------- 
    //    1 |6 2 9|7 8 1|3 4 5|
    //    2 |4 7 3|9 6 5|8 1 2|
    //    3 |8 1 5|2 4 3|6 9 7|
    //    ------------------- 
    //    4 |9 5 8|3 1 2|4 7 6|
    //    5 |7 3 2|4 5 6|1 8 9|
    //    6| 1 6 4|8 7 9|2 5 3|
    //    ------------------- 
    //    7 3 8 1|5 2 7|9 6 4
    //    8 |5 9 6|1 3 4|7 2 8|
    //    9 |2 4 7|6 9 8|5 3 1|
    //    ------------------- 
	//_________________________________________________

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
        return g;
    } // fin initGrilleComplete

    //.........................................................................

     /** MODIFICI
     *  pré-requis : gSecret est une grille de Sudoku complète de mêmes dimensions que gIncomplete et 0 <= nbTrous <= 81
     *  action :     modifie gIncomplete pour qu'elle corresponde à une version incomplète de la grille de Sudoku gSecret (gIncomplete peut être complétée en gSecret), 
     *               avec nbTrous trous à des positions aléatoires
     * STRAT : boucle nbTrou fois dans laquelle : lister les coord de gsecret qui sont nons nulles
     * choisir au hazard paarmi ces coord pour supprimer la valeur =0.
     */
    public static void initGrilleIncomplete(int nbTrous, int [][] gSecret, int[][] gIncomplete){
        //___________________________________________________________________________
        copieMatrice(gSecret, gIncomplete);
        Random r = new Random();
        for (int k = 0; k < nbTrous; k++) {
            
        //METHODE AVEC TAB DE COORDS
            int[][] listeCoordNonNulRestant = coordGrilleNonNul(gIncomplete);
            //boucle
            int indice = r.nextInt(listeCoordNonNulRestant.length);
            gIncomplete[listeCoordNonNulRestant[indice][0]]
                    [listeCoordNonNulRestant[indice][1]] 
                    = 0;
        }
        
        } // fin initGrilleIncomplete



        

    /** pré-requis : gSecret est une grille de Sudoku complète et 0 <= nbTrous <= 81
     *  résultat :   une grille de Sudoku incomplète pouvant être complétée en gSecret 
     *               et ayant nbTrous trous à des positions aléatoires
     * STRAT : boucle nbTrou fois dans laquelle : lister les coord de gsecret qui sont nons nulles
     * choisir au hazard paarmi ces coord pour supprimer la valeur =0.
     */
    public static int [][] genereGrilleIncomplete(int nbTrous, int [][] gSecret){
	//___________________________________________________________________________
        // int i, j;
        // i = 0; j = 0;
        Random r = new Random();
        for (int k = 0; k < nbTrous; k++) {

            // METHODE CHELOU
            // if (k < 2 * gSecret.length * gSecret.length / 3) {
            // i = r.nextInt(gSecret.length);
            // j = r.nextInt(gSecret.length);
            // while (i == 0 || j == 0 || gSecret[i][j] == 0) {
            // i = r.nextInt(gSecret.length);
            // j = r.nextInt(gSecret.length);
            // }
            // System.out.println((i+1) + " " + (j+1) + " " + gSecret[i][j]);
            // gSecret[i][j] = 0;
            // }
            // else {

            // METHODE AVEC TAB DE COORDS
            int[][] listeCoordNonNulRestant = coordGrilleNonNul(gSecret);
            // boucle
            int indice = r.nextInt(listeCoordNonNulRestant.length);
            gSecret[listeCoordNonNulRestant[indice][0]][listeCoordNonNulRestant[indice][1]] = 0;
        }
        return gSecret;
    } // fin initGrilleIncomplete

    public static int[][] coordGrilleNonNul(int[][] g) {
        int[][] listeCoordNonNul = new int[0][2];
        // int compteur = 0;
        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g.length; j++) {
                if (g[i][j] != 0) {
                    int[] coordNonNul = { i, j };
                    listeCoordNonNul = ajouterElemMat(listeCoordNonNul, coordNonNul, 2);
                    // compteur = compteur + 1;
                    // System.out.println(compteur);
                }
            }
        }
        return listeCoordNonNul;
    }

    // .........................................................................

    /**
     * pré-requis : 0 <= nbTrous <= 81
     * résultat : une grille 9x9 saisie dont les valeurs sont comprises ente 0 et 9
     * avec exactement nbTrous valeurs nulles
     * et avec re-saisie jusqu'à ce que ces conditions soient vérifiées.
     * On suppose dans la version de base que la grille saisie est bien une grille
     * de Sudoku incomplète.
     * stratégie : utilise la fonction saisirEntierMinMax
     */
    public static int[][] saisirGrilleIncomplete(int nbTrous) {

        int k = 3;
        int nbTrousCount = 0;
        int[][] grille = new int[k * k][k * k];

        while (nbTrousCount != nbTrous) {
            grille = new int[k * k][k * k];
            afficheGrille(k, grille);
            for (int i = 0; i < grille.length; i++) {
                for (int j = 0; j < grille.length; j++) {
                    System.out.println(
                            "Veuillez remplir la grille ci-dessus (saisir 0 pour un Trou), vous pouvez remplir plusieurs cases en séparant la saisie par un ou des espaces, le remplissage se fait de gauche à droite puis de haut en bas. \nVous êtes aux coordonées ("
                                    + (i + 1) + ", " + (j + 1) + ") : ");
                    System.out.println("Nb de Trous requis : " + nbTrous);
                    System.out.println("Nb de Trous saisis : " + nbTrousCount);
                    if (nbTrousCount < nbTrous) {
                        grille[i][j] = saisirEntierMinMax(0, 9);
                    } else {
                        System.out.println("Vous ne pouvez plus saisir de trous!");
                        grille[i][j] = saisirEntierMinMax(1, 9);
                    }
                    // System.out.print("\033[H\033[2J"); //move console slo thats whats above is
                    // not shown
                    // System.out.flush(); // reposition cursor
                    afficheGrille(k, grille);
                    if (grille[i][j] == 0) {
                        nbTrousCount++;
                    }
                }
            }
        }
        input.close();
        return grille;
        // _________________________________________________

    } // fin saisirGrilleIncomplete

    // .........................................................................

    /**
     * pré-requis : gOrdi est une grille de Sudoku incomplète,
     * valPossibles est une matrice 9x9 de tableaux de 10 booléens
     * et nbValPoss est une matrice 9x9 d'entiers
     * action : met dans valPossibles l'ensemble des entiers de 1 à 9 pour chaque
     * trou de gOrdi et leur nombre dans nbValPoss
     */
    public static void initPleines(int[][] gOrdi, boolean[][][] valPossibles, int[][][] nbValPoss) {
        // POSER QUESTION int[][] ou int[][][]
        
        // ________________________________________________________________________________________________
        
        for (int i = 0; i<gOrdi.length;i++){
            for (int j = 0; j<gOrdi[0].length;j++){
                valPossibles[i][j] = ensPlein(gOrdi.length);
            }    
        }
        for (int i = 0; i<gOrdi.length;i++){
            for (int j = 0; j<gOrdi[0].length;j++){
                nbValPoss[i][j] = boolToInt(gOrdi.length); //verif par rapport a ensplein ??????
            }    
        }


        
    } // fin initPleines

    public static int[] boolToInt(int n) {
                // _____________________________________
        int[] tab = new int[n+1];
        for (int i = 1; i < tab.length; i++) {
            tab[i] = i;
        }
        return tab;

    }

    // .........................................................................

    /**
     * pré-requis : gOrdi est une grille de Sudoku incomplète,
     * 0<=i<9, 0<=j<9,g[i][j]>0,
     * valPossibles est une matrice 9x9 de tableaux de 10 booléens
     * et nbValPoss est une matrice 9x9 d'entiers
     * action : supprime dans les matrices valPossibles et nbValPoss la valeur gOrdi[i][j] pour chaque case de la ligne, de la colonne et du carré contenant la case (i,j) correspondant à un trou de gOrdi.
     */
    public static void suppValPoss(int[][] gOrdi, int i, int j, boolean[][][] valPossibles, int[][] nbValPoss) {
        // _____________________________________________________________________________________________________________
        //verif ligne/colonne/carre par TROU et supprimer 

            //LIGNES
        for (int l = 0; l<gOrdi.length; l++) {
            if (gOrdi[i][l]>0){
            supprime(valPossibles[i][j], gOrdi[i][l]);
                }
            
        }

        //COLONNES
        for (int k = 0; k<gOrdi.length;k++){
            if (gOrdi[k][j]>0){
                supprime(valPossibles[k][j],gOrdi[k][j]);
            }
        }
        
        //CARRE
        int k = racineParfaite(gOrdi.length);
        int xDebCarre, yDebCarre;
        xDebCarre = debCarre(k,i,j)[0];
        yDebCarre = debCarre(k,i,j)[1];

        for (int ligne = xDebCarre; ligne < xDebCarre + k; ligne++) {
            for (int colonne = yDebCarre; colonne < yDebCarre + k; colonne++){
                if (gOrdi[ligne][colonne]>0){
                    supprime(valPossibles[ligne][colonne],gOrdi[ligne][colonne]);
                }
            }
        }

    } // fin suppValPoss


    
    public static int racineParfaite(int n) {
            int tmp;
            int d = n / 2;
            do {
            tmp = d;
            d = (tmp + (n / tmp)) / 2;
            } while ((tmp - d) != 0);
            return d;
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

    } // fin initPossibles

    // .........................................................................

    /**
     * pré-requis : gSecret, gHumain et gOrdi sont des grilles 9x9
     * action : demande au joueur humain de saisir le nombre nbTrous compris entre 0
     * et 81,
     * met dans gSecret une grille de Sudoku complète,
     * met dans gHumain une grille de Sudoku incomplète, pouvant être complétée en
     * gSecret
     * et ayant exactement nbTrous trous de positions aléatoires,
     * met dans gOrdi une grille de Sudoku incomplète saisie par le joueur humain
     * ayant nbTrous trous,
     * met dans valPossibles l'ensemble des valeurs possibles de chaque trou de
     * gOrdi
     * et leur nombre dans nbValPoss.
     * retour : la valeur de nbTrous
     */
    public static int initPartie(int[][] gSecret, int[][] gHumain, int[][] gOrdi, boolean[][][] valPossibles,
            int[][] nbValPoss) {
        // ______________________________________________________________________________________________

    } // fin initPartie

    // ...........................................................
    // Tour du joueur humain
    // ...........................................................

    /**
     * pré-requis : gHumain est une grille de Sudoju incomplète pouvant se compléter
     * en
     * la grille de Sudoku complète gSecret
     *
     * résultat : le nombre de points de pénalité pris par le joueur humain pendant
     * le tour de jeu
     *
     * action : effectue un tour du joueur humain
     */
    public static int tourHumain(int[][] gSecret, int[][] gHumain) {
        // ___________________________________________________________________

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

    } // fin chercheTrou

    // .........................................................................

    /**
     * pré-requis : gOrdi est une grille de Sudoju incomplète,
     * valPossibles est une matrice 9x9 de tableaux de 10 booléens
     * et nbValPoss est une matrice 9x9 d'entiers
     * action : effectue un tour de l'ordinateur
     */
    public static int tourOrdinateur(int[][] gOrdi, boolean[][][] valPossibles, int[][] nbValPoss) {
        // ________________________________________________________________________________________________

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
        // _____________________________

    } // fin partie

    // .........................................................................

    /**
     * pré-requis : aucun
     * action : effectue une partie de Sudoku entre le joueur humain et l'ordinateur
     * et affiche qui a gagné
     */
    public static void main(String[] args) {
        // ________________________________________

    } // fin main

} // fin SudokuBase
