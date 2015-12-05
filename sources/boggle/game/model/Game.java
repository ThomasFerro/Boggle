package boggle.game.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import boggle.game.entity.Human;
import boggle.game.entity.Player;
import boggle.words.Dice;
import boggle.words.DiceGrid;
import boggle.words.LexicalTree;

/**
 * 
 * @author leleuj ferrot
 *
 */
public abstract class Game {
	//TODO
	private int round;
	private Player[] players;
	private Player currentPlayer;
	private DiceGrid grid;
	private LexicalTree tree;
	private int minSize;
	private int[] pointGrid;
	private String gridPath;
	private String treePath;

	Game(Player[] players, File config) {
		this.round = 0;
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

	protected void run() {
		do {
			this.round++;
			for(int i = 0; i < players.length; i++) {
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
				
				//Tour d'un joueur
				currentPlayer = players[i];
				currentPlayer.getWords().clear();

				//Actions joueur
				playerInput();

				//Fin du tour
				endTurn();

				System.out.println("Player :"+ currentPlayer.getName() +"; Score :" +currentPlayer.getScore()+"\n");
			}
		}while(!isFinished());
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
	
	private void playerInput() {
		//Mode textuel : Attente entrée jusqu'à "Submit":
		System.out.println(currentPlayer.getName()+": Enter your words :");
		Scanner sc = new Scanner(System.in);
		String mot = "";
		do {
			mot = sc.nextLine();
			currentPlayer.addWord(mot);
		}while(!mot.equalsIgnoreCase("SUBMIT"));
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

	//-------------------------------------------

	public static void main(String[] args) {
		//Tests Game
		Player[] players = new Player[3];
		players[0] = new Human("Billy");
		players[1] = new Human("John");
		players[2] = new Human("Oui");
		Game game = new RoundGame(players, new File("config/regles-4x4.config"), 1);
	}
}
