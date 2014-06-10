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
        Tabuleiro inicial = t.clone();
        if(this.bt(t))
            System.out.println("Solucao encontrada.");
        else
            System.out.println("Solucao nao encontrada.");
    }
    
    private Boolean bt(Tabuleiro t){
        int regra;
        Tabuleiro no;
        //t.imprimeTabuleiro();
        while(true){
            regra = t.regraAplicavel();
            //System.out.println(regra);
            if(regra>=0){
                //System.out.println("Linha: "+t.linhaAtual+"\t Existe regra: "+regra);
                no=t.clone();
                no.incluiRainha(no.linhaAtual, regra);
                if(no.cheio()){
                    //System.out.println("Solucao encontrada (2)");
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
                //if(t.igual(inicial)){
                    //System.out.println("Solucao nao encontrada (2)");
                //    return false;
                //}
                return false;
            }
        }
    }
}
