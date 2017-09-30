/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import estruturas.DoublyLinkedList;
import java.io.IOException;
import java.util.HashSet;
import java.util.ListIterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class FicheiroIT {

    private final static String nomeFicheiro = "fx_rep_test.txt";

    public FicheiroIT() {
    }

    /**
     * Test of lerFicheiroReparticao method, of class Ficheiro.
     */
    @Test
    public void testLerFicheiroReparticao() throws IOException {

        System.out.println("lerFicheiroReparticao");
        //Lista ligada a ler
        DoublyLinkedList<Reparticao> listaReparticoes = new DoublyLinkedList<>();
        Ficheiro.lerFicheiroReparticao(nomeFicheiro, listaReparticoes);

        DoublyLinkedList<Reparticao> outra = new DoublyLinkedList<>();
        ListIterator<Reparticao> it = listaReparticoes.listIterator();
        while (it.hasNext()) {
            outra.addLast(it.next());
        }
        assertTrue("Supposed DoublyLinkedLists are equal", listaReparticoes.equals(outra));

    }

}
