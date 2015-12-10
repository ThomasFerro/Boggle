
# Mini-jeu Boggle - Projet Génie Logiciel - DA2I
## Leleu Julien - Ferro Thomas

###Liste des fonctionnalités

- [x] Jeu de Boggle développé en Java, disponible sur tous les OS compatibles avec Java donc.
- [x] Interface User-Friendly en Swing.
- [x] Multi-joueur en local (choix du pseudo avant début de la partie).
- [x] Règles personnalisables (cf [partie II.2](#ii2---ajout-de-règles-personnalisées)).
- [x] Mémorisation des cinque meilleurs scores.

### I - Compilation des sources et exécution du programme
TODO: Décrire procédure de compilation et d'éxecution

Une version prête à l'usage est disponible au format _.jar_ directement sur [ce Github](https://github.com/ThomasFerro/ProjetLongGL) et éxecutable avec la commande suivante, dans le même dossier que votre éxecutable :

` java -jar Boggle.jar  `

Vous pouvez aussi modifier les sources vous-même et recompiler le projet grâce à la commande :

` javac TODO `

Depuis le dossier sources (avec les dossier *bin*, *config*...).
Puis l'éxécuter, toujours depuis le même dossier, avec la commande :

` java -classpath bin boggle.game.controller.GameEngine `

-----------------------------

### II - Documentation utilisateur
#### II.1 - Lancement du jeu
TODO : Décrire lancement du jeu avec captures



#### II.2 - Ajout de règles personnalisées

Vous pouvez rajouter votre règles personnalisée en suivant scrupuleusement les instructions suivantes :

- Ces modifications seront apportées dans les fichiers du sous dossier *config*, dans le *.jar* (cf [partie I](#i---compilation-des-sources-et-exécution-du-programme) pour l'utilisation du fichier *.jar*)
- Placez votre fichier *nom_du_fichier_.config* dans ce dossier, en respectant le format suivant :  
  - taille-min:  x
  - des: *x.csv*
  - dictionnaire: *x.txt*
  - point: [tableau du type *1,1,2,3,5,11*]
- Le fichier représentant les dès doit être de la forme suivante : six lettres séparées d'un point virgule représentant les six faces du dès
- Le dictionnaire doit être de la forme suivante : un mot par ligne


- Une fois la partie lancer, vous pourrez charger ce fichier depuis le menu en cliquant sur **CONFIG**.

-----------------------------

### III - Documentation technique
#### III.1 - Génération d'une partie
TODO : Décrire génération de la partie
#### III.2 - Déroulement typique d'une partie

Un partie typique, d'un point de vu utilisateur, se déroule en plusieurs étapes :
- Lancement de l'éxecutable, choix des options dans le menu du jeu.
- Choix du nom des joueurs depuis des popups.
- Dans la fenêtre de jeu, chacun leur tour, les joueurs entrent des mots en cliquant sur les dès. 
- Après avoir fini son mot, le joueur clic sur **Add Word** pour le valider ce dernier mais peut aussi annuler son action en cliquant sur **Clear**. 
- Quand il a fini, le joueur peut cliquer sur **Submit** pour valider sa liste de mot, visible sur la droite de l'écran de jeu.
- Une fois validée, la liste est vérifiée et les points sont calculés en fonction des configurations prédéfinies.
- Le tour passe au joueur suivant, et ainsi de suite jusqu'à atteindre la limite, soit de points, soit de manches.
- Quand cette limite est atteinte, les scores sont calculés, le joueur ayant le plus de point est déclaré vainqueur et la liste des meilleurs score est modifiée, cas échéant.

#### III.3 - Remarques
Cf [Javadoc](#) pour plus d'informations

----------------------------

### IV - Pistes d'améliorations et conclusion
TODO : Les améliorations non implentées + écrire une conclusion au projet
