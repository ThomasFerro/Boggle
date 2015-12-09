package boggle.game.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Properties;

import boggle.game.controller.GameEngineV2;
import boggle.game.controller.traitement.Command;
import boggle.game.controller.traitement.Message;
import boggle.game.entity.Human;
import boggle.game.entity.Player;
import boggle.words.Dice;
import boggle.words.DiceGrid;
import boggle.words.LexicalTree;

public abstract class GameV2 extends Observable{
	private int round;
	private Player[] players;
	private Player currentPlayer;
	private GameEngineV2 gameEngine;
	private DiceGrid grid;
	private LexicalTree tree;
	private int minSize;
	private int[] pointGrid;
	private String gridPath;
	private String treePath;

	public GameV2(Player[] players, File config) {
		this.gameEngine = new GameEngineV2(this);
		this.addObserver(gameEngine);
		this.players = players;
		//TODO : Trouver un moyen d'extends Player de Observable
		for(Player player : this.players) {
			((Human)player).addObserver(gameEngine);
		}
		this.round = 0;
		this.loadConfigs(config);
	}

	public void demarrer() {
		//Load the properties then run the game:
		try {
			//Créer le DiceGrid :
			grid = new DiceGrid(minSize+1,gridPath, this.getGameEngine());
			//Créer l'arbre:
			tree = LexicalTree.readWords(treePath);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}

		//Run the game
		System.out.println("Running...");
		do {
			for(int i = 0; i < players.length; i++) {
				//Actions sur la grille :
				grid.notify(new Command("GRID", Message.MELANGER_GRILLE));
				
				//A SUPPRIMER --------------------------------
				Dice[][] lgrid = grid.getGrid();
				for (int j = 0; j < lgrid.length; j ++) {
					for (int k = 0; k < lgrid[j].length; k++) {
						System.out.print(lgrid[j][k].getCurrentFace());
					}
					System.out.println();
				}
				// FIN A SUPPRIMER ---------------------------
				
				//Actions sur le joueur:
				currentPlayer = players[i];
				currentPlayer.notify(new Command("PLAYER", Message.DONNER_MAIN));
				while(currentPlayer.isPlaying()) {
					System.out.print("");
				}
				endTurn();
				//TODO : Une fois le Timer en place : Si fin du timer :
				//currentPlayer.notify(new Command("PLAYER", Message.PRENDRE_MAIN));
				currentPlayer.notify(new Command("PLAYER", Message.CALCULER_SCORE));
			}
			this.notify(new Command("GAME", Message.INCREMENTER_TOUR));
			System.out.println("Fin du tour.");
		}while(!isFinished());
		System.out.println("Fin de la partie.");
	}
	
	protected abstract boolean isFinished();

	public void arreter() {
		//Compare les points de chacun, établie le gagnant.
		List<Player> p = Arrays.asList(players);
		Collections.sort(p, Collections.reverseOrder());
		System.out.println("GAGNANT : " + p.get(0).getName() + " AVEC " + p.get(0).getScore() + "POINTS");
	}

	public void incrementer_tour() {
		this.round++;
	}

	public void notify(Object o) {
		this.setChanged();
		this.notifyObservers(o);
	}

	private boolean loadConfigs(File config) {
		//Load the properties :
		Properties props;
		FileInputStream stream = null;
		try {
			props = new Properties();
			stream = new FileInputStream(config);
			props.load(stream);
			this.minSize = Integer.parseInt(props.getProperty("taille-min"));

			//Points : 
			String[] tab = props.getProperty("taille-min").split(",");
			pointGrid = new int[tab.length];
			for(int i = 0; i < tab.length; i++) {
				pointGrid[i] = Integer.parseInt(tab[i]);
			}

			//Des :
			try {
				gridPath = "config/"+props.getProperty("des");
			}
			catch(Exception ex) {
				System.out.println("Error : Grid/Dice load");
			}

			//Dictionnaire :
			try
			{
				treePath = "config/"+props.getProperty("dictionnaire");
			}
			catch(Exception ex) {
				System.out.println("Error : Dictionary load");
			}
		}
		catch(Exception e) {
			System.out.println("Error : Load properties");
		}
		finally {
			try {
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	protected void endTurn() {
		//Pour chaque mot de la liste
		for(String word : currentPlayer.getWords()) {
			//Vérifier la validité du mot :
			if(tree.contains(word)) {
				//Vérifier la valeur puis ajouter au score du joueur
				currentPlayer.setScore(currentPlayer.getScore()+checkScore(word));
			}
		}
	}

	private int checkScore(String word) {
		int sizeTab = pointGrid.length;

		//On ne donne pas de point si le mot est plus petit que la taille minimum
		//Si le mot est plus grand que le maximum de lettres, donner le maximum de points
		//Sinon, on donne le nombre de points correspondants

		if(word.length() >= minSize) {
			if(word.length() >= sizeTab) 
				return pointGrid[sizeTab-1];
			for(int i = 0; i < sizeTab; i++) {
				if(word.length() == (minSize+i)) 
					return pointGrid[i];
			}
		}	
		return 0;
	}

	public int getRound() {
		return round;
	}

	public Player[] getPlayers() {
		return players;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public GameEngineV2 getGameEngine() {
		return gameEngine;
	}

	public DiceGrid getGrid() {
		return this.grid;
	}

	public LexicalTree getTree() {
		return tree;
	}
}
