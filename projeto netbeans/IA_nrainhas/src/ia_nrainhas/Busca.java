/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_nrainhas;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Contem a implementacao dos algoritmos de busca backtracking, largura,
 * profundidade, ordenada, gulosa, A* e IDA* para o problema das N-Rainhas.
 * 
 */
public class Busca {

    private int estadosExpandidos, estadosVisitados;

    public Busca() {
        estadosExpandidos = estadosVisitados = 0;
    }

    /**
     * Efetua uma busca recursiva pelo metodo de backtracking.
     * @param t Tabuleiro vazio que o algoritmo tentara preencher.
     */
    public void backtracking(Tabuleiro t) {
        estadosExpandidos++;
        if (this.bt(t)) {
            System.out.println("Solucao encontrada. Estados expandidos e visitados: " + estadosExpandidos);
        } else {
            System.out.println("Solucao nao encontrada. Estados expandidos e visitados: " + estadosExpandidos);
        }
    }
    
    /**
     * Extensao do algoritmo backtracking.
     * @param t estado do tabuleiro a ser analisado.
     * @return true se a busca obteve sucesso, false se obteve fracasso.
     */
    private Boolean bt(Tabuleiro t) {
        int regra;
        Tabuleiro no;
        while (true) {
            regra = t.regraAplicavel();
            if (regra >= 0) {
                no = t.clone();
                estadosExpandidos++;
                no.incluiRainha(no.getLinhaAtual(), regra);
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
    
    /**
     * Efetua uma busca pelo metodo de busca em largura.
     * @param t Tabuleiro vazio que o algoritmo tentara preencher.
     */
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
                        u.incluiRainha(u.getLinhaAtual(), regra);
                        abertos.addLast(u);
                        regra = n.regraAplicavel();
                        estadosExpandidos++;
                    }
                    abertos.removeFirst();
                }
            }
        }
    }
    
    /**
     * Efetua uma busca pelo metodo de busca em profundidade.
     * @param t Tabuleiro vazio que o algoritmo tentara preencher.
     */
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
                        u.incluiRainha(u.getLinhaAtual(), regra);
                        abertos.add(u);
                        regra = n.regraAplicavel();
                        estadosExpandidos++;
                    }
                }
            }
        }
    }
    
    /**
     * Efetua uma busca pelo metodo de busca ordenada.
     * @param t Tabuleiro vazio que o algoritmo tentara preencher.
     */
    public void ordenada(Tabuleiro t) {
        LinkedList<Tabuleiro> abertos = new LinkedList<Tabuleiro>();
        t.setCusto(0);
        t.setAvaliacao(t.getAvaliacao());
        boolean sucesso = false;
        boolean fracasso = false;
        int regra;
        abertos.add(t);
        estadosExpandidos++;
        Iterator itrMenor;
        Tabuleiro tAux, tAux2;

        while (!(sucesso || fracasso)) {
            if (abertos.isEmpty()) {
                fracasso = true;
                System.out.println("Solucao não encontrada. Estados expandidos: " + estadosExpandidos + ". Estados visitados: " + estadosVisitados + ".");
            } else {
                //Inicio do codigo com ordenacao
                /*
                Collections.sort(abertos);
                tAux = abertos.getFirst();
                abertos.removeFirst();
                */
                //Termino do codigo com ordenacao
                                
                //Inicio do codigo sem ordenacao
                ///*
                itrMenor = abertos.iterator();
                tAux = abertos.getFirst();
                while(itrMenor.hasNext()){
                    tAux2 = (Tabuleiro) itrMenor.next();
                    if(tAux.getAvaliacao()>tAux2.getAvaliacao())
                        tAux=tAux2;
                }
                abertos.removeFirstOccurrence(tAux);
                //*/
                //termino do codigo sem ordenacao
                
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
                        u.incluiRainha(u.getLinhaAtual(), regra);
                        u.setCusto(1);
                        u.setAvaliacao(tAux.getAvaliacao()+u.getCusto());
                        abertos.add(u);
                        regra = tAux.regraAplicavel();
                        estadosExpandidos++;
                    }
                }
            }
        }        
    }
    
    /**
     * Efetua uma busca pelo metodo de busca gulosa.
     * @param t Tabuleiro gerado aleatoriamente. A funcao ira mover as rainhas
     * ate encontrar uma solucao.
     */
    public void gulosa(Tabuleiro t){
        LinkedList<Tabuleiro> abertos = new LinkedList<Tabuleiro>();
        LinkedList<Tabuleiro> fechados = new LinkedList<Tabuleiro>();
        LinkedList<Tabuleiro> proxEstados;
        Iterator itrAbertos, itrFechados, itrProximos, itrMenor;
        Tabuleiro teste;
        Tabuleiro tAux, tAux2;
        boolean sucesso = false;
        boolean fracasso = false;
        boolean repetido;
        t.setAvaliacao(t.getHeuristica());
        abertos.add(t);
        estadosExpandidos++;        
        
        while (!(sucesso || fracasso)) {
            if (abertos.isEmpty()) {
                fracasso = true;
                System.out.println("Solucao não encontrada. Estados expandidos: " + estadosExpandidos + ". Estados visitados: " + estadosVisitados + ".");
            } else {
                //Inicio do codigo com ordenacao
                //Collections.sort(abertos);
                //tAux = abertos.getFirst();
                //fechados.add(tAux);
                //abertos.removeFirst();
                //Termino do codigo com ordenacao
                
                //Inicio do codigo sem ordenacao
                itrMenor = abertos.iterator();
                tAux = abertos.getFirst();
                while(itrMenor.hasNext()){
                    tAux2 = (Tabuleiro) itrMenor.next();
                    if(tAux.getAvaliacao()>tAux2.getAvaliacao())
                        tAux=tAux2;
                }
                fechados.add(tAux);
                abertos.removeFirstOccurrence(tAux);
                //Termino do codigo sem ordenacao
                
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
                }
            }
         }
    }
    
    /**
     * Efetua uma busca pelo metodo de busca A*.
     * @param t Tabuleiro gerado aleatoriamente. A funcao ira mover as rainhas
     * ate encontrar uma solucao.
     */
    public void a_estrela(Tabuleiro t){
        LinkedList<Tabuleiro> abertos = new LinkedList<Tabuleiro>();
        LinkedList<Tabuleiro> fechados = new LinkedList<Tabuleiro>();
        LinkedList<Tabuleiro> proxEstados;
        Iterator itrAbertos, itrFechados, itrProximos, itrMenor;
        boolean sucesso = false;
        boolean fracasso = false;
        t.setCusto(0);
        t.setAvaliacao(t.getHeuristica()+t.getCusto());
        abertos.add(t);
        estadosExpandidos++;
        
        Boolean repetido;
        Tabuleiro teste, tAux, tAux2;
        
        while (!(sucesso || fracasso)) {
            if (abertos.isEmpty()) {
                fracasso = true;
                System.out.println("Solucao não encontrada. Estados expandidos: " + estadosExpandidos + ". Estados visitados: " + estadosVisitados + ".");
            } else {
                //Inicio do codigo com ordenacao
                //Collections.sort(abertos);
                //tAux = abertos.getFirst();
                //fechados.add(tAux);
                //abertos.removeFirst();
                //Termino do codigo com ordenacao
                
                //Inicio do codigo sem ordenacao
                itrMenor = abertos.iterator();
                tAux = abertos.getFirst();
                while(itrMenor.hasNext()){
                    tAux2 = (Tabuleiro) itrMenor.next();
                    if(tAux.getAvaliacao()>tAux2.getAvaliacao())
                        tAux=tAux2;
                }
                fechados.add(tAux);
                abertos.removeFirstOccurrence(tAux);
                //Termino do codigo sem ordenacao
                
                estadosVisitados++;
                if (tAux.getHeuristica()==0) {
                    tAux.imprimeTabuleiro();
                    System.out.println("Solução encontrada. Estados expandidos: " + estadosExpandidos + ". Estados visitados: " + estadosVisitados + ".");
                    sucesso = true;
                } else {
                    
                    Tabuleiro u;
                    
                    proxEstados = tAux.proximosEstadosA();
                    itrProximos=proxEstados.iterator();
                    while(itrProximos.hasNext()){
                        u = (Tabuleiro) itrProximos.next();
                        itrAbertos=abertos.iterator();
                        itrFechados=fechados.iterator();
                        repetido = false;                        
                        while( (itrAbertos.hasNext() || itrFechados.hasNext()) && !repetido){
                            if(itrAbertos.hasNext()){
                                teste = (Tabuleiro) itrAbertos.next();
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
                }
            }
         }
    }
    
    /**
     * Efetua uma busca pelo metodo de busca IDA*.
     * @param t Tabuleiro gerado aleatoriamente. A funcao ira mover as rainhas
     * ate encontrar uma solucao.
     */
    public void ida_estrela(Tabuleiro t){
        LinkedList<Tabuleiro> abertos = new LinkedList<Tabuleiro>();
        LinkedList<Tabuleiro> fechados = new LinkedList<Tabuleiro>();
        LinkedList<Tabuleiro> proxEstados;
        LinkedList<Tabuleiro> descartados = new LinkedList<Tabuleiro>();
        t.setCusto(0);
        t.setAvaliacao(t.getHeuristica()+t.getCusto());
        boolean sucesso = false;
        boolean fracasso = false;
        abertos.add(t);
        estadosExpandidos++;
        Iterator itrAbertos, itrFechados, itrProximos, itrMenor;
        Boolean repetido;
        Tabuleiro teste, tAux, tAux2;
        int nDescartados = 0, iterNovas = 1;
        
        int patamar = t.getAvaliacao(), patamar_old=-1;
        
        while (!(sucesso || fracasso)) {
            if (abertos.isEmpty()) {
                Collections.sort(descartados);
                patamar_old=patamar;
                patamar = descartados.getFirst().getAvaliacao();
                if(patamar_old == patamar){
                    fracasso = true;
                    System.out.println("Solucao nao encontrada. Estados expandidos: " + estadosExpandidos + ". Estados visitados: " + estadosVisitados + ".");
                    System.out.println("Total de estados descartados: "+nDescartados+". Total de redefinicoes do patamar: "+iterNovas);
                    break;
                }
                iterNovas++;
                descartados = new LinkedList<Tabuleiro>();
                abertos = new LinkedList<Tabuleiro>();
                fechados = new LinkedList<Tabuleiro>();
                abertos.add(t);
            }
            else {
                //Inicio do codigo com ordenacao
                Collections.sort(abertos);
                tAux = abertos.getFirst();
                fechados.add(tAux);
                abertos.removeFirst();
                //Termino do codigo com ordenacao
                
                //Inicio do codigo sem ordenacao
                /*
                itrMenor = abertos.iterator();
                tAux = abertos.getFirst();
                while(itrMenor.hasNext()){
                    tAux2 = (Tabuleiro) itrMenor.next();
                    if(tAux.getAvaliacao()>tAux2.getAvaliacao())
                        tAux=tAux2;
                }
                fechados.add(tAux);
                abertos.removeFirstOccurrence(tAux);
                * */
                //Termino do codigo sem ordenacao
                
                estadosVisitados++;
                if (tAux.getHeuristica()==0) {
                    tAux.imprimeTabuleiro();
                    System.out.println("Solução encontrada. Estados expandidos: " + estadosExpandidos + ". Estados visitados: " + estadosVisitados + ".");
                    System.out.println("Total de estados descartados: "+nDescartados+". Total de redefinicoes do patamar: "+iterNovas);
                    sucesso = true;
                } else {
                    
                    Tabuleiro u;
                    
                    proxEstados = tAux.proximosEstadosA();
                    itrProximos=proxEstados.iterator();
                    while(itrProximos.hasNext()){
                        u = (Tabuleiro) itrProximos.next();
                        if(u.getAvaliacao()<=patamar){
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
                        else{
                            descartados.add(u);
                            nDescartados++;
                        }
                    }
                }
            }
         }
    }
}
