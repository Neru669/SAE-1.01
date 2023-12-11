import java.util.*;

public class Eliminationvalimposs {

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

    /**
     * MODIFICI
     * pré-requis : mat1 et mat2 ont les mêmes dimensions
     * action : copie toutes les valeurs de mat1 dans mat2 de sorte que mat1 et mat2
     * soient identiques
     */
    public static void copieMatrice(int[][] mat1, int[][] mat2) {
        // ________________________________________________________
        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat1[0].length; j++) {
                mat2[i][j] = mat1[i][j];
            }
        }
    } // fin copieMatrice

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
        boolean[] tab_bool = new boolean[n + 1];
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
        if (ens[val] == true) {
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
        int[] tabIntTrue = new int[0];
        for (int i = 1; i < ens.length; i++) {
            if (ens[i] == true) {
                tabIntTrue = ajouterElem(tabIntTrue, i);
            }
        }
        Random ran = new Random();
        int selec = ran.nextInt(tabIntTrue.length);
        return tabIntTrue[selec];
    } // fin

    public static int[] ajouterElem(int[] tab, int el) {
        int[] anothertab = new int[tab.length + 1];
        for (int i = 0; i < tab.length; i++) {
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
                // pour remplacer 0 par rien :d
                if (g[i][j] == -1) { // POUR VOIR TROUVER DANS PILE
                    System.out.print("x");
                } else if (g[i][j] == 0) {
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
        int debX = (i / k) * k; // abscisse indice de 0 a 8
        int debY = (j / k) * k; // ordo

        int[] debCoord = { debX, debY };

        // int[] debCoord = { ((i - 1) / k) * k + 1, ((j - 1) / k) * k + 1 }; // indice
        // de 1 a 9

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
        flipFlopGrille(gComplete);

    } // fin initGrilleComplete

    public static void flipFlopGrille(int[][] grille) {
        Random r = new Random();

        for (int m = 0; m < 100; m++) { // nombre de transfo
            int pick = r.nextInt(4);
            if (pick == 0) {
                rotation90deg(grille);
            } else if (pick == 1) {
                symetrieHorizontal(grille);
            } else if (pick == 2) {
                symetrieDiagPrin(grille);
            } else {
                echangeLignes(0, 1, grille);
            }
        }
    }

    public static void rotation90deg(int[][] gComplete) {

        int[][] grilleRota = new int[gComplete.length][gComplete.length]; // obligé mat carré

        for (int i = 0; i < gComplete.length; i++) {
            for (int j = 0; j < gComplete.length; j++) {
                grilleRota[j][gComplete.length - i - 1] = gComplete[i][j];
            }
        }

        copieMatrice(grilleRota, gComplete);

    }

    public static void symetrieHorizontal(int[][] gComplete) {

        int[][] grilleSym = new int[gComplete.length][gComplete.length];

        for (int i = 0; i < gComplete.length; i++) {
            for (int j = 0; j < gComplete.length; j++)
                grilleSym[gComplete.length - i - 1][j] = gComplete[i][j];
        }

        copieMatrice(grilleSym, gComplete);
    }

    public static void symetrieDiagPrin(int[][] gComplete) {

        int[][] grilleSymD = new int[gComplete.length][gComplete.length]; // obligé mat carré

        for (int i = 0; i < gComplete.length; i++) {
            for (int j = 0; j < gComplete.length; j++) {
                grilleSymD[j][i] = gComplete[i][j];
            }
        }

        copieMatrice(grilleSymD, gComplete);

    }

    public static void echangeLignes(int i1, int i2, int[][] gComplete) {
        int k = racineParfaite(gComplete.length);

        int[] coord1 = debCarre(k, i2, k);
        int[] coord2 = debCarre(k, i1, k);

        if (coord1[0] == coord2[0] && coord1[1] == coord2[1]) { // j=peu importe
            int temp;
            for (int j = 0; j < gComplete.length; j++) {
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
     * gSecret),
     * avec nbTrous trous à des positions aléatoires
     * STRAT : boucle nbTrou fois dans laquelle : lister les coord de gsecret qui
     * sont nons nulles
     * choisir au hazard paarmi ces coord pour supprimer la valeur =0.
     */
    public static void initGrilleIncomplete(int nbTrous, int[][] gSecret, int[][] gIncomplete) {
        // ___________________________________________________________________________
        copieMatrice(gSecret, gIncomplete);
        Random r = new Random();
        for (int k = 0; k < nbTrous; k++) {

            // METHODE AVEC TAB DE COORDS
            int[][] listeCoordNonNulRestant = coordGrilleNonNul(gIncomplete);
            // boucle
            int indice = r.nextInt(listeCoordNonNulRestant.length);
            gIncomplete[listeCoordNonNulRestant[indice][0]][listeCoordNonNulRestant[indice][1]] = 0;
        }

    } // fin initGrilleIncomplete

    public static int initGrilleIncompleteFacile(int[][] gSecret, int[][] gIncomplete) {

        int compteur = 0;
        copieMatrice(gSecret, gIncomplete);

        for (int i = 0; i < gIncomplete.length; i++) {
            for (int j = 0; j < gIncomplete.length; j++) {
                if (verifierLignes(0, i, gIncomplete)
                        && verifierColonnes(0, j, gIncomplete)
                        && verifierCarre(0, i, j, gIncomplete)) {
                    compteur = compteur + 1;
                    gIncomplete[i][j] = 0;
                }
            }
        }

        flipFlopGrille(gIncomplete);

        return compteur; // nombre de trou
    }

    // FAIRE FONCTION VERIFIER LIGNES/colonnes/CARRES

    public static boolean verifierLignes(int input, int i, int[][] grille) {
        for (int k = 0; k < grille.length; k++) {
            if (grille[i][k] == input) {
                return false;
            }
        }
        return true;
    }

    public static boolean verifierColonnes(int input, int j, int[][] grille) {
        for (int k = 0; k < grille.length; k++) {
            if (grille[k][j] == input) {
                return false;
            }
        }
        return true;
    }

    public static boolean verifierCarre(int input, int i, int j, int[][] grille) {
        int k = racineParfaite(grille.length);
        int xDebCarre, yDebCarre;
        xDebCarre = debCarre(k, i, j)[0];
        yDebCarre = debCarre(k, i, j)[1];

        for (int ligne = xDebCarre; ligne < xDebCarre + k; ligne++) {
            for (int colonne = yDebCarre; colonne < yDebCarre + k; colonne++) {
                if (grille[ligne][colonne] == input) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * pré-requis : gSecret est une grille de Sudoku complète et 0 <= nbTrous <= 81
     * résultat : une grille de Sudoku incomplète pouvant être complétée en gSecret
     * et ayant nbTrous trous à des positions aléatoires
     * STRAT : boucle nbTrou fois dans laquelle : lister les coord de gsecret qui
     * sont nons nulles
     * choisir au hazard paarmi ces coord pour supprimer la valeur =0.
     */
    public static int[][] genereGrilleIncomplete(int nbTrous, int[][] gSecret) {
        // ___________________________________________________________________________

        Random r = new Random();
        for (int k = 0; k < nbTrous; k++) {
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
        int nbTrousCount = 0;
        while (nbTrousCount != nbTrous) {
            grille = new int[k * k][k * k];
            afficheGrille(k, grille);
            for (int i = 0; i < grille.length; i++) {
                for (int j = 0; j < grille.length; j++) {
                    System.out.println(
                            "Veuillez remplir la grille ci-dessus (saisir 0 pour un Trou), vous pouvez remplir plusieurs cases en séparant la saisie par un ou des espaces, le remplissage se fait de gauche à droite puis de haut en bas. \nVous êtes aux coordonées ("
                                    + (i + 1) + ", " + (j + 1) + ") : ");
                    System.out.println("Nombre de Trous requis : " + nbTrous);
                    System.out.println("Nombre de Trous saisis : " + nbTrousCount);
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
        // POSER QUESTION int[][] LE NOMBRE DE VAL POSSIBLE (pas les valeurs en soit) ou
        // int[][][]

        // ________________________________________________________________________________________________

        for (int i = 0; i < gOrdi.length; i++) {
            for (int j = 0; j < gOrdi[0].length; j++) {
                if (gOrdi[i][j] == 0) {
                    valPossibles[i][j] = ensPlein(gOrdi.length);
                    nbValPoss[i][j] = gOrdi.length;
                } else {
                    nbValPoss[i][j] = 0;
                }
            }
        }

        // for (int i = 0; i<gOrdi.length;i++){
        // for (int j = 0; j<gOrdi[0].length;j++){
        // nbValPoss[i][j] = boolToInt(gOrdi.length); //verif par rapport a ensplein
        // ??????
        // }
        // }

    } // fin initPleines INUTILE POUR LINSTANT

    public static int[] boolToInt(int n) {
        // _____________________________________
        int[] tab = new int[n + 1];
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
     * action : supprime dans les matrices valPossibles et nbValPoss la valeur
     * gOrdi[i][j] MODIFICI (k EXTENSION) pour chaque case de la ligne, de la
     * colonne et du carré contenant
     * la case (i,j) correspondant à un trou de gOrdi.
     */
    public static void suppValPoss(int[][] gOrdi, int i, int j, boolean[][][] valPossibles, int[][] nbValPoss) {
        // _____________________________________________________________________________________________________________
        // verif ligne/colonne/carre par TROU et supprimer

        // LIGNES
        for (int l = 0; l < gOrdi.length; l++) {
            if (gOrdi[i][l] == 0) {
                boolean check = supprime(valPossibles[i][l], gOrdi[i][j]);
                if (check) {
                    if (nbValPoss[i][l] == -1) {
                        nbValPoss[i][l] = nbValPoss[i][l] + 1;
                    } else {
                        nbValPoss[i][l] = nbValPoss[i][l] - 1;
                    }
                }
            }

        }

        // COLONNES
        for (int k = 0; k < gOrdi.length; k++) {
            if (gOrdi[k][j] == 0) {
                boolean check = supprime(valPossibles[k][j], gOrdi[i][j]);
                if (check) {
                    if (nbValPoss[k][j] == -1) {
                        nbValPoss[k][j] = nbValPoss[k][j] + 1;
                    } else {
                        nbValPoss[k][j] = nbValPoss[k][j] - 1;
                    }
                }
            }
        }

        // CARRE
        int k = racineParfaite(gOrdi.length);
        int xDebCarre, yDebCarre;
        xDebCarre = debCarre(k, i, j)[0];
        yDebCarre = debCarre(k, i, j)[1];

        for (int ligne = xDebCarre; ligne < xDebCarre + k; ligne++) {
            for (int colonne = yDebCarre; colonne < yDebCarre + k; colonne++) {
                if (gOrdi[ligne][colonne] == 0) {
                    boolean check = supprime(valPossibles[ligne][colonne], gOrdi[i][j]);
                    if (check) {
                        if (nbValPoss[ligne][colonne] == -1) {
                            nbValPoss[ligne][colonne] = nbValPoss[ligne][colonne] + 1;
                        } else {
                            nbValPoss[ligne][colonne] = nbValPoss[ligne][colonne] - 1;
                        }
                    }
                }
            }
        }
    } // fin suppValPoss

    // suprimetoutesvaleurspossibles et 0
    public static void updateCaseRempli(int[][] gOrdi, int i, int j, boolean[][][] valPossibles, int[][] nbValPoss) {
        // verifie bien que gordi n'est PLUS un trou
        if (gOrdi[i][j] != 0) {
            for (int k = 1; k < valPossibles[i][j].length; k++) {
                supprime(valPossibles[i][j], k);
            }
            nbValPoss[i][j] = 0;
        }
    }

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
        initPleines(gOrdi, valPossibles, nbValPoss);
        for (int i = 0; i < gOrdi.length; i++) {
            for (int j = 0; j < gOrdi.length; j++) {
                suppValPoss(gOrdi, i, j, valPossibles, nbValPoss);
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
     */
    public static int initPartie(int[][] gSecret, int[][] gHumain, int[][] gOrdi, boolean[][][] valPossibles,
            int[][] nbValPoss, int[][] tabTrouEvident) {
        // ______________________________________________________________________________________________
        int nb = 82;
        while (nb < 0 || nb > 81) {
            System.out.println("Entre un nombre entre 0 et 81. Il s'agit du nombre de trous à compléter.");
            nb = input.nextInt();
        }
        initGrilleComplete(gSecret);
        initGrilleIncompleteFacile(gSecret, gHumain);
        // saisirGrilleIncomplete(nb, gOrdi); //vrai
        initGrilleIncomplete(nb, gSecret, gOrdi); // test ====================================================
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

        // Afficher gHUMAIN
        // Demander à l'entité humaine de choisir un trou (coord)
        // Verifier quil sagit bien dun trou, sinon repeter le demande (while)
        // Demander si il veut utiliser un jojojoker
        // Si OUI remplir le trou avec bonne val et +1 pen
        // SINON
        // Remplir le trou avec une valeur de 1 à 9 saisit
        // Si valeur FAUSSE, point de pénalité = + 1
        // et recommencer depuis joker
        //
        // ___________________________________________________________________
        int pen = 0;
        boolean boolTrou = false;

        String[] possibleAnsYes = { "Y", "y", "yes", "Yes", "YES" };
        String[] possibleAnsNo = { "N", "n", "no", "No", "NO" };

        while (!boolTrou) {
            afficheGrille(3, gHumain);
            System.out.println("Veuillez choisir les coordonnées d'un trou à remplir (lignes, colonnes) : ");
            System.out.print("i = ");
            int i = input.nextInt() - 1;
            System.out.print("j = ");
            int j = input.nextInt() - 1;

            while (gHumain[i][j] != 0) {
                System.out.println(
                        "Veuillez choisir les coordonnées d'un trou remplissable à remplir (lignes, colonnes) : ");
                System.out.print("i = ");
                i = input.nextInt() - 1;
                System.out.print("j = ");
                j = input.nextInt() - 1;
            }

            System.out.println("Voulez-vous un joker ? Fais ton chois et rentre Y/N ou yes/no");

            String ans = input.nextLine();

            while (!verifyStringFromTabString(ans, possibleAnsYes) &&
                    !verifyStringFromTabString(ans, possibleAnsNo)) {
                ans = input.nextLine();
                // System.out.println(ans);
            }

            if (verifyStringFromTabString(ans, possibleAnsYes)) {
                pen = pen + 1; // penalité
                gHumain[i][j] = gSecret[i][j];
                boolTrou = true;
            } else {
                System.out.println("Choisissez le nombre pour remplir cette case :");
                int ansTrou = saisirEntierMinMax(1, gSecret.length);
                if (ansTrou != gSecret[i][j]) {
                    pen = pen + 1;
                    System.out.println("Dommage, réessaie");
                } else {
                    gHumain[i][j] = gSecret[i][j];
                    boolTrou = true;
                }
            }
        }
        afficheGrille(3, gHumain);
        System.out.println("Au tour de Ordi :");
        return pen;
    } // fin tourHumain

    /**
     * action : verifier si un string est contenu dans un tab de String
     *
     * @param in
     * @param tabString
     * @return boolean true si oui false sinon
     */
    public static boolean verifyStringFromTabString(String in, String[] tabString) {
        for (String elem : tabString) {
            if (in.equals(elem)) {
                return true;
            }
        }
        return false;
    }
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
        int k = 0, m = 0;
        int[] tab = { -1, -1 };

        for (int i = 0; i < gOrdi.length; i++) {
            for (int j = 0; j < gOrdi[i].length; j++) {

                if (nbValPoss[i][j] == 2 && m == 0) { // 2valpos trou
                    tab[0] = i;
                    tab[1] = j;
                    k = 1;
                    m = 1;
                }

                if (gOrdi[i][j] == 0 && k == 0) { // trousup
                    tab[0] = i;
                    tab[1] = j;
                    k = 1;
                }
            }
        }

        return tab;

    } // fin chercheTrou

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

    public static void synchroTrouEvident(int[][] gOrdi, int[][] nbValPoss, int i, int j, int[][] tabTrouEvident,
            boolean checkTrouEvident) {

        if (checkTrouEvident) {
            tabTrouEvident[0][0] = tabTrouEvident[0][0] - 1;
        }

        // LIGNES
        for (int l = 0; l < gOrdi.length; l++) {
            if (gOrdi[i][l] == 0) {
                if (nbValPoss[i][l] == 1) {
                    tabTrouEvident[0][0] = tabTrouEvident[0][0] + 1;
                    tabTrouEvident[tabTrouEvident[0][0]][0] = i;
                    tabTrouEvident[tabTrouEvident[0][0]][1] = l;
                    nbValPoss[i][l] = -1; // eviter les doublons
                }

            }
        }

        // COLONNES
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

        // CARRE
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

    // .........................................................................

    /**
     * pré-requis : gOrdi est une grille de Sudoju incomplète,
     * valPossibles est une matrice 9x9 de tableaux de 10 booléens
     * et nbValPoss est une matrice 9x9 d'entiers
     * action : effectue un tour de l'ordinateur
     */
    public static int tourOrdinateur(int[][] gOrdi, boolean[][][] valPossibles, int[][] nbValPoss,
            int[][] tabTrouEvident) {
        // ________________________________________________________________________________________________
        /// étape 1: vérifier si trou évident, si oui le remplir. Si pls trous évidents
        // choisir le premier. Sinon ordi prend un joker. L'humain saisi la bonne valeur
        // du premier trou. Si humain rentre mauvaise valeur humain le dit et
        int[] tab = chercheTrou(gOrdi, nbValPoss);

        int k = 0, answ;

        if (tabTrouEvident[0][0] != 0) { // trous evidents
            int i = tabTrouEvident[tabTrouEvident[0][0]][0];
            int j = tabTrouEvident[tabTrouEvident[0][0]][1];

            while (k < valPossibles[i][j].length && valPossibles[i][j][k] != true) {
                k = k + 1;
            }
            gOrdi[i][j] = k;
            // RETIRERE CE QUI A ETE AJOUTE EYEYE
            suppValPoss(gOrdi, i, j, valPossibles, nbValPoss);
            updateCaseRempli(gOrdi, i, j, valPossibles, nbValPoss);
            synchroTrouEvident(gOrdi, nbValPoss, i, j, tabTrouEvident, true);
            return 0;
        }

        // if (nbValPoss[tab[0]][tab[1]] == 1) { /// evident
        // while (k < valPossibles[tab[0]][tab[1]].length &&
        // valPossibles[tab[0]][tab[1]][k] != true) {
        // k = k + 1;
        // }
        // gOrdi[tab[0]][tab[1]] = k;
        // // RETIRERE CE QUI A ETE AJOUTE EYEYE
        // suppValPoss(gOrdi, tab[0], tab[1], valPossibles, nbValPoss);
        // updateCaseRempli(gOrdi, tab[0], tab[1], valPossibles, nbValPoss);
        // return 0;
        // }

        else if (nbValPoss[tab[0]][tab[1]] == 2) {
            int count = 0;
            int valposs[] = new int[2];
            while (k < valPossibles[tab[0]][tab[1]].length && count < 2) {
                // STOCKER LES DEUX VAL
                if (valPossibles[tab[0]][tab[1]][k] == true) {
                    valposs[count] = k;
                    // RETIRERE CE QUI A ETE AJOUTE EYEYE

                    count = count + 1;
                }
                k = k + 1;
            }
            // CHOISIR UNE DES DEUX VAL
            Random r = new Random();
            int valChoix = r.nextInt(2);

            // VERIFIER SI JUSTE
            System.out.println("Ordi entre " + valposs[valChoix] + " aux coordonées (" + (tab[0] + 1) + ", "
                    + (tab[1] + 1) + ").");

            System.out.println("Est-ce juste ? (Yes/No ou Y/N) : ");

            String[] possibleAnsYes = { "Y", "y", "yes", "Yes", "YES" };
            String[] possibleAnsNo = { "N", "n", "no", "No", "NO" };

            String ans = input.nextLine();

            while (!verifyStringFromTabString(ans, possibleAnsYes) && !verifyStringFromTabString(ans, possibleAnsNo)) {
                ans = input.nextLine();
                // System.out.println(ans);
            }

            if (verifyStringFromTabString(ans, possibleAnsYes)) {
                gOrdi[tab[0]][tab[1]] = valposs[valChoix];
                suppValPoss(gOrdi, tab[0], tab[1], valPossibles, nbValPoss);
                updateCaseRempli(gOrdi, tab[0], tab[1], valPossibles, nbValPoss);
                synchroTrouEvident(gOrdi, nbValPoss, tab[0], tab[1], tabTrouEvident, true);
                return 0;
            } else {
                valChoix = (valChoix - 1) * (-1);
                gOrdi[tab[0]][tab[1]] = valposs[valChoix];
                suppValPoss(gOrdi, tab[0], tab[1], valPossibles, nbValPoss);
                updateCaseRempli(gOrdi, tab[0], tab[1], valPossibles, nbValPoss);
                synchroTrouEvident(gOrdi, nbValPoss, tab[0], tab[1], tabTrouEvident, true);
                return 1;
            }
        }

        else {
            System.out
                    .println("Je n'arrive pas à trouver la bonne réponse. Pourriez-vous combler le trou dans la ligne "
                            + tab[0] + " et la colonne " + tab[1] + " pour moi s'il-te-plait?");
            answ = input.nextInt();
            gOrdi[tab[0]][tab[1]] = answ;
            suppValPoss(gOrdi, tab[0], tab[1], valPossibles, nbValPoss);
            updateCaseRempli(gOrdi, tab[0], tab[1], valPossibles, nbValPoss);
            return 1;
        }
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

        int[][] gSecret = new int[9][9];
        int[][] gHumain = new int[9][9];
        int[][] gOrdi = new int[9][9];
        int[][] nbValPoss = new int[9][9];
        boolean[][][] valPossibles = new boolean[9][9][10];
        int[][] tabTrouEvident = new int[gOrdi.length * gOrdi.length][2];
        int penHum = 0, penOrdi = 0;
        int nbTrou = initPartie(gSecret, gHumain, gOrdi, valPossibles, nbValPoss, tabTrouEvident);

        // afficherMat(tabTrouEvident); // test

        for (int i = 0; i < nbTrou; i++) {
            penHum = penHum + tourHumain(gSecret, gHumain);

            afficheGrille(3, gOrdi); // test
            afficheGrille(3, nbValPoss); // test
            penOrdi = penOrdi + tourOrdinateur(gOrdi, valPossibles, nbValPoss, tabTrouEvident);
            afficherMat(tabTrouEvident); // test
            afficheGrille(3, gOrdi); // test
            afficheGrille(3, nbValPoss); // test
        }
        if (penHum == penOrdi) { // matchnul
            return 0;
        } else if (penHum < penOrdi) { // homme gagne
            return 1;
        } else {
            return 2;
        }

        // _____________________________

    } // fin partie



    public static int[][] stockerTrou(int[][] gOrdi, int[][] nbValPoss, int k) {
        // stock les coordonnes des trous pour lequel nbvalpos = k;
        int[][] tabTrouK = new int[gOrdi.length * gOrdi.length][2];
        tabTrouK[0][0] = 0;

        for (int i = 0; i < gOrdi.length; i++) {
            for (int j = 0; j < gOrdi.length; j++) {
                if (nbValPoss[i][j] == k) {
                    tabTrouK[0][0] = tabTrouK[0][0] + 1;
                    tabTrouK[tabTrouK[0][0]][0] = i;
                    tabTrouK[tabTrouK[0][0]][1] = j;
                }
            }
        }
        return tabTrouK;
    }

    public static int[] stockerTrouLigne(int[][] gOrdi, int[][] nbValPoss, int k, int i) {
        /// stocke les j de la ligne i où le nbValPoss = k.
        int[] tabTrouK = new int[gOrdi.length];
        tabTrouK[0] = 0;

        for (int j = 1; j < gOrdi.length; j++) {
            if (nbValPoss[i][j] == k) {
                tabTrouK[tabTrouK[0]] = j;
                tabTrouK[0] = tabTrouK[0] + 1;
            }
        }
        return tabTrouK;
    }

    
    public static void enleveValSaufJ1J2(int[][] gOrdi, boolean[][][] valPoss, int ligne, int[][] nbValPoss) {
        int[] stockage = stockerTrouLigne(gOrdi, nbValPoss, 2, ligne);
        for (int j = 1; j < stockage.length - 1; j++) {
            for (int j2 = j + 1; j2 < stockage.length; j2++) {
                if (ValPossEgal(valPoss, ligne, j, ligne, j2)) {
                    for (int k = 1; k < valPoss[ligne][j].length; k++) {
                        if (valPoss[ligne][j][k]) {
                            suppValPossLigneSaufJ1J2(gOrdi, valPoss, nbValPoss, j, j2, ligne, k);
                        }
                    }
                }
            }
        }
    }

    public static void suppValPossLigneSaufJ1J2(int[][] gOrdi, boolean[][][] valPoss, int[][] nbValPoss, int j1, int j2,
            int ligne, int k) {

        for (int j = 0; j < gOrdi.length; j++) {
            if (gOrdi[ligne][j] == 0 && j != j1 && j != j2) {
                valPoss[ligne][j][k] = false;
                nbValPoss[ligne][j] = nbValPoss[ligne][j] - 1;
            }
        }
    }

    public static boolean ValPossEgal(boolean[][][] valPossibles, int i1, int j1, int i2, int j2) {
        // Verifie les valposs entre deux cases (i1, j1) et (i2, j2)
        for (int i = 1; i < valPossibles[1].length; i++) {
            if (valPossibles[i1][j1][i] != valPossibles[i2][j2][i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * pré-requis : gOrdi est une grille de Sudoku incomplète,
     * 0<=i<9, 0<=j<9,g[i][j]>0,
     * valPossibles est une matrice 9x9 de tableaux de 10 booléens
     * et nbValPoss est une matrice 9x9 d'entiers
     * action : supprime dans les matrices valPossibles et nbValPoss la valeur
     * gOrdi[i][j] MODIFICI (k EXTENSION) pour chaque case de la ligne, de la
     * colonne et du carré contenant
     * la case (i,j) correspondant à un trou de gOrdi.
     */
    public static void suppValPossV2(int[][] gOrdi, int i, int j, boolean[][][] valPossibles, int[][] nbValPoss,
            int valK) {
        // _____________________________________________________________________________________________________________
        // verif ligne/colonne/carre par TROU et supprimer

        // LIGNES
        for (int l = 0; l < gOrdi.length; l++) {
            if (gOrdi[i][l] == 0) {
                boolean check = supprime(valPossibles[i][l], valK);
                if (check) {
                    if (nbValPoss[i][l] == -1) {
                        nbValPoss[i][l] = nbValPoss[i][l] + 1;
                    } else {
                        nbValPoss[i][l] = nbValPoss[i][l] - 1;
                    }
                }
            }

        }

        // COLONNES
        for (int k = 0; k < gOrdi.length; k++) {
            if (gOrdi[k][j] == 0) {
                boolean check = supprime(valPossibles[k][j], valK);
                if (check) {
                    if (nbValPoss[k][j] == -1) {
                        nbValPoss[k][j] = nbValPoss[k][j] + 1;
                    } else {
                        nbValPoss[k][j] = nbValPoss[k][j] - 1;
                    }
                }
            }
        }

        // CARRE
        int k = racineParfaite(gOrdi.length);
        int xDebCarre, yDebCarre;
        xDebCarre = debCarre(k, i, j)[0];
        yDebCarre = debCarre(k, i, j)[1];

        for (int ligne = xDebCarre; ligne < xDebCarre + k; ligne++) {
            for (int colonne = yDebCarre; colonne < yDebCarre + k; colonne++) {
                if (gOrdi[ligne][colonne] == 0) {
                    boolean check = supprime(valPossibles[ligne][colonne], valK);
                    if (check) {
                        if (nbValPoss[ligne][colonne] == -1) {
                            nbValPoss[ligne][colonne] = nbValPoss[ligne][colonne] + 1;
                        } else {
                            nbValPoss[ligne][colonne] = nbValPoss[ligne][colonne] - 1;
                        }
                    }
                }
            }
        }
    } // fin suppValPoss

    // .........................................................................

    /**
     * pré-requis : aucun
     * action : effectue une partie de Sudoku entre le joueur humain et l'ordinateur
     * et affiche qui a gagné
     */
    public static void main(String[] args) {
        // ________________________________________
        System.out.println(partie());
    } // fin main

} // fin SudokuBase
