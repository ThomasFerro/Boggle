package boggle.game;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import boggle.words.Letter;
import boggle.words.LetterGrid;
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
	private LetterGrid grid;
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

	/**
	 * 
	 * @param config The config file (eg : regles-4x4.config)
	 * @return False if the load has failed, true otherwise
	 */
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

	/**
	 * Create the grid and the tree with the loadConfig properties
	 * @return True if the tree and the grid succesfully loaded
	 */
	private boolean launch() {
		try {
			//Créer le LetterGrid :
			grid = new LetterGrid(minSize+1,gridPath);
			//Créer l'arbre:
			tree = LexicalTree.readWords(treePath);
		}
		catch(Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * For each player : shake the grid, wait for the player to play his turn then call the endTurn method while the end game condition is false.
	 */
	protected void run() {
		do {
			this.round++;
			for(int i = 0; i < players.length; i++) {
				//Shake de la grille:
				grid.shake();
				
				//A SUPPRIMER --------------------------------
				Letter[][] lgrid = grid.getGrid();
				for (int j = 0; j < lgrid.length; j ++) {
					for (int k = 0; k < lgrid[j].length; k++) {
						System.out.print(lgrid[j][k].getCharacter());
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

	/**
	 * Check if the words are valid with the tree then attribute the points by calling the checkScore method
	 */
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

	/**
	 * Check the length of the word and return the points according to the point grid.
	 * @param word : The word to check.
	 * @return The value of that word.
	 */
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
	
	/**
	 * For textual mode, wait for the players' input until he type "submit".
	 */
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

	/**
	 * Check if the round or point limit is reached.
	 * @return True if the limit is reached.
	 */
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

	public LetterGrid getGrid() {
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
