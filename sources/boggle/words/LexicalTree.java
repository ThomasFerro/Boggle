package boggle.words;

import java.util.List ;

/** La classe ArbreLexical permet de stocker de façon compacte et
 * d'accéder rapidement à un ensemble de mots.*/
public class LexicalTree {
    public static final int ALPHABET_HEIGTH = 26 ;
    private boolean isWord ; // vrai si le noeud courant est la fin d'un mot valide
    private LexicalTree[] sons = new LexicalTree[ALPHABET_HEIGTH] ; // les sous-arbres
    
    /** Crée un arbre vide (sans aucun mot) */
    public LexicalTree() {
        // à compléter
    }

    /** Indique si le noeud courant est situé à l'extrémité d'un mot
     * valide */
    public boolean isWord() { return isWord ; }
    
    
    /** Place le mot spécifié dans l'arbre
     * @return <code>true</code> si le mot a été ajouté,
     * <code>false</code> sinon*/
    public boolean add(String word) {
        // à compléter
        return false ;
    }

    /** Teste si l'arbre lexical contient le mot spécifié.
        @return <code>true</code> si <code>o</code> est un mot
        (String) contenu dans l'arbre, <code>false</code> si
        <code>o</code> n'est pas une instance de String ou si le mot
        n'est pas dans l'arbre lexical. */
    public boolean contains(String word) {
        // à compléter
        return false ;
    }

    /** Ajoute à la liste <code>resultat<code> tous les mots de
     * l'arbre commençant par le préfixe spécifié. 
     * @return <code>true</code> si <code>resultat</code> a été
     * modifié, <code>false</code> sinon.*/
    public boolean wordsBeginningWith(String prefix, List<String> result) {
        // à compléter
        return false ;
    }
    
    /** Crée un arbre lexical qui contient tous les mots du fichier
     * spécifié. */
    public static LexicalTree readWords(String file) {
        // à compléter
        return null ;
    }
}
