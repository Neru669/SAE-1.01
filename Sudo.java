public class Sudo{
  public static void main(String [] args){
    // int a = SudokuBase.saisirEntierMinMax(9, 12);
    // System.out.println(a);

    int[][] g5 = new int[25][25];
    int[][] g3 = new int[9][9];
    int[][] g2 = new int[4][4];
    int[][] g1 = new int[1][1];
    int[][] g0 = new int[0][0];
    SudokuBase.afficheGrille(0, g0);
    SudokuBase.afficheGrille(1, g1);
    SudokuBase.afficheGrille(2, g2);
    SudokuBase.afficheGrille(3, g3);
    SudokuBase.afficheGrille(5, g5);

    // Ceci est un commentaire test bakabakabakabkabkabkabkabkabka
  }
}
