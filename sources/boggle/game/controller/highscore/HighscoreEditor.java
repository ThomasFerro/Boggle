package boggle.game.controller.highscore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import boggle.game.entity.Human;

/**
 * Class used to update the Highscore file.
 * @author ferrot leleuj
 */
public class HighscoreEditor {
	
	private final String PATH;
	private String[][] scores; 
	
	/**
	 * Initialize the array and the path.
	 * @param path The file path.
	 */
	public HighscoreEditor(String path) {
		PATH = path;
		scores = new String[10][3];
		for (int i = 0; i < scores.length; i++) {
			for (int j = 0; j < scores[i].length; j++) {
				scores[i][j] = "0";
			}
		}
	}
	
	/**
	 * Read the file and fill the array with it.
	 * @throws IOException
	 */
	public void readHighscore() throws IOException {
		File highscore = new File(PATH);
		if (!highscore.exists()) {
			highscore.createNewFile();
		}
		BufferedReader file = new BufferedReader(new FileReader(highscore));
		String chaine;
		
		int i = 0;
		while ((chaine = file.readLine()) != null && i < 10) {
				String[] data = chaine.split(",");
				if (data != null) {
					scores[i][0] = data[1];
					scores[i][1] = data[2];
					scores[i][2] = data[3];
				}
				i++;
		}
		file.close();
	}
	
	/**
	 * Sort the highscore array.
	 */
	public void sort() {
		for (int i = 0; i < scores.length; i ++) {
			for (int j = i; j < scores.length; j ++) {
				if (scores[i][1] != null && scores[j][1] != null && Integer.parseInt(scores[i][1]) < Integer.parseInt(scores[j][1])) {
					swap(i,j);
				}
			}
		}
	}

	/**
	 * Swap the two players in parameters.
	 * @param i The first player to swap.
	 * @param i2 The second player so swap.
	 */
	public void swap(int i, int i2) {
		String[] tmp = scores[i];
		scores[i] = scores[i2];
		scores[i2] = tmp;
	}

	/**
	 * Insert a player in the highscore array.
	 * @param p The player to insert.
	 * @param rounds Number of rounds played.
	 */
	public void insert(Human p, int rounds) {
		int i = 0;
		int score = p.getScore();
		boolean isHighscore = false;
		while (i < scores.length) {
			if (scores[i][1] != null && Integer.parseInt(scores[i][1]) < score) {
				isHighscore = true;
				break;
			}
			i++;
		}
		if (isHighscore) {
			scores[9] = new String[] {p.getName(), "" + p.getScore(), "" + rounds};
		}
	}
	
	/**
	 * Insert the array into the file.
	 */
	public void writeHighscore() {
		try {
			File highscore = new File(PATH);
			if (!highscore.exists()) {
				highscore.createNewFile();
			}
			BufferedWriter file = new BufferedWriter(new FileWriter(highscore));
			for (int i = 0; i < scores.length; i++) {
				file.write("" + (i + 1));
				for (int j = 0; j < scores[i].length; j++) {
					file.write("," + scores[i][j]);
				}
				file.newLine();
			}
			file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();			
		}
	}
	
	/**
	 * Insert and sort an entry into the array.
	 * @param h The Human to insert then sort.
	 * @param rounds Number of rounds played.
	 * @throws IOException
	 */
	public void insertAndSort(Human h, int rounds) throws IOException {
		readHighscore();
		sort();
		insert(h, rounds);
		sort();
		writeHighscore();
	}
	
	public String toString() {
		String str = "name\tScore\tRounds\n";
		for (int i = 0; i < scores.length; i ++) {
			for (int j = 0; j < scores[i].length; j++) {
				str += scores [i][j] + "\t";
			}
			str += "\n";
		}
		return str;
	}
}