package boggle.game.controller.traitement;

public class Command {
	private String traitementName;
	private Message message;
	
	public Command(String s, Message m) {
		traitementName = s;
		message = m;
	}

	public String getTraitementName() {
		return traitementName;
	}

	public Message getMessage() {
		return message;
	}
}
