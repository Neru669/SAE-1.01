SAE SUDOKU

Groupe S1
Rigaux Héloïse
Renaud Julien

Niveau d'implication : 
Rigaux (50%), Renaud (50%), le travail a été réalisé ensemble et 
équitablement dans son intégralité.

Chaque fichier peut être lancé directement depuis le terminal; la partie commencera automatiquement et
quelconque(s) explication(s) additionnelle(s) nécessaire(s) seront données pendant le déroulement de la partie.

commandes pour lancer le programme :
    javac SudokuBase.java 
    java SudokuBase
ou pour les extensions X :
    javac SudokuExtensionX.java
    java SudokuExtensionX

Note : concernant les extensions, nous marquons dans le code les fonctions modifées et ajoutées par 
un commentaire dans leurs descriptions, du type  '//EXTENSION[numéro de l'extension]'. Les 
fonctions modifiées sont des copiés collées de l'original avec une légère modification de leur nom 
du type 'maFonction' à 'maFonctionV2'. Dans ce README sont également détaillés les
stratégies liées à chacune des extensions.

N.B. les fichiers 'grille1.txt' (49 trous) et 'grille2.txt' (1 trou) sont des grilles de Sudoku valides (et modifiables).
Si vous choisissez d'utiliser ces grilles, assurez-vous que le nombre de trou choisi demandé en début de partie
(ou déterminé cf.Extension5) correspond au nombre de trous du fichier texte voulu. 

EXTENSION1 =====================================================================================

Joker versus choix aléatoire

STRATEGIE ---------------

En effet, en absence de trous évidents, 
il est judicieux pour l'Ordi de faire un choix aléatoire
lorsqu'il y a seulement 2 valeurs possibles. Il a une chance sur deux 
de réussir son coup. Au dela de 2, il aura plus de chances de se prendre un
point de pénalité que de réussir (pour 3 valeurs possibles, p=1/3 de réussir 
contre p=2/3 de se prendre un point de pénalité).

