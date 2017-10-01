/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import estruturas.DoublyLinkedList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class Ficheiro {

    public static final String FX_REPARTICAO = "fx_repartições.txt";
    public static final String FX_CIDADAO = "fx_cidadaos.txt";
    public static final String FX_SENHAS = "fx_senhas.txt";
    public static final String SEPARADOR = ",";

    public static void lerFicheiroReparticao(String nomeFicheiro, DoublyLinkedList<Reparticao> listaReparticao) throws FileNotFoundException {

        final int CAMPO_CIDADE = 0, CAMPO_NR_REPARTICAO = 1, CAMPO_COD_POSTAL = 2;
        List<String> lista = new ArrayList<>();
        Scanner scn = null;
        try {
            File file = new File(nomeFicheiro);
            scn = new Scanner(file);
            while (scn.hasNext()) {
                lista.add(scn.next());
            }
            if (scn != null) {
                scn.close();
            }
            if (!lista.isEmpty()) {
                String linhaSplit[] = null;
                for (String s : lista) {
                    linhaSplit = s.split(SEPARADOR);
                    String cidade = linhaSplit[CAMPO_CIDADE];
                    int num_reparticao = Integer.parseInt(linhaSplit[CAMPO_NR_REPARTICAO]);
                    CodigoPostal cp = new CodigoPostal(Integer.parseInt(linhaSplit[CAMPO_COD_POSTAL]));
                    Set<Assunto> setAssunto = new HashSet<>();
                    for (int i = CAMPO_COD_POSTAL + 1; i < linhaSplit.length; i++) {
                        if (Assunto.isAssunto(linhaSplit[i])) {
                            setAssunto.add(Assunto.converterStringParaAssunto(linhaSplit[i]));
                        }
                    }
                    Reparticao r = new Reparticao(cidade, num_reparticao, cp, setAssunto);
                    listaReparticao.addLast(r);
                }
            }
        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException("Não foi possível ler o ficheiro: " + nomeFicheiro);
        } finally {
            if (scn != null) {
                scn.close();
            }
        }
    }

    public static void lerFicheiroCidadaos(String nomeFicheiro, Set<Cidadao> setCidadao) throws FileNotFoundException {

        final int CAMPO_NOME = 0, CAMPO_NIF = 1,
                CAMPO_EMAIL = 2, CAMPO_CODPOSTAL = 3,
                CAMPO_REPARTICAO = 4;
        List<String> lista = new ArrayList<>();
        Scanner scn = null;
        try {
            File file = new File(nomeFicheiro);
            scn = new Scanner(file);
            while (scn.hasNext()) {
                lista.add(scn.next());
            }
            if (!lista.isEmpty()) {
                String linhaSplit[] = null;
                for (String s : lista) {
                    linhaSplit = s.split(SEPARADOR);
                    String nome = linhaSplit[CAMPO_NOME];
                    int nif = Integer.parseInt(linhaSplit[CAMPO_NIF]);
                    String email = linhaSplit[CAMPO_EMAIL];
                    CodigoPostal cdp = new CodigoPostal(linhaSplit[CAMPO_CODPOSTAL]);
                    int nr_rep = Integer.parseInt(linhaSplit[CAMPO_REPARTICAO]);
                    Cidadao c = new Cidadao(nome, nif, email, cdp, nr_rep);
                    setCidadao.add(c);
                }
            }

        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException("Não foi possível ler o ficheiro: " + nomeFicheiro);
        } finally {
            if (scn != null) {
                scn.close();
            }
        }
    }

    public static void lerSenhas(String nomeFicheiro) throws FileNotFoundException {
        final int CAMPO_NIF = 0, CAMPO_COD_ASSUNTO = 1, CAMPO_ORDEM = 2;
        List<String> lista = new ArrayList<>();
        Scanner scn = null;
        try {
            File file = new File(nomeFicheiro);
            scn = new Scanner(file);
            while (scn.hasNext()) {
                lista.add(scn.next());
            }
            if (!lista.isEmpty()) {
                String linhaSplit[] = null;
                for (String s : lista) {
                    linhaSplit = s.split(SEPARADOR);
                    int nif = Integer.parseInt(linhaSplit[CAMPO_NIF]);
                    Assunto assunto = Assunto.converterStringParaAssunto(linhaSplit[CAMPO_COD_ASSUNTO]);
                    int numero_ordem = Integer.parseInt(linhaSplit[CAMPO_ORDEM]);
                }
            }

        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException("Não foi possível ler o ficheiro: " + nomeFicheiro);
        } finally {
            if (scn != null) {
                scn.close();
            }

        }
    }
}
