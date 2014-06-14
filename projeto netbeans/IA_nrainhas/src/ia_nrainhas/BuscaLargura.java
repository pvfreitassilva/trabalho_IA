/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_nrainhas;

import java.util.LinkedList;

/**
 *
 * @author Amanda Ferreira de Castro
 */
public class BuscaLargura extends Busca {

    private LinkedList<Tabuleiro> abertos;
    private LinkedList<Tabuleiro> fechados;

    public BuscaLargura() {
        super();
        abertos = new LinkedList<Tabuleiro>();
        fechados = new LinkedList<Tabuleiro>();
    }
}
