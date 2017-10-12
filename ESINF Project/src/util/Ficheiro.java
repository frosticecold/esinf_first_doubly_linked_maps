/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Cidadao;
import model.GestaoAtendimento;
import model.Reparticao;
import model.Senha;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class Ficheiro {

    private String DELIMITADOR = ",";

    public Ficheiro() {
    }

    /**
     * Método que lê um ficheiro de texto génerico e retorna uma lista com o
     * conteúdo
     *
     * @param nomeFicheiro String com o nome do ficheiro
     * @return List<String> com conteúdo do ficheiro
     */
    public List<String> lerFicheiro(String nomeFicheiro) {                      
        Scanner scn = null;                                                     //O(1)
        List<String> lista = new ArrayList<>();                                 //O(1)
        try {
            scn = new Scanner(new FileReader(nomeFicheiro));                    //O(1)
            while (scn.hasNext()) {                                             //O(n)
                lista.add(scn.next());                                          //O(1)
            }
        } catch (FileNotFoundException ex) {
            System.out.printf("Foi impossível ler o ficheiro %s\n", nomeFicheiro);//O(1)
        } finally {
            if (scn != null) {                                                  //O(1)
                scn.close();                                                    //O(1)
            }
        }
        return lista;                                                           //O(1)
    }                                                                           //Total O(n)

    /**
     * Método que lê todas as repartições para a Gestão de Atendimento E
     * adiciona os devidos serviços às repartições
     *
     * @param ga GestaoAtendimento
     * @param nomeFicheiro nome do ficheiro
     */
    public void lerReparticoes(GestaoAtendimento ga, String nomeFicheiro) {
        Reparticao r = null;                                                    //O(1)
        final int C_CIDADE = 0, C_NUMREP = 1, C_CODPOSTAL = 2;                  //O(1)
        List<String> lista = lerFicheiro(nomeFicheiro);                         //O(n)
        for (int i = 0; i < lista.size(); i++) {                                //O(n) * O(n) * O(n) = O(n^3)
            String ls[] = lista.get(i).split(DELIMITADOR);                      //O(n)
            r = new Reparticao(ls[C_CIDADE], Integer.parseInt(ls[C_NUMREP]), ls[C_CODPOSTAL]);//O(1)
            if (ls.length > C_CODPOSTAL + 1) {                                  //O(1)
                for (int j = C_CODPOSTAL + 1; j < ls.length; j++) {             //O(n)
                    r.adicionarServico(ls[j].charAt(0));                        //O(1)
                }
            }
            ga.adicionarReparticao(r);                                          //O(1)
        }
    }                                                                           //Total O(n^3)

    /**
     * Método que lê todos os cidadãos de um ficheiro de texto
     *
     * @param ga GestaoAtendimento
     * @param nomeFicheiro Nome do Ficheiro
     */
    public void lerCidadaos(GestaoAtendimento ga, String nomeFicheiro) {
        Cidadao c = null;
        final int C_NOME = 0, C_NIF = 1, C_EMAIL = 2, C_CODPOSTAL = 3, C_NUMREP = 4;
        List<String> lista = lerFicheiro(nomeFicheiro);
        for (String s : lista) {
            String ls[] = s.split(DELIMITADOR);
            c = new Cidadao(ls[C_NOME], Long.parseLong(ls[C_NIF]), ls[C_EMAIL], ls[C_CODPOSTAL], Integer.parseInt(ls[C_NUMREP]));
            ga.adicionarCidadao(c);

        }
    }

    /**
     * Método que lê todas as senhas de um ficheiro de texto e adiciona as
     * senhas à devida repartição
     *
     * @param ga GestaoAtendimento
     * @param nomeFicheiro nome de ficheiro
     */
    public void lerSenhas(GestaoAtendimento ga, String nomeFicheiro) {
        Senha senha = null;
        final int C_NIF = 0, C_COD_ASSUNTO = 1, C_ORDEM = 2;
        final char PRIMEIRO_CAR = 0;
        List<String> lista = lerFicheiro(nomeFicheiro);
        for (String str : lista) {
            String ls[] = str.split(DELIMITADOR);
            long nif = Long.parseLong(ls[C_NIF]);
            senha = new Senha(nif, ls[C_COD_ASSUNTO].charAt(PRIMEIRO_CAR), Integer.parseInt(ls[C_ORDEM]));
            Reparticao r = ga.obterReparticaoAssociadaNif(nif);
            if (r != null) {
                r.adicionarSenha(senha);
            }

        }
    }

}
