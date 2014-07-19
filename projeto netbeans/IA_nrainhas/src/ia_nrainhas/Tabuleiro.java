/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_nrainhas;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author Paulo Vitor
 */
public class Tabuleiro implements Comparable<Tabuleiro>{
    int tabuleiro[];
    int rainhas;
    int regra;
    int linhaAtual;
    private int avaliacao;
    private int heuristica;

    public Tabuleiro (int rainhas){
        int i;
        this.rainhas = rainhas;
        tabuleiro = new int[rainhas];
        for(i=0;i<rainhas;i++)
            tabuleiro[i]=-1;
        regra=0;
        linhaAtual=0;
        avaliacao = -1;
        heuristica = -1;
    }
    
    public void distribuiRainhas(){
        int i;
        Random gerador = new Random();
        for(i=0;i<rainhas;i++)
            tabuleiro[i]=gerador.nextInt(rainhas);
        regra=-1;
        linhaAtual=rainhas;
        heuristica = this.heuristica();
    }
    
    public void sorteiaMovimento(){
        Random gerador = new Random();
        int linha, coluna;
        if(linhaAtual!=rainhas){
            System.out.println("O tabuleiro nao foi distribuido. Nao foi possivel sortear um movimento.");
        }
        else{
            linha = gerador.nextInt(rainhas);
            coluna = gerador.nextInt(rainhas);
            tabuleiro[linha]=coluna;
            heuristica = this.heuristica();
        }
    }
    
    public void setAvaliacao(int avaliacao){
        this.avaliacao = avaliacao;
    }
    
    public int getAvaliacao(){
        return avaliacao;
    }
    
    public int getHeuristica(){
        return heuristica;
    }
    
    public int regraAplicavel(){
        int coluna;
        if(linhaAtual==0 && regra<rainhas){
            regra++;
            return regra-1;
        }
        if(regra==rainhas){
            regra = -1;
            return -1;
        }
        
        for(coluna=regra; coluna<rainhas; coluna++)
            if(!existeAtaque(coluna)){
                regra = coluna+1;
                return coluna;
            }
        regra = -1;
        return -1;
    }
    
    private Boolean existeAtaque(int coluna){
        int linha, linhaAux, i;
        for(linha=0;linha<linhaAtual;linha++)
            if(tabuleiro[linha]==coluna)
                return true;
        i=1;
        for(linha=linhaAtual-1;linha>=0;linha--){
            if(tabuleiro[linha]==coluna-i || tabuleiro[linha]==coluna+i)
                return true;
            i++;
        }   
        return false;
    }
    
    public void moveRainha(int linha, int coluna){
        tabuleiro[linha]=coluna;
        heuristica = this.heuristica();
    }
    
    public Tabuleiro clone(){
        Tabuleiro t = new Tabuleiro(rainhas);
        int i;
        for(i=0;i<rainhas;i++)
            t.tabuleiro[i]=this.tabuleiro[i];
        t.linhaAtual=this.linhaAtual;
        t.setAvaliacao(avaliacao);
        t.setHeuristica(heuristica);     
        return t;
    }
    
    public Boolean igual(Tabuleiro t){
        int i;
        if(t==null)
            return false;
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
            linha = linha.concat(" _");
        System.out.println(linha);
        for(i=0;i<rainhas;i++){
            System.out.print("|");
            for(j=0;j<rainhas;j++){
                if(tabuleiro[i]==j)
                    System.out.print("R|");
                else 
                    System.out.print("_|");
            }
            System.out.println("");
        }
    }
    
    private int heuristica() {
        int i, j, ataques = 0, aux = 0;
        for (i = 0; i < rainhas - 1; i++) {
            for (j = i + 1; j < rainhas; j++) {
                if (tabuleiro[i] == tabuleiro[j] || tabuleiro[i] + j - aux == tabuleiro[j] || tabuleiro[i] - j + aux == tabuleiro[j]) {
                    ataques++;
                }
            }
            aux++;
        }
        return ataques;
    }
    
    public void setHeuristica(int h){
        heuristica = h;
    }
    
    public LinkedList<Tabuleiro> proximosEstadosGulosa(){
        int i,j;
        Tabuleiro t;
        LinkedList<Tabuleiro> novosEstados = new LinkedList<Tabuleiro>();
        
        for(i=0;i<rainhas;i++){
            for(j=0;j<rainhas;j++)
                if(tabuleiro[i]!=j){
                    t = this.clone();
                    t.moveRainha(i, j);
                    t.setAvaliacao(t.getHeuristica());
                    novosEstados.add(t);
                }
        }
        return novosEstados;
    }
    
    public LinkedList<Tabuleiro> proximosEstadosA(){
        int i,j;
        Tabuleiro t;
        LinkedList<Tabuleiro> novosEstados = new LinkedList<Tabuleiro>();
        
        for(i=0;i<rainhas;i++){
            for(j=0;j<rainhas;j++)
                if(tabuleiro[i]!=j){
                    t = this.clone();
                    t.moveRainha(i, j);
                    t.setAvaliacao(t.getHeuristica()+1);
                    novosEstados.add(t);
                }
        }
        return novosEstados;
    }
    
     public int compareTo(Tabuleiro t){
        if(this.avaliacao < t.getAvaliacao())
            return -1;
        else if(this.avaliacao > t.getAvaliacao())
            return 1;
        else
            return 0;
    }
}
