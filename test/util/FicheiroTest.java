/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import model.GestaoAtendimento;
import model.Reparticao;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class FicheiroTest {

    public FicheiroTest() {
    }

    /**
     * Test of lerFicheiro method, of class Ficheiro.
     */
    @Test
    public void testLerFicheiroTest() {
        System.out.println("lerFicheiro");
        String nomeFicheiro = "fx_teste.txt";

        //Inicializar lista Teste
        List<String> listaTeste = new ArrayList<>();
        String a = "a,b,c";
        String b = "a,1,2";
        String c = "b,2,3";
        String d = "c,3,4";
        listaTeste.add(a);
        listaTeste.add(b);
        listaTeste.add(c);
        listaTeste.add(d);
        //Fim inicializar lista Teste

        Ficheiro instance = new Ficheiro();
        List<String> expResult = listaTeste;
        List<String> result = instance.lerFicheiro(nomeFicheiro);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testLerFicheiroReparticao() {
        System.out.println("lerReparticoes");
        String nomeFicheiro = "fx_rep_test.txt";
        GestaoAtendimento ga = new GestaoAtendimento();
        Ficheiro instance = new Ficheiro();
        int expResult = 7;
        instance.lerReparticoes(ga, nomeFicheiro);
        int result = ga.obterListaReparticoes().size();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testLerFicheiroCidadaos() {
        System.out.println("lerCidadaos");
        String nomeFicheiro = "fx_cid_test.txt";
        GestaoAtendimento ga = new GestaoAtendimento();
        Reparticao r = new Reparticao("Porto", 1234, "4200");
        
        Ficheiro instance = new Ficheiro();
        instance.lerReparticoes(ga, "fx_rep_test.txt");

        Ficheiro instance1 = new Ficheiro();
        instance1.lerCidadaos(ga, nomeFicheiro);
        
        
        int expResult = 3;
        int result = ga.obterCidadaosAssociadosAReparticao(r).size();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testLerFicheiroSenhas() {
        System.out.println("lerSenhas");
        String nomeFicheiro = "fx_senhas_test.txt";
        GestaoAtendimento ga = new GestaoAtendimento();
        
        
        Ficheiro instance = new Ficheiro();
        instance.lerReparticoes(ga, "fx_rep_test.txt");
        
        Ficheiro instance1 = new Ficheiro();
        instance1.lerCidadaos(ga, "fx_cid_test.txt");
        
        Ficheiro instance2 = new Ficheiro();
        instance2.lerSenhas(ga, nomeFicheiro);
        
        char expResult = 'A';
        char result = ga.determinarServicosComMaiorProcura().get(0).getServico();
        assertEquals(expResult, result);
        
        int result1 = ga.determinarServicosComMaiorProcura().get(0).getQtdSenhas();
        int expResult1 = 4;
        assertEquals(expResult1, result1);
    }

    @Test
    public void TestLerFicheiroTest() throws FileNotFoundException {

        System.out.println("lerFicheiroInexistente");
        String nomeFicheiro = "fx_teste12.txt";

        Ficheiro instance = new Ficheiro();
        List result = instance.lerFicheiro(nomeFicheiro);
        List expResult = new ArrayList<>();

        assertEquals(expResult, result);
    }

}
