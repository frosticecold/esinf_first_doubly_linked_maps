/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
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
        Reparticao r2 = new Reparticao("Lisboa", 3214, "1000");
        boolean expResult = false;
        boolean result = r2.equals(r);
        assertEquals(expResult, result);
    }

    /**
     * Test of quantasSenhasPorServico method, of class Reparticao.
     */
    @Test
    public void testQuantasSenhasPorServico() {
        System.out.println("quantasSenhasPorServico");
        Reparticao instance = new Reparticao(CID_OMISSAO, NUM_REPARTICAO, COD_POSTAL);
        Senha s1 = new Senha(123456789, 'A', 1);
        Senha s2 = new Senha(123456719, 'C', 5);
        Senha s3 = new Senha(123455789, 'D', 8);
        Senha s4 = new Senha(123475789, 'A', 2);
        Senha s5 = new Senha(129392789, 'A', 3);
        instance.adicionarServiço('A');
        instance.adicionarServiço('B');
        instance.adicionarServiço('C');
        instance.adicionarServiço('D');
        
        instance.adicionarSenha(s1);
        instance.adicionarSenha(s2);
        instance.adicionarSenha(s3);
        instance.adicionarSenha(s4);
        instance.adicionarSenha(s5);
        int expResult = 3;
        int result = instance.quantasSenhasPorServico('A');
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Reparticao.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Reparticao instance = new Reparticao(CID_OMISSAO, NUM_REPARTICAO, COD_POSTAL);
        int hash = 5;
        hash = 97 * hash + instance.getNumReparticao();
        hash = 97 * hash + Objects.hashCode(instance.getCodigoPostal());
        int expResult = hash;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Reparticao.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Reparticao instance = new Reparticao(CID_OMISSAO, NUM_REPARTICAO, COD_POSTAL);
        String expResult = "Reparticao{cidade=Porto, numReparticao=1234, codigoPostal=4000, listaServicos=[]}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of determinarProcura method, of class Reparticao.
     */
    @Test
    public void testDeterminarProcura() {
        System.out.println("determinarProcura");
        Reparticao instance = new Reparticao(CID_OMISSAO, NUM_REPARTICAO, COD_POSTAL);
        Senha s1 = new Senha(123456789, 'A', 1);
        Senha s2 = new Senha(123456719, 'C', 5);
        Senha s3 = new Senha(123455789, 'D', 8);
        Senha s4 = new Senha(123475789, 'A', 2);
        Senha s5 = new Senha(129392789, 'A', 3);
        instance.adicionarServiço('A');
        instance.adicionarServiço('B');
        instance.adicionarServiço('C');
        instance.adicionarServiço('D');
        
        instance.adicionarSenha(s1);
        instance.adicionarSenha(s2);
        instance.adicionarSenha(s3);
        instance.adicionarSenha(s4);
        instance.adicionarSenha(s5);
        
        Map<Character, Integer> expResult = new HashMap<>();
        expResult.put('A', 3);
        expResult.put('B', 0);
        expResult.put('C', 1);
        expResult.put('D', 1);
        Map<Character, Integer> result = instance.determinarProcura();
        assertEquals(expResult, result);
    }

}
