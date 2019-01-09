/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class SenhaTest {

    public SenhaTest() {
    }

    /**
     * Test of getNif method, of class Senha.
     */
    @Test
    public void testGetNif() {
        System.out.println("getNif");
        //Criar uma Senha
        long nif = 123456789;
        int numOrdem = 1;
        char cod = 'A';
        Senha s = new Senha(nif, cod, numOrdem);
        //Fim criar uma Senha
        assertEquals(nif, s.getNif());
    }

    /**
     * Test of getCodServico method, of class Senha.
     */
    @Test
    public void testGetCodServico() {
        System.out.println("getCodServico");

        //Criar uma Senha
        long nif = 123456789;
        int numOrdem = 1;
        char cod = 'A';
        Senha s = new Senha(nif, cod, numOrdem);
        //Fim criar uma Senha

        assertEquals(cod, s.getCodServico());
    }

    /**
     * Test of getNumOrdem method, of class Senha.
     */
    @Test
    public void testGetNumOrdem() {
        System.out.println("getNumOrdem");

        //Criar uma Senha
        long nif = 123456789;
        int numOrdem = 1;
        char cod = 'A';
        Senha s = new Senha(nif, cod, numOrdem);
        //Fim criar uma Senha

        assertEquals(numOrdem, s.getNumOrdem());
    }

    /**
     * Test of toString method, of class Senha.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        long nif = 123456789;
        int numOrdem = 1;
        char cod = 'A';
        Senha instance = new Senha(nif, cod, numOrdem);
        String expResult = "Senha{nif=123456789, cod_servico=A, num_ordem=1}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}
