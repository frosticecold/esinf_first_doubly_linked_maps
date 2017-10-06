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
public class CidadaoIT {

    Cidadao c1, c2;

    public CidadaoIT() {
        //Cidadao c1
        long nif = 123456789;
        String nome = "José";
        String email = "emaila@mail.pt";
        String codigopostal = "4480-667";
        int numreparticao = 12345;
        c1 = new Cidadao(nif, nome, email, codigopostal, numreparticao);

        //Cidadao c2
        long nif2 = 987654321;
        String nome2 = "Ana";
        String email2 = "emailb@mail.pt";
        String codigopostal2 = "2260-123";
        int numreparticao2 = 12346;
        c2 = new Cidadao(nif2, nome2, email2, codigopostal2, numreparticao2);

    }

    /**
     * Test of getNif method, of class Cidadao.
     */
    @Test
    public void testGetNif() {
        System.out.println("getNif");

        //Teste 1
        long expResult = 123456789;
        long result = c1.getNif();

        assertTrue("Os nifs são iguais", expResult == result);

        assertFalse(("Os nifs não são iguais"), c1.getNif() == c2.getNif());
    }

    /**
     * Test of getNome method, of class Cidadao.
     */
    @Test
    public void testGetNome() {
        System.out.println("getNome");
        String expResult = "José";
        String result = c1.getNome();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEmail method, of class Cidadao.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        String expResult = "emaila@mail.pt";
        String result = c1.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCodigoPostal method, of class Cidadao.
     */
    @Test
    public void testGetCodigoPostal() {
        System.out.println("getCodigoPostal");
        String expResult = "4480-667";;
        String result = c1.getCodigoPostal();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumReparticao method, of class Cidadao.
     */
    @Test
    public void testGetNumReparticao() {
        System.out.println("getNumReparticao");
        int expResult = 12345;
        int result = c1.getNumReparticao();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNif method, of class Cidadao.
     */
    @Test
    public void testSetNif() {
        System.out.println("setNif");
        long nif = 987654321;
        c2.setNif(nif);
        assertEquals(nif, c2.getNif());
    }

    /**
     * Test of setNome method, of class Cidadao.
     */
    @Test
    public void testSetNome() {
        System.out.println("setNome");
        String nome = "Andreia";
        Cidadao instance = c1;
        instance.setNome(nome);

        assertEquals(nome, c1.getNome());
    }

    /**
     * Test of setEmail method, of class Cidadao.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "emailc@mail.pt";
        Cidadao instance = c1;
        instance.setEmail(email);

        assertEquals(email, c1.getEmail());
    }

    /**
     * Test of setCodigoPostal method, of class Cidadao.
     */
    @Test
    public void testSetCodigoPostal() {
        System.out.println("setCodigoPostal");
        String codigoPostal = "1234-567";
        Cidadao instance = c1;
        instance.setCodigoPostal(codigoPostal);

        assertEquals(codigoPostal, c1.getCodigoPostal());
    }

    /**
     * Test of setNumReparticao method, of class Cidadao.
     */
    @Test
    public void testSetNumReparticao() {
        System.out.println("setNumReparticao");
        int numReparticao = 9999;
        Cidadao instance = c1;
        instance.setNumReparticao(numReparticao);

        assertEquals(numReparticao, c1.getNumReparticao());
    }

}
