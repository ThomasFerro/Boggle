package boggle.game.controller.highscore;

import java.io.File;
import java.util.Scanner;

import javax.swing.table.AbstractTableModel;

public class HighscoreModel extends AbstractTableModel{

	private String[] columnNames = {"Nickname", "Points", "Rounds"};
	private Object[][] data;

	public HighscoreModel(File hs) {
		data = new Object[11][getColumnCount()];
		//Header:
		data[0][0] = "Nickname";
		data[0][1] = "Points";
		data[0][2] = "Rounds";
		
		Scanner sc = null;
		String[] ligne;
		try {
			sc = new Scanner(hs);
			while(sc.hasNext()) {
				//For each line, add the nickname, the rounds and the points to the table data
				ligne = sc.nextLine().split(",");
				data[Integer.parseInt(ligne[0])][0] = ligne[1]; 
				data[Integer.parseInt(ligne[0])][1] = ligne[2]; 
				data[Integer.parseInt(ligne[0])][2] = ligne[3]; 
			}
		}
		catch(Exception e) {
			
		}
		finally {
			try {
				sc.close();
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public Object getValueAt(int x, int y) {
		return data[x][y];
	}
}
