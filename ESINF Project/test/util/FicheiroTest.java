/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class FicheiroTest {

    public FicheiroTest() {
    }

    /**
     * Test of lerFicheiro method, of class Ficheiro.
     */
    @Test
    public void testLerFicheiroTest() {
        System.out.println("lerFicheiro");
        String nomeFicheiro = "fx_teste.txt";

        //Inicializar lista Teste
        List<String> listaTeste = new ArrayList<>();
        String a = "a,b,c";
        String b = "a,1,2";
        String c = "b,2,3";
        String d = "c,3,4";
        listaTeste.add(a);
        listaTeste.add(b);
        listaTeste.add(c);
        listaTeste.add(d);
        //Fim inicializar lista Teste

        Ficheiro f = new Ficheiro();
        List<String> listaLer = f.lerFicheiro(nomeFicheiro);

        Ficheiro instance = new Ficheiro();
        List<String> expResult = listaTeste;
        List<String> result = instance.lerFicheiro(nomeFicheiro);
        assertEquals(expResult, result);

    }

}
