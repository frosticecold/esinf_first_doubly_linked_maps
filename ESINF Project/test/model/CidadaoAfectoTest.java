/*
 */
package model;

import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pc asus
 */
public class CidadaoAfectoTest {
    
    public CidadaoAfectoTest() {
    }

    /**
     * Test of getNumReparticao method, of class CidadaoAfecto.
     */
    @Test
    public void testGetNumReparticao() {
        System.out.println("getNumReparticao");
        CidadaoAfecto instance = null;
        int expResult = 0;
        int result = instance.getNumReparticao();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCidade method, of class CidadaoAfecto.
     */
    @Test
    public void testGetCidade() {
        System.out.println("getCidade");
        CidadaoAfecto instance = null;
        String expResult = "";
        String result = instance.getCidade();
        assertEquals(expResult, result);
    }

    /**
     * Test of getListaNif method, of class CidadaoAfecto.
     */
    @Test
    public void testGetListaNif() {
        System.out.println("getListaNif");
        CidadaoAfecto instance = null;
        Set<Long> expResult = null;
        Set<Long> result = instance.getListaNif();
        assertEquals(expResult, result);
    }
    
}
