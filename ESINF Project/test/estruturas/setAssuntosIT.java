/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estruturas;

import java.util.HashSet;
import java.util.Set;
import model.Assunto;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class setAssuntosIT {

    public setAssuntosIT() {
    }

    /**
     * Test of adicionarAssunto method, of class setAssuntos.
     */
    @Test
    public void testAdicionarAssunto() {
        System.out.println("adicionarAssunto");
        Assunto a = Assunto.ALTERACOES;
        Assunto b = Assunto.IRS_IRC;
        Assunto c = Assunto.TESOURARIA;
        Set<Assunto> instance = new HashSet<>();

        assertTrue("Contêm assunto A", instance.add(a));
        assertTrue("Contêm assunto B", instance.add(b));
        assertFalse("Adicionar de novo o Assunto A", instance.add(a));

    }

    /**
     * Test of removerAssunto method, of class setAssuntos.
     */
    @Test
    public void testRemoverAssunto() {
        System.out.println("removerAssunto");
        Assunto a = Assunto.ALTERACOES;
        Assunto b = Assunto.IRS_IRC;
        Assunto c = Assunto.TESOURARIA;
        Set<Assunto> instance = new HashSet<>();

        instance.add(a);
        instance.add(b);

        assertTrue("Remover Assunto A", instance.remove(a));
        assertTrue("Remover Assunto B", instance.remove(b));
        assertFalse("Remover Assunto C", instance.remove(c));
    }

    /**
     * Test of size method, of class setAssuntos.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        Assunto a = Assunto.ALTERACOES;
        Assunto b = Assunto.IRS_IRC;
        Assunto c = Assunto.TESOURARIA;
        Set<Assunto> instance = new HashSet<>();

        instance.add(a);
        instance.add(b);
        instance.add(c);

        int expectedSize = 3;
        assertEquals(expectedSize, instance.size());

        //Remover assunto c
        expectedSize--;
        instance.remove(c);
        assertEquals(expectedSize, instance.size());

        //Remover assunto b
        expectedSize--;
        instance.remove(b);
        assertEquals(expectedSize, instance.size());
    }

    /**
     * Test of containsAssunto method, of class setAssuntos.
     */
    @Test
    public void testContainsAssunto() {
        System.out.println("containsAssunto");
        Assunto a = Assunto.ALTERACOES;
        Assunto b = Assunto.IRS_IRC;
        Assunto c = Assunto.TESOURARIA;
        Set<Assunto> instance = new HashSet<>();

        instance.add(a);
        instance.add(b);

        assertTrue("Contêm assunto A", instance.contains(a));
        assertTrue("Contêm assunto B", instance.contains(b));
        assertFalse("Não contêm assunto C", instance.contains(c));
    }

}
