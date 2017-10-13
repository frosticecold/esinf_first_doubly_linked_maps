/*
 */
package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pc asus
 */
public class GestaoAtendimentoTest {

    Reparticao rep;
    
    private static final String CID_OMISSAO = "Porto";
    private static final int NUM_REPARTICAO = 1234;
    private static final String COD_POSTAL = "4000";

    public GestaoAtendimentoTest() {
        rep = new Reparticao(CID_OMISSAO, NUM_REPARTICAO, COD_POSTAL);
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
        Cidadao c_1 = new Cidadao("Ana", 111222333, "ana@gmail.com", "4200-072", 1234);
        Cidadao c_2 = new Cidadao("Berta", 222333444, "berta@gmail.com", "4200-071", 1234);
        lc1.add(c_2);
        lc1.add(c_1);
        CidadaosAfetadosPorReparticao c1 = new CidadaosAfetadosPorReparticao("Porto", 1234, lc1);
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
        expResult.adicionarServico('A');
        expResult.adicionarServico('B');
        expResult.adicionarServico('C');
        expResult.adicionarServico('D');
        Reparticao result = instance.obterReparticaoAssociadaNif(nif);
        assertEquals(expResult, result);
    }

    @Test
    public void testLerFicheiroDados() {
        System.out.println("obterReparticaoAssociadaNif");
        GestaoAtendimento instance = new GestaoAtendimento();
        Reparticao rp1 = new Reparticao("Guarda", 1238, "4300");
        Cidadao c = new Cidadao("Carlos", 11223344L, "carlos@gmail.com", "4300-010", 1238);
        rp1.adicionarServico('A');
        rp1.adicionarServico('B');
        rp1.adicionarServico('C');
        instance.lerFicheirosDados();
        List<Cidadao> lista = instance.obterCidadaosAssociadosAReparticao(rp1);

        boolean expResult = true;
        boolean result = lista.contains(c);
        assertEquals(expResult, result);

    }

    @Test
    public void testDeterminarServicosComMaiorProcura() {
        System.out.println("determinarServicosComMaiorProcura");
        GestaoAtendimento instance = new GestaoAtendimento();
        instance.lerFicheirosDados();
        //Declarado em 11/10/2017 3 senhas de A no ficheiro ficheiro_senhas.txt
        int numSenhasA = 3;
        char tipoServico = 'A';
        Map<Character, Integer> determinarServicosComMaiorProcura = instance.determinarServicosComMaiorProcura();
        int result = determinarServicosComMaiorProcura.get(tipoServico);
        assertEquals(numSenhasA, result);
    }

    /**
     * Test of obterListaReparticoes method, of class GestaoAtendimento.
     */
    @Test
    public void testObterListaReparticoes() {
        System.out.println("obterListaReparticoes");
        GestaoAtendimento instance = new GestaoAtendimento();
        Reparticao r = new Reparticao(CID_OMISSAO, NUM_REPARTICAO, COD_POSTAL);
        instance.adicionarReparticao(r);
        Reparticao r2 = new Reparticao("Lisboa", 1235, "1000");
        instance.adicionarReparticao(r2);
        List<Reparticao> expResult = new ArrayList<>();
        expResult.add(r);
        expResult.add(r2);
        List<Reparticao> result = instance.obterListaReparticoes();
        assertEquals(expResult, result);
    }

    /**
     * Test of obterCidadaosAssociadosAReparticao method, of class GestaoAtendimento.
     */
    @Test
    public void testObterCidadaosAssociadosAReparticao() {
        System.out.println("obterCidadaosAssociadosAReparticao");
        Reparticao r = new Reparticao(CID_OMISSAO, NUM_REPARTICAO, COD_POSTAL);
        GestaoAtendimento instance = new GestaoAtendimento();
        instance.adicionarReparticao(r);
        Cidadao c = new Cidadao("José", 123456789, "emaila@email.pt", "4000-367", 1234);
        Cidadao c1 = new Cidadao("Maria", 247018481, "emailb@email.pt", "4082-090", 1234);
        Cidadao c2 = new Cidadao("Alberto", 345128679, "emailc@email.pt", "4021-237", 1234);
        instance.adicionarCidadao(c);
        instance.adicionarCidadao(c1);
        instance.adicionarCidadao(c2);
        List<Cidadao> expResult = new ArrayList<>();
        expResult.add(c2);
        expResult.add(c1);
        expResult.add(c);
        List<Cidadao> result = instance.obterCidadaosAssociadosAReparticao(r);
        assertEquals(expResult, result);
    }

    /**
     * Test of obterReparticaoPorCodigoPostal method, of class GestaoAtendimento.
     */
    @Test
    public void testObterReparticaoPorCodigoPostal() {
        System.out.println("obterReparticaoPorCodigoPostal");
        String codPostal = COD_POSTAL;
        GestaoAtendimento instance = new GestaoAtendimento();
        Reparticao r = new Reparticao(CID_OMISSAO, NUM_REPARTICAO, COD_POSTAL);
        instance.adicionarReparticao(r);
        Reparticao expResult = r;
        Reparticao result = instance.obterReparticaoPorCodigoPostal(codPostal);
        assertEquals(expResult, result);
    }

    /**
     * Test of obterCidadaosPorCodigoPostal method, of class GestaoAtendimento.
     */
    @Test
    public void testObterCidadaosPorCodigoPostal() {
        System.out.println("obterCidadaosPorCodigoPostal");
        Reparticao r = new Reparticao(CID_OMISSAO, NUM_REPARTICAO, COD_POSTAL);
        GestaoAtendimento instance = new GestaoAtendimento();
        instance.adicionarReparticao(r);
        Cidadao c = new Cidadao("José", 123456789, "emaila@email.pt", "4000-367", 1234);
        Cidadao c1 = new Cidadao("Maria", 247018481, "emailb@email.pt", "4082-090", 1234);
        Cidadao c2 = new Cidadao("Alberto", 345128679, "emailc@email.pt", "4021-237", 1234);
        instance.adicionarCidadao(c);
        instance.adicionarCidadao(c1);
        instance.adicionarCidadao(c2);
        List<Cidadao> expResult = new ArrayList<>();
        expResult.add(c);
        List<Cidadao> result = instance.obterCidadaosPorCodigoPostal(r);
        assertEquals(expResult, result);
    }
    @Test
    public void testconhecerUtilizacaoReparticao(){
        System.out.println("conhecerUtilizacaoReparticao");
        int hora = 10;
        int min = 30;
        GestaoAtendimento instance = new GestaoAtendimento();
        instance.lerFicheirosDados();
        //Vamos obter a Reparticao do Porto
        Reparticao r = instance.obterReparticaoPorCodigoPostal("4200");
        Map<Integer, List<Senha>> conhecerUtilizacaoReparticao = instance.conhecerUtilizacaoReparticao(rep, hora, min);
    
    }
   
}
