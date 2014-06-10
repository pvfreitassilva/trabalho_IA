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
    int tabuleiro[];
    int rainhas;
    int regra;
    int linhaAtual;

    public Tabuleiro (int rainhas){
        int i;
        this.rainhas = rainhas;
        tabuleiro = new int[rainhas];
        for(i=0;i<rainhas;i++)
            tabuleiro[i]=-1;
        regra=0;
        linhaAtual=0;
    }
    
    public Tabuleiro (Tabuleiro no, int regra){
        tabuleiro = no.tabuleiro.clone();
        rainhas = no.rainhas;
        this.regra = 0;
        linhaAtual = no.linhaAtual;       
        tabuleiro[linhaAtual]=regra;
        linhaAtual++;
    }

    public void distribuiRainhas(){
        int i;
        Random gerador = new Random();
        for(i=0;i<rainhas;i++)
            tabuleiro[i]=gerador.nextInt(rainhas);
    }
    
    public int regraAplicavel(){
        int coluna;
        if(linhaAtual==0 && regra<rainhas){
            regra++;
            return regra-1;
        }
        if(regra==rainhas)
            return -1;
        
        for(coluna=regra; coluna<rainhas; coluna++)
            if(!existeAtaque(coluna)){
                regra = coluna+1;
                return coluna;
            }
        return -1;
    }
    
    private Boolean existeAtaque(int coluna){
        int linha1, linhaAux, i;
        for(linha1=0;linha1<linhaAtual;linha1++)
            if(tabuleiro[linha1]==coluna)
                return true;
        i=1;
        for(linhaAux=linhaAtual-1;linhaAux>=0;linhaAux--){
            if(tabuleiro[linhaAux]==coluna-i || tabuleiro[linhaAux]==coluna+i)
                return true;
            i++;
        }   
        return false;
    }
    
    public Tabuleiro clone(){
        Tabuleiro t = new Tabuleiro(rainhas);
        int i;
        for(i=0;i<rainhas;i++)
            t.tabuleiro[i]=this.tabuleiro[i];
        t.linhaAtual=this.linhaAtual;
        //t.regra=this.regra;
        return t;
    }
    
    public Boolean igual(Tabuleiro t){
        int i;
        for(i=0;i<rainhas;i++)
            if(t.tabuleiro[i]!=this.tabuleiro[i])
                return false;
        return true;
    }
    
    public void incluiRainha(int linha, int coluna){
        this.tabuleiro[linha]=coluna;
        this.linhaAtual++;
    }
    
    public void removeRainha(int linha){
        this.tabuleiro[linha]=-1;
        linhaAtual--;
    }
    
    public boolean cheio(){
        if(linhaAtual==rainhas)
            return true;
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
            for(j=0;j<rainhas;j++){
                if(tabuleiro[i]==j)
                    System.out.print("R | ");
                else 
                    System.out.print("  | ");
            }
            System.out.println('\n'+linha);
        }
    }
}
