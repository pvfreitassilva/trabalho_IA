/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_nrainhas;

import static java.util.Collections.list;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Amanda Ferreira de Castro
 */
public class BuscaLargura extends Busca {

    private LinkedList<Tabuleiro> abertos;
    //private LinkedList<Tabuleiro> fechados;

    public BuscaLargura() {
        super();
        abertos = new LinkedList<Tabuleiro>();
        //fechados = new LinkedList<Tabuleiro>();
    }

    private void imprimeLista(LinkedList l) {
        Iterator x = l.listIterator();
        while (x.hasNext()) {
            ((Tabuleiro) x.next()).imprimeTabuleiro();
        }
    }

    public void executaBuscaLargura(Tabuleiro t) {
        boolean sucesso = false;
        boolean fracasso = false;
        int regra;
        //Tabuleiro aux = t.clone();
        abertos.add(t);

        while (!(sucesso || fracasso)) {
            if (abertos.isEmpty()) {
                fracasso = true;
                System.out.println("Solucao nao encontrada.");
            } else {
                Tabuleiro n = abertos.getFirst().clone();
                //System.out.println("primeiro de abertos: ");
                //n.imprimeTabuleiro();
                if (n.cheio()) {
                    System.out.println("Solução encontrada!");
                    n.imprimeTabuleiro();
                    sucesso = true;
                } else {
                    Tabuleiro u;
                    regra = n.regraAplicavel();
                    while (regra != -1) {
                        u = n.clone();
                        u.incluiRainha(u.linhaAtual, regra);
                        abertos.addLast(u);
                        regra = n.regraAplicavel();
                    }
                    //fechados.addLast(n);
                    abertos.removeFirst();
                    
                    //System.out.println("abertos depois do while: " + abertos.size());
                    //imprimeLista(abertos);
                }
            }
        }
    }
}
