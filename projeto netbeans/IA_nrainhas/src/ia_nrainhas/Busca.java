/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_nrainhas;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Paulo Vitor
 */
public class Busca {

    private int estadosExpandidos, estadosVisitados;

    public Busca() {
        estadosExpandidos = estadosVisitados = 0;
    }

    public void backtracking(Tabuleiro t) {
        estadosExpandidos++;
        if (this.bt(t)) {
            System.out.println("Solucao encontrada. Estados expandidos e visitados: " + estadosExpandidos);
        } else {
            System.out.println("Solucao nao encontrada. Estados expandidos e visitados: " + estadosExpandidos);
        }
    }

    private Boolean bt(Tabuleiro t) {
        int regra;
        Tabuleiro no;
        while (true) {
            regra = t.regraAplicavel();
            if (regra >= 0) {
                no = t.clone();
                estadosExpandidos++;
                no.incluiRainha(no.linhaAtual, regra);
                if (no.cheio()) {
                    no.imprimeTabuleiro();
                    return true;
                } else {
                    if (bt(no)) {
                        return true;
                    }
                }
            } else {
                return false;
            }
        }
    }

    public void ordenada(Tabuleiro t) {
        LinkedList<NoTabuleiro> abertos = new LinkedList<NoTabuleiro>();
        NoTabuleiro nt = new NoTabuleiro(t,1);
        boolean sucesso = false;
        boolean fracasso = false;
        int regra;
        abertos.add(nt);
        estadosExpandidos++;

        while (!(sucesso || fracasso)) {
            if (abertos.isEmpty()) {
                fracasso = true;
                System.out.println("Solucao não encontrada. Estados expandidos: " + estadosExpandidos + ". Estados visitados: " + estadosVisitados + ".");
            } else {
                NoTabuleiro ntAux = abertos.getFirst();
                estadosVisitados++;
                if (ntAux.getTabuleiro().cheio()) {
                    ntAux.getTabuleiro().imprimeTabuleiro();
                    System.out.println("Solução encontrada. Estados expandidos: " + estadosExpandidos + ". Estados visitados: " + estadosVisitados + ".");
                    sucesso = true;
                } else {
                    Tabuleiro u;
                    regra = ntAux.getTabuleiro().regraAplicavel();
                    NoTabuleiro ntAux2;
                    while (regra != -1) {
                        u = ntAux.getTabuleiro().clone();
                        u.incluiRainha(u.linhaAtual, regra);
                        ntAux2 = new NoTabuleiro (u,1);
                        abertos.add(ntAux2);
                        regra = ntAux.getTabuleiro().regraAplicavel();
                        estadosExpandidos++;
                    }
                    abertos.removeFirst();
                    Collections.sort(abertos);
                    //imprimeLista(abertos);
                    //Thread.sleep(100000000);
                }
            }
        }        
    }

    public void imprimeLista(LinkedList<NoTabuleiro> abertos){
        Iterator i = abertos.iterator();
        NoTabuleiro nt;
        while(i.hasNext()){
            nt = (NoTabuleiro) i.next();
            nt.getTabuleiro().imprimeTabuleiro();
        }
    }
    
    public void largura(Tabuleiro t) {
        LinkedList<Tabuleiro> abertos = new LinkedList<Tabuleiro>();
        boolean sucesso = false;
        boolean fracasso = false;
        int regra;
        abertos.add(t);
        estadosExpandidos++;

        while (!(sucesso || fracasso)) {
            if (abertos.isEmpty()) {
                fracasso = true;
                System.out.println("Solucao não encontrada. Estados expandidos: " + estadosExpandidos + ". Estados visitados: " + estadosVisitados + ".");
            } else {
                Tabuleiro n = abertos.getFirst();
                estadosVisitados++;
                if (n.cheio()) {
                    n.imprimeTabuleiro();
                    System.out.println("Solução encontrada. Estados expandidos: " + estadosExpandidos + ". Estados visitados: " + estadosVisitados + ".");
                    sucesso = true;
                } else {
                    Tabuleiro u;
                    regra = n.regraAplicavel();
                    while (regra != -1) {
                        u = n.clone();
                        u.incluiRainha(u.linhaAtual, regra);
                        abertos.addLast(u);
                        regra = n.regraAplicavel();
                        estadosExpandidos++;
                    }
                    abertos.removeFirst();
                }
            }
        }
    }

    public void profundidade(Tabuleiro t) {
        LinkedList<Tabuleiro> abertos = new LinkedList<Tabuleiro>();
        boolean sucesso = false;
        boolean fracasso = false;
        int regra;
        abertos.add(t);
        estadosExpandidos++;

        while (!(sucesso || fracasso)) {
            if (abertos.isEmpty()) {
                fracasso = true;
                System.out.println("Solucao não encontrada. Estados expandidos: " + estadosExpandidos + ". Estados visitados: " + estadosVisitados + ".");
            } else {
                Tabuleiro n = abertos.getLast();
                estadosVisitados++;
                if (n.cheio()) {
                    n.imprimeTabuleiro();
                    System.out.println("Solução encontrada. Estados expandidos: " + estadosExpandidos + ". Estados visitados: " + estadosVisitados + ".");
                    sucesso = true;
                } else {
                    Tabuleiro u;
                    regra = n.regraAplicavel();
                    abertos.removeLast();  
                    while (regra != -1) {
                        u = n.clone();
                        u.incluiRainha(u.linhaAtual, regra);
                        abertos.add(u);
                        regra = n.regraAplicavel();
                        estadosExpandidos++;
                    }
                }
            }
        }
    }
}
