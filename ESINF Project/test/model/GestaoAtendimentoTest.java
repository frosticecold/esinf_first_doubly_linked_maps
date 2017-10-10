/*
 */
package model;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pc asus
 */
public class GestaoAtendimentoTest {

    Reparticao r;

    public GestaoAtendimentoTest() {
        r = new Reparticao("Porto", 1234, "4000");
    }

    /**
     * Test of adicionarReparticao method, of class GestaoAtendimento.
     */
    @Test
    public void testAdicionarReparticao() {
        System.out.println("adicionarReparticao");
        GestaoAtendimento instance = new GestaoAtendimento();
        boolean expResult = true;
        boolean result = instance.adicionarReparticao(r);
        assertEquals(expResult, result);
        expResult = false;
        assertEquals(expResult, result);
    }

    /**
     * Test of removerReparticao method, of class GestaoAtendimento.
     */
    @Test
    public void testRemoverReparticao() {
        System.out.println("removerReparticao");
        Reparticao r1 = r;
        GestaoAtendimento instance = new GestaoAtendimento();
        instance.adicionarReparticao(r1);
        boolean expResult = true;
        boolean result = instance.removerReparticao(r1);
        assertEquals(expResult, result);
    }

    /**
     * Test of quaisCidadaosAfectos method, of class GestaoAtendimento.
     */
    @Test
    public void testQuaisCidadaosAfectos() {
        System.out.println("quaisCidadaosAfectos");
        GestaoAtendimento instance = new GestaoAtendimento();
        List<CidadaoAfecto> expResult = null;
        List<CidadaoAfecto> result = instance.quaisCidadaosAfectos();
        assertEquals(expResult, result);
    }

    /**
     * Test of adicionarCidadao method, of class GestaoAtendimento.
     */
    @Test
    public void testAdicionarCidadao() {
        System.out.println("adicionarCidadao");
        Cidadao c = null;
        GestaoAtendimento instance = new GestaoAtendimento();
        boolean expResult = false;
        boolean result = instance.adicionarCidadao(c);
        assertEquals(expResult, result);
    }

    /**
     * Test of passarCidadaosDeUmaReparticaoParaOutra method, of class
     * GestaoAtendimento.
     */
    @Test
    public void testPassarCidadaosDeUmaReparticaoParaOutra() {
        System.out.println("passarCidadaosDeUmaReparticaoParaOutra");
        Reparticao r = null;
        GestaoAtendimento instance = new GestaoAtendimento();
        instance.passarCidadaosDeUmaReparticaoParaOutra(r);
    }

    /**
     * Test of obterReparticaoAssociadaNif method, of class GestaoAtendimento.
     */
    @Test
    public void testObterReparticaoAssociadaNif() {
        System.out.println("obterReparticaoAssociadaNif");
        Long nif = null;
        GestaoAtendimento instance = new GestaoAtendimento();
        Reparticao expResult = null;
        Reparticao result = instance.obterReparticaoAssociadaNif(nif);
        assertEquals(expResult, result);
    }

}
