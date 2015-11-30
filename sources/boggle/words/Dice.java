package boggle.words;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Dice {

	private List<List> element;
	private int size;

	public Dice() {
		element = new ArrayList<List>();
	}
	
	public static Dice readCSV(String path) {
		Dice d = new Dice();
		
		try {
			BufferedReader file = new BufferedReader(new FileReader(path));
			String line;
			while ((line = file.readLine()) != null) {
				String [] tabChaine = line.split(";");
				d.getElement().add(Arrays.asList(tabChaine));
			}
			file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return d;
	}
	
	public List getElement() {
		return element;
	}
	
	public char getRandomValue(int x, int y) {
		Random r = new Random();
		int indice = x + (y * size);
		int random = r.nextInt(6);
		return ((String) element.get(indice).get(random)).charAt(0);
	}
	
	public static void main(String[] args) {
		Dice d = Dice.readCSV("config/des-4x4.csv");
		System.out.println(d.getElement());
		System.out.println(d.getRandomValue(0,0));
	}
}