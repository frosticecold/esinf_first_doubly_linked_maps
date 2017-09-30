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
public class CodigoPostalTest {

    public CodigoPostalTest() {
    }

    /**
     * Test of getPrefixo method, of class CodigoPostal.
     */
    @Test
    public void testGetPrefixo() {
        System.out.println("getPrefixo");
        final int prefixo = 4480;
        CodigoPostal cp = new CodigoPostal(prefixo);
        int expResult = 4480;
        int result = cp.getPrefixo();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSufixo method, of class CodigoPostal.
     */
    @Test
    public void testGetSufixo() {
        System.out.println("getSufixo");
        final int prefixo = 4480;
        final int sufixo = 667;
        CodigoPostal instance = new CodigoPostal(prefixo, sufixo);
        int expResult = 667;
        int result = instance.getSufixo();
        assertEquals(expResult, result);
    }


    /**
     * Test of equals method, of class CodigoPostal.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        CodigoPostal p1 = new CodigoPostal(1234,456);
        CodigoPostal p2 = new CodigoPostal(1234,456);
        CodigoPostal p3 = new CodigoPostal(4321,654);
        Object obj = p2;
        Object obj2 = p3;
        assertFalse("Os códigos postais não são iguais",obj.equals(obj2));
        
        
        boolean expResult = true;
        boolean result = p1.equals(p2);
        assertEquals(expResult, result);
    }

}
