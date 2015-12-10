package boggle.game.controller.highscore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import boggle.game.entity.Human;

public class HighscoreEditor {
	
	private final String PATH;
	private Map<Human, Integer> scores; //Score stocké dans Player, en Integer : le nombre de rounds
	
	public HighscoreEditor(String path) {
		PATH = path;
		scores = new HashMap<Human, Integer>();
	}
	
	public void readHighscore() throws IOException {
		BufferedReader file = new BufferedReader(new FileReader(PATH));
		String chaine;
		
		while ((chaine = file.readLine()) != null) {
				String[] data = chaine.split(",");
				Human p = new Human(data[1]);
				p.setScore(Integer.parseInt(data[2]));
				scores.put(p, Integer.parseInt(data[3]));
		}
		file.close();
	}
	
	public void insert(Human p, int rounds) {
		scores.put(p, rounds);
	}
	
	public void getHighscore(){
		
	}
	
	public static void main(String[] args) {
		HighscoreEditor h = new HighscoreEditor("config/Highscore");
		try {
			h.readHighscore();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
