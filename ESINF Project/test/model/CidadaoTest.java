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
 * @author Raúl Correia
 */
public class CidadaoTest {

    public CidadaoTest() {
    }

    /**
     * Test of equals method, of class Cidadao.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        CodigoPostal cp = new CodigoPostal(4480, 667);
        Cidadao c1 = new Cidadao("José", 12345, "email@email.pt", cp, 1234);
        Cidadao c2 = new Cidadao("Ana", 4321, "email2@email.pt", cp, 4321);
        Cidadao c3 = new Cidadao("Ana", 4321, "email2@email.pt", cp, 4321);
        //Testar que dois cidadaos são diferentes
        boolean expResult = false;
        boolean result = c1.equals(c2);
        assertEquals(expResult, result);

        //Testar que dois cidadaos são iguais
        expResult = true;
        result = c2.equals(c3);
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class Cidadao.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        CodigoPostal cp = new CodigoPostal(4480, 667);
        int nif1 = 1234;
        int nif2 = 4321;
        Cidadao c1 = new Cidadao("José", nif1, "email@email.pt", cp, 1234);
        Cidadao c2 = new Cidadao("Ana", nif2, "email2@email.pt", cp, 4567);
        Cidadao c3 = new Cidadao("João", nif2, "email3@email.pt", cp, 6789);

        int expResult = nif1 - nif2;
        int result = c1.compareTo(c2);
        assertEquals(expResult, result);

        expResult = nif2 - nif2;
        result = c2.compareTo(c3);
        assertEquals(expResult, result);
    }

}
