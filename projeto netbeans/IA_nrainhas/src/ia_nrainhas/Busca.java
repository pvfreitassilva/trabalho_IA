/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_nrainhas;

/**
 *
 * @author Paulo Vitor
 */
public class Busca {
    
    public void backtracking(Tabuleiro t){
        if(this.bt(t))
            System.out.println("Solucao encontrada.");
        else
            System.out.println("Solucao nao encontrada.");
    }
    
    private Boolean bt(Tabuleiro t){
        int regra;
        Tabuleiro no;
        while(true){
            regra = t.regraAplicavel();
            if(regra>=0){
                no=t.clone();
                no.incluiRainha(no.linhaAtual, regra);
                if(no.cheio()){
                    no.imprimeTabuleiro();
                    return true;
                }
                else{
                    if(bt(no)){
                        return true;
                    }
                }
            }
            else{
                return false;
            }
        }
    }
}
