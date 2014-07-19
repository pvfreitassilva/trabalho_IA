/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_nrainhas;

/**
 *
 * @author Paulo Vitor
 */
public class NoTabuleiro implements Comparable<NoTabuleiro>{
    
    private Tabuleiro no;
    private int avaliacao;
    
    public NoTabuleiro(Tabuleiro t, int avaliacao){
        no = t;
        this.avaliacao = avaliacao;
    }
    
    public int getAvaliacao(){
        return avaliacao;
    }
    
    public Tabuleiro getTabuleiro(){
        return no;
    }
    
    public int compareTo(NoTabuleiro nt){
        if(this.avaliacao > nt.getAvaliacao())
            return -1;
        else
            return 1;
    }
    
}
