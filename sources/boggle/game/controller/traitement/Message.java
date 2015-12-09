package boggle.game.controller.traitement;

public enum Message {
	DEMARRER_PARTIE, ARRETER_PARTIE, INCREMENTER_TOUR, //Game
	DONNER_MAIN, PRENDRE_MAIN, CALCULER_SCORE, //Player
	MELANGER_GRILLE, BLOQUER_GRILLE; //Grid
}
