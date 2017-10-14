/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
public class ProcuraPorServicoIT {

    public ProcuraPorServicoIT() {
    }

    /**
     * Test of compareTo method, of class ProcuraPorServico.
     */
    @Test
    public void testProcuraServico() {
        System.out.println("procuraServico");
        ProcuraPorServico o = new ProcuraPorServico('A', 10);
        int expResult = 10;
        int result = o.getQtdSenhas();
        assertEquals(expResult, result);

        assertEquals('A', o.getServico());
    }

}
