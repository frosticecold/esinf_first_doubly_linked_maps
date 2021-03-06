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
        Cidadao a = new Cidadao("Ana", 111222333L, "ana@gmail.com", "4200-072", 1234);
        Cidadao b = new Cidadao("Berta", 222333444L, "berta@gmail.com", "4200-071", 1234);
        Cidadao c = new Cidadao("Beatriz", 141478792, "beatriz@gmail.com", "4200-048", 1234);
        Cidadao d = new Cidadao("Martim", 176568493, "martim@gmail.com", "4200-932", 1234);
        Cidadao e = new Cidadao("Paulo", 197784479, "paulo@gmail.com", "4200-829", 1234);
        lc1.add(a);
        lc1.add(b);
        lc1.add(c);
        lc1.add(d);
        lc1.add(e);
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
        
        instance = new GestaoAtendimento();
        instance.lerFicheirosDados();
        c = new Cidadao("Test",1231234321,"email@email.email","4480",1234);
        instance.adicionarCidadao(c);
        
        int expectedNumRep = 1236;
        int resultRep = c.getNumReparticao();
        assertEquals(expectedNumRep, resultRep);
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
        Reparticao rp1 = new Reparticao("Guarda", 1236, "4300");
        Cidadao c = new Cidadao("Carlos", 11223344L, "carlos@gmail.com", "4300-010", 1236);
        rp1.adicionarServico('A');
        rp1.adicionarServico('B');
        rp1.adicionarServico('C');
        instance.lerFicheirosDados();
//        instance.adicionarReparticao(rp1);
//        instance.adicionarCidadao(c);
        List<Cidadao> lista = instance.obterCidadaosAssociadosAReparticao(rp1);
        boolean tr = instance.adicionarCidadao(c);
        boolean expResult = true;
        boolean result = lista.contains(c);
        assertEquals(expResult, result);

    }

    @Test
    public void testDeterminarServicosComMaiorProcura() {
        System.out.println("determinarServicosComMaiorProcura");
        GestaoAtendimento instance = new GestaoAtendimento();
        instance.lerFicheirosDados();
        //Declarado em 13/10/2017 10 senhas de A no ficheiro ficheiro_senhas.txt
        int numSenhasA = 10;
        char tipoServico = 'A';
        List<ProcuraPorServico> listaProcura = instance.determinarServicosComMaiorProcura();
        int result = listaProcura.get(0).getQtdSenhas();
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
     * Test of obterCidadaosAssociadosAReparticao method, of class
     * GestaoAtendimento.
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
     * Test of obterReparticaoPorCodigoPostal method, of class
     * GestaoAtendimento.
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
        List<Cidadao> result = instance.obterCidadaosPorCodigoPostal(r.getCodigoPostal());
        assertEquals(expResult, result);
    }

    @Test
    public void testconhecerUtilizacaoReparticao() {
        System.out.println("conhecerUtilizacaoReparticao");
        int hora = 10;
        int min = 30;
        GestaoAtendimento instance = new GestaoAtendimento();

        Reparticao r = new Reparticao("Porto", 1234, "4200");
        r.adicionarServico('A');
        r.adicionarServico('B');
        r.adicionarServico('C');
        r.adicionarServico('D');

        Senha a = new Senha(111222333, 'A', 1);
        Senha b = new Senha(111222333, 'B', 1);
        Senha c = new Senha(111222333, 'C', 1);
        Senha d = new Senha(222333444, 'A', 2);
        Senha e = new Senha(222333444, 'B', 2);
        Senha f = new Senha(117130626, 'B', 3);
        Senha g = new Senha(123226788, 'D', 1);

        r.adicionarSenha(a);
        r.adicionarSenha(b);
        r.adicionarSenha(c);
        r.adicionarSenha(d);
        r.adicionarSenha(e);
        r.adicionarSenha(f);
        r.adicionarSenha(g);

        Map<Integer, List<Senha>> conhecerUtilizacaoReparticao = instance.conhecerUtilizacaoReparticao(r, hora, min);

        // ((10 - 9)*60 + (30 - 0))/10 = 9 filas de senhas (90 minutos)
        List<Senha> lista1 = conhecerUtilizacaoReparticao.get(0);
        //Testar se a senha Senha(111222333, 'A', 1); aparece apenas uma vez na fila e as outras da mesma pessoa não
        assertTrue("1) Lista1 contém Senha 111222333 A 1", lista1.contains(a));
        assertFalse("1) Lista1 não contém Senha 111222333 B 1", lista1.contains(b));
        assertFalse("1) Lista1 não contém Senha 111222333 C 1", lista1.contains(c));

        //Testar se Contêm outras senhas
        assertFalse("1) Lista1 não contém Senha(222333444, 'A', 2)", lista1.contains(d));
        assertTrue("1) Lista1 contêm Senha 123226788, 'D', 1", lista1.contains(g));

        List<Senha> lista2 = conhecerUtilizacaoReparticao.get(1);
        assertTrue("2) Lista2 contém Senha(222333444, 'A', 2);", lista2.contains(d));
        assertFalse("2) Lista2 não contém Senha(222333444, 'B', 2);", lista2.contains(e));
        assertFalse("2) Lista2 contém Senha Senha(117130626, 'B', 3);", lista2.contains(f));

        //Ultima senha
        List<Senha> lista3 = conhecerUtilizacaoReparticao.get(2);
        assertTrue("3) Lista3 contém a Senha(117130626, 'B', 3)", lista3.contains(f));

    }

}
