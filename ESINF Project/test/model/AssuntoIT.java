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
public class AssuntoIT {

    public AssuntoIT() {
    }

    /**
     * Test of getTipoAssunto method, of class Assunto.
     */
    @Test
    public void testGetTipoAssunto() {
        System.out.println("getTipoAssunto");
        Assunto a1 = Assunto.ALTERACOES; // 'C'
        Assunto a2 = Assunto.INICIO_ATIVIDADE; // 'B'
        Assunto a3 = Assunto.IRS_IRC; // 'A'
        
        
        assertTrue("Assunto.IRS_IRC = 'C'", a3.getTipoAssunto() == 'A');
        assertTrue("Assunto.INICIO_ATIVIDADE = 'B'", a2.getTipoAssunto() == 'B');
        assertTrue("Assunto.ALTERAÇOES = 'A'", a1.getTipoAssunto() == 'C');

        assertFalse("Assunto.ALTERAÇOES != Assunto.INICIO_ATIVIDADE", a1.equals(a2));

    }

}
