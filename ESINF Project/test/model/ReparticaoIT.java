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
public class ReparticaoIT {

    private Reparticao r1;
    private static String cidadeOmissao = "Porto";
    private static int numReparticao = 1234;
    private static String codigoPostal = "4480";

    public ReparticaoIT() {
        r1 = new Reparticao(cidadeOmissao, numReparticao, codigoPostal);
    }

    /**
     * Test of getCidade method, of class Reparticao.
     */
    @Test
    public void testGetCidade() {
        System.out.println("getCidade");
        Reparticao instance = r1;
        String expResult = cidadeOmissao;
        String result = instance.getCidade();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumReparticao method, of class Reparticao.
     */
    @Test
    public void testGetNumReparticao() {
        System.out.println("getNumReparticao");
        Reparticao instance = r1;
        int expResult = numReparticao;
        int result = instance.getNumReparticao();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCodigoPostal method, of class Reparticao.
     */
    @Test
    public void testGetCodigoPostal() {
        System.out.println("getCodigoPostal");
        Reparticao instance = r1;
        String expResult = codigoPostal;
        String result = instance.getCodigoPostal();
        assertEquals(expResult, result);
    }

    /**
     * Test of adicionarServiço method, of class Reparticao.
     */
    @Test
    public void testAdicionarServiço() {
        System.out.println("adicionarServiço");

        Reparticao instance = new Reparticao(cidadeOmissao, numReparticao, codigoPostal);
        boolean expResult = true;
        boolean result = instance.adicionarServiço('A');
        assertEquals(expResult, result);

        expResult = false;
        result = instance.adicionarServiço('A');
        assertEquals(expResult, result);
    }

}
