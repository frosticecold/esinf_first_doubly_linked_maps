/*
 */
package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pc asus
 */
public class GestaoAtendimentoTest {

    Reparticao rep;

    public GestaoAtendimentoTest() {
        rep = new Reparticao("Porto", 1234, "4000");
    }

    /**
     * Test of adicionarReparticao method, of class GestaoAtendimento.
     */
    @Test
    public void testAdicionarReparticao() {
        System.out.println("adicionarReparticao");
        GestaoAtendimento instance = new GestaoAtendimento();
        boolean expResult = true;
        boolean result = instance.adicionarReparticao(rep);
        assertEquals(expResult, result);
        //Vamos tentar adicionar repetido
        expResult = false;
        result = instance.adicionarReparticao(rep);
        assertEquals(expResult, result);
    }

    /**
     * Test of removerReparticao method, of class GestaoAtendimento.
     */
    @Test
    public void testRemoverReparticao() {
        System.out.println("removerReparticao");
        Reparticao r1 = rep;
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
        instance.lerFicheirosDados();
        Set<Cidadao> lc1 = new HashSet<>();
        Cidadao c_1 = new Cidadao("Ana",111222333,"ana@gmail.com","4200-072",1234);
        Cidadao c_2 = new Cidadao("Berta",223344,"berta@gmail.com","4200-071",1234);
        lc1.add(c_2);
        lc1.add(c_1);
        CidadaoAfecto c1 = new CidadaoAfecto("Porto", 1234, lc1);
        String expResult = c1.toString();
        String result = instance.quaisCidadaosAfectos().get(0).toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of adicionarCidadao method, of class GestaoAtendimento.
     */
    @Test
    public void testAdicionarCidadao() {
        System.out.println("adicionarCidadao");
        Reparticao r = new Reparticao("VilaDoConde", 1234, "4480");
        Cidadao c = new Cidadao("João", 123456789, "email@email.pt", "4480-667", 1234);
        GestaoAtendimento instance = new GestaoAtendimento();
        instance.adicionarReparticao(r);
        boolean expResult = true;
        boolean result = instance.adicionarCidadao(c);
        assertEquals(expResult, result);

        expResult = false;
        result = instance.adicionarCidadao(c);
        assertEquals(expResult, result);
    }

    /**
     * Test of obterReparticaoAssociadaNif method, of class GestaoAtendimento.
     */
    @Test
    public void testObterReparticaoAssociadaNif() {
        System.out.println("obterReparticaoAssociadaNif");
        Long nif = 111222333L;
        GestaoAtendimento instance = new GestaoAtendimento();
        instance.lerFicheirosDados();
        String codPostalPorto = "4200";
        Reparticao expResult = instance.obterReparticaoPorCodigoPostal(codPostalPorto);
        expResult.adicionarServiço('A');
        expResult.adicionarServiço('B');
        expResult.adicionarServiço('C');
        expResult.adicionarServiço('D');
        Reparticao result = instance.obterReparticaoAssociadaNif(nif);
        assertEquals(expResult, result);
    }

    @Test
    public void testLerFicheiroDados() {
        System.out.println("obterReparticaoAssociadaNif");
        GestaoAtendimento instance = new GestaoAtendimento();
        Reparticao rp1 = new Reparticao("Guarda", 1238, "4300");
        Cidadao c = new Cidadao("Carlos", 11223344L, "carlos@gmail.com", "4300-010", 1238);
        rp1.adicionarServiço('A');
        rp1.adicionarServiço('B');
        rp1.adicionarServiço('C');
        instance.lerFicheirosDados();
        List<Cidadao> lista = instance.obterCidadaosAssociadosAReparticao(rp1);

        boolean expResult = true;
        boolean result = lista.contains(c);
        assertEquals(expResult, result);

    }
}
