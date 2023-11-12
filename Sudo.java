import java.lang.reflect.Array;
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

    //TEST GRILLE INIT
    // int[][] g = SudokuBase.initGrilleComplete();
    // SudokuBase.afficheGrille(3, g);

    // //TEST GRILLE INCOMPLETE
    // int[][] gTrou = SudokuBase.initGrilleIncomplete(79, g);
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

    SudokuBase.saisirGrilleIncomplete(10);




    long endTime   = System.nanoTime();
    long totalTime = endTime - startTime;
    System.out.println(totalTime / 1e9);
  }
}
