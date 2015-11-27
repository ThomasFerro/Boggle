package boggle.words;

import java.util.List ;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/** La classe ArbreLexical permet de stocker de façon compacte et
 * d'accéder rapidement à un ensemble de mots.*/
public class LexicalTree {
    public static final int ALPHABET_HEIGTH = 26 ;
    private boolean isWord ; // vrai si le noeud courant est la fin d'un mot valide
    private LexicalTree[] sons = new LexicalTree[ALPHABET_HEIGTH] ; // les sous-arbres
    
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
        // TODO: à compléter
    	//Check if the lettre is the last, create a new branch otherwise.
    	if(word.length() < level) {
    		
    		//sons[word.charAt(level)] 
    	}
        return false ;
    }

    /** Teste si l'arbre lexical contient le mot spécifié.
        @return <code>true</code> si <code>o</code> est un mot
        (String) contenu dans l'arbre, <code>false</code> si
        <code>o</code> n'est pas une instance de String ou si le mot
        n'est pas dans l'arbre lexical. */
    public boolean contains(String word, int level) {
        // TODO: à compléter
        return false ;
    }

    /** Ajoute à la liste <code>resultat<code> tous les mots de
     * l'arbre commençant par le préfixe spécifié. 
     * @return <code>true</code> si <code>resultat</code> a été
     * modifié, <code>false</code> sinon.*/
    public boolean wordsBeginningWith(String prefix, List<String> result) {
        // TODO: à compléter
        return false ;
    }
    
    /** Crée un arbre lexical qui contient tous les mots du fichier
     * spécifié. */
    public static LexicalTree readWords(String filePath) {
        // TODO: à compléter

    	LexicalTree tree = new LexicalTree();
    	
    	//Read the file with BufferedReader : 
    	BufferedReader br = null;
    	
    	try {
    		String currentLine;
        	br = new BufferedReader(new FileReader(filePath));
        	
        	//Reading..
        	while((currentLine = br.readLine()) != null) {
        		//Add the word to the lexical tree:
        		tree.add(currentLine, 0);
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
    	/*
    	String test = "test".toUpperCase();
    	System.out.println((char)(((int)test.charAt(0))));*/
    }
    
}
