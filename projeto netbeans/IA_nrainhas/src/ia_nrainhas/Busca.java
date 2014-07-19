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
        LinkedList<Tabuleiro> abertos = new LinkedList<Tabuleiro>();
        t.setAvaliacao(1);
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
                Tabuleiro tAux = abertos.getFirst();
                estadosVisitados++;
                if (tAux.cheio()) {
                    tAux.imprimeTabuleiro();
                    System.out.println("Solução encontrada. Estados expandidos: " + estadosExpandidos + ". Estados visitados: " + estadosVisitados + ".");
                    sucesso = true;
                } else {
                    Tabuleiro u;
                    regra = tAux.regraAplicavel();
                    while (regra != -1) {
                        u = tAux.clone();
                        u.incluiRainha(u.linhaAtual, regra);
                        u.setAvaliacao(1);
                        abertos.add(u);
                        regra = tAux.regraAplicavel();
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

    public void imprimeLista(LinkedList<Tabuleiro> abertos){
        Iterator i = abertos.iterator();
        Tabuleiro t;
        while(i.hasNext()){
            t = (Tabuleiro) i.next();
            t.imprimeTabuleiro();
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
    
    public void gulosa(Tabuleiro t){
        LinkedList<Tabuleiro> abertos = new LinkedList<Tabuleiro>();
        LinkedList<Tabuleiro> fechados = new LinkedList<Tabuleiro>();
        LinkedList<Tabuleiro> proxEstados;
        t.distribuiRainhas();
        t.setAvaliacao(t.getHeuristica());
        boolean sucesso = false;
        boolean fracasso = false;
        int rainhas = t.rainhas, i;
        abertos.add(t);
        estadosExpandidos++;
        Iterator itrAbertos, itrFechados, itrProximos;
        Boolean repetido;
        Tabuleiro teste;
        
        while (!(sucesso || fracasso)) {
            if (abertos.isEmpty()) {
                fracasso = true;
                System.out.println("Solucao não encontrada. Estados expandidos: " + estadosExpandidos + ". Estados visitados: " + estadosVisitados + ".");
            } else {
                Tabuleiro tAux = abertos.getFirst();
                fechados.add(tAux);
                abertos.removeFirst();
                
                //tAux.imprimeTabuleiro();
                //System.out.println(tAux.getAvaliacao());
                
                estadosVisitados++;
                if (tAux.getHeuristica()==0) {
                    tAux.imprimeTabuleiro();
                    System.out.println("Solução encontrada. Estados expandidos: " + estadosExpandidos + ". Estados visitados: " + estadosVisitados + ".");
                    sucesso = true;
                } else {
                    
                    Tabuleiro u;
                    
                    proxEstados = tAux.proximosEstadosGulosa();
                    itrProximos=proxEstados.iterator();
                    while(itrProximos.hasNext()){
                        u = (Tabuleiro) itrProximos.next();
                        itrAbertos=abertos.iterator();
                        itrFechados=fechados.iterator();
                        repetido = false;                        
                        while( (itrAbertos.hasNext() || itrFechados.hasNext()) && !repetido){
                            if(itrAbertos.hasNext()){
                                teste = (Tabuleiro)itrAbertos.next();
                                if(u.igual(teste)){
                                    repetido = true;
                                }
                            }
                            if(itrFechados.hasNext()){
                                teste = (Tabuleiro)itrFechados.next();
                                if(u.igual(teste)){
                                    repetido = true;
                                }
                            }
                        }
                        if(!repetido){
                            abertos.add(u);
                            estadosExpandidos++;
                        }
                    }
                    Collections.sort(abertos);
                }
            }
         }
    }
}
