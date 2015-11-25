package boggle.words;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Dice {

	private char[][] elements;
	private int size;

	public Dice(int size, String pathDice) {
		this.size = size;
		elements = new char[size*size][6];
		initElements(pathDice);
	}

	public void initElements(String pathDice) {
		try {
			BufferedReader fichier_source = new BufferedReader(new FileReader(
					pathDice));
			String ligne;
			int i = 0;
			while ((ligne = fichier_source.readLine()) != null) {
				String[] tabChaine = ligne.split(";");			//On decoupe la ligne
				for (int j = 0; j < elements[0].length; j++) {	
					elements[i][j] = tabChaine[j].charAt(0);	//On range dans chaque ligne du tableau elements
				}
				i++;
			}
			fichier_source.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public char [][] getElements() {
		return elements;
	}
	
	public void elementsToString() {
		for (int i = 0; i < elements.length; i++) {
			System.out.print(i + "\t");
			for (int j = 0; j < elements[i].length; j++) {
				System.out.print(elements[i][j] + " ");
			}
			System.out.print("\n");
		}
	}

	public char getRandomValue(int x, int y) {
		Random r = new Random();
		int indice = x + (y * size);
		int random = r.nextInt(6);
		return elements[indice][random];
	}
	
	/*public static void main(String[] args) {
		Dice d = new Dice (4, "config/des-4x4.csv");
		d.elementsToString();
		System.out.println(d.getRandomValue(0, 0));
	}*/
}