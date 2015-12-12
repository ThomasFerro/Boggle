package boggle.game.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;

import boggle.game.controller.traitement.Command;
import boggle.game.controller.traitement.Message;
import boggle.game.controller.traitement.MessageRetour;

/**
 */
public class Human extends Observable implements Player{
    private String name;
    private int score;
    private List<String> words;
    private boolean playing;
    
    public Human(String name) {
    	this.playing = false;
		this.name = name;
		this.score = 0;
		this.words = new ArrayList<String>();
    }

    //Player + Entity's methods
    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }
    public int getScore() { return this.score; }
    public void setScore(int score) { this.score = score; }
    public void addWord(String word) { words.add(word); }
    public List<String> getWords() { return words; }

	public int compareTo(Player o) {
		return this.getScore() - o.getScore();
	}
	
	public void notify(Object o) {
		this.setChanged();
		this.notifyObservers(o);
	}
	
	public MessageRetour plays() {
		//Joue
		this.playing = true;
		System.out.println(name+" JOUE");
		Scanner sc = new Scanner(System.in);
		String rep = "";
		do {
			System.out.print("Mot : ");
			rep = sc.nextLine();
			if (!rep.equals("")) { this.addWord(rep); };
			//TODO : A supprimer une fois en graphique
			if(rep.equals("")) 
				this.notify(new Command("PLAYER", Message.PRENDRE_MAIN));
		}while(isPlaying());
		this.playing = false;
		return MessageRetour.OK;
	}
	
	public MessageRetour stopPlaying() {
		this.playing = false;
		return MessageRetour.OK;
	}
	
	public boolean isPlaying() {
		return this.playing;
	}
	
	public MessageRetour updateScore(int points) {
		this.score+=points;
		return MessageRetour.OK;
	}
}
