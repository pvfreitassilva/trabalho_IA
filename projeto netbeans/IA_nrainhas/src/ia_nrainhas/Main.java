/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_nrainhas;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Paulo Vitor
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int rainhas;
        int opcao;
        int continua;
        long tempoInicial, tempoTotal;
        Scanner entrada = new Scanner(System.in);
        SimpleDateFormat s = new SimpleDateFormat("mm:ss:ms");
        Tabuleiro tabuleiro;
        Busca b;
        
        System.out.println("#########################################");
        System.out.println("#  Trabalho de Inteligencia Artificial  #");
        System.out.println("#          UFJF - DCC - 2014-1          #");
        System.out.println("#                Alunos:                #");
        System.out.println("#       Amanda Ferreira de Castro       #");
        System.out.println("#     Paulo Vitor Freitas da Silva      #");
        System.out.println("#########################################");

        do{
            System.out.println("\nInforme o numero de rainhas (minimo 4):");
            do{
                rainhas = entrada.nextInt();
                if(rainhas<4)
                    System.out.println("O numero de rainhas nao pode ser menor que 4. Digite novamente:");
            }while(rainhas<4);

            
            
            Tabuleiro aux = new Tabuleiro(rainhas);
            aux.distribuiRainhas();
            aux.imprimeTabuleiro();
            System.out.println("N ataques:"+aux.heuristica());
            
            
            System.out.println("Informe o algoritmo de busca desejado:");
            System.out.println("1. Backtracking");
            System.out.println("2. Busca em largura");
            System.out.println("3. Busca em profundidade");
            System.out.println("4. Busca ordenada");
            System.out.println("5. Busca gulosa");
            System.out.println("6. Busca A*");
            System.out.println("7. Busca IDA*");
            do{
                opcao = entrada.nextInt();
                if(opcao<1 || opcao>7)
                    System.out.println("Opcao invalida. Digite novamente:");			
            }
            while(opcao<1 || opcao>7);
 
            tabuleiro = new Tabuleiro(rainhas);
            b = new Busca();
            tempoInicial=System.currentTimeMillis();
            
            switch(opcao){
                case 1: {
                    System.out.println("Buscando usando backtracking....");
                    b.backtracking(tabuleiro);
                    break;
                }
                case 2: {
                    System.out.println("Buscando usando busca em largura...");
                    b.largura(tabuleiro);
                    break;
                }
                case 3: {
                    System.out.println("Buscando usando busca em profundidade...");                    
                    b.profundidade(tabuleiro);
                    break;
                }
                case 4: {
                    System.out.println("Buscando usando busca ordenada...");
                    //b.ordenada(tabuleiro);
                    break;
                }
                case 5: {System.out.println("Buscando usando busca gulosa...");
                    //b.gulosa(tabuleiro);
                    break;
                }
                case 6: {System.out.println("Buscando usando busca A*...");
                    //b.a_estrela(tabuleiro);
                    break;
                }
                case 7: {System.out.println("Buscando usando busca IDA*...");
                    //b.ida_estrela(tabuleiro);
                    break;
                }
                default: break;
            }
            
            tempoTotal=System.currentTimeMillis()-tempoInicial;
            System.out.println("Tempo total de execucao da busca: "+s.format(new Date(tempoTotal)));

            System.out.println("Deseja realizar uma nova busca?");
            System.out.println("1. Sim");
            System.out.println("2. Nao");
            do{
                continua = entrada.nextInt();
                if(continua!=1 && continua!=2)
                    System.out.println("Opcao invalida. Digite novamente:");
            }while(continua!=1 && continua!=2);
        }while(continua!=2);
    }
}
