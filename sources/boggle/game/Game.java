package boggle.game;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

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
	private LetterGrid grid;
	private LexicalTree tree;
	private int minSize;
	private int[] pointGrid;

	Game(Player[] players, File config) {
		this.players = players;
		loadConfigs(config);
		if(launch())
			run();
		else
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
				grid = new LetterGrid(minSize+1,"config/"+props.getProperty("des"));
			}
			catch(Exception ex) {
				System.out.println("Error : Grid/Dice load");
			}
			
			//Dictionnaire :
			try
			{
				tree = LexicalTree.readWords("config/"+props.getProperty("dictionnaire"));
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
		//TODO : Créer une grille et un dictionnaire puis lance le run
		try {
			//Créer le tout.
		}
		catch(Exception e) {
			return false;
		}
		return true;
	}

	protected void run() {
		//TODO : Boucle tant que !isFinished(), passe au joueur suivant en début de boucle
	}

	protected void endTurn() {
		//TODO : Vérifie les mots et attribut les points
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
	
	public LetterGrid getGrid() {
		return grid;
	}

	public LexicalTree getTree() {
		return tree;
	}

	public int[] getPointGrid() {
		return pointGrid;
	}
	
	//-------------------------------------------

	public static void main(String[] args) {
		//Tests Game
		Player[] players = new Player[3];
		players[0] = new Human("Billy");
		players[1] = new Human("John");
		players[2] = new Human("Oui");
		Game game = new Game(players, new File("config/regles-4x4.config")) {
			public boolean isFinished() { return true; }
		};
		
	}

}