Nous commencons par modifier la fonction chercheTrou de sorte que si aucun
trou évident n'est détecté, il stocke les coordonnées du premier trou
(dans l'ordre des lignes croissantes) à 2 valeurs possibles (si ce dernier existe) ;
sinon stocke le premier trou à plus de deux valeurs possibles.

Nous modifions également la fonction tourOrdinateur, pour implémenter le cas où le nombre
de valeurs possibles est de 2, pour lequel l'Ordi devra choisir aléatoirment une valeur
parmi les deux. Pour cela, nous utilisons la fonction uneValeur. Nous modifions également 
cette fonction de sorte que l'ordinateur demande à l'humain une confirmation pour 
chaque case choisie/remplie par l'Ordi, grâce à une fonction ajoutée nommée 'askHumanToConfirm'. 
Nous supposons dans cette extension que l'humaine RESTE HONNETE.


FONCTION MODIFIEE ---------------

chercheTrou --> chercheTrouV1
tourOrdinateur --> tourOrdinateurV1

FONCTION AJOUTEE ----------------

askHumanToConfirm



EXTENSION2 =====================================================================================

Gestion de la tricherie 

STRATEGIE ---------------

Pour vérifier les doublons lors de la saisie, nous modifions la fonction 
saisirGrilleIncomplete, de sorte qu'à chacune des cases remplies ; nous vérifions 
si dans la ligne, colonne et carré contenant la case s'il existe un doublon de la valeur
saisie (sauf 0 pour la saisie d'un trou) dans cette case. 
Si oui, il re-remplit la case jusqu'à vérifier la condition 
'pas de doublons'. Pour cela nous ajoutons 3 fonctions de vérifications : 
'vérifierLignes' ; 'verifierColonnes'; 'verifierCarre'
Ajout : Même vérification pour saisirGrilleIncompleteFichier.

1er cas (trou avec aucune valeur possible) : pour cela nous modifions 
la fonction tourOrdinateur : lorsque la fonction détecte  un tel trou, nous
choisissons d'affecter la valeur -1 au point de pénalité, qui indique la fin
de la partie (condition qui sera vérifée dans la fonction  partie).

2eme cas (mauvais joker par humain) : pour cela il suffit de modifier tourOrdinateur 
à la saisie de la valeur par l'humain de vérifier si cette-dernière est bien 
contenu dans l'ensemble des valeurs possibles : cela revient à vérifier 
si valPossibles[i][j][val] est bien 'true' ; sinon 
nous retournons -1, et comme le cas précédent, ce qui indique la fin de la partie.

3eme cas (mensonge par humain) : toujours dans tourOrdinateur, lorsqu'Ordi rempli
une case avec un trou évident, il demande confirmation auprès de l'humain. Comme
il n'y a qu'une seule solution, si l'humain répond par la négation, tourOrdinateur détecte
la tentative de triche et la fonction tourOrdinateur retourne alors -1, signifiant la fin
de la partie. Dans le cas contraire, la partie continue.


FONCTION MODIFIEE ---------------

saisirGrilleIncomplete --> saisirGrilleIncompleteV2
saisirGrilleIncompleteFichier --> saisirGrilleIncompleteFichier
tourOrdiV1 --> tourOrdiV2
partie --> partie


FONCTION AJOUTEE ----------------

verifierLignes
verifierColonnes
verifierCarre


EXTENSION3 =====================================================================================

Optimisation de la gestion des trous évidents

STRATEGIE ---------------

Créer une pile contenant les coordonnées des trous évidents
'int[][] tabTrouEvident = new int[82][2]' en stockant le cardinal
de l'ensemble dans l'indice [0][0] de cette pile.

Initialiser la pile : parcourir la grille et insérer les coords 
des trous évidents dans la pile grâce à notre fonction 'initChercheTrouEvident'

Lors d'un remplissage de trou (évident ou non) par l'Ordi, d'autres trous évidents peuvent
apparaître sur la ligne/colonne/carré de la case remplie : 
nous créons une fonction 'synchroTrouEvident' qui vérifie et insère 
les coordonnées des nouveaux trous évidents dans la pile. Cette fonction 
prend également un boolean en paramètre qui indique si le trou rempli était évident ; 
auquel cas il faut décrementer le cardinal au préalable. 

Note : pour éviter la re-détection des trous évidents déjà existants, lors de 
l'initialisation et lors d'une nouvelle détection, 
le nbValPoss des trous évidents de la pile sont initialisés à -1.


FONCTION MODIFIEE ---------------

updateCaseRempli --> updateCaseRempli (pour être adaptable à notre stratégie, 
dans le cas où nbValPoss devient -1)
partie --> partie (ajout de la pile)
initPartie --> initPartie (inclusion l'initialisation de la pile)
tourOrdinateurV2 --> tourOrdinateurV3 (nouvelle gestion du cas trous évidents)

FONCTION AJOUTEE ----------------

initChercheTrouEvident
synchroTrouEvident


EXTENSION4 =====================================================================================

Construction d’une grille de Sudoku complète

STRATEGIE ---------------

Nous avons crée 4 fonctions de transformations, que nous appliquons 
ensuite de façon aléatoire dans 'initGrilleComplete' suite à la création 
d'une grille de Sudoku valide.
Nous utilisons la class Random pour générer un entier entre 0 et 4(exclu)
qui determinera le choix de la transformation ; et nous répétons ce 
processus dans une boucle (100 fois pour assurer une complète randomisation
de la grille)


FONCTION MODIFIEE ---------------

initGrilleComplete --> initGrilleCompleteV4
initPartie --> initPartie (initGrilleCompleteV4)

FONCTION AJOUTEE ----------------

Ajout d'une variable static privée (globale à la classe) : 'String fichierGrille'
Pour faciliter le changement de fichier.

Fonctions de transformations de grille carré :
    echangeLignes
    symetrieDiagPrin
    symetrieHorizontal
    rotation90deg


EXTENSION5 =====================================================================================

Construction d’une grille incomplète de niveau facile 

STRATEGIE ---------------

Nous modifions la fonctions initGrilleIncomplete de sorte qu'elle parcourt 
la grille en cherchant une case pour un trou évident. Pour cela, pour chacune 
des cases, on fait 'semblant' de former un trou. On resynchronise la grille en fonction 
de ce changement (actualisation de nbValPoss et de ValPossibles par la fonction 
'initPossibles') et on vérifie si les nbValPossibles des trous de la ligne, colonne, et carré 
contenant cette case restent bien évidents grâce aux fonctions de vérifications 
'verifierLignesValUnique', 'verifierColonnesValUnique' et 'verifierCarreValUnique'. 
Si oui, le trou évident est crée, et on répète le processus, jusqu'à ne plus trouver de trous évidents. 
Sinon, on remet la valeur d'origine dans la case. Pour chaque création de trous évidents, 
on transforme la grille grâce à la fonction 'flipFlopGrille'. initGrilleCompleteV5 renvoi 
le nombre de trous crées.

FONCTION MODIFIEE ---------------

initGrilleIncomplete --> initGrilleIncompleteV5
initPartie --> initPartie (modifier de facon à ce que le nombre de trou est 
fixé par le nombre de trou créees par initGrilleCompleteV5)

FONCTION AJOUTEE ----------------

flipFlopGrille : rassemble les fonctions de transformations, et 
réalise une succession de 100 transformations aléatoires dans une
seule et même fonction.

Note : elle a deux grilles en paramètre, gSecret et gIncomplete, de sorte à lorsqu'on 
modifie gIncomplete, on modifie aussi gSecret avec les mêmes  changements.

verifierLignesValUnique
verifierColonnesValUnique
verifierCarreValUnique


EXTENSION6 =====================================================================================

Comment trouver plus de trous évidents ?

STRATEGIE ---------------

Initialisations des tableaux 'colPossibles' 'ligPossibles'
'nbColPoss' et 'nbLigPoss' par la fonction ajoutée 'initPossiblesColLig'.

Pour colPossibles c'est une matrice 9x9
    abscisse i : indice des lignes de 0 à 8
    ordonnes j : valeurs possibles de 0 à 10 (0 négligé)
    le contenu dans (i, j) les colonnes qui possèdent des trous contenant la valeur j, une valeur possible
    de la ligne i. nbColPoss est le cardinal (le nombre de colonnes 
    qui vérifient cette condition pour une ligne et valeur correspondante).
Même principe pour ligPossibles.

Intégration de ces grilles pour trouver plus de trous évidents :

Notre stratégie (ABSENTE DANS LE CODE) :
Il faut modifier les fonctions de recherche de trou évidents de sorte que si nbColPoss == 1
ou nbLigPoss == 1, cela veut dire que la case de la colonne ou de la ligne associée (information
présente dans colPossibles et ligPossibles) est un trou évident. Il faudrait donc vérifier si ce
trou n'a pas déjà été ajouté/détecté dans la pile (peut-être conditioner la vérification à ne pas
prendre en compte les trous déjà dans la pile dont leur nbValPoss est initialisé à -1), puis l'ajouter
dans la pile...

FONCTION MODIFIEE ---------------

initPartie --> initPartie (prend les nouveaux tableaux en paramètres, lance la fonction 
d'initialisation de ces tableaux)

partie --> partie (intégrer l'initialisation de 'colPossibles' 'ligPossibles'
'nbColPoss' et 'nbLigPoss')


FONCTION AJOUTEE ----------------

initPossiblesColLig


EXTENSION7 (NON ABOUTI) =====================================================================================

Elimination de valeurs impossibles

STRATEGIE ---------------

Pour k=2, cas de la ligne:
Lorsque deux cases (j1 et j2) d'une même ligne (i), colonne ou carré contiennent la même paire de valeurs possibles, 
cela signifie que ces deux valeurs doivent être présentes dans ces deux cases spécifiques.
Donc ces valeurs ne peuvent pas apparaître dans les autres cases de la même ligne, colonne ou carré. 
On peut donc les éliminer de toutes les cases de la ligne, colonne ou carré concernés sauf (i, j1) et (i, j2).

Généralisation pour k=n (n de 2 à 8) :
Si l'union des ensembles de valeurs contenues dans n cases d'une même ligne, colonne ou carré est de cardinal n, 
c'est que les n valeurs doivent être réparties parmi les n cases. 
Ils ne peuvent pas apparaître ailleurs dans cette ligne, colonne ou carré. 

Sratégie :

1. Identifier toutes les combinaisons possibles de n cases dans une ligne, colonne ou carré.
2. Pour chaque combinaison, vérifier si l'union de leurs ensembles de valeurs possibles est de cardinal n.
    Si oui, éliminer ces valeurs des autres cases de la même ligne, colonne ou carré.

Les fonctions ajoutées pour répondre à la stratégie (ici cas k=2, et cas des lignes seulement) :
    - stockerTrouLigne => stocke les coordonnées des trous d'une ligne pour lequel nbvalpos = k
    - ValPossEgal => vérifie si les valeurs possible d'un trou est le même qu'un autre trou
    - suppValPossLigneSaufJ1J2 => enlève les valeurs impossibles d'une ligne sauf les deux cases entrées en 
paramètres
    - enleveValSaufJ1J2 => compare une à une les valeurs possibles de chacun des trous pour lequel
nbvalpos = 2. Si égales enlève les valeurs impossibles de tous les trous de la ligne SAUF les deux
comparés.

FIN DU README =====================================================================================