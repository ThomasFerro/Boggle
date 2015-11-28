package boggle.words;

import java.util.ArrayList;
import java.util.List ;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;


/** La classe ArbreLexical permet de stocker de façon compacte et
 * d'accéder rapidement à un ensemble de mots.*/
public class LexicalTree {
    public static final int ALPHABET_HEIGTH = 26 ;
    private boolean isWord ; // vrai si le noeud courant est la fin d'un mot valide
    public LexicalTree[] sons = new LexicalTree[ALPHABET_HEIGTH] ; // les sous-arbres
    
    /** Crée un arbre vide (sans aucun mot) */
    public LexicalTree() {
    	this.isWord = false;
    }

    /** Indique si le noeud courant est situé à l'extrémité d'un mot
     * valide */
    public boolean isWord() { return isWord ; }
    
    
    /** Place le mot spécifié dans l'arbre
     * @return <code>true</code> si le mot a été ajouté,
     * <code>false</code> sinon*/
    public boolean add(String word, int level) {
    	//Check if the lettre is the last, create a new branch otherwise.
    	if(level < word.length()) {
    		if(sons[(int)(word.charAt(level)-'A')] == null)
    			sons[(int)(word.charAt(level)-'A')] = new LexicalTree();
    		return sons[(int)(word.charAt(level)-'A')].add(word, level+1);
    	}
    	else {
    		isWord = true;
    		return true;
    	}
    }

    /** Teste si l'arbre lexical contient le mot spécifié.
        @return <code>true</code> si <code>o</code> est un mot
        (String) contenu dans l'arbre, <code>false</code> si
        <code>o</code> n'est pas une instance de String ou si le mot
        n'est pas dans l'arbre lexical. */
    public boolean contains(String word) { 
    	return contains(word.toUpperCase(),0);
    }
    
    private boolean contains(String word, int level) {
    	//If this is the last letter of the word 
		if(word.length() == (level+1)) {
			//Check if the tree contains the letter and if it's a word:
			return (sons[(int)(word.charAt(level)-'A')] != null) ? sons[(int)(word.charAt(level)-'A')].isWord() : false;
		}
		else {
			//Check if the tree contains the letter and search deeper into the tree:
			return (sons[(int)(word.charAt(level)-'A')] != null) ? sons[(int)(word.charAt(level)-'A')].contains(word, level+1) : false;
		}
    }

    /** Ajoute à la liste <code>resultat<code> tous les mots de
     * l'arbre commençant par le préfixe spécifié. 
     * @return <code>true</code> si <code>resultat</code> a été
     * modifié, <code>false</code> sinon.*/
    public boolean wordsBeginningWith(String prefix, List<String> result) {
        return wordsBeginningWith(prefix.toUpperCase(), result, 0);
    }
    
    private boolean wordsBeginningWith(String prefix, List<String> result, int level) {
        // TODO: à compléter
    	//First step, find the prefix in the tree:
		//Check if the tree contains the letter and go deeper into the three to find the prefix or return false if the prefix doesn't exist:
    	if(prefix.length() < (level+1)) {
    		if(sons[(int)(prefix.charAt(level)-'A')] != null) {
    			return sons[(int)(prefix.charAt(level)-'A')].wordsBeginningWith(prefix, result, (level+1));
    		}
        	return false;
    	}
    	
    	//Second step, fill the list with words beginning with the prefix
    	if(prefix.length() == (level+1)) {
        	//Search for all the words :
    		
    		
    	}
    	else
    	{
    		//
    		
    	}
    	return false;
    }
    
    /** Crée un arbre lexical qui contient tous les mots du fichier
     * spécifié. */
    public static LexicalTree readWords(String filePath) {

    	LexicalTree tree = new LexicalTree();
    	
    	boolean valid;
    	
    	//Read the file with BufferedReader : 
    	BufferedReader br = null;
    	
    	try {
    		String currentLine;
        	br = new BufferedReader(new FileReader(filePath));
        	
        	//Reading..
        	while((currentLine = br.readLine()) != null) {
        		//Normalizer : remove all accents.
        		currentLine = Normalizer.normalize(currentLine.toUpperCase(),Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        		
        		//Check if the word if valid :
        		valid = true;
        		for(int i = 0; i < currentLine.length(); i++)
        			if(((int)(currentLine.charAt(i)-'A') < 0) || ((int)(currentLine.charAt(i)-'A') > 25)) 
        				valid = false;
        		//Add the word to the lexical tree:
        		if(valid) 
        			tree.add(currentLine,0);
        	}
    	}
    	catch(IOException e) {
    		System.out.println(e.getMessage());
    	}
    	finally{
    		try {
    			if(br != null)
    				br.close();
    		}
    		catch(IOException ex) {
    			System.out.println(ex.getMessage());
    		}
    	}
    	
        return tree;
    }
    
    public static void main(String[] args) {
    	LexicalTree tree = LexicalTree.readWords("config/dict-fr.txt");
    	
    	//Test WordsBeginningWith :
    	List<String> result = new ArrayList<String>();
    	System.out.println("Test WordsBeginningWith : \"anti\"");
    	tree.wordsBeginningWith("anti", result);
    	for(String elem: result) {
    		System.out.println("Element :" + elem);
    	}
    	
    	//Faits et vérifiés : readWords, add, contains.
    	//TODO : wordsBeginningWith.
    }
    
}
