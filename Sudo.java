
import java.util.Arrays;

public class Sudo{
  public static void main(String [] args){
    long startTime = System.nanoTime();
    // //TEST SAISIR ENTIER entre min max
    // int a = SudokuBase.saisirEntierMinMax(9, 12);
    // System.out.println(a);

    // // TEST GRILLE
    // int[][] g5 = new int[25][25];
    // int[][] g3 = new int[9][9];
    // int[][] g2 = new int[4][4];
    // int[][] g1 = new int[1][1];
    // int[][] g0 = new int[0][0];
    // SudokuBase.afficheGrille(0, g0);
    // SudokuBase.afficheGrille(1, g1);
    // SudokuBase.afficheGrille(2, g2);
    // SudokuBase.afficheGrille(3, g3);
    // SudokuBase.afficheGrille(5, g5);

    //TEST DEBUT CARRE
    int[] deb = SudokuBase.debCarre(3, 9, 9);
    System.out.println("(" + deb[0] + ", " + deb[1] + ")");
    // Ceci est un commentaire test bakabakabakabkabkabkabkabkabka

    //TEST GRILLE Genre
    // int[][] g = SudokuBase.genereGrilleComplete();
    // SudokuBase.afficheGrille(3, g);

    // //TEST GRILLE INCOMPLETE Genere
    // int[][] gTrou = SudokuBase.genereGrilleIncomplete(79, g);
    // SudokuBase.afficheGrille(3, gTrou);
    // int[][] listeCoordNonNul = SudokuBase.coordGrilleNonNul(gTrou);
    // SudokuBase.afficherMat(listeCoordNonNul);


    //TEST ajouterelemmat et supprimerelemmat
    // int[][] mat = {{1, 2, 3}, {1, 2, 3}};
    // int[] val = {2, 2, 3, 4, 5};
    // int[][] newMat = SudokuBase.ajouterElemMat(mat, val);
    // newMat = SudokuBase.ajouterElemMat(newMat, val);
    // SudokuBase.afficherMat(newMat);

    // newMat = SudokuBase.supprimerElemMat(newMat, 3);
    // SudokuBase.afficherMat(newMat);


    /*int[] deb = SudokuBase.debCarre(3, 9, 9);
    System.out.println("(" + deb[0] + ", " + deb[1] + ")");
    boolean [] gbool = SudokuBase.ensPlein(7);
    for (int i = 0; i<gbool.length;i++){
      if (gbool[i]==false){
        System.out.print("False ");
      }
      else {
        System.out.print("True ");
      }
    }*/


    //random git test from vscode
    //random test 2

    //TEST SAISI GRILLE INCOMPLETE

     // SudokuBase.saisirGrilleIncomplete(10);
    // long endTime   = System.nanoTime();
    // long totalTime = endTime - startTime;
    // System.out.println(totalTime / 1e9);
    // boolean[] bp = SudokuBase.ensPlein(6);
    // System.out.println(Arrays.toString(bp));
    // SudokuBase.supprime(bp, 4);
    // System.out.println(Arrays.toString(bp));
    // System.out.println(SudokuBase.uneValeur(bp));
    
    // SudokuBase.saisirGrilleIncomplete(10);

 



    // SudokuBase.suppValPoss(gOrdie, 0, 0, valPossibles, gOrdie);

    int[][] grilleComp = new int[9][9];
    int[][] grilleInc = new int[9][9];

    SudokuBase.initGrilleComplete(grilleComp);
    SudokuBase.initGrilleIncomplete(10, grilleComp, grilleInc);
    SudokuBase.afficheGrille(3, grilleComp);

    boolean [][][] valPossibles = new boolean[9][9][10];
    int [][] gOrdi = new int [9][9];
    int [][] nbValPoss = new int[9][9];

    // SudokuBase.initPleines(grilleInc, valPossibles, nbValPoss);
    // // System.out.println(Arrays.deepToString(valPossibles));
    // // System.out.println(Arrays.deepToString(nbValPoss));

    // // SudokuBase.afficheGrille(3, nbValPoss);

    // SudokuBase.suppValPoss(grilleInc, 0, 3, valPossibles, nbValPoss);
    
    // // SudokuBase.afficheGrille(3, nbValPoss);
    
    // SudokuBase.initPossibles(grilleInc, valPossibles, nbValPoss);

    // SudokuBase.afficheGrille(3, nbValPoss);

    SudokuBase.initPartie(grilleComp, grilleInc, gOrdi, valPossibles, nbValPoss);
    // SudokuBase.tourHumain(grilleComp, grilleInc);
    SudokuBase.afficheGrille(3, gOrdi);
    SudokuBase.afficheGrille(3, nbValPoss);

    int[] coordTrou = SudokuBase.chercheTrou(gOrdi, nbValPoss);

    System.out.println(Arrays.toString(coordTrou));

    int penOrdi = SudokuBase.tourOrdinateur(gOrdi, valPossibles, nbValPoss);

    // SudokuBase.afficheGrille(3, gOrdie);

    System.out.println(penOrdi);
    
    long endTime   = System.nanoTime();
    long totalTime = endTime - startTime;
    System.out.println(totalTime / 1e9);
  }
}
