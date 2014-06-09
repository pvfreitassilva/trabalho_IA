/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_nrainhas;
import java.util.Random;

/**
 *
 * @author Paulo Vitor
 */
public class Tabuleiro {
    int tabuleiro[][];
    int rainhas;
    int regra;
    int linhaAtual;

    public Tabuleiro (int rainhas){
        int i,j;
        this.rainhas = rainhas;
        tabuleiro = new int[rainhas][rainhas];
        for(i=0;i<rainhas;i++)
            for(j=0;j<rainhas;j++)
                tabuleiro[i][j]=0;
        regra=0;
        linhaAtual=0;
    }
    
    public Tabuleiro (Tabuleiro no, int regra){
        tabuleiro = no.tabuleiro.clone();
        rainhas = no.rainhas;
        this.regra = 0;
        linhaAtual = no.linhaAtual;       
        tabuleiro[linhaAtual][regra]=1;
        linhaAtual++;
    }

    public void distribuiRainhas(){
        int i;
        Random gerador = new Random();
        for(i=0;i<rainhas;i++)
            tabuleiro[i][gerador.nextInt(rainhas)]=1;
    }
    
    public int regraAplicavel(){
        int coluna;
        if(regra==rainhas)
            return 0;
        for(coluna=0; coluna<rainhas;coluna++)
            if(!existeAtaque(coluna)){
                regra = coluna;
                return coluna;
            }
        return 0;
    }
    
    private Boolean existeAtaque(int coluna){
        /*int i,j;
        for(i=0;i<rainhas;i++)
            for(j=0;j<coluna;j++)
                if(tabuleiro[i][j]==1)
                    if(i==linhaAtual || j==coluna)
                        return true;
        return false;
        */
        
        return false;
    }
    
    public void imprimeTabuleiro(){
        int i,j;
        String linha = new String();
        for(i=0;i<rainhas;i++)
            linha = linha.concat("----");
        linha = linha.concat("-");
        System.out.println(linha);
        for(i=0;i<rainhas;i++){
            System.out.print("| ");
            for(j=0;j<rainhas;j++)
                System.out.print(tabuleiro[i][j]+" | ");
            System.out.println('\n'+linha);
        }
    }
}
