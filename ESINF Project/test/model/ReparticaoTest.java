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
public class ReparticaoTest {

    private final Reparticao r1;
    private static final String CID_OMISSAO = "Porto";
    private static final int NUM_REPARTICAO = 1234;
    private static final String COD_POSTAL = "4000";

    public ReparticaoTest() {
        r1 = new Reparticao(CID_OMISSAO, NUM_REPARTICAO, COD_POSTAL);
    }

    /**
     * Test of getCidade method, of class Reparticao.
     */
    @Test
    public void testGetCidade() {
        System.out.println("getCidade");
        Reparticao instance = r1;
        String expResult = CID_OMISSAO;
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
        int expResult = NUM_REPARTICAO;
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
        String expResult = COD_POSTAL;
        String result = instance.getCodigoPostal();
        assertEquals(expResult, result);
    }

    /**
     * Test of adicionarServiço method, of class Reparticao.
     */
    @Test
    public void testAdicionarServiço() {
        System.out.println("adicionarServiço");

        Reparticao instance = new Reparticao(CID_OMISSAO, NUM_REPARTICAO, COD_POSTAL);
        boolean expResult = true;
        boolean result = instance.adicionarServiço('A');
        assertEquals(expResult, result);

        expResult = false;
        result = instance.adicionarServiço('A');
        assertEquals(expResult, result);
    }

    /**
     * Test of adicionarSenha method, of class Reparticao.
     */
    @Test
    public void testAdicionarSenha() {
        System.out.println("adicionarSenha");
        Senha senha = new Senha(123456789, 'A', 0);
        Reparticao instance = new Reparticao(CID_OMISSAO, NUM_REPARTICAO, COD_POSTAL);
        instance.adicionarServiço('A');
        boolean expResult = true;
        boolean result = instance.adicionarSenha(senha);
        assertEquals(expResult, result);
    }

    /**
     * Test of adicionarSenha method, of class Reparticao.
     */
    @Test
    public void testAdicionarSenhaFail() {
        System.out.println("adicionarSenhaFail");
        Senha senha = new Senha(123456789, 'A', 0);
        Reparticao instance = new Reparticao(CID_OMISSAO, NUM_REPARTICAO, COD_POSTAL);
        boolean expResult = false;
        boolean result = instance.adicionarSenha(senha);
        assertEquals(expResult, result);
    }

    /**
     * Test of tirarSenha method, of class Reparticao.
     */
    @Test
    public void testTirarSenha() {
        System.out.println("tirarSenha");
        long nif = 123456789;
        char cod_servico = 'A';
        final int NUM_SENHA = 2;
        Reparticao instance = new Reparticao(CID_OMISSAO, NUM_REPARTICAO, COD_POSTAL);
        instance.adicionarServiço(cod_servico);

        Senha senha = new Senha(nif, cod_servico, NUM_REPARTICAO);
        instance.adicionarSenha(senha);

        int expResult = NUM_SENHA;
        int result = instance.tirarSenha(nif, cod_servico).getNumOrdem();
        assertEquals(expResult, result);
    }

    /**
     * Test of abandonarFilas method, of class Reparticao.
     */
    @Test
    public void testAbandonarFilas() {
        System.out.println("abandonarFilas");
        long nif = 123456789;
        Reparticao instance = new Reparticao(CID_OMISSAO, NUM_REPARTICAO, COD_POSTAL);
        instance.adicionarServiço('A');
        instance.adicionarServiço('B');
        instance.tirarSenha(nif, 'A');
        instance.tirarSenha(nif, 'B');
        boolean expResult = true;
        boolean result = instance.abandonarFilas(nif);
        assertEquals(expResult, result);

        int nrSenhas = 0;
        int expres = nrSenhas;
        int res = instance.quantasSenhasPorServico('A');
        assertEquals(expres, res);

        expres = nrSenhas;
        res = instance.quantasSenhasPorServico('B');
        assertEquals(expres, res);
    }

    /**
     * Test of equals method, of class Reparticao.
     */
    @Test
    public void testEquals() {
        System.out.println("equalsTrue");
        Reparticao r = new Reparticao(CID_OMISSAO, NUM_REPARTICAO, COD_POSTAL);
        Reparticao r2 = new Reparticao("Porto", 1234, "4000");
        boolean expResult = true;
        boolean result = r2.equals(r);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Reparticao.
     */
    @Test
    public void testEqualsFail() {
        System.out.println("equalsFalse");
        Reparticao r = new Reparticao(CID_OMISSAO, NUM_REPARTICAO, COD_POSTAL);
        Reparticao r1 = new Reparticao("Lisboa", 3214, "1000");
        boolean expResult = false;
        boolean result = r1.equals(r);
        assertEquals(expResult, result);
    }

}
