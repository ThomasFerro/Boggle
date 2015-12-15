
# Mini-jeu Boggle - Projet Génie Logiciel - DA2I
## Leleu Julien - Ferro Thomas

###Liste des fonctionnalités

- [x] Jeu de Boggle développé en Java, disponible sur tous les OS compatibles avec Java donc.
- [x] Interface User-Friendly en Swing.
- [x] Multi-joueur en local (choix du pseudo avant début de la partie).
- [x] Règles personnalisables (cf [partie II.2](#ii2---ajout-de-règles-personnalisées)).
- [x] Gestion de la fin du tour par un sablier.
- [x] Mémorisation des cinque meilleurs scores.

### I - Compilation des sources et exécution du programme en version 1

Une version prête à l'usage est disponible au format _.jar_ directement sur [ce Github](https://github.com/ThomasFerro/ProjetLongGL) et éxecutable avec la commande suivante, dans le même dossier que votre éxecutable :

` java -jar Boggle.jar  `

Vous pouvez aussi modifier les sources vous-même et recompiler le projet grâce à la commande :

` javac -d bin/ -sourcepath sources/ sources/boggle/game/controller/GameEngine.java `

Depuis le dossier sources (avec les dossier *bin*, *config*...).
Puis l'éxécuter, toujours depuis le même dossier, avec la commande :

` java -classpath bin boggle.game.controller.GameEngine `

-----------------------------

### II - Documentation utilisateur
#### II.1 - Lancement du jeu

Le lancement du jeu est prévu pour être le plus clair possible pour l'utilisateur, voici comment il se déroule :
- Lancement de l'éxecutable (cf commande d'éxecution dans la [partie I](#i---compilation-des-sources-et-exécution-du-programme), choix des options dans le menu du jeu.
![alt tag](https://raw.github.com/ThomasFerro/ProjetLongGL/VF1/img/BoggleMenu.png)
- Choix du nom des joueurs depuis des popups.
- Puis la partie se lance...
![alt tag](https://raw.github.com/ThomasFerro/ProjetLongGL/VF1/img/BoggleJeu.png)

#### II.2 - Déroulement d'une partie point de vue utilisateur

Un partie typique, d'un point de vu utilisateur, se déroule en plusieurs étapes :
- Dans la fenêtre de jeu, chacun leur tour, les joueurs entrent des mots en cliquant sur les dès. 
- Après avoir fini son mot, le joueur clic sur **Add Word** pour le valider ce dernier mais peut aussi annuler son action en cliquant sur **Clear**. 
- Quand il a fini, le joueur peut cliquer sur **Submit** pour valider sa liste de mot, visible sur la droite de l'écran de jeu.
- Une fois validée, la liste est vérifiée et les points sont calculés en fonction des configurations prédéfinies.
- Le tour passe au joueur suivant, et ainsi de suite jusqu'à atteindre la limite, soit de points, soit de manches.
- Quand cette limite est atteinte, les scores sont calculés, le joueur ayant le plus de point est déclaré vainqueur et la liste des meilleurs score est modifiée, cas échéant.

#### II.3 - Ajout de règles personnalisées

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

À noter que nous avons réalisé une première version, celle du rendu, comprenant le jeu fonctionnel ainsi que quelques améliorations. Cependant, nous n'avions pas assez de recul au démarrage du projet et cette première version présente un clair problème de conception, notament au niveau du respect du pattern MVC. Par manque de temps, nous avons décidé de continuer malgré tout cette première version et de développer en parallèle une seconde, avec une refonte du GameEngine et des connexions entre Modèle, Vue et Controller.

#### III.1 - Génération et déroulement d'une partie point de vue technique

La génération d'une partie se fait grâce aux paramètres passées par l'utilisateur, depuis le fichier *config* et grâce à ses choix dans le menu. On souhaite notamment savoir si la partie à générer est limitée en points ou en tours. On récupère aussi cette limite, une limite de temps pour le tour, le nombre de joueurs ainsi que le fichier de configuration.


Une fois ces choix effectués, on affiche une popup par joueur afin qu'il inscrive son pseudo, puis la partie commence.


À partir d'ici, on note les grosses différences entre la première et la seconde version, voici le déroulement de chaque :

##### Version 2 (encore en développement)

Dans la seconde, le déroulement de la partie et les connexions entre les différentes parties du modèle MVC sont plus logiques et on retire un maximum de responsabilité au GameEngine, qui reprend son rôle normal de moteur de jeu, rediriger les appels entre toutes les parties du Boggle.


En effet, voici comment ce déroule une partie :
- Le Launcher passe à l'affichage de l'écran de jeu et lance une nouvelle instance de jeu avec les paramètres précédemment entrées par l'appel du message **DEMARRER_PARTIE**.
- La partie charge les configurations et se lance, après avoir créé son GameEngine (qui ici sert de moyen pour transmettre les messages entre la partie, les joueurs et la grille, et non plus à faire tout le traitement de la partie et de l'interface).
- On entre ensuite dans la boucle de jeu, qui se termine lorsque la méthode vérifiant la fin du tour ne renverra plus *faux*.
- Dans cette boucle, on définie le joueur courant, on secoue la grille et met à jour l'affichage puis on donne la main au joueur avec le message **DONNER_MAIN** et on attend que ce dernier la rende (ou que le sablier l'y oblige) avant de passer au joueur suivant.
- Lorsqu'un joueur à la main, tout se passe en graphique, avec la grilles et les boutons *Add Word* et *Submit* : l'ajout des mots comme la fin du tour.
- Une fois la main reprise par la partie, le jeu passe au joueur suivant.
- Quand chaque joueur à joué, la partie vérifie la condition de fin de jeu et si cette dernière est atteinte, on calcule les scores et définie un gagnant. Une popup est affichée, le tableau de score mit à jour cas échéant et l'affichage est redirigé vers le menu.

##### Version 1

Dans la première version, le déroulement de la partie était similaire dans le fond mais le GameEngine avait plusieurs rôles qui n'étaient pas les siens. En effet, C'est lui qui contient le *main* principale et qui gère l'initialisation et la mise en place de l'interface graphique. Il est aussi *Observeur* de tous les boutons du jeu.

La partie commençait donc ainsi :
- Initialisation de l'interface du menu, affichage de ce dernier.
- Une fois les informations entrées par l'utilisateur, chargement de la fenêtre de jeu, toujours pas le GameEngine, et ajout de tous les *Listeners*. Puis démarrage d'un **Thread** de *Game*.
- La partie suivait ensuite le même déroulement que dans la V2, à cela près que ce n'était pas le joueur qui jouait mais bien la partie. De plus, il n'était pas question de *Messages* envoyant des signaux aux joueurs et à la grille.


L'utilisation de l'accès concurrent par le biais d'un *Thread* nous aura posé de nombreux problèmes, notamment à l'affichage de la grille.


#### III.2 - Remarques
Cf [Javadoc](#) pour plus d'informations

----------------------------

### IV - Pistes d'amélioration et conclusion

Ce projet nous a permis de voir de nombreuses facettes de la programmation : de la réflexion en amont avant de se jeter sur le code afin d'être le plus clair possible sur le sujet à la conception effective de cette application et à la résolution de multiples questions d'ergonomies et d'optimisation.


De plus, nous avons pu mettre en application les notions de décomposition du code, notamment avec l'utilisation du pattern MVC (Model-View-Controller).


Nous avons de nombreuses pistes d'amélioration à explorer pour ce projet, voici quelques exemple :
- [ ] Conception d'une intelligence artificielle à plusieurs niveaux.
- [ ] Possibilité de proposer des mots en les tappant en plus de pouvoir directement cliquer sur les dés.
- [ ] Mise en place d'une aide contextuelle qui, à partir de trois lettres inscrites, affiche en surbrillance les lettres succeptibles de faire une mot plus grand.
