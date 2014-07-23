/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_nrainhas;
import java.util.LinkedList;
import java.util.Random;

/**
 * Mantem a estrutura de um tabuleiro NxN.
 * @author Paulo Vitor
 */
public class Tabuleiro implements Comparable<Tabuleiro>{
    private int tabuleiro[];
    private int rainhas;
    private int regra;
    private int linhaAtual;
    private int avaliacao;
    private int heuristica;
    private int custo;

    /**
     * Construtor da classe.
     * @param rainhas numero de rainhas do tabuleiro.
     */
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
        custo=0;
    }
    
    public int getLinhaAtual(){
        return linhaAtual;
    }
    
    /**
     * Cria um tabuleiro aleatorio e calcula sua heuristica.
     */
    public void distribuiRainhas(){
        int i;
        Random gerador = new Random();
        for(i=0;i<rainhas;i++)
            tabuleiro[i]=gerador.nextInt(rainhas);
        regra=-1;
        linhaAtual=rainhas;
        heuristica = this.heuristica();
    }
    
    /**
     * Atribui o valor da funcao de avaliacao ao tabuleiro.
     * @param avaliacao valor da funcao de avaliacao.
     */
    public void setAvaliacao(int avaliacao){
        this.avaliacao = avaliacao;
    }
    
    /**
     * 
     * @return Retorna o valor da funcao de avaliacao do tabuleiro.
     */
    public int getAvaliacao(){
        return avaliacao;
    }
    
    /**
     * 
     * @return Retorna o valor da funcai heuristica do tabuleiro.
     */
    public int getHeuristica(){
        return heuristica;
    }
    
    public void setCusto(int custo){
        this.custo = custo;
    }
    
    public int getCusto(){
        return custo;
    }
    
    /**
     * Calcula a proxima regra aplicavel no tabuleiro. A regra e a coluna
     * cuja rainha pode ser disposta sem receber ataques na linha atual.
     * @return Retorna a proxima regra aplicavel do tabuleiro.
     */
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
    
    /**
     * Verifica que se existe ataque de outra rainha na coluna da linha atual.
     * @param coluna coluna da linha atual a ser avaliada.
     * @return true se existir ataque, false caso contrario
     */
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
    
    /**
     * Move uma rainha do tabuleiro.
     * @param linha linha da rainha a ser movida.
     * @param coluna coluna para onde a rainha deve ser movida.
     */
    private void moveRainha(int linha, int coluna){
        tabuleiro[linha]=coluna;
        heuristica = this.heuristica();
        custo=1;
    }
    
    /**
     * Faz um clone do tabuleiro;
     * @return clone do tabuleiro
     */
    public Tabuleiro clone(){
        Tabuleiro t = new Tabuleiro(rainhas);
        int i;
        for(i=0;i<rainhas;i++)
            t.tabuleiro[i]=this.tabuleiro[i];
        t.linhaAtual=this.linhaAtual;
        t.setAvaliacao(avaliacao);
        t.setHeuristica(heuristica);    
        t.setCusto(custo);
        return t;
    }
    
    /**
     * Verifica se as rainhas do tabuleiro estao igualmente dispostas
     * @param t tabuleiro a ser comparado
     * @return true se os tabuleiros forem iguais, false caso contrario.
     */
    public Boolean igual(Tabuleiro t){
        int i;
        if(t==null)
            return false;
        for(i=0;i<rainhas;i++)
            if(t.tabuleiro[i]!=this.tabuleiro[i])
                return false;
        return true;
    }
    
    /**
     * Insere uma rainha na linha e coluna desejadas e ajusta a indicacao da proxima
     * linha vazia.
     * @param linha linha onde a rainha deve ser inserida.
     * @param coluna coluna onde a rainha deve ser inserida.
     */
    public void incluiRainha(int linha, int coluna){
        this.tabuleiro[linha]=coluna;
        this.linhaAtual++;
    }
    
    /**
     * Verifica se o tabuleiro esta cheio.
     * @return true se o tabuleiro estiver cheio, false caso contrario.
     */
    public boolean cheio(){
        if(linhaAtual==rainhas)
            return true;
        return false;
    }
    
    /**
     * Imprime uma representacao do tabuleiro
     */
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
    
    /**
     * Calcula a heuristica do tabuleiro. A heuristica e dada pela quantidade de ataques
     * no tabuleiro.
     * @return valor da heuristica.
     */
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
    
    /**
     * Ajuda o valor da heuristica
     * @param h heuristica a ser atribuida ao tabuleiro.
     */
    private void setHeuristica(int h){
        heuristica = h;
    }
    
    /**
     * Calcula os proximo estados possiveis para a busca gulosa. Cada estado e igual
     * ao pai com uma rainha deslocada.
     * @return todos os proximo estados possives para a busca gulosa.
     */    
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
    
    /**
     * Calcula os proximo estados possiveis para as buscas A*  e IDA*. Cada estado e igual
     * ao pai com uma rainha deslocada.
     * @return todos os proximo estados possives para a busca gulosa.
     */ 
    public LinkedList<Tabuleiro> proximosEstadosA(){
        int i,j;
        Tabuleiro t;
        LinkedList<Tabuleiro> novosEstados = new LinkedList<Tabuleiro>();
        
        for(i=0;i<rainhas;i++){
            for(j=0;j<rainhas;j++)
                if(tabuleiro[i]!=j){
                    t = this.clone();
                    t.moveRainha(i, j);
                    t.setAvaliacao(t.getHeuristica()+t.getCusto());
                    novosEstados.add(t);
                }
        }
        return novosEstados;
    }
    
    /**
     * Funcao para uso da ordenacao de listas do Java. Compara a avaliacao entre
     * os tabuleiros.
     * @param t tabuleiro a ser comparado
     * @return -1 se a avaliacao do tabuleiro passado por parametro for maior,
     * 0 se for igual, e 1 se for menor.
     */
    public int compareTo(Tabuleiro t){
        if(this.avaliacao < t.getAvaliacao())
            return -1;
        else if(this.avaliacao > t.getAvaliacao())
            return 1;
        else
            return 0;
    }
}
