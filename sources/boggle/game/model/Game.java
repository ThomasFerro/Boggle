package boggle.game.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Properties;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import boggle.game.controller.highscore.HighscoreEditor;
import boggle.game.entity.Human;
import boggle.game.entity.Player;
import boggle.words.Dice;
import boggle.words.DiceGrid;
import boggle.words.LexicalTree;

/**
 * The game class, run by the engine.
 * @author leleuj ferrot
 */
public abstract class Game extends Observable implements Runnable {
	private int round;
	private int timeLimit;
	private Player[] players;
	private Player currentPlayer;
	private DiceGrid grid;
	private LexicalTree tree;
	private int minSize;
	private int[] pointGrid;
	private String gridPath;
	private String treePath;
	private boolean submited;
	private Sablier sablier;

	/**
	 * Initialize the timer, the players and the time limit.
	 * @param players The player array.
	 * @param config The configuration file.
	 * @param timeLimit The time limit.
	 */
	public Game(Player[] players, File config, int timeLimit) {
		sablier = new Sablier();
		this.round = 0;
		this.timeLimit = timeLimit;
		this.players = players;
		loadConfigs(config);
		if(!launch())
			System.out.println("Game initialization error");
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
			String[] tab = props.getProperty("points").split(",");
			pointGrid = new int[tab.length];
			for(int i = 0; i < tab.length; i++) 
				pointGrid[i] = Integer.parseInt(tab[i]);


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

	private boolean launch() {
		try {
			//Créer le DiceGrid :
			grid = new DiceGrid(minSize+1,gridPath);
			//Créer l'arbre:
			tree = LexicalTree.readWords(treePath);
		}
		catch(Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Start a timer's thread then run the game.
	 */
	public void run() {
		Thread t = new Thread(sablier);
		t.start();
		while(!isFinished()) {
			this.round++;
			for(int i = 0; i < players.length; i++) {
				//Tour d'un joueur
				currentPlayer = players[i];
				currentPlayer.getWords().clear();
				submited = false;

				//Shake de la grille:
				grid.shake();

				//A SUPPRIMER --------------------------------
				Dice[][] lgrid = grid.getGrid();
				for (int j = 0; j < lgrid.length; j ++) {
					for (int k = 0; k < lgrid[j].length; k++) {
						System.out.print(lgrid[j][k].getCurrentFace());
					}
					System.out.println();
				}
				// FIN A SUPPRIMER ---------------------------
				sablier.setTimeLeft(timeLimit);
				//Actions joueur
				while(!isSubmited()) {
					System.out.print("");
				}
				sablier.setFinished();
				//Fin du tour
				endTurn();

				System.out.println("Player :"+ currentPlayer.getName() +"; Score :" +currentPlayer.getScore()+"\n");
			}
		}
		highscoreUpdate(players, round);
		endGame();
	}
	
	protected void endGame() { 
		//Boucle pour trouver le/les gagnants. Notifie le GameEngine.
		ArrayList<Player> joueurs = new ArrayList<Player>();
		int bestScore = 0;
		for(Player p : players) {
			if(p.getScore() > bestScore) {
				joueurs.clear();
				joueurs.add(p);
				bestScore = p.getScore();
			}
			else {
				if(p.getScore() == bestScore) {
					joueurs.add(p);
				}
			}
		}
		setChanged();
		notifyObservers(joueurs);
	}

	protected void endTurn() {
		//Pour chaque mot de la liste
		for(String word : currentPlayer.getWords()) {
			//Vérifier la validité du mot :
			if(!word.equals("") && tree.contains(word)) {
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
			return pointGrid[word.length()-minSize];
		}	
		return 0;
	}

	/**
	 * Update the highscore.
	 * @param players
	 * @param round
	 */
	public void highscoreUpdate(Player[] players, int round) {
		try {
			HighscoreEditor h = new HighscoreEditor("config/Highscore");
			for (Player p : players) {
				if (p instanceof Human) {
					h.insertAndSort((Human)p, round);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean isSubmited() {
		return submited;
	}

	/**
	 * Set the player's state to "Submited", finishing the turn.
	 */
	public void setSubmited() {
		submited = true;
	}

	protected abstract boolean isFinished();

	//-------------------------------------------
	//Getters and Setters :
	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public Player[] getPlayers() {
		return players;
	}

	public DiceGrid getGrid() {
		return grid;
	}

	public LexicalTree getTree() {
		return tree;
	}

	public int[] getPointGrid() {
		return pointGrid;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public Sablier getSablier() {
		return sablier;
	}

	//-------------------------------------------
}
