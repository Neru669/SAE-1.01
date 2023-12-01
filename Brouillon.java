/**
 * Brouillon
 */
public class Brouillon {

    public static void main(String[] args) {
        int [][] g = new int[9][9];
        CreationGrille.initGrilleComplete(g);
        CreationGrille.afficheGrille(3, g);
        
        // CreationGrille.rotation90deg(g);
        // CreationGrille.afficheGrille(3, g);


        // CreationGrille.symetrieHorizontal(g);
        // CreationGrille.afficheGrille(3, g);
        
        // CreationGrille.symetrieDiagPrin(g);
        // CreationGrille.afficheGrille(3, g);

        // CreationGrille.echangeLignes(0, 1, g);
        // CreationGrille.afficheGrille(3, g);

        // CreationGrille.initGrilleComplete(g);
        // CreationGrille.afficheGrille(3, g);
        boolean boll;
        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g.length; j++) {
                boll = CreationGrille.verifierColonnes(g[i][j], j, g);
                System.out.println(boll);
                boll = CreationGrille.verifierCarre(g[i][j], i, j, g);
                System.out.println(boll);
                boll = CreationGrille.verifierLignes(g[i][j], i, g);
                System.out.println(boll);
            }
        }
    }
}