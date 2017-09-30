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
 * @author Raúl Correia
 */
public class SenhaIT {

    public SenhaIT() {
    }

    /**
     * Test of getNumSenha method, of class Senha.
     */
    @Test
    public void testGetNumSenha() {
        System.out.println("getNumSenha");
        Senha s = new Senha(1, 'A');
        int expResult = 1;
        int result = s.getNumSenha();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCodigoSenha method, of class Senha.
     */
    @Test
    public void testGetCodigoSenha() {
        System.out.println("getCodigoSenha");
        Senha instance = new Senha(1, 'A');
        char expResult = 'A';
        char result = instance.getCodigoSenha();
        assertEquals(expResult, result);
    }

}
