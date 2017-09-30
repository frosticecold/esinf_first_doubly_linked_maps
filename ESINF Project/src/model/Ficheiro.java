/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import estruturas.DoublyLinkedList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class Ficheiro {

    public static final String FX_REPARTICAO = "fx_repartições.txt";
    public static final String FX_CIDADAO = "fx_cidadaos.txt";
    public static final String FX_SENHAS = "fx_senhas.txt";
    public static final String SEPARADOR = ",";

    public static void lerFicheiroReparticao(String nomeFicheiro, DoublyLinkedList<Reparticao> listaReparticao) throws IOException {
        final int CAMPO_CIDADE = 0, CAMPO_NR_REPARTICAO = 1, CAMPO_COD_POSTAL = 2;

        List<String> lista = new ArrayList<>();
        Scanner scn = null;
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
    }
}
