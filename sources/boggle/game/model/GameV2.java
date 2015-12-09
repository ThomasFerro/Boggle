package boggle.game.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Observable;
import java.util.Properties;

import boggle.game.controller.GameEngineV2;
import boggle.game.controller.traitement.Command;
import boggle.game.controller.traitement.Message;
import boggle.game.entity.Human;
import boggle.game.entity.Player;
import boggle.words.DiceGrid;
import boggle.words.LexicalTree;

public class GameV2 extends Observable{
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
			grid = new DiceGrid(minSize+1,gridPath);
			//Créer l'arbre:
			tree = LexicalTree.readWords(treePath);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		//Run the game
		System.out.println("Running...");
		
		for(int i = 0; i < players.length; i++) {
			currentPlayer = players[i];
			currentPlayer.notify(new Command("PLAYER", Message.DONNER_MAIN));
			currentPlayer.notify(new Command("PLAYER", Message.PRENDRE_MAIN));
			//TODO : Calcul du score avec endTurn() pas bon, trouver méthode avec le notify ci-dessous
			currentPlayer.notify(new Command("PLAYER", Message.CALCULER_SCORE));
		}
		this.notify(new Command("GAME", Message.INCREMENTER_TOUR));
	}
	
	public void arreter() {
		//TODO : Arreter une partie ?? 
	}
	
	public void incrementer_tour() {
		this.round++;
		//TODO : Calculer score ici ??
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
}
