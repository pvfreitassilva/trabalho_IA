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
 
            Tabuleiro tabuleiro = new Tabuleiro(rainhas);
            Busca b = new Busca();
            
            SimpleDateFormat s = new SimpleDateFormat("mm:ss:ms");
            
            switch(opcao){
                case 1: {
                    System.out.println("Buscando usando backtracking...");
                    tempoInicial=System.currentTimeMillis();
                    b.backtracking(tabuleiro);
                    tempoTotal=System.currentTimeMillis()-tempoInicial;
                    
                    System.out.println("Tempo total de execucao da busca: "+s.format(new Date(tempoTotal))+"'"+(tempoTotal%1000));
                    //System.out.println("Tempo em milissegundos: "+tempoTotal+"ms");
                    break;
                }
                case 2: {
                    BuscaLargura bl = new BuscaLargura();
                    System.out.println("Buscando usando largura...");
                    tempoInicial=System.currentTimeMillis();
                    
                    bl.executaBuscaLargura(tabuleiro);
                    
                    tempoTotal=System.currentTimeMillis()-tempoInicial;
                    System.out.println("Tempo total de execucao da busca: "+s.format(new Date(tempoTotal))+"'"+(tempoTotal%1000));
                    break;
                }
                case 3: {break;}
                case 4: {break;}
                case 5: {break;}
                case 6: {break;}
                case 7: {break;}
                default: break;
            }

            System.out.println("Deseja realizar uma nova busca?");
            System.out.println("1. Sim");
            System.out.println("2. Nao");
            do{
                continua = entrada.nextInt();
                if(continua!=1 && continua!=2)
                    System.out.println("Opcao invalida. Digite novamente:");
            }while(continua!=1 && continua!=2);

            //tabuleiro.distribuiRainhas();
            //tabuleiro.imprimeTabuleiro();
        }while(continua!=2);
    }
}
