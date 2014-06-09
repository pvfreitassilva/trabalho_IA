/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_nrainhas;
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
        Scanner entrada = new Scanner(System.in);

        System.out.println("Informe o numero de rainhas (minimo 4):");
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
        b.backtracking(tabuleiro);
        
        //tabuleiro.distribuiRainhas();
        //tabuleiro.imprimeTabuleiro();
    }
}
