/*
 */
package model;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pc asus
 */
public class CidadaoAfectoTest {

    Set<Cidadao> listaCidadao = new HashSet<>();

    public CidadaoAfectoTest() {
        Cidadao c1 = new Cidadao("Jose", 123456789, "emaila@email.pt", "4002-291", 1234);
        Cidadao c2 = new Cidadao("Maria", 123465789, "emailb@email.pt", "1008-395", 1234);
        listaCidadao.add(c1);
        listaCidadao.add(c2);
    }

    /**
     * Test of getNumReparticao method, of class CidadaoAfecto.
     */
    @Test
    public void testGetNumReparticao() {
        final int numReparticao = 1234;
        System.out.println("getNumReparticao");
        CidadaosAfetosPorReparticao instance = new CidadaosAfetosPorReparticao("Porto", numReparticao, listaCidadao);
        int expResult = numReparticao;
        int result = instance.getNumReparticao();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCidade method, of class CidadaoAfecto.
     */
    @Test
    public void testGetCidade() {
        System.out.println("getCidade");
        CidadaosAfetosPorReparticao instance = new CidadaosAfetosPorReparticao("Porto", 1234, listaCidadao);
        String expResult = "Porto";
        String result = instance.getCidade();
        assertEquals(expResult, result);
    }

    /**
     * Test of getListaNif method, of class CidadaoAfecto.
     */
    @Test
    public void testGetListaNif() {
        System.out.println("getListaNif");
        CidadaosAfetosPorReparticao instance = new CidadaosAfetosPorReparticao("Porto", 1234, listaCidadao);
        Set<Long> listaNif = new HashSet<>();
        long l1 = 123456789;
        long l2 = 123465789;
        listaNif.add(l1);
        listaNif.add(l2);
        
        Set<Long> expResult = listaNif;
        Set<Long> result = instance.getListaNif();
        assertEquals(expResult, result);
    }

}
