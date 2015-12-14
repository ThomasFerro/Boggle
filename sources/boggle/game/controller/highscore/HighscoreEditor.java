package boggle.game.controller.highscore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import boggle.game.entity.Human;

public class HighscoreEditor {
	
	private final String PATH;
	private String[][] scores; 
	
	public HighscoreEditor(String path) {
		PATH = path;
		scores = new String[10][3];
		for (int i = 0; i < scores.length; i++) {
			for (int j = 0; j < scores[i].length; j++) {
				scores[i][j] = "0";
			}
		}
	}
	
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
	
	public void sort() {
		for (int i = 0; i < scores.length; i ++) {
			for (int j = i; j < scores.length; j ++) {
				if (scores[i][1] != null && scores[j][1] != null && Integer.parseInt(scores[i][1]) < Integer.parseInt(scores[j][1])) {
					swap(i,j);
				}
			}
		}
	}

	public void swap(int i, int i2) {
		String[] tmp = scores[i];
		scores[i] = scores[i2];
		scores[i2] = tmp;
	}

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
	
	public static void main(String[] args) {
		HighscoreEditor h = new HighscoreEditor("config/Highscore");
		try {
			h.readHighscore();
			h.sort();
			System.out.println(h);
			Human p = new Human("Joseph");
			p.setScore(3);
			h.insert(p, 3);
			h.writeHighscore();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
